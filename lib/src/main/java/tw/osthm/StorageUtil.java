package tw.osthm;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Most of the code are copied from:
 * https://github.com/sromku/android-storage/blob/master/storage/src/main/java/com/snatik/storage/Storage.java
 *
 * Thanks to Roman Kushnarenko for making his code public and available for everyone.
 *
 * This storage util currently not supporting android 10+ Scoped Storage, i'll try to add them soon.
 */

/**
 * Common class for internal and external storage implementations
 *
 * @author Roman Kushnarenko - sromku (sromku@gmail.com)
 */
public class StorageUtil {
    private static final String TAG = "StorageUtil";

    public static boolean isExternalWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean createDirectory(String path) {
        File directory = new File(path);
        if (directory.exists()) {
            Log.w(TAG, "Directory '" + path + "' already exists");
            return false;
        }
        return directory.mkdirs();
    }

    public static boolean createDirectory(String path, boolean override) {

        // Check if directory exists. If yes, then delete all directory
        if (override && isDirectoryExists(path)) {
            deleteDirectory(path);
        }

        // Create new directory
        return createDirectory(path);
    }

    public static boolean deleteDirectory(String path) {
        return deleteDirectoryImpl(path);
    }

    public static boolean isDirectoryExists(String path) {
        return new File(path).exists();
    }

    public static boolean createFile(String path, String content) {
        return createFile(path, content.getBytes());
    }

    public static boolean createFile(String path, byte[] content) {
        try {
            OutputStream stream = new FileOutputStream(new File(path));

            stream.write(content);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            Log.e(TAG, "Failed create file", e);
            return false;
        }
        return true;
    }

    public static boolean createFile(String path, Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return createFile(path, byteArray);
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        return file.delete();
    }

    public static boolean isFileExist(String path) {
        return new File(path).exists();
    }

    // Copied from: https://www.journaldev.com/9400/android-external-storage-read-write-save-file
    public static String readFile(String path) throws IOException {
        StringBuilder output = new StringBuilder();
        FileInputStream fis = new FileInputStream(path);
        DataInputStream in = new DataInputStream(fis);
        BufferedReader br =
                new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            output.append(strLine);
        }
        in.close();
        return output.toString();
    }

    // Copied from https://github.com/sromku/android-storage/blob/master/storage/src/main/java/com/snatik/storage/Storage.java
    public static byte[] readFileBytes(String path) {
        final FileInputStream stream;
        try {
            stream = new FileInputStream(new File(path));
            return readFile(stream);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Failed to read file to input stream", e);
            return null;
        }
    }

    // A modified copy of https://github.com/sromku/android-storage/blob/master/storage/src/main/java/com/snatik/storage/Storage.java
    public static byte[] readFile(final FileInputStream stream) {
        class Reader extends Thread {
            byte[] array = null;
        }

        Reader reader = new Reader() {
            public void run() {
                LinkedList<Pair<byte[], Integer>> chunks = new LinkedList<Pair<byte[], Integer>>();

                // read the file and build chunks
                int size = 0;
                int globalSize = 0;
                do {
                    try {
                        int chunkSize = 8192;
                        // read chunk
                        byte[] buffer = new byte[chunkSize];
                        size = stream.read(buffer, 0, chunkSize);
                        if (size > 0) {
                            globalSize += size;

                            // add chunk to list
                            chunks.add(new Pair<byte[], Integer>(buffer, size));
                        }
                    } catch (Exception e) {
                        // very bad
                    }
                } while (size > 0);

                try {
                    stream.close();
                } catch (Exception e) {
                    // very bad
                }

                array = new byte[globalSize];

                // append all chunks to one array
                int offset = 0;
                for (Pair<byte[], Integer> chunk : chunks) {
                    // flush chunk to array
                    System.arraycopy(chunk.first, 0, array, offset, chunk.second);
                    offset += chunk.second;
                }
            }

            ;
        };

        reader.start();
        try {
            reader.join();
        } catch (InterruptedException e) {
            Log.e(TAG, "Failed on reading file from storage while the locking Thread", e);
            return null;
        }

        return reader.array;
    }

    public static void appendFile(String path, String content) {
        appendFile(path, content.getBytes());
    }

    public static void appendFile(String path, byte[] bytes) {
        if (!isFileExist(path)) {
            Log.w(TAG, "Impossible to append content, because such file doesn't exist");
            return;
        }

        try {
            FileOutputStream stream = new FileOutputStream(new File(path), true);
            stream.write(bytes);
            stream.write(System.getProperty("line.separator").getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            Log.e(TAG, "Failed to append content to file", e);
        }
    }

    public static List<File> getNestedFiles(String path) {
        File file = new File(path);
        List<File> out = new ArrayList<File>();
        getDirectoryFilesImpl(file, out);
        return out;
    }

    public static List<File> getFiles(String dir) {
        return getFiles(dir, null);
    }

    public static List<File> getFiles(String dir, final String matchRegex) {
        File file = new File(dir);
        File[] files = null;
        if (matchRegex != null) {
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String fileName) {
                    return fileName.matches(matchRegex);
                }
            };
            files = file.listFiles(filter);
        } else {
            files = file.listFiles();
        }
        return files != null ? Arrays.asList(files) : null;
    }

    public static File getFile(String path) {
        return new File(path);
    }

    public static boolean rename(String fromPath, String toPath) {
        File file = getFile(fromPath);
        File newFile = new File(toPath);
        return file.renameTo(newFile);
    }

    public static boolean copy(String fromPath, String toPath) {
        File file = getFile(fromPath);
        if (!file.isFile()) {
            return false;
        }

        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(file);
            outStream = new FileOutputStream(new File(toPath));
            FileChannel inChannel = inStream.getChannel();
            FileChannel outChannel = outStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (Exception e) {
            Log.e(TAG, "Failed copy", e);
            return false;
        } finally {
            closeSilently(inStream);
            closeSilently(outStream);
        }
        return true;
    }

    public static boolean move(String fromPath, String toPath) {
        if (copy(fromPath, toPath)) {
            return getFile(fromPath).delete();
        }
        return false;
    }

    private static boolean deleteDirectoryImpl(String path) {
        File directory = new File(path);

        // If the directory exists then delete
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files == null) {
                return true;
            }
            // Run on all sub files and folders and delete them
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectoryImpl(file.getAbsolutePath());
                } else {
                    file.delete();
                }
            }
        }
        return directory.delete();
    }

    private static void getDirectoryFilesImpl(File directory, List<File> out) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files == null) {
                return;
            } else {
                for (File file : files) {
                    if (file.isDirectory()) {
                        getDirectoryFilesImpl(file, out);
                    } else {
                        out.add(file);
                    }
                }
            }
        }
    }

    private static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }
}
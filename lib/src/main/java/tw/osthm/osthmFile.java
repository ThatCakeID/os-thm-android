package tw.osthm;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * <h1>osthmFile</h1>
 * <p>osthmFile class is used to read and write osthm files in osthm format, or JSON format</p>
 * <p>Example use cases: </p>
 * <code>new osthmFile(new File("my/path/to/file.os-thm")).toHashMap();  // Reading files
 * new osthmFile(new File("my/path/to/file.os-thm")).setData(myTheme).write(osthmFile.OSTHM); // Write theme data as osthm format
 * </code>
 *
 * @author Iyxan23
 */

// INFO: This class only returns and only receives full JSON Theme data

public class osthmFile {

    private JSONObject data;  // Raw JSON Data
    private File file;

    // Types used in the function write()
    public static final int OSTHM = 0;
    public static final int RAW_JSON = 2;

    public osthmFile() { }

    public osthmFile(File file) {
        this.file = file;
    }

    // Sets the file target
    // ===================================================================
    public osthmFile setFile(File file) {
        this.file = file;
        return this;
    }

    public osthmFile setFilePath(String filePath) {
        this.file = new File(filePath);
        return this;
    }
    // ===================================================================


    // Sets the Data that later will be written to the file using
    // the write() method
    // ===================================================================
    public osthmFile setData(String data) throws JSONException {
        this.data = new JSONObject(data);
        return this;
    }

    public osthmFile setData(JSONObject data) {
        this.data = data;
        return this;
    }

    public osthmFile setData(HashMap<String, Object> data) {
        this.data = new JSONObject(data);
        return this;
    }

    public osthmFile setData(OsThmTheme theme) {
        try {
            data.put("themesjson", theme.toHashMap());
        } catch (JSONException e) {
            Log.e("osthmFile", "Failed to set data as OsThmTheme, Error message: " + e.toString());
        }
        return this;
    }

    public osthmFile setData(OsThmMetadata metadata) {
        JSONObject tmp = null;
        try {
            // Extract the themesjson
            tmp = data.getJSONObject("themesjson");
        } catch (JSONException ignored) {}

        // Then replace the data with metadata
        data = new JSONObject(metadata.toHashMap());

        // And put back the themesjson if themesjson exists
        if (tmp != null) {
            try {
                data.put("themesjson", tmp);
            } catch (JSONException e) {
                // ..oops something went wrong
                Log.e("osthFile", "Failed to put back themesjson while setting data as OsThmMetadata");
            }
        }

        return this;
    }
    // ===================================================================


    // Reading the file and converting it into specific types
    // ===================================================================
    public HashMap<String, Object> toHashMap() {
        // Check if data is not null
        if (data != null)
            // Convert from JSONObject into JSON String into HashMap<String, Object> using GSon
            return new Gson().fromJson(
                    data.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
            );

        // Read the file
        data = readFile();

        // If somehow the reading failed
        if (data == null)
            // Return null
            return null;
        else
            // Call the same function, because i'm lazy copying the gson code
            // NOTE: No, it will not be looping, because this function is invoked when the data is
            //       filled / not null
            return toHashMap();
    }

    public String toJsonString() {
        // Check if data is not null
        if (data != null)
            // Then return the json string
            return data.toString();

        // If it is null, read the file
        data = readFile();

        // If somehow the reading failed
        if (data == null)
            // return null
            return null;
        else
            // return the JSON String
            return data.toString();
    }

    public JSONObject toJSONObject() {
        // Check if data is not null
        if (data != null)
            // Return the data
            return data;

        // If it is null, read the file
        data = readFile();

        // And return the data
        return data;
    }

    public OsThmTheme toOsThmTheme() {
        // Check if the data has been set by the user
        if (data != null) {
            try {
                return new OsThmTheme(data.getString("themesjson"));
            } catch (JSONException e) {
                // ..what? the user probably inserted an invalid data, return null instead
                return null;
            }
        }

        // If it is null, read the file
        data = readFile();

        // If somehow the reading failed
        if (data == null)
            // Return null
            return null;
        else
            // Call the same function
            return toOsThmTheme();
    }

    public OsThmMetadata toOsThmMetadata() {
        // Check if the data has been set by the user
        if (data != null)
            return new OsThmMetadata(this.toHashMap());

        // If it is null, read the file
        data = readFile();

        // If somehow the reading failed
        if (data == null)
            // Return null
            return null;
        else
            // Call the same function
            return toOsThmMetadata();
    }
    // ===================================================================

    // Check if JSONObject is valid as osthm
    private boolean isJSONValidAsOsThm(JSONObject json) {
        try {
            new OsThmTheme((HashMap<String, Integer>) new Gson().fromJson(
                    json.getString("themesjson").toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
            ));

            new OsThmMetadata((HashMap<String, Object>) new Gson().fromJson(
                    json.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
            ));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Returns boolean indicating if the writing was successful or not
    public boolean write(int type) {
        // Check if data is null
        if (data == null)
            throw new RuntimeException(new osthmException("Data has not been set yet"));

        // Check if file is null
        if (file == null)
            throw new RuntimeException(new osthmException("File has not been set yet"));

        // Check if the JSON is valid as osthm
        if (!isJSONValidAsOsThm(data))
            throw new RuntimeException(new osthmException("Invalid JSON data"));

        // Check the type
        if (type == osthmFile.OSTHM) {
            return write_osthm();

        } else if (type == osthmFile.RAW_JSON) {
            // Casually writes JSONObject to the file as a String
            return StorageUtil.createFile(file.getAbsolutePath(), data.toString());

        } else {
            // Looks like the dev has put an invalid argument
            throw new IllegalArgumentException("Type argument is not valid, it should be osthmFile.OSTHM or osthmFile.RAW_JSON");
        }
    }

    // Utilities
    // ===================================================================
    private UUID asUuid(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long firstLong = bb.getLong();
        long secondLong = bb.getLong();
        return new UUID(firstLong, secondLong);
    }

    private byte[] asBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    private void writeShort(OutputStream os, short s) throws IOException {
        os.write((byte) (s >> 8));
        os.write((byte) s);
    }
    // ===================================================================


    // Crucial part for the reading and writing
    // ===================================================================
    private JSONObject readFile() {
        byte[] data = StorageUtil.readFileBytes(file.getAbsolutePath());
        try {
            return new JSONObject(new String(data, StandardCharsets.UTF_8));
        } catch (Exception e) {
            // Not json, decode using the os-thm format
            // (Continued in the below block for prettier code)
        }

        // Inflate the file (Decompress) using ZLIB
        // Decompress the bytes
        try {
            Inflater decompresser = new Inflater();
            decompresser.setInput(data, 0, data.length);
            byte[] result = new byte[data.length * 2];  // Estimation, if there's any people that
                                                        // has some experience in compression,
                                                        // please fix this
            int resultLength = decompresser.inflate(result);
            decompresser.end();

            // Decode the bytes into a String
            String outputString = new String(result, 0, resultLength, StandardCharsets.UTF_8);
            Log.d("osthmFile", "Output String: " + outputString);

            return new JSONObject(outputString);
        } catch (DataFormatException | JSONException e) {
            Log.e("osthmFile", "Failed to decompress file, error: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    private boolean write_osthm() {
        // NOTE: This function is under development, this commit is unstable
        try {
            OutputStream stream = new FileOutputStream(file);

            OsThmMetadata metadata = this.toOsThmMetadata();
            OsThmTheme theme = this.toOsThmTheme();

            stream.write(" os-thm ".getBytes());

            // Length of each strings
            stream.write(new byte[] {(byte) metadata.themesname.length(), (byte) metadata.themesinfo.length()});
            stream.write(0x00);  // Spacing
            stream.write(metadata.themesname.getBytes());
            stream.write(0x00);
            stream.write(asBytes(UUID.fromString(metadata.uuid)));
            stream.write(0x00);
            stream.write(metadata.themesinfo.getBytes());
            stream.write(0x00);
            stream.write(metadata.themeversion);
            stream.write(metadata.os_thm_version);
            stream.write(0x00);

            // Write every os-thm colors
            stream.write((char) theme.colorPrimary);
            stream.write((char) theme.colorPrimaryText);
            stream.write((char) theme.colorPrimaryDark);
            stream.write((char) theme.colorStatusbarTint);
            stream.write((char) theme.colorBackground);
            stream.write((char) theme.colorBackgroundText);
            stream.write((char) theme.colorAccent);
            stream.write((char) theme.colorAccentText);
            stream.write(0x01 & theme.shadow);
            stream.write((char) theme.colorControlHighlight);
            stream.write((char) theme.colorHint);
            stream.write((char) theme.colorPrimaryTint);
            stream.write((char) theme.colorBackgroundTint);
            stream.write((char) theme.colorPrimaryCard);
            stream.write((char) theme.colorBackgroundCard);
            stream.write((char) theme.colorPrimaryCardText);
            stream.write((char) theme.colorBackgroundCardText);
            stream.write((char) theme.colorPrimaryCardTint);
            stream.write((char) theme.colorBackgroundCardTint);
            stream.write((char) theme.colorDialog);
            stream.write((char) theme.colorDialogText);
            stream.write((char) theme.colorDialogTint);

            stream.flush();
            stream.close();

            // Read the file again and compress it using the ZLIB compression algorithm
            byte[] file_data = StorageUtil.readFileBytes(file.getAbsolutePath());
            byte[] tmp = new byte[file_data.length];

            // Compress the bytes
            Deflater compresser = new Deflater();
            compresser.setStrategy(Deflater.BEST_COMPRESSION);
            compresser.setInput(file_data);
            compresser.finish();
            int compressedDataLength = compresser.deflate(tmp);
            compresser.end();

            byte[] compressed_data;
            compressed_data = Arrays.copyOfRange(tmp, 0, compressedDataLength);

            // Write back the data
            StorageUtil.createFile(file.getAbsolutePath(), compressed_data);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    // ===================================================================
}

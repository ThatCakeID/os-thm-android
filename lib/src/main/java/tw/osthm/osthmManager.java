package tw.osthm;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class osthmManager {

    public static final String externalDir = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static void init() {
        // Initialize

        // Check if .osthm folder exist
        if (!StorageUtil.isDirectoryExists(externalDir + "/.osthm/")) {
            // Initialize the folder structure
            // Create the folder .osthm and .osthm/themes
            StorageUtil.createDirectory(externalDir + "/.osthm/themes");

            // Create the config file
            StorageUtil.createFile(externalDir + "/.osthm/conf", "");
        }
    }

    public static ArrayList<String> getThemesPlain() throws IOException {
        ArrayList<String> output = new ArrayList<>();

        // List the files in the /.osthm/themes/ directory
        List<File> themes = StorageUtil.getFiles(externalDir + "/.osthm/themes/");

        // Loop each of the files in themes
        for (File theme: themes) {
            // Check if the file is file, exist, and it is readable
            if (theme.isFile() && theme.exists() && theme.canRead()) {
                // Append the file content to the array
                output.add(StorageUtil.readFile(theme.getAbsolutePath()));
            }
        }

        return output;
    }

    public static ArrayList<OsThmTheme> getThemes(Context context) throws IOException {
        ArrayList<OsThmTheme> output = new ArrayList<>();

        // List the files in the /.osthm/themes/ directory
        List<File> themes = StorageUtil.getFiles(externalDir + "/.osthm/themes/");

        // Loop each of the files in themes
        for (File theme: themes) {
            // Check if the file is file, exist, and it is readable
            if (theme.isFile() && theme.exists() && theme.canRead()) {
                String file_content = StorageUtil.readFile(theme.getAbsolutePath());
                HashMap<String, Object> file_data = new Gson().fromJson(file_content, new TypeToken<HashMap<String, Object>>() {}.getType());
                HashMap<String, Integer> themesjson = new Gson().fromJson((String) file_data.get("themesjson"), new TypeToken<HashMap<String, Integer>>() {}.getType());
                output.add(new OsThmTheme(context, themesjson));
            }
        }

        return output;
    }

    public static void saveTheme(OsThmTheme themeData,
                                 String themesname,  String themesinfo,      String themesauthor,
                                 int themeversion,   String UUID) throws osthmException {
        // Being worked on
    }

    /*
     * Currently being worked
     *
    public static ArrayList<HashMap<String, Object>> getThemesHashMap() throws IOException {
        ArrayList<HashMap<String, Object>> output = new ArrayList<>();

        // List the files in the /.osthm/themes/ directory
        List<File> themes = StorageUtil.getFiles(externalDir + "/.osthm/themes/");

        // Loop each of the files in themes
        for (File theme: themes) {
            // Check if the file is file, exist, and it is readable
            if (theme.isFile() && theme.exists() && theme.canRead()) {
                // Append the file content to the array
                String file_content = StorageUtil.readFile(theme.getAbsolutePath());
                new Gson().fromJson(file_content,
                                    new TypeToken<ArrayList<HashMap<String, Integer>>>() {}.getType()));
                output.add();
            }
        }

        return output;
    }
     */



}

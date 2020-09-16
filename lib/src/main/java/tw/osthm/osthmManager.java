package tw.osthm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class osthmManager {

    public static final String externalDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String themes_folder = externalDir + "/.osthm/themes/";
    public static final String config_file = externalDir + "/.osthm/conf";

    public static void init() {
        // Initialize

        // Check if .osthm folder exist
        if (!StorageUtil.isDirectoryExists(externalDir + "/.osthm/")) {
            // Initialize the folder structure
            // Create the folder .osthm and .osthm/themes
            StorageUtil.createDirectory(themes_folder);

            // Create the config file
            StorageUtil.createFile(config_file, "{\"osthm-version\": \"3\", \"currentTheme\": null}");
        }
    }

    public static boolean setCurrentTheme(String uuid) {
        if (isThemeExist(uuid)) {
            StorageUtil.createFile(config_file, "{\"osthm-version\": \"3\", \"currentTheme\": \"" + uuid + "\"");
            return true;
        }
        return false;
    }

    public static ArrayList<String> getAllThemesPlain() throws IOException {
        ArrayList<String> output = new ArrayList<>();

        // List the files in the /.osthm/themes/ directory
        List<File> themes = StorageUtil.getFiles(themes_folder);

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

    public static ArrayList<OsThmTheme> getAllThemes(Context context) throws IOException {
        ArrayList<OsThmTheme> output = new ArrayList<>();

        // List the files in the /.osthm/themes/ directory
        List<File> themes = StorageUtil.getFiles(themes_folder);

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

    // Returns true if the addition successful, otherwise it will return false if the addition is unsuccessful
    public static boolean addTheme(String json, String UUID) {
        return StorageUtil.createFile(themes_folder + UUID + ".json", json);
    }

    public static boolean isThemeExist(String uuid) {
        // Check if UUID given is valid
        try {
            UUID.fromString(uuid);
            // Valid UUID
            return StorageUtil.isFileExist(themes_folder + uuid + ".json");
        } catch (IllegalArgumentException e) {
            // Invalid UUID
            return false;
        }

    }

    // Returns true if the removal was successful, otherwise it will return false if the removal was unsuccessful
    public static boolean removeTheme(String UUID) {
        String file_path = themes_folder + UUID + ".json";
        if (!isThemeExist(UUID))
            return false;
        return StorageUtil.deleteFile(file_path);
    }

    public static String getThemePlain(String UUID) throws osthmException {
        if (isThemeExist(UUID)) {
            try {
                return StorageUtil.readFile(themes_folder + UUID + ".json");
            } catch (IOException e) {
                throw new osthmException("An error occurred, please contact ThatCakeID Team about this error");
            }
        } else
            throw new osthmException("Theme doesn't exist");
    }

    /*
     * Currently being worked
     *
    public static ArrayList<HashMap<String, Object>> getThemesHashMap() throws IOException {
        ArrayList<HashMap<String, Object>> output = new ArrayList<>();

        // List the files in the /.osthm/themes/ directory
        List<File> themes = StorageUtil.getFiles(themes_folder);

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

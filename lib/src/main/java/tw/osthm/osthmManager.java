package tw.osthm;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        if (!StorageUtil.isDirectoryExists(themes_folder) ||
                !StorageUtil.isDirectoryExists(config_file)) {
            // Initialize the folder structure
            // Create the folder .osthm and .osthm/themes
            StorageUtil.createDirectory(themes_folder);

            // Create the config file
            StorageUtil.createFile(config_file, "{}");
        }
    }

    public static void setConf(String key, String value) throws IOException {
        HashMap<String, String> data = parseConfJson();
        data.put(key, value);
        writeConfJson(data);
    }

    public static String getConf(String key, String defaultValue) throws IOException {
        HashMap<String, String> data = parseConfJson();
        return data.containsKey(key) ? data.get(key) : defaultValue;
    }

    public static boolean containsConf(String key) throws IOException {
        HashMap<String, String> data = parseConfJson();
        return data.containsKey(key);
    }

    //public static void setTheme()

    // Utilities ===================================================================================
    private static HashMap<String, String> parseConfJson() throws IOException {
        return new Gson().fromJson(StorageUtil.readFile(config_file),
                new TypeToken<HashMap<String, Object>>(){}.getType());
    }

    private static void writeConfJson(HashMap<String, String> confhashmap) {
        StorageUtil.appendFile(config_file, new Gson().toJson(confhashmap));
    }

    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUUIDValid(String test) {
        try {
            UUID.fromString(test);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}

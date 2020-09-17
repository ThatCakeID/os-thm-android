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
    public static final String themes_folder = externalDir + "/.osthm/themes/";
    public static final String config_file = externalDir + "/.osthm/conf";

    public static void init(Context mContext) {
        // Initialize

        // Check if .osthm folder exist
        if (!StorageUtil.isDirectoryExists(themes_folder) ||
                !StorageUtil.isFileExist(config_file)) {
            // Initialize the folder structure
            // Create the folder .osthm and .osthm/themes
            StorageUtil.createDirectory(themes_folder);

            // Create the config file
            StorageUtil.createFile(config_file, "{}");
        }
    }

    public static void setConf(String key, String value) throws IOException {
        HashMap<String, String> data = loadConfJson();
        data.put(key, value);
        StorageUtil.createFile(config_file, new Gson().toJson(data));
    }

    public static String getConf(String key, String defaultValue) throws IOException {
        HashMap<String, String> data = loadConfJson();
        return data.containsKey(key) ? data.get(key) : defaultValue;
    }

    public static boolean containsConf(String key) throws IOException {
        HashMap<String, String> data = loadConfJson();
        return data.containsKey(key);
    }

    public static void setTheme(String json) {
        HashMap<String, Object> parsedJson = new Gson().fromJson(json,
                new TypeToken<HashMap<String, Object>>(){}.getType());
        StorageUtil.createFile(themes_folder + parsedJson.get("uuid").toString(), json);
    }

    public static String getThemes() throws IOException {
        List<File> files = StorageUtil.getFiles(themes_folder);
        ArrayList<HashMap<String, Object>> themes = new ArrayList<>();
        for(File file : files) {
            themes.add((HashMap<String, Object>)new Gson().fromJson(StorageUtil
                            .readFile(file.getAbsolutePath()),
                    new TypeToken<HashMap<String, Object>>(){}.getType()));
        }
        return new Gson().toJson(themes);
    }

    // Utilities ===================================================================================
    private static HashMap<String, String> loadConfJson() throws IOException {
        return new Gson().fromJson(StorageUtil.readFile(config_file),
                new TypeToken<HashMap<String, Object>>(){}.getType());
    }
}

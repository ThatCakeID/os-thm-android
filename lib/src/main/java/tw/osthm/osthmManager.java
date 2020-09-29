package tw.osthm;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class osthmManager {

    public static final String externalDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String themes_folder = externalDir + "/.os-thm/themes/";
    public static final String config_file = externalDir + "/.os-thm/conf";
    public static final String exported_themes_folder = externalDir + "/os-thm/";

    // Initialize
    public static void init() {
        // Initialize

        // Check if os-thm folder exist
        if (!StorageUtil.isDirectoryExists(exported_themes_folder))
            StorageUtil.createDirectory(exported_themes_folder);

        // Check if .os-thm folder exist
        if (!StorageUtil.isDirectoryExists(themes_folder) ||
                !StorageUtil.isFileExist(config_file)) {
            // Initialize the folder structure
            // Create the folder .osthm and .osthm/themes
            StorageUtil.createDirectory(themes_folder);

            // Create the config file
            StorageUtil.createFile(config_file, "{}");
        } else {
            // Check if the config file is valid or not
            try {
                if (!isJSONValid(StorageUtil.readFile(config_file)))
                    StorageUtil.createFile(config_file, "{}");
            } catch (IOException ignored) {
            }

            // Check if the themes are valid or not
            List<File> files = StorageUtil.getFiles(themes_folder);
            for (File file : files) {
                // If the theme is invalid, then delete it
                try {
                    if (!isJSONValid(StorageUtil.readFile(file.getAbsolutePath())))
                        StorageUtil.deleteFile(file.getAbsolutePath());
                    else {
                        // Detect if there are any filename that doesn't matches the UUID, then fix it
                        HashMap<String, Object> thm = new Gson().fromJson(StorageUtil
                                        .readFile(file.getAbsolutePath()),
                                new TypeToken<HashMap<String, Object>>() {
                                }.getType());

                        if (!(thm.get("uuid").toString() + ".os-thm").equals(file.getName()))
                            StorageUtil.rename(file.getAbsolutePath(), themes_folder +
                                    thm.get("uuid").toString() + ".os-thm");
                    }
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static void setConf(String key, String value) {
        HashMap<String, String> data = loadConfJson();
        data.put(key, value);
        StorageUtil.createFile(config_file, new Gson().toJson(data));
    }

    public static String getConf(String key, String defaultValue) {
        HashMap<String, String> data = loadConfJson();
        return data.containsKey(key) ? data.get(key) : defaultValue;
    }

    public static boolean containsConf(String key) {
        HashMap<String, String> data = loadConfJson();
        return data.containsKey(key);
    }

    public static void removeConf(String key) throws IOException {
        HashMap<String, String> data = loadConfJson();
        if (data.containsKey(key)) {
            data.remove(key);
            StorageUtil.createFile(config_file, new Gson().toJson(data));
        }
    }

    public static void clearConf() {
        StorageUtil.createFile(config_file, "{}");
    }

    public static void setTheme(HashMap<String, Object> theme) {
        StorageUtil.createFile(themes_folder + theme.get("uuid").toString() + ".os-thm",
                new Gson().toJson(theme));
    }

    public static ArrayList<HashMap<String, Object>> getThemes() {
        List<File> files = StorageUtil.getFiles(themes_folder);
        ArrayList<HashMap<String, Object>> themes = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(new TypeToken<HashMap<String, Object>>() {
                }.getType(),
                new HashMapDeserializerFix());
        Gson gson = gsonBuilder.create();
        for (File file : files) {
            try {
                themes.add((HashMap<String, Object>) gson.fromJson(StorageUtil
                                .readFile(file.getAbsolutePath()),
                        new TypeToken<HashMap<String, Object>>() {
                        }.getType()));
            } catch (IOException ignored) {
            }
        }
        return themes;
    }

    public static void removeTheme(String uuid) {
        if (StorageUtil.isFileExist(themes_folder + uuid + ".os-thm"))
            StorageUtil.deleteFile(themes_folder + uuid + ".os-thm");
    }

    public static void clearThemes() {
        List<File> files = StorageUtil.getFiles(themes_folder);
        for (File file : files)
            StorageUtil.deleteFile(file.getAbsolutePath());
    }

    public static boolean containsTheme(String uuid) {
        List<File> files = StorageUtil.getFiles(themes_folder);
        for (File file : files) {
            if (file.getName().equals(uuid + ".os-thm")) {
                return true;
            }
        }

        return false;
    }

    public static String exportThemeFile(String json) {
        int fileNum = 0;
        while (true) {
            if (StorageUtil.isFileExist(exported_themes_folder + "theme_" + Integer.toString(fileNum) + ".os-thm"))
                fileNum++;
            else {
                StorageUtil.createFile(exported_themes_folder + "theme_" + Integer.toString(fileNum) + ".os-thm", json);
                return exported_themes_folder + "theme_" + Integer.toString(fileNum) + ".os-thm";
            }
        }
    }

    // Utilities ===================================================================================

    // Load config file into HashMap
    private static HashMap<String, String> loadConfJson() {
        try {
            return new Gson().fromJson(StorageUtil.readFile(config_file),
                    new TypeToken<HashMap<String, String>>() {
                    }.getType());
        } catch (IOException e) {
            return null;
        }
    }

    // Check if the JSON is valid or not
    private static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }
}

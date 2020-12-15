package tw.osthm;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

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
            Log.e("osthmFile", "Failed to set data as OsThmTheme (tw/osthm/osthmFile.java:77), Error message: " + e.toString());
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


    // ===================================================================
    public HashMap<String, Object> toHashMap() {
        if (data != null)
            return new Gson().fromJson(
                    data.toString(), new TypeToken<HashMap<String, Object>>() {}.getType()
            );

        return null;
    }

    public String toJsonString() {
        if (data != null)
            return data.toString();

        return null;
    }

    public JSONObject toJSONObject() {
        if (data != null)
            return data;

        return null;
    }

    public OsThmTheme toOsThmTheme() {
        // Check if the data has been set by the user
        if (data != null)
            try {
                return new OsThmTheme(data.getString("themesjson"));
            } catch (JSONException e) {
                // ..what? the user probably inserted an invalid data, return null instead
                return null;
            }

        // Return null
        return null;
    }

    public OsThmMetadata toOsThmMetadata() {
        // Check if the data has been set by the user
        if (data != null)
            return new OsThmMetadata(this.toHashMap());

        // Return null
        return null;
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
            return StorageUtil.createFile(file.getAbsolutePath(), data.toString());
        } else {
            throw new RuntimeException(new osthmException("Type argument is not valid, it should be osthmFile.OSTHM or osthmFile.RAW_JSON"));
        }
    }

    private boolean write_osthm() {

        return true;
    }
}

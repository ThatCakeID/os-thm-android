package tw.osthm;

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
    // ===================================================================


    // ===================================================================
    public HashMap<String, Object> toHashMap() {
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
    // ===================================================================

    // Returns boolean indicating if the writing was successful or not
    public boolean write(int type) {
        // Check if data is null
        if (data == null)
            throw new RuntimeException(new osthmException("Data has not been set yet"));

        // Check if file is null
        if (file == null)
            throw new RuntimeException(new osthmException("File has not been set yet"));

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

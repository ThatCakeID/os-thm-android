package tw.osthm;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

public class osthmFile {

    private String data;  // Raw JSON Data
    private File file;

    // Types used in the function write()
    public static final int OSTHM = 0;
    public static final int RAW_JSON = 2;

    public osthmFile() { }

    public osthmFile(File file) {
        this.file = file;
    }

    public osthmFile setData(String data) {
        this.data = data;
        return null;
    }

    public osthmFile setData(JSONObject data) {
        // this.data = data;
        return null;
    }

    public osthmFile setData(HashMap<String, Object> data) {
        // this.data = data;
        return null;
    }

    public HashMap<String, Object> toHashMap() {
        return null;
    }

    public String toJsonString() {
        return data;
    }

    public void write(int type) {

    }
}

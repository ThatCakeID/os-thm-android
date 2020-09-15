package tw.osthm;

import android.os.Environment;

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
}

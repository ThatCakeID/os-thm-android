package tw.osthm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * <h1>Theme metadata object model</h1>
 * This theme object model is used to
 * represent a theme metadata in an object
 *
 * @author ThatCakeID Team
 * @version 1.0
 * @since 2020
 */

public class OsThmMetadata {
    public String themesname;
    public String themesinfo;
    public String themesauthor;
    public int os_thm_version;
    public String uuid;
    public int themeversion;

    public OsThmMetadata(String themesname, String themesinfo, String themesauthor, int os_thm_version, String uuid, int themeversion) {
        this.themesname = themesname;
        this.themesinfo = themesinfo;
        this.themesauthor = themesauthor;
        this.os_thm_version = os_thm_version;
        this.uuid = uuid;
        this.themeversion = themeversion;
    }

    public OsThmMetadata() {
        new OsThmMetadata((HashMap<String, Object>) new Gson().fromJson(DefaultThemes
                        .getDefaultThemes().get(0).get("themesjson").toString(),
                new TypeToken<HashMap<String, Integer>>() {
                }.getType()));
    }

    public OsThmMetadata(HashMap<String, Object> data) {
        themesname = data.get("themesname").toString();
        themesinfo = data.get("themesinfo").toString();
        themesauthor = data.get("themesauthor").toString();
        os_thm_version = (int) data.get("os-thm-version");
        uuid = data.get("uuid").toString();
        themeversion = (int) data.get("theme-version");
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("themesname", themesname);
        data.put("themesinfo", themesinfo);
        data.put("themesauthor", themesauthor);
        data.put("os-thm-version", os_thm_version);
        data.put("uuid", uuid);
        data.put("theme-version", themeversion);

        return data;
    }
}

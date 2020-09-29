package tw.osthm;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
}

package tw.osthm;

import java.util.ArrayList;
import java.util.HashMap;

import static tw.osthm.osthmEngine.metadataVersion;

public class DefaultThemes {

    /*
     * Vanilla theme default colors:
     * colorPrimary:            -14575885
     * colorPrimaryText:        -1
     * colorPrimaryDark:        -15242838
     * colorStatusbarTint:       1
     * colorBackground:         -1
     * colorBackgroundText:     -16777216
     * colorAccent:             -720809
     * colorAccentText:         -1
     * shadow:                   1
     * colorControlHighlight:    1073741824
     * colorHint:               -5723992
     * colorPrimaryTint:        -1
     * colorBackgroundTint:     -14575885
     * colorPrimaryCard:        -1
     * colorBackgroundCard:     -1
     * colorPrimaryCardText:    -16777216
     * colorBackgroundCardText: -16777216
     * colorPrimaryCardTint:    -16777216
     * colorBackgroundCardTint: -16777216
     * colorDialog:             -1
     * colorDialogText:         -16777216
     * colorDialogTint:         -16777216
     */

    public static ArrayList<HashMap<String, Object>> getDefaultThemes() {
        ArrayList<HashMap<String, Object>> defaultThemes = new ArrayList<>();
        defaultThemes = new ArrayList<>();
        defaultThemes.add(0, addKeyToHashMap("themesname", "Vanilla"));
        defaultThemes.get(0).put("themesjson", "{\"colorPrimary\":-14575885,\"colorBackgroundCardTint\":-16777216,\"colorPrimaryDark\":-15242838,\"colorBackgroundText\":-16777216,\"colorBackground\":-1,\"shadow\":1,\"colorPrimaryTint\":-1,\"colorHint\":-5723992,\"colorStatusbarTint\":1,\"colorPrimaryCardTint\":-16777216,\"colorAccent\":-720809,\"colorPrimaryText\":-1,\"colorBackgroundCardText\":-16777216,\"colorBackgroundTint\":-14575885,\"colorControlHighlight\":1073741824,\"colorAccentText\":-1,\"colorBackgroundCard\":-1,\"colorPrimaryCardText\":-16777216,\"colorPrimaryCard\":-1,\"colorDialog\":-1,\"colorDialogText\":-16777216,\"colorDialogTint\":-16777216}");
        defaultThemes.get(0).put("themesinfo", "The default style theme of os-thm");
        defaultThemes.get(0).put("themesauthor", "リェンーゆく");
        defaultThemes.get(0).put("os-thm-version", metadataVersion);
        defaultThemes.get(0).put("uuid", "default");
        defaultThemes.get(0).put("theme-version", 2);
        defaultThemes.add(1, addKeyToHashMap("themesname", "Dark"));
        defaultThemes.get(1).put("themesjson", "{\"colorPrimary\":-14575885,\"colorBackgroundCardTint\":-6774616,\"colorPrimaryDark\":-14342875,\"colorBackgroundText\":-1,\"colorBackground\":-14342875,\"shadow\":1,\"colorPrimaryTint\":-1,\"colorHint\":-8355712,\"colorStatusbarTint\":1,\"colorPrimaryCardTint\":-6774616,\"colorAccent\":-720809,\"colorPrimaryText\":-1,\"colorBackgroundCardText\":-6774616,\"colorBackgroundTint\":-14575885,\"colorControlHighlight\":1090519039,\"colorAccentText\":-1,\"colorBackgroundCard\":-12566464,\"colorPrimaryCardText\":-6774616,\"colorPrimaryCard\":-12566464,\"colorDialog\":-12566464,\"colorDialogText\":-6774616,\"colorDialogTint\":-6774616}");
        defaultThemes.get(1).put("themesinfo", "A Material dark theme for os-thm");
        defaultThemes.get(1).put("themesauthor", "thatcakepiece");
        defaultThemes.get(1).put("os-thm-version", metadataVersion);
        defaultThemes.get(1).put("uuid", "dark");
        defaultThemes.get(1).put("theme-version", 3);
        return defaultThemes;
    }

    // Utilites
    // =============================================================================================

    /**
     * This method returns a HashMap containing
     * the given key and object. Used as Util in
     * osthm
     *
     * @param key   Key
     * @param value Value
     * @return HashMap containing the given key and value
     */

    private static HashMap<String, Object> addKeyToHashMap(String key, Object value) {
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put(key, value);
        return hashmap;
    }
}

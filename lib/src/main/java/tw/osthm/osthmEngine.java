//NOTE: You shouldn't modify any codes in this engine yourself
//      as it might will conflicts with other themes.
package tw.osthm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class osthmEngine {

    public static int themesVersion = 3;
    public static int metadataVersion = 3;
    private static SharedPreferences data;
    private static ArrayList<HashMap<String, Object>> defaultThemes;

    private static void initializeData(Context mContext) {
        //Initializes some data here
        data = mContext.getSharedPreferences("teamdata", Activity.MODE_PRIVATE);
        if (data.getString("themelists", "").equals("")) data.edit().putString("themelists", "[]").apply();
        if (data.getString("currentTheme", "").equals("")) data.edit().putString("currentTheme", "default").apply();
        defaultThemes = new ArrayList<>();
        /* defaultThemes.add(0, addKeyToHashMap("themesname", "Vanilla"));
        defaultThemes.get(0).put("themesjson", "[{\"colorPrimary\":\"#2196F3\",\"colorPrimaryDark\":\"#1769AA\",\"colorBackgroundText\":\"#000000\",\"colorBackground\":\"#FFFFFF\",\"colorButton\":\"#F50057\",\"shadow\":\"1\",\"colorHint\":\"#A8A8A8\",\"colorRipple\":\"#40000000\",\"colorPrimaryCardImage\":\"#000000\",\"version\":\"" + Integer.toString(themesVersion) + "\",\"colorPrimaryText\":\"#FFFFFF\",\"colorPrimaryImage\":\"#FFFFFF\",\"colorBackgroundImage\":\"#2196F3\",\"colorBackgroundCardText\":\"#000000\",\"statusbarIcon\":\"1\",\"colorBackgroundCard\":\"#FFFFFF\",\"colorButtonText\":\"#FFFFFF\",\"colorPrimaryCardText\":\"#000000\",\"colorPrimaryCard\":\"#FFFFFF\",\"colorBackgroundCardImage\":\"#000000\"}]");
        defaultThemes.get(0).put("themesinfo", "The default style theme of os-thm");
        defaultThemes.get(0).put("themesauthor", "リェンーゆく");
        defaultThemes.get(0).put("os-thm-version", Integer.toString(metadataVersion));
        defaultThemes.get(0).put("uuid", "default");
        defaultThemes.get(0).put("theme-version", 1);
        defaultThemes.add(1, addKeyToHashMap("themesname", "Dark"));
        defaultThemes.get(1).put("themesjson", "[{\"colorPrimary\":\"#2196F3\",\"colorPrimaryDark\":\"#252525\",\"colorBackgroundText\":\"#FFFFFF\",\"colorBackground\":\"#252525\",\"colorButton\":\"#F50057\",\"shadow\":\"1\",\"colorHint\":\"#808080\",\"colorRipple\":\"#40FFFFFF\",\"colorPrimaryCardImage\":\"#98A0A8\",\"version\":\"" + Integer.toString(themesVersion) + "\",\"colorPrimaryText\":\"#FFFFFF\",\"colorPrimaryImage\":\"#FFFFFF\",\"colorBackgroundImage\":\"#2196F3\",\"colorBackgroundCardText\":\"#98A0A8\",\"statusbarIcon\":\"1\",\"colorBackgroundCard\":\"#404040\",\"colorButtonText\":\"#FFFFFF\",\"colorPrimaryCardText\":\"#98A0A8\",\"colorPrimaryCard\":\"#404040\",\"colorBackgroundCardImage\":\"#98A0A8\"}]");
        defaultThemes.get(1).put("themesinfo", "A Material dark theme for os-thm");
        defaultThemes.get(1).put("themesauthor", "thatcakepiece");
        defaultThemes.get(1).put("os-thm-version", Integer.toString(metadataVersion));
        defaultThemes.get(1).put("uuid", "dark");
        defaultThemes.get(1).put("theme-version", 1); */
    }

    private static ArrayList<HashMap<String, Object>> getThemeListPrivate() {
        //Get theme from sharedpreferences (private method)
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        for (int i=0;i<defaultThemes.size();i++) {
            metadataarray.add(i, defaultThemes.get(i));
        }
        return metadataarray;
    }

    private static boolean isExistInDefaultTheme(String UUIDvar) {
        boolean isExist = false;
        for (int i=0;i<defaultThemes.size();i++) if (defaultThemes.get(i).containsKey("uuid")) isExist = true;
        return isExist;
    }

    public static ArrayList<HashMap<String, Object>> getThemeList(Context mContext) {
        //Get theme from sharedpreferences (public method)
        initializeData(mContext);
        return getThemeListPrivate();
    }

    public static void migrateOldTheme(Context mContext, String UUIDvar) {
        //Migrate specified old theme to newer version
        initializeData(mContext);
    }

    public static void migrateOlderTheme(Context mContext, int position) {
        initializeData(mContext);
        position -= defaultThemes.size();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        ArrayList<HashMap<String, Object>> oldTheme = new Gson().fromJson(metadataarray.get(position).get("themesjson").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        ArrayList<HashMap<String, Object>> newShinyFancyTheme = new ArrayList<>();
        newShinyFancyTheme.add(addKeyToHashMap("colorPrimary", Color.parseColor(oldTheme.get(0).get("colorPrimary").toString())));
        newShinyFancyTheme.get(0).put("colorPrimaryDark", Color.parseColor(oldTheme.get(0).get("colorPrimaryDark").toString()));
        newShinyFancyTheme.get(0).put("colorStatusbarTint", Integer.valueOf(oldTheme.get(0).get("statusbarIcon").toString()));
        newShinyFancyTheme.get(0).put("colorBackground", Color.parseColor(oldTheme.get(0).get("colorBackground").toString()));
        newShinyFancyTheme.get(0).put("colorAccent", Color.parseColor(oldTheme.get(0).get("colorButton").toString()));
        newShinyFancyTheme.get(0).put("shadow", Color.parseColor(oldTheme.get(0).get("shadow").toString()));
        newShinyFancyTheme.get(0).put("colorControlHighlight", Color.parseColor(oldTheme.get(0).get("colorRipple").toString()));
        newShinyFancyTheme.get(0).put("colorHint", Color.parseColor(oldTheme.get(0).get("colorHint").toString()));
        if (oldTheme.get(0).containsKey("version")) {
            newShinyFancyTheme.get(0).put("colorPrimaryTint", Color.parseColor(oldTheme.get(0).get("colorPrimaryImage").toString()));
            newShinyFancyTheme.get(0).put("colorBackgroundTint", Color.parseColor(oldTheme.get(0).get("colorBackgroundImage").toString()));
            newShinyFancyTheme.get(0).put("colorPrimaryCard", Color.parseColor(oldTheme.get(0).get("colorPrimaryCard").toString()));
            newShinyFancyTheme.get(0).put("colorBackgroundCard", Color.parseColor(oldTheme.get(0).get("colorBackgroundCard").toString()));
            newShinyFancyTheme.get(0).put("colorPrimaryCardText", Color.parseColor(oldTheme.get(0).get("colorPrimaryCardText").toString()));
            newShinyFancyTheme.get(0).put("colorBackgroundCardText", Color.parseColor(oldTheme.get(0).get("colorBackgroundCardText").toString()));
            newShinyFancyTheme.get(0).put("colorPrimaryCardTint", Color.parseColor(oldTheme.get(0).get("colorPrimaryCardImage").toString()));
            newShinyFancyTheme.get(0).put("colorBackgroundCardTint", Color.parseColor(oldTheme.get(0).get("colorBackgroundCardImage").toString()));
            newShinyFancyTheme.get(0).put("colorPrimaryText", Color.parseColor(oldTheme.get(0).get("colorPrimaryText").toString()));
            newShinyFancyTheme.get(0).put("colorBackgroundText", Color.parseColor(oldTheme.get(0).get("colorBackgroundText").toString()));
            newShinyFancyTheme.get(0).put("colorAccentText", Color.parseColor(oldTheme.get(0).get("colorButtonText").toString()));
        } else {
            newShinyFancyTheme.get(0).put("colorPrimaryTint", 0xFFFFFFFF);
            newShinyFancyTheme.get(0).put("colorBackgroundTint", 0xFF2196F3);
            newShinyFancyTheme.get(0).put("colorPrimaryCard", 0xFFFFFFFF);
            newShinyFancyTheme.get(0).put("colorBackgroundCard", 0xFFFFFFFF);
            newShinyFancyTheme.get(0).put("colorPrimaryCardText", 0xFF000000);
            newShinyFancyTheme.get(0).put("colorBackgroundCardText", 0xFF000000);
            newShinyFancyTheme.get(0).put("colorPrimaryCardTint", 0xFF000000);
            newShinyFancyTheme.get(0).put("colorBackgroundCardTint", 0xFF000000);
            if(newShinyFancyTheme.get(0).get("colorPrimaryText") == "1")
                newShinyFancyTheme.get(0).put("colorPrimaryText", 0xFFFFFFFF);
            else newShinyFancyTheme.get(0).put("colorPrimaryText", 0xFF000000);
            if(newShinyFancyTheme.get(0).get("colorBackgroundText") == "1")
                newShinyFancyTheme.get(0).put("colorBackgroundText", 0xFFFFFFFF);
            else newShinyFancyTheme.get(0).put("colorBackgroundText", 0xFF000000);
            if(newShinyFancyTheme.get(0).get("colorButtonText") == "1")
                newShinyFancyTheme.get(0).put("colorAccentText", 0xFFFFFFFF);
            else newShinyFancyTheme.get(0).put("colorAccentText", 0xFF000000);
            newShinyFancyTheme.get(0).put("colorAccentText", 0xFFFFFFFF);
        }
        newShinyFancyTheme.get(0).put("version", Integer.toString(themesVersion));
        metadataarray.get(position).put("themesjson", new Gson().toJson(newShinyFancyTheme));
        if (metadataarray.get(position).containsKey("os-thm-version")) {
            metadataarray.get(position).put("themesinfo", "Migrated from theme v1");
            metadataarray.get(position).put("themesauthor", "os-thm");
        }
        metadataarray.get(position).put("os-thm-version", Integer.toString(metadataVersion));
        metadataarray.get(position).put("uuid", UUID.randomUUID().toString());
        metadataarray.get(position).put("theme-version", 1);
        data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
    }

    public static void migrateAllOldThemes(Context mContext) {
        //Migrate all old themes to newer version
        initializeData(mContext);
    }

    public static void addTheme(Context mContext, int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                         int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                         int colorAccent, int colorAccentText, int shadow, int colorControlHighlight,
                         int colorHint, int colorPrimaryTint, int colorBackgroundTint,
                         int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText,
                         int colorBackgroundCardText, int colorPrimaryCardTint, int colorBackgroundCardTint,
                         String themesname, String themesinfo, int themeversion, String themesauthor) throws Exception {
        //Add new theme using given hex colors and generate new UUID
        addTheme(mContext, colorPrimary, colorPrimaryText, colorPrimaryDark, colorStatusbarTint, colorBackground,
                colorBackgroundText, colorAccent, colorAccentText, shadow, colorControlHighlight, colorHint,
                colorPrimaryTint, colorBackgroundTint, colorPrimaryCard, colorBackgroundCard,
                colorPrimaryCardText, colorBackgroundCardText, colorPrimaryCardTint, colorBackgroundCardTint,
                themesname, themesinfo, themesauthor, themeversion, UUID.randomUUID().toString());
    }

    public static void addTheme(Context mContext, int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                                int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                                int colorAccent, int colorAccentText, int shadow, int colorControlHighlight,
                                int colorHint, int colorPrimaryTint, int colorBackgroundTint,
                                int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText,
                                int colorBackgroundCardText, int colorPrimaryCardTint, int colorBackgroundCardTint,
                                String themesname, String themesinfo, String themesauthor,
                                int themeversion, String UUIDvar) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        if (indexUUID.contains(UUIDvar) || isExistInDefaultTheme(UUIDvar))
            throw new osthmException("Theme with same UUID is exist!");
        else {
            ArrayList<HashMap<String, Object>> themearray = new ArrayList<>();
            themearray.add(addKeyToHashMap("colorPrimary", colorPrimary));
            themearray.get(0).put("colorPrimaryText", colorPrimaryText);
            themearray.get(0).put("colorPrimaryDark", colorPrimaryDark);
            themearray.get(0).put("colorStatusbarTint", colorStatusbarTint);
            themearray.get(0).put("colorBackground", colorBackground);
            themearray.get(0).put("colorBackgroundText", colorBackgroundText);
            themearray.get(0).put("colorAccent", colorAccent);
            themearray.get(0).put("colorAccentText", colorAccentText);
            themearray.get(0).put("shadow", shadow);
            themearray.get(0).put("colorControlHighlight", colorControlHighlight);
            themearray.get(0).put("colorHint", colorHint);
            themearray.get(0).put("colorPrimaryTint", colorPrimaryTint);
            themearray.get(0).put("colorBackgroundTint", colorBackgroundTint);
            themearray.get(0).put("colorPrimaryCard", colorPrimaryCard);
            themearray.get(0).put("colorBackgroundCard", colorBackgroundCard);
            themearray.get(0).put("colorPrimaryCardText", colorPrimaryCardText);
            themearray.get(0).put("colorBackgroundCardText", colorBackgroundCardText);
            themearray.get(0).put("colorPrimaryCardTint", colorPrimaryCardTint);
            themearray.get(0).put("colorBackgroundCardTint", colorBackgroundCardTint);
            themearray.get(0).put("version", Integer.toString(themesVersion));
            metadataarray.add(addKeyToHashMap("themesname", themesname));
            metadataarray.get(metadataarray.size()-1).put("themesjson", new Gson().toJson(themearray));
            metadataarray.get(metadataarray.size()-1).put("themesinfo", themesinfo);
            metadataarray.get(metadataarray.size()-1).put("themesauthor", themesauthor);
            metadataarray.get(metadataarray.size()-1).put("os-thm-version", Integer.toString(metadataVersion));
            metadataarray.get(metadataarray.size()-1).put("uuid", UUIDvar);
            metadataarray.get(metadataarray.size()-1).put("theme-version", themeversion);
            data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
        }
    }

    public static void editTheme(Context mContext, String UUIDvar, int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                                 int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                                 int colorAccent, int colorAccentText, int shadow, int colorControlHighlight,
                                 int colorHint, int colorPrimaryTint, int colorBackgroundTint,
                                 int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText,
                                 int colorBackgroundCardText, int colorPrimaryCardTint, int colorBackgroundCardTint,
                                 String themesname, String themesinfo, String themesauthor, int themeversion) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        if (indexUUID.contains(UUIDvar)) {
            if (metadataarray.get(indexUUID.indexOf(UUIDvar)).get("os-thm-version") == Integer.toString(metadataVersion)) {
                ArrayList<HashMap<String, Object>> themearray = new ArrayList<>();
                themearray.add(addKeyToHashMap("colorPrimary", colorPrimary));
                themearray.get(0).put("colorPrimaryText", colorPrimaryText);
                themearray.get(0).put("colorPrimaryDark", colorPrimaryDark);
                themearray.get(0).put("colorStatusbarTint", colorStatusbarTint);
                themearray.get(0).put("colorBackground", colorBackground);
                themearray.get(0).put("colorBackgroundText", colorBackgroundText);
                themearray.get(0).put("colorAccent", colorAccent);
                themearray.get(0).put("colorAccentText", colorAccentText);
                themearray.get(0).put("shadow", shadow);
                themearray.get(0).put("colorControlHighlight", colorControlHighlight);
                themearray.get(0).put("colorHint", colorHint);
                themearray.get(0).put("colorPrimaryTint", colorPrimaryTint);
                themearray.get(0).put("colorBackgroundTint", colorBackgroundTint);
                themearray.get(0).put("colorPrimaryCard", colorPrimaryCard);
                themearray.get(0).put("colorBackgroundCard", colorBackgroundCard);
                themearray.get(0).put("colorPrimaryCardText", colorPrimaryCardText);
                themearray.get(0).put("colorBackgroundCardText", colorBackgroundCardText);
                themearray.get(0).put("colorPrimaryCardTint", colorPrimaryCardTint);
                themearray.get(0).put("colorBackgroundCardTint", colorBackgroundCardTint);
                themearray.get(0).put("version", Integer.toString(themesVersion));
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesname", themesname);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesjson", new Gson().toJson(themearray));
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesinfo", themesinfo);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesauthor", themesauthor);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("os-thm-version", Integer.toString(metadataVersion));
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("uuid", UUIDvar);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("theme-version", themeversion);
                data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
            } else
                throw new osthmException("Incompatible metadata version!");
        } else {
            if (isExistInDefaultTheme(UUIDvar))
                throw new osthmException("You can't edit a default theme!");
            else
                throw new osthmException("Theme with given UUID isn't exist!");
        }
    }

    public static void setCurrentTheme(Context mContext, String UUIDvar) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();
        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        if (indexUUID.contains(UUIDvar)) {
            if (metadataarray.get(indexUUID.indexOf(UUIDvar)).get("os-thm-version") == Integer.toString(metadataVersion)) {
                HashMap<String, Object> thmjsn = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<HashMap<String, Object>>() {
                }.getType());
                if (thmjsn.get("version") == Integer.toString(themesVersion))
                    data.edit().putString("currentTheme", UUIDvar).apply();
                else
                    throw new osthmException("Incompatible theme version!");
            } else
                throw new osthmException("Incompatible theme metadata version!");
        } else
            throw new osthmException("No matching theme with the given UUID!");
    }

    public static HashMap<String, Object> getCurrentTheme(Context mContext) {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();
        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        ArrayList<HashMap<String, Object>> arraythm = new Gson().fromJson(metadataarray.get(indexUUID.indexOf(data.getString("currentTheme", ""))).get("themesjson").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        return arraythm.get(0);
    }

    public static String getCurrentThemeUUID(Context mContext) {
        initializeData(mContext);
        return data.getString("currentTheme", "");
    }

    public static void importThemes(Context mContext, String json) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        ArrayList<HashMap<String, Object>> thmarray = new Gson().fromJson(json, new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        if (thmarray.size() > 0) {
            for (int i = 0; i < thmarray.size(); i++) {
                if (indexUUID.contains(thmarray.get(i).get("uuid")) || isExistInDefaultTheme(thmarray.get(i).get("uuid").toString())) {
                    if ((int)thmarray.get(i).get("theme-version") > (int)metadataarray.get(indexUUID.indexOf(thmarray.get(i).get("uuid"))).get("theme-version")  && !(thmarray.get(i).get("uuid") == "default" || thmarray.get(i).get("uuid") == "dark"))
                        metadataarray.set(indexUUID.indexOf(thmarray.get(i).get("uuid")), thmarray.get(i));
                    else
                        throw new osthmException("Theme(s) can't be imported because the theme(s) are already exist!");
                } else {
                    metadataarray.add(thmarray.get(i));
                }
            }
            data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
        } else
            throw new osthmException("This JSON things is empty, what do you hope for? ._.");
    }

    public static String exportThemes(Context mContext, ArrayList<String> UUIDvars) throws Exception {
        if (UUIDvars.size() > 0) {
            initializeData(mContext);
            ArrayList<String> indexUUID = new ArrayList<>();
            ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();
            for (int i = 0; i < metadataarray.size(); i++)
                indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
            ArrayList<HashMap<String, Object>> thmarray = new ArrayList<>();
            for (int i = 0; i < UUIDvars.size(); i++) {
                if (indexUUID.contains(UUIDvars.get(i))) {
                    thmarray.add(metadataarray.get(indexUUID.indexOf(UUIDvars.get(i))));
                } else
                    throw new osthmException("Theme(s) aren't exists!");
            }
            return new Gson().toJson(thmarray);
        } else
            throw new osthmException("There is no UUID given, what do you hope for? ._.");
    }

    public static void removeTheme(Context mContext, String UUIDvar) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        if (indexUUID.contains(UUIDvar)) {
            if (data.getString("currentTheme", "") == UUIDvar)
                throw new osthmException("Theme is in use!");
            else {
                metadataarray.remove(indexUUID.indexOf(UUIDvar));
                data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
            }
        } else {
            if (isExistInDefaultTheme(UUIDvar))
                throw new osthmException("You can't delete a default theme!");
            else
                throw new osthmException("Theme with given UUID isn't exist!");
        }
    }

    public static void removeAllThemes(Context mContext) throws Exception {
        initializeData(mContext);
        if (data.getString("currentTheme", "") == "default" || data.getString("currentTheme", "") == "dark")
            data.edit().putString("themelists", "[]").apply();
        else
            throw new osthmException("Theme is in use!");
    }

    private static HashMap<String, Object> addKeyToHashMap(String key, Object value) {
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put(key, value);
        return hashmap;
    }
}
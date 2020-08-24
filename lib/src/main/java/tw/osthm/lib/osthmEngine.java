//NOTE: You shouldn't modify any codes in this engine yourself
//      as it might will conflicts with other themes.
package tw.osthm.lib;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class osthmEngine {

    public int themesVersion = 3;
    public int metadataVersion = 3;
    private SharedPreferences data;

    private void initializeData(Context mContext) {
        //Initializes some data here
        data = mContext.getSharedPreferences("teamdata", Activity.MODE_PRIVATE);
        if (data.getString("themelists", "").equals("")) data.edit().putString("themelists", "[]").apply();
        if (data.getString("currentTheme", "").equals("")) data.edit().putString("currentTheme", "default").apply();
    }

    private ArrayList<HashMap<String, Object>> getThemeListPrivate() {
        //Get theme from sharedpreferences (private method)
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        metadataarray.add(0, (HashMap<String, Object>) new HashMap<String, Object>().put("themesname", "Vanilla"));
        metadataarray.get(0).put("themesjson", "[{\"colorPrimary\":\"#2196F3\",\"colorPrimaryDark\":\"#1769AA\",\"colorBackgroundText\":\"#000000\",\"colorBackground\":\"#FFFFFF\",\"colorButton\":\"#F50057\",\"shadow\":\"1\",\"colorHint\":\"#A8A8A8\",\"colorRipple\":\"#40000000\",\"colorPrimaryCardImage\":\"#000000\",\"version\":\"" + Integer.toString(themesVersion) + "\",\"colorPrimaryText\":\"#FFFFFF\",\"colorPrimaryImage\":\"#FFFFFF\",\"colorBackgroundImage\":\"#2196F3\",\"colorBackgroundCardText\":\"#000000\",\"statusbarIcon\":\"1\",\"colorBackgroundCard\":\"#FFFFFF\",\"colorButtonText\":\"#FFFFFF\",\"colorPrimaryCardText\":\"#000000\",\"colorPrimaryCard\":\"#FFFFFF\",\"colorBackgroundCardImage\":\"#000000\"}]");
        metadataarray.get(0).put("themesinfo", "The default style theme of os-thm");
        metadataarray.get(0).put("themesauthor", "リェンーゆく");
        metadataarray.get(0).put("os-thm-version", Integer.toString(metadataVersion));
        metadataarray.get(0).put("uuid", "default");
        metadataarray.get(0).put("theme-version", 1);
        metadataarray.add(1, (HashMap<String, Object>) new HashMap<String, Object>().put("themesname", "Vanilla"));
        metadataarray.get(1).put("themesjson", "[{\"colorPrimary\":\"#2196F3\",\"colorPrimaryDark\":\"#252525\",\"colorBackgroundText\":\"#FFFFFF\",\"colorBackground\":\"#252525\",\"colorButton\":\"#F50057\",\"shadow\":\"1\",\"colorHint\":\"#808080\",\"colorRipple\":\"#40FFFFFF\",\"colorPrimaryCardImage\":\"#98A0A8\",\"version\":\"" + Integer.toString(themesVersion) + "\",\"colorPrimaryText\":\"#FFFFFF\",\"colorPrimaryImage\":\"#FFFFFF\",\"colorBackgroundImage\":\"#2196F3\",\"colorBackgroundCardText\":\"#98A0A8\",\"statusbarIcon\":\"1\",\"colorBackgroundCard\":\"#404040\",\"colorButtonText\":\"#FFFFFF\",\"colorPrimaryCardText\":\"#98A0A8\",\"colorPrimaryCard\":\"#404040\",\"colorBackgroundCardImage\":\"#98A0A8\"}]");
        metadataarray.get(1).put("themesinfo", "A Material dark theme for os-thm");
        metadataarray.get(1).put("themesauthor", "thatcakepiece");
        metadataarray.get(1).put("os-thm-version", Integer.toString(metadataVersion));
        metadataarray.get(1).put("uuid", "dark");
        metadataarray.get(1).put("theme-version", 1);
        return metadataarray;
    }

    public ArrayList<HashMap<String, Object>> getThemeList(Context mContext) {
        //Get theme from sharedpreferences (public method)
        initializeData(mContext);
        return getThemeListPrivate();
    }

    //TODO: Implement migrate old theme function
    public void migrateOldTheme(Context mContext, String UID) {
        //Migrate specified old theme to newer version
        initializeData(mContext);
    }

    public void migrateAllOldThemes(Context mContext) {
        //Migrate all old themes to newer version
        initializeData(mContext);
    }

    //experimental incomplete codes ;-;
    /*
    public String dbgMigrate(String json) {
        ArrayList<HashMap<String, Object>> metadataarrayjsn = new Gson().fromJson(json, new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        ArrayList<HashMap<String, Object>> jsnthm = new Gson().fromJson(metadataarrayjsn.get(0).get("themesjson").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        ArrayList<HashMap<String, Object>> metadataarray = new ArrayList<>();
        ArrayList<HashMap<String, Object>> themearray = new ArrayList<>();
        themearray.add((HashMap<String, Object>) new HashMap<String, Object>().put("colorPrimary", Color.parseColor(jsnthm.get(0).get("colorPrimary").toString())));
        themearray.get(0).put("colorPrimaryText", jsnthm.get(0).get("colorPrimaryText"));
        themearray.get(0).put("colorPrimaryDark", jsnthm.get(0).get("colorPrimaryDark"));
        themearray.get(0).put("statusbarIcon", jsnthm.get(0).get("statusbarIcon"));
        themearray.get(0).put("colorBackground", colorBackground);
        themearray.get(0).put("colorBackgroundText", colorBackgroundText);
        themearray.get(0).put("colorButton", colorButton);
        themearray.get(0).put("colorButtonText", colorButtonText);
        themearray.get(0).put("shadow", shadow);
        themearray.get(0).put("colorRipple", colorRipple);
        themearray.get(0).put("colorHint", colorHint);
        themearray.get(0).put("colorPrimaryImage", colorPrimaryImage);
        themearray.get(0).put("colorBackgroundImage", colorBackgroundImage);
        themearray.get(0).put("colorPrimaryCard", colorPrimaryCard);
        themearray.get(0).put("colorBackgroundCard", colorBackgroundCard);
        themearray.get(0).put("colorPrimaryCardText", colorPrimaryCardText);
        themearray.get(0).put("colorBackgroundCardText", colorBackgroundCardText);
        themearray.get(0).put("colorPrimaryCardImage", colorPrimaryCardImage);
        themearray.get(0).put("colorBackgroundCardImage", colorBackgroundCardImage);
        themearray.get(0).put("version", Integer.toString(themesVersion));
        return new Gson().toJson(metadataarray);
    } */
    //end of experimental codes

    public void addTheme(Context mContext, int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                         int statusbarIcon, int colorBackground, int colorBackgroundText,
                         int colorButton, int colorButtonText, int shadow, int colorRipple,
                         int colorHint, int colorPrimaryImage, int colorBackgroundImage,
                         int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText,
                         int colorBackgroundCardText, int colorPrimaryCardImage, int colorBackgroundCardImage,
                         String themesname, String themesinfo, int themeversion, String themesauthor) throws Exception {
        //Add new theme using given hex colors and generate new UUID
        addTheme(mContext, colorPrimary, colorPrimaryText, colorPrimaryDark, statusbarIcon, colorBackground,
                colorBackgroundText, colorButton, colorButtonText, shadow, colorRipple, colorHint,
                colorPrimaryImage, colorBackgroundImage, colorPrimaryCard, colorBackgroundCard,
                colorPrimaryCardText, colorBackgroundCardText, colorPrimaryCardImage, colorBackgroundCardImage,
                themesname, themesinfo, themesauthor, themeversion, UUID.randomUUID().toString());
    }

    public void addTheme(Context mContext, int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                         int statusbarIcon, int colorBackground, int colorBackgroundText,
                         int colorButton, int colorButtonText, int shadow, int colorRipple,
                         int colorHint, int colorPrimaryImage, int colorBackgroundImage,
                         int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText,
                         int colorBackgroundCardText, int colorPrimaryCardImage, int colorBackgroundCardImage,
                         String themesname, String themesinfo, String themesauthor,
                         int themeversion, String UUIDvar) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        if (indexUUID.contains(UUIDvar) || UUIDvar == "default" || UUIDvar == "dark")
            throw new osthmException("Theme with same UUID is exist!");
        else {
            ArrayList<HashMap<String, Object>> themearray = new ArrayList<>();
            themearray.add((HashMap<String, Object>) new HashMap<String, Object>().put("colorPrimary", colorPrimary));
            themearray.get(0).put("colorPrimaryText", colorPrimaryText);
            themearray.get(0).put("colorPrimaryDark", colorPrimaryDark);
            themearray.get(0).put("statusbarIcon", statusbarIcon);
            themearray.get(0).put("colorBackground", colorBackground);
            themearray.get(0).put("colorBackgroundText", colorBackgroundText);
            themearray.get(0).put("colorButton", colorButton);
            themearray.get(0).put("colorButtonText", colorButtonText);
            themearray.get(0).put("shadow", shadow);
            themearray.get(0).put("colorRipple", colorRipple);
            themearray.get(0).put("colorHint", colorHint);
            themearray.get(0).put("colorPrimaryImage", colorPrimaryImage);
            themearray.get(0).put("colorBackgroundImage", colorBackgroundImage);
            themearray.get(0).put("colorPrimaryCard", colorPrimaryCard);
            themearray.get(0).put("colorBackgroundCard", colorBackgroundCard);
            themearray.get(0).put("colorPrimaryCardText", colorPrimaryCardText);
            themearray.get(0).put("colorBackgroundCardText", colorBackgroundCardText);
            themearray.get(0).put("colorPrimaryCardImage", colorPrimaryCardImage);
            themearray.get(0).put("colorBackgroundCardImage", colorBackgroundCardImage);
            themearray.get(0).put("version", Integer.toString(themesVersion));
            metadataarray.add((HashMap<String, Object>) new HashMap<String, Object>().put("themesname", themesname));
            metadataarray.get(0).put("themesjson", new Gson().toJson(themearray));
            metadataarray.get(0).put("themesinfo", themesinfo);
            metadataarray.get(0).put("themesauthor", themesauthor);
            metadataarray.get(0).put("os-thm-version", Integer.toString(metadataVersion));
            metadataarray.get(0).put("uuid", UUIDvar);
            metadataarray.get(0).put("theme-version", themeversion);
            data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
        }
    }

    public void editTheme(Context mContext, String UUIDvar, int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                          int statusbarIcon, int colorBackground, int colorBackgroundText,
                          int colorButton, int colorButtonText, int shadow, int colorRipple,
                          int colorHint, int colorPrimaryImage, int colorBackgroundImage,
                          int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText,
                          int colorBackgroundCardText, int colorPrimaryCardImage, int colorBackgroundCardImage,
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
                themearray.add((HashMap<String, Object>) new HashMap<String, Object>().put("colorPrimary", colorPrimary));
                themearray.get(0).put("colorPrimaryText", colorPrimaryText);
                themearray.get(0).put("colorPrimaryDark", colorPrimaryDark);
                themearray.get(0).put("statusbarIcon", statusbarIcon);
                themearray.get(0).put("colorBackground", colorBackground);
                themearray.get(0).put("colorBackgroundText", colorBackgroundText);
                themearray.get(0).put("colorButton", colorButton);
                themearray.get(0).put("colorButtonText", colorButtonText);
                themearray.get(0).put("shadow", shadow);
                themearray.get(0).put("colorRipple", colorRipple);
                themearray.get(0).put("colorHint", colorHint);
                themearray.get(0).put("colorPrimaryImage", colorPrimaryImage);
                themearray.get(0).put("colorBackgroundImage", colorBackgroundImage);
                themearray.get(0).put("colorPrimaryCard", colorPrimaryCard);
                themearray.get(0).put("colorBackgroundCard", colorBackgroundCard);
                themearray.get(0).put("colorPrimaryCardText", colorPrimaryCardText);
                themearray.get(0).put("colorBackgroundCardText", colorBackgroundCardText);
                themearray.get(0).put("colorPrimaryCardImage", colorPrimaryCardImage);
                themearray.get(0).put("colorBackgroundCardImage", colorBackgroundCardImage);
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
            if (UUIDvar == "default" || UUIDvar == "dark")
                throw new osthmException("You can't edit a default theme!");
            else
                throw new osthmException("Theme with given UUID isn't exist!");
        }
    }

    public void setCurrentTheme(Context mContext, String UUIDvar) throws Exception {
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

    public HashMap<String, Object> getCurrentTheme(Context mContext) {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();
        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        ArrayList<HashMap<String, Object>> arraythm = new Gson().fromJson(metadataarray.get(indexUUID.indexOf(data.getString("currentTheme", ""))).get("themesjson").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        return arraythm.get(0);
    }

    public String getCurrentThemeUUID(Context mContext) {
        initializeData(mContext);
        return data.getString("currentTheme", "");
    }

    public void importThemes(Context mContext, String json) throws Exception {
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
                if (indexUUID.contains(thmarray.get(i).get("uuid")) || thmarray.get(i).get("uuid") == "default" || thmarray.get(i).get("uuid") == "dark") {
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

    public String exportThemes(Context mContext, ArrayList<String> UUIDvars) throws Exception {
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

    public void removeTheme(Context mContext, String UUIDvar) throws Exception {
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
            if (UUIDvar == "default" || UUIDvar == "dark")
                throw new osthmException("You can't delete a default theme!");
            else
                throw new osthmException("Theme with given UUID isn't exist!");
        }
    }

    public void removeAllThemes(Context mContext) throws Exception {
        initializeData(mContext);
        if (data.getString("currentTheme", "") == "default" || data.getString("currentTheme", "") == "dark")
            data.edit().putString("themelists", "[]").apply();
        else
            throw new osthmException("Theme is in use!");
    }
}
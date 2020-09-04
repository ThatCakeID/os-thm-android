/*
 * Copyright ThatCakeID 2020
 *
 * Contributors:
 *  - Ryenyuku <insert ur email here lol> 2020 - present
 *  - Iyxan23 <nurihsanalghifari@gmail.com> 2020 - present
 *
 * NOTE: You shouldn't modify any codes in this engine yourself
 * as it might will conflicts with other themes.
 */
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
        data = mContext.getSharedPreferences("teamdata", Activity.MODE_PRIVATE);

        if (data.getString("themelists", "").equals(""))
            data.edit().putString("themelists", "[]").apply();

        if (data.getString("currentTheme", "").equals(""))
            data.edit().putString("currentTheme", "default").apply();

        defaultThemes = new ArrayList<>();
        defaultThemes.add(0, addKeyToHashMap("themesname", "Vanilla"));
        defaultThemes.get(0).put("themesjson", "[{\"colorPrimary\":-14575885,\"colorBackgroundCardTint\":-16777216,\"colorPrimaryDark\":-15242838,\"colorBackgroundText\":-16777216,\"colorBackground\":-1,\"shadow\":1,\"colorPrimaryTint\":-1,\"colorHint\":-5723992,\"colorStatusbarTint\":1,\"version\":" + Integer.toString(themesVersion) + ",\"colorPrimaryCardTint\":-16777216,\"colorAccent\":-720809,\"colorPrimaryText\":-1,\"colorBackgroundCardText\":-16777216,\"colorBackgroundTint\":-14575885,\"colorControlHighlight\":1073741824,\"colorAccentText\":-1,\"colorBackgroundCard\":-1,\"colorPrimaryCardText\":-16777216,\"colorPrimaryCard\":-1}]");
        defaultThemes.get(0).put("themesinfo", "The default style theme of os-thm");
        defaultThemes.get(0).put("themesauthor", "リェンーゆく");
        defaultThemes.get(0).put("os-thm-version", metadataVersion);
        defaultThemes.get(0).put("uuid", "default");
        defaultThemes.get(0).put("theme-version", 2);
        defaultThemes.add(1, addKeyToHashMap("themesname", "Dark"));
        defaultThemes.get(1).put("themesjson", "[{\"colorPrimary\":-14575885,\"colorBackgroundCardTint\":-6774616,\"colorPrimaryDark\":-14342875,\"colorBackgroundText\":-1,\"colorBackground\":-14342875,\"shadow\":1,\"colorPrimaryTint\":-1,\"colorHint\":-8355712,\"colorStatusbarTint\":1,\"version\":" + Integer.toString(themesVersion) + ",\"colorPrimaryCardTint\":-6774616,\"colorAccent\":-720809,\"colorPrimaryText\":-1,\"colorBackgroundCardText\":-6774616,\"colorBackgroundTint\":-14575885,\"colorControlHighlight\":1090519039,\"colorAccentText\":-1,\"colorBackgroundCard\":-12566464,\"colorPrimaryCardText\":-6774616,\"colorPrimaryCard\":-12566464}]");
        defaultThemes.get(1).put("themesinfo", "A Material dark theme for os-thm");
        defaultThemes.get(1).put("themesauthor", "thatcakepiece");
        defaultThemes.get(1).put("os-thm-version", metadataVersion);
        defaultThemes.get(1).put("uuid", "dark");
        defaultThemes.get(1).put("theme-version", 3);

    }

    private static ArrayList<HashMap<String, Object>> getThemeListPrivate() {
        // Get theme from SharedPreferences (private method)
        ArrayList<HashMap<String, Object>> metadataarray =
                new Gson().fromJson(data.getString("themelists", ""),
                                    new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        metadataarray.addAll(0, defaultThemes);

        return metadataarray;
    }

    private static boolean isExistInDefaultTheme(String themeUUID) {
        boolean isExist = false;

        for (HashMap<String, Object> theme: defaultThemes)
            if (theme.containsKey("uuid"))
                isExist = true;

        return isExist;
    }

    private static HashMap<String, Object> migrateOlderThemePrivate(HashMap<String, Object> metadataarray) {
        ArrayList<HashMap<String, Object>> oldTheme =
                new Gson().fromJson(metadataarray.get("themesjson").toString(),
                                   new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        ArrayList<HashMap<String, Integer>> newShinyFancyTheme = new ArrayList<>();

        newShinyFancyTheme.add(addKeyToIntHashMap("colorPrimary", Color.parseColor(oldTheme.get(0).get("colorPrimary").toString())));
        newShinyFancyTheme.get(0).put("colorPrimaryDark", Color.parseColor(oldTheme.get(0).get("colorPrimaryDark").toString()));
        newShinyFancyTheme.get(0).put("colorStatusbarTint", Integer.valueOf(oldTheme.get(0).get("statusbarIcon").toString()));
        newShinyFancyTheme.get(0).put("colorBackground", Color.parseColor(oldTheme.get(0).get("colorBackground").toString()));
        newShinyFancyTheme.get(0).put("colorAccent", Color.parseColor(oldTheme.get(0).get("colorButton").toString()));
        newShinyFancyTheme.get(0).put("shadow", Integer.valueOf(oldTheme.get(0).get("shadow").toString()));
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

            if (oldTheme.get(0).get("colorPrimaryText") == "1")
                newShinyFancyTheme.get(0).put("colorPrimaryText", 0xFFFFFFFF);
            else
                newShinyFancyTheme.get(0).put("colorPrimaryText", 0xFF000000);

            if (oldTheme.get(0).get("colorBackgroundText") == "1")
                newShinyFancyTheme.get(0).put("colorBackgroundText", 0xFFFFFFFF);
            else
                newShinyFancyTheme.get(0).put("colorBackgroundText", 0xFF000000);

            if (oldTheme.get(0).get("colorButtonText") == "1")
                newShinyFancyTheme.get(0).put("colorAccentText", 0xFFFFFFFF);
            else
                newShinyFancyTheme.get(0).put("colorAccentText", 0xFF000000);

            newShinyFancyTheme.get(0).put("colorAccentText", 0xFFFFFFFF);
        }

        newShinyFancyTheme.get(0).put("version", themesVersion);
        metadataarray.put("themesjson", new Gson().toJson(newShinyFancyTheme));

        if (!metadataarray.containsKey("os-thm-version")) {
            metadataarray.put("themesinfo", "Migrated from theme v1");
            metadataarray.put("themesauthor", "os-thm");
        }

        metadataarray.put("os-thm-version", metadataVersion);
        metadataarray.put("uuid", UUID.randomUUID().toString());
        metadataarray.put("theme-version", 1);

        return metadataarray;
    }

    private static HashMap<String, Object> migrateOldThemePrivate(HashMap<String, Object> metadataarray) {
        return null;
    }

    public static ArrayList<HashMap<String, Object>> getThemeList(Context mContext) {
        // Get theme from sharedpreferences (public method)
        initializeData(mContext);

        return getThemeListPrivate();
    }

    public static void migrateOldTheme(Context mContext, String UUIDvar) {
        // Migrate specified old theme to newer version
        initializeData(mContext);
    }

    public static void migrateAllOldThemes(Context mContext) {
        // Migrate all old themes to newer version

        initializeData(mContext);
    }

    public static void addTheme(Context mContext,           int colorPrimary,           int colorPrimaryText,   int colorPrimaryDark,
                                int colorStatusbarTint,     int colorBackground,        int colorBackgroundText,
                                int colorAccent,            int colorAccentText,        int shadow,             int colorControlHighlight,
                                int colorHint,              int colorPrimaryTint,       int colorBackgroundTint,
                                int colorPrimaryCard,       int colorBackgroundCard,    int colorPrimaryCardText,
                                int colorBackgroundCardText,int colorPrimaryCardTint,   int colorBackgroundCardTint,

                                String themesname,          String themesinfo,
                                String themesauthor,        int themeversion            ) throws Exception {
        // Add new theme using given hex colors and generate new UUID

        addTheme(mContext, colorPrimary, colorPrimaryText, colorPrimaryDark, colorStatusbarTint, colorBackground,
                colorBackgroundText, colorAccent, colorAccentText, shadow, colorControlHighlight, colorHint,
                colorPrimaryTint, colorBackgroundTint, colorPrimaryCard, colorBackgroundCard,
                colorPrimaryCardText, colorBackgroundCardText, colorPrimaryCardTint, colorBackgroundCardTint,
                themesname, themesinfo, themesauthor, themeversion, UUID.randomUUID().toString());
    }

    public static void addTheme(Context mContext,           int colorPrimary,           int colorPrimaryText,   int colorPrimaryDark,
                                int colorStatusbarTint,     int colorBackground,        int colorBackgroundText,
                                int colorAccent,            int colorAccentText,        int shadow,             int colorControlHighlight,
                                int colorHint,              int colorPrimaryTint,       int colorBackgroundTint,
                                int colorPrimaryCard,       int colorBackgroundCard,    int colorPrimaryCardText,
                                int colorBackgroundCardText,int colorPrimaryCardTint,   int colorBackgroundCardTint,

                                String themesname,  String themesinfo,      String themesauthor,
                                int themeversion,   String UUIDvar                              ) throws Exception {

        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray =
                new Gson().fromJson(data.getString("themelists", ""),
                                    new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        if (indexUUID.contains(UUIDvar) || isExistInDefaultTheme(UUIDvar))
            throw new osthmException("Theme with same UUID is exist!");
        else {
            ArrayList<HashMap<String, Integer>> themearray = new ArrayList<>();

            themearray.add(addKeyToIntHashMap("colorPrimary",       colorPrimary)           );

            themearray.get(0).put("colorPrimaryText",               colorPrimaryText        );
            themearray.get(0).put("colorPrimaryDark",               colorPrimaryDark        );
            themearray.get(0).put("colorStatusbarTint",             colorStatusbarTint      );
            themearray.get(0).put("colorBackground",                colorBackground         );
            themearray.get(0).put("colorBackgroundText",            colorBackgroundText     );
            themearray.get(0).put("colorAccent",                    colorAccent             );
            themearray.get(0).put("colorAccentText",                colorAccentText         );
            themearray.get(0).put("shadow",                         shadow                  );
            themearray.get(0).put("colorControlHighlight",          colorControlHighlight   );
            themearray.get(0).put("colorHint",                      colorHint               );
            themearray.get(0).put("colorPrimaryTint",               colorPrimaryTint        );
            themearray.get(0).put("colorBackgroundTint",            colorBackgroundTint     );
            themearray.get(0).put("colorPrimaryCard",               colorPrimaryCard        );
            themearray.get(0).put("colorBackgroundCard",            colorBackgroundCard     );
            themearray.get(0).put("colorPrimaryCardText",           colorPrimaryCardText    );
            themearray.get(0).put("colorBackgroundCardText",        colorBackgroundCardText );
            themearray.get(0).put("colorPrimaryCardTint",           colorPrimaryCardTint    );
            themearray.get(0).put("colorBackgroundCardTint",        colorBackgroundCardTint );
            themearray.get(0).put("version", themesVersion);

            metadataarray.add(addKeyToHashMap("themesname", themesname));

            metadataarray.get(metadataarray.size() - 1).put("themesjson", new Gson().toJson(themearray));
            metadataarray.get(metadataarray.size() - 1).put("themesinfo",       themesinfo          );
            metadataarray.get(metadataarray.size() - 1).put("themesauthor",     themesauthor        );
            metadataarray.get(metadataarray.size() - 1).put("os-thm-version",   metadataVersion     );
            metadataarray.get(metadataarray.size() - 1).put("uuid",             UUIDvar             );
            metadataarray.get(metadataarray.size() - 1).put("theme-version",    themeversion        );

            data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
        }
    }

    public static void editTheme(Context mContext,           int colorPrimary,           int colorPrimaryText,   int colorPrimaryDark,
                                 int colorStatusbarTint,     int colorBackground,        int colorBackgroundText,
                                 int colorAccent,            int colorAccentText,        int shadow,             int colorControlHighlight,
                                 int colorHint,              int colorPrimaryTint,       int colorBackgroundTint,
                                 int colorPrimaryCard,       int colorBackgroundCard,    int colorPrimaryCardText,
                                 int colorBackgroundCardText,int colorPrimaryCardTint,   int colorBackgroundCardTint,

                                 String themesname,  String themesinfo,      String themesauthor,
                                 int themeversion,   String UUIDvar                              ) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(
                data.getString("themelists", ""),
                new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        if (indexUUID.contains(UUIDvar)) {
            if ((int) metadataarray.get(indexUUID.indexOf(UUIDvar)).get("os-thm-version") == metadataVersion) {
                ArrayList<HashMap<String, Integer>> themearray = new ArrayList<>();

                themearray.add(addKeyToIntHashMap("colorPrimary",       colorPrimary               ));
                themearray.get(0).put("colorPrimaryText",               colorPrimaryText            );
                themearray.get(0).put("colorPrimaryDark",               colorPrimaryDark            );
                themearray.get(0).put("colorStatusbarTint",             colorStatusbarTint          );
                themearray.get(0).put("colorBackground",                colorBackground             );
                themearray.get(0).put("colorBackgroundText",            colorBackgroundText         );
                themearray.get(0).put("colorAccent",                    colorAccent                 );
                themearray.get(0).put("colorAccentText",                colorAccentText             );
                themearray.get(0).put("shadow",                         shadow                      );
                themearray.get(0).put("colorControlHighlight",          colorControlHighlight       );
                themearray.get(0).put("colorHint",                      colorHint                   );
                themearray.get(0).put("colorPrimaryTint",               colorPrimaryTint            );
                themearray.get(0).put("colorBackgroundTint",            colorBackgroundTint         );
                themearray.get(0).put("colorPrimaryCard",               colorPrimaryCard            );
                themearray.get(0).put("colorBackgroundCard",            colorBackgroundCard         );
                themearray.get(0).put("colorPrimaryCardText",           colorPrimaryCardText        );
                themearray.get(0).put("colorBackgroundCardText",        colorBackgroundCardText     );
                themearray.get(0).put("colorPrimaryCardTint",           colorPrimaryCardTint        );
                themearray.get(0).put("colorBackgroundCardTint",        colorBackgroundCardTint     );
                themearray.get(0).put("version", themesVersion);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesname", themesname);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesjson", new Gson().toJson(themearray));
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesinfo", themesinfo);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesauthor", themesauthor);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("os-thm-version", metadataVersion);
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
            if ((int) metadataarray.get(indexUUID.indexOf(UUIDvar)).get("os-thm-version") == metadataVersion) {

                HashMap<String, Integer> thmjsn = new Gson().fromJson(
                        data.getString("themelists", ""),
                        new TypeToken<HashMap<String, Integer>>() {}.getType());

                if (thmjsn.get("version") == themesVersion)
                    data.edit().putString("currentTheme", UUIDvar).apply();

                else
                    throw new osthmException("Incompatible theme version!");
            } else
                throw new osthmException("Incompatible theme metadata version!");
        } else
            throw new osthmException("No matching theme with the given UUID!");
    }

    public static HashMap<String, Integer> getCurrentTheme(Context mContext) {
        initializeData(mContext);

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        ArrayList<HashMap<String, Integer>> arraythm = new Gson().fromJson(
                metadataarray.get(indexUUID.indexOf(data.getString("currentTheme", "")))
                             .get("themesjson").toString(),

                new TypeToken<ArrayList<HashMap<String, Integer>>>() {}.getType());

        return arraythm.get(0);
    }

    public static String getCurrentThemeUUID(Context mContext) {
        initializeData(mContext);

        return data.getString("currentTheme", "");
    }

    public static void importThemes(Context mContext, String json) throws Exception {
        initializeData(mContext);

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(
                data.getString("themelists", ""),
                new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add((String) metadataarray.get(indexUUID.size()).get("uuid"));

        ArrayList<HashMap<String, Object>> thmarray = new Gson().fromJson(
                json,
                new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        if (thmarray.size() > 0) {
            for (HashMap<String, Object> theme: thmarray) {
                if (theme.containsKey("os-thm-version")) {
                    if (theme.get("os-thm-version") instanceof Integer) {
                        if ((int) theme.get("os-thm-version") > metadataVersion) {
                            throw new osthmException("Sorry, this theme version is newer than the current theme engine can handle.");
                        } else {
                            if ((int) theme.get("os-thm-version") < metadataVersion) {
                                theme.putAll(migrateOldThemePrivate(theme));
                            } else {
                                ArrayList<HashMap<String, Object>> parsedthmjsn = new Gson().fromJson(theme.get("themesjson").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>() {
                                }.getType());
                                if (parsedthmjsn.get(0).containsKey("version"))
                                    if (parsedthmjsn.get(0).get("version") instanceof Integer) {
                                        if ((int) parsedthmjsn.get(0).get("version") > themesVersion) {
                                            throw new osthmException("Sorry, this theme version is newer than the current theme engine can handle.");
                                        } else if ((int) parsedthmjsn.get(0).get("version") < themesVersion)
                                            theme.putAll(migrateOldThemePrivate(theme));
                                        else
                                            theme.putAll(migrateOlderThemePrivate(theme));
                                    }
                            }
                        }
                    } else {
                        theme.putAll(migrateOlderThemePrivate(theme));
                    }
                } else {
                    theme.putAll(migrateOlderThemePrivate(theme));
                }
                if (indexUUID.contains(theme.get("uuid")) || isExistInDefaultTheme(theme.get("uuid").toString())) {
                    if ((int) theme.get("theme-version") > (int) metadataarray.get(indexUUID.indexOf(theme.get("uuid"))).get("theme-version") && !isExistInDefaultTheme(theme.get("uuid").toString())) {
                        metadataarray.set(indexUUID.indexOf(theme.get("uuid")), theme);
                    }
                    else {
                        throw new osthmException("Theme(s) can't be imported because the theme(s) are already exist!");
                    }
                } else {
                    metadataarray.add(theme);
                }
            }
            data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
        } else {
            throw new osthmException("This JSON things is empty, what do you hope for? ._.");
        }
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
        } else {
            throw new osthmException("There is no UUID given, what do you hope for? ._.");
        }
    }

    public static void removeTheme(Context mContext, String UUIDvar) throws Exception {
        initializeData(mContext);

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(
                data.getString("themelists", ""),
                new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        if (indexUUID.contains(UUIDvar)) {
            if (data.getString("currentTheme", "").equals(UUIDvar)) {
                throw new osthmException("Theme is in use!");
            }
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

        if (isExistInDefaultTheme(data.getString("currentTheme", "")))
            data.edit().putString("themelists", "[]").apply();
        else
            throw new osthmException("Theme is in use!");
    }

    private static HashMap<String, Object> addKeyToHashMap(String key, Object value) {
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put(key, value);
        return hashmap;
    }

    private static HashMap<String, Integer> addKeyToIntHashMap(String key, Integer value) {
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put(key, value);
        return hashmap;
    }
}
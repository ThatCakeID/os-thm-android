/*
 * Copyright ThatCakeID 2020
 *
 * Contributors:
 *  - リェンーゆく (ryenyuku) <teamworks1732@gmail.com> 2017 - present
 *  - Iyxan23 <nurihsanalghifari@gmail.com> 2019 - present
 *
 * NOTE: You shouldn't modify any codes in this engine yourself
 * as it might will conflicts with other themes.
 */
package tw.osthm;

import android.graphics.Color;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * <h1>os-thm Engine</h1>
 * os-thm Engine is a theme engine library for
 * creating, using, and managing os-thm theme
 * module file format
 * <p>
 *
 * @author ThatCakeID Team
 * @version 3.0.1
 * @since 2019
 */

public class osthmEngine {

    public static final int metadataVersion = 3;
    public static final String codename = "Cheese";

    /**
     * This method is used to initialize components used by the library
     */

    private static void initializeData() {
        osthmManager.init();
        if (!osthmManager.containsConf("currentTheme"))
            osthmManager.setConf("currentTheme", "default");
    }

    /**
     * This method is used to get list of themes, private method
     *
     * @return ListOfThemes
     */

    private static ArrayList<HashMap<String, Object>> getThemeListPrivate() {
        ArrayList<HashMap<String, Object>> metadataarray = osthmManager.getThemes();

        metadataarray.addAll(0, DefaultThemes.getDefaultThemes());

        return metadataarray;
    }

    /**
     * This method used to check if the requested theme UUID is exist in the defaultThemes entry
     *
     * @param themeUUID Requested Theme UUID
     * @return Does theme exist in the default theme
     */
    private static boolean isExistInDefaultTheme(String themeUUID) {
        for (HashMap<String, Object> theme : DefaultThemes.getDefaultThemes())
            if (theme.get("uuid").equals(themeUUID)) {
                return true;
            }

        return false;
    }

    /**
     * This method is used to convert older version theme of os-thm from v2
     * into the current os-thm version
     *
     * @param metadataarray Old Theme
     * @return Converted Theme (Usable Theme)
     */

    private static HashMap<String, Object> migrateOlderThemePrivate(HashMap<String, Object> metadataarray) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(new TypeToken<HashMap<String, Object>>() {
                }.getType(),
                new HashMapDeserializerFix());
        Gson gson = gsonBuilder.create();

        ArrayList<HashMap<String, Object>> oldTheme =
                gson.fromJson(metadataarray.get("themesjson").toString(),
                        new TypeToken<ArrayList<HashMap<String, Object>>>() {
                        }.getType());

        HashMap<String, Integer> newShinyFancyTheme = new HashMap<>();

        newShinyFancyTheme.put("colorPrimary", Color.parseColor(oldTheme.get(0).get("colorPrimary").toString()));
        newShinyFancyTheme.put("colorPrimaryDark", Color.parseColor(oldTheme.get(0).get("colorPrimaryDark").toString()));
        newShinyFancyTheme.put("colorStatusbarTint", Integer.valueOf(oldTheme.get(0).get("statusbarIcon").toString()));
        newShinyFancyTheme.put("colorBackground", Color.parseColor(oldTheme.get(0).get("colorBackground").toString()));
        newShinyFancyTheme.put("colorAccent", Color.parseColor(oldTheme.get(0).get("colorButton").toString()));
        newShinyFancyTheme.put("shadow", Integer.valueOf(oldTheme.get(0).get("shadow").toString()));
        newShinyFancyTheme.put("colorControlHighlight", Color.parseColor(oldTheme.get(0).get("colorRipple").toString()));
        newShinyFancyTheme.put("colorHint", Color.parseColor(oldTheme.get(0).get("colorHint").toString()));
        newShinyFancyTheme.put("colorPrimaryTint", Color.parseColor(oldTheme.get(0).get("colorPrimaryImage").toString()));
        newShinyFancyTheme.put("colorBackgroundTint", Color.parseColor(oldTheme.get(0).get("colorBackgroundImage").toString()));
        newShinyFancyTheme.put("colorPrimaryCard", Color.parseColor(oldTheme.get(0).get("colorPrimaryCard").toString()));
        newShinyFancyTheme.put("colorBackgroundCard", Color.parseColor(oldTheme.get(0).get("colorBackgroundCard").toString()));
        newShinyFancyTheme.put("colorPrimaryCardText", Color.parseColor(oldTheme.get(0).get("colorPrimaryCardText").toString()));
        newShinyFancyTheme.put("colorBackgroundCardText", Color.parseColor(oldTheme.get(0).get("colorBackgroundCardText").toString()));
        newShinyFancyTheme.put("colorPrimaryCardTint", Color.parseColor(oldTheme.get(0).get("colorPrimaryCardImage").toString()));
        newShinyFancyTheme.put("colorBackgroundCardTint", Color.parseColor(oldTheme.get(0).get("colorBackgroundCardImage").toString()));
        newShinyFancyTheme.put("colorPrimaryText", Color.parseColor(oldTheme.get(0).get("colorPrimaryText").toString()));
        newShinyFancyTheme.put("colorBackgroundText", Color.parseColor(oldTheme.get(0).get("colorBackgroundText").toString()));
        newShinyFancyTheme.put("colorAccentText", Color.parseColor(oldTheme.get(0).get("colorButtonText").toString()));
        newShinyFancyTheme.put("colorDialog", -1);
        newShinyFancyTheme.put("colorDialogText", -16777216);
        newShinyFancyTheme.put("colorDialogTint", -16777216);

        HashMap<String, Object> newmetadata = new HashMap<>();
        newmetadata.put("themesjson", new Gson().toJson(newShinyFancyTheme));
        newmetadata.put("os-thm-version", metadataVersion);
        newmetadata.put("uuid", UUID.randomUUID().toString());
        newmetadata.put("theme-version", 1);

        return newmetadata;
    }

    // Unfinished
    private static HashMap<String, Object> migrateOldThemePrivate(HashMap<String, Object> metadataarray) {
        return null;
    }

    /**
     * This method is used to get list of themes, public method
     *
     * @return List Of Themes
     */

    public static ArrayList<HashMap<String, Object>> getThemeList() {
        initializeData();

        return getThemeListPrivate();
    }

    // Unfinished
    public static void migrateOldTheme(String UUIDvar) {
        // Migrate specified old theme to newer version
        initializeData();
    }

    // Unfinished
    public static void migrateAllOldThemes() {
        // Migrate all old themes to newer version
        initializeData();
    }


    /**
     * This method is used to add theme into the theme list using the OsThmTheme Object
     * with randomly generated UUID
     *
     * @param themeData    OsThmTheme object
     * @param themeName    Theme name
     * @param themeInfo    Theme info/ description
     * @param themeAuthor  Theme Author
     * @param themeVersion Theme version
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(OsThmTheme themeData, String themeName, String themeInfo,
                                String themeAuthor, int themeVersion) throws osthmException {
        // Add new theme using given OsThmTheme Object and generate a random UUID

        addTheme(themeData.colorPrimary, themeData.colorPrimaryText, themeData.colorPrimaryDark, themeData.colorStatusbarTint, themeData.colorBackground,
                themeData.colorBackgroundText, themeData.colorAccent, themeData.colorAccentText, themeData.shadow, themeData.colorControlHighlight, themeData.colorHint,
                themeData.colorPrimaryTint, themeData.colorBackgroundTint, themeData.colorPrimaryCard, themeData.colorBackgroundCard,
                themeData.colorPrimaryCardText, themeData.colorBackgroundCardText, themeData.colorPrimaryCardTint, themeData.colorBackgroundCardTint, themeData.colorDialog,
                themeData.colorDialogText, themeData.colorDialogTint, themeName, themeInfo, themeAuthor, themeVersion, UUID.randomUUID().toString());
    }


    /**
     * This method is used to add theme into the theme list using the OsThmTheme Object
     * with defined UUID
     *
     * @param themeData    OsThmTheme object
     * @param themesname   Theme name
     * @param themesinfo   Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @param UUID         Theme UUID
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(OsThmTheme themeData, String themesname, String themesinfo,
                                String themesauthor, int themeversion, String UUID)
            throws osthmException {
        // Add new theme using given OsThmTheme Object

        addTheme(themeData.colorPrimary, themeData.colorPrimaryText, themeData.colorPrimaryDark, themeData.colorStatusbarTint, themeData.colorBackground,
                themeData.colorBackgroundText, themeData.colorAccent, themeData.colorAccentText, themeData.shadow, themeData.colorControlHighlight, themeData.colorHint,
                themeData.colorPrimaryTint, themeData.colorBackgroundTint, themeData.colorPrimaryCard, themeData.colorBackgroundCard,
                themeData.colorPrimaryCardText, themeData.colorBackgroundCardText, themeData.colorPrimaryCardTint, themeData.colorBackgroundCardTint, themeData.colorDialog,
                themeData.colorDialogText, themeData.colorDialogTint, themesname, themesinfo, themesauthor, themeversion, UUID);
    }

    /**
     * This method is used to add theme into the theme list using hex colors
     * and OsThmMetadata object
     *
     * @param colorPrimary            Primary Color
     * @param colorPrimaryText        Primary Text Color
     * @param colorPrimaryDark        Primary Dark Color
     * @param colorStatusbarTint      Statusbar Color
     * @param colorBackground         Background color for root
     * @param colorBackgroundText     Background color for text
     * @param colorAccent             Color Accent
     * @param colorAccentText         Color Accent for text
     * @param shadow                  Is shadow enabled
     * @param colorControlHighlight   Color on highlight
     * @param colorHint               Color Hint for EditText
     * @param colorPrimaryTint        Imageview tint color
     * @param colorBackgroundTint     Background Tint color
     * @param colorPrimaryCard        Card Color
     * @param colorBackgroundCard     Card Background Color
     * @param colorPrimaryCardText    Color for Text on card
     * @param colorBackgroundCardText Color for Text Background on card
     * @param colorPrimaryCardTint    Tint for imageview on card
     * @param colorBackgroundCardTint Tint for imageview on card
     * @param colorDialog             Background color for dialog & bottomsheet
     * @param colorDialogText         Text color for textview on dialog & bottomsheet
     * @param colorDialogTint         Tint color for imageview on dialog & bottomsheet
     * @param themeMetadata           OsThmMetadata object containing extra metadata
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                                int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                                int colorAccent, int colorAccentText, int shadow,
                                int colorControlHighlight, int colorHint, int colorPrimaryTint,
                                int colorBackgroundTint, int colorPrimaryCard, int colorBackgroundCard,
                                int colorPrimaryCardText, int colorBackgroundCardText, int colorPrimaryCardTint,
                                int colorBackgroundCardTint, int colorDialog, int colorDialogText,
                                int colorDialogTint, OsThmMetadata themeMetadata) throws osthmException {
        // Add new theme using given hex colors and OsThmMetadata object

        addTheme(colorPrimary, colorPrimaryText, colorPrimaryDark, colorStatusbarTint, colorBackground,
                colorBackgroundText, colorAccent, colorAccentText, shadow, colorControlHighlight, colorHint,
                colorPrimaryTint, colorBackgroundTint, colorPrimaryCard, colorBackgroundCard,
                colorPrimaryCardText, colorBackgroundCardText, colorPrimaryCardTint, colorBackgroundCardTint,
                colorDialog, colorDialogText, colorDialogTint, themeMetadata.themesname, themeMetadata.themesinfo,
                themeMetadata.themesauthor, themeMetadata.themeversion, themeMetadata.uuid);
    }

    /**
     * This method is used to add theme into the theme list, and generate a random uuid
     *
     * @param colorPrimary            Primary Color
     * @param colorPrimaryText        Primary Text Color
     * @param colorPrimaryDark        Primary Dark Color
     * @param colorStatusbarTint      Statusbar Color
     * @param colorBackground         Background color for root
     * @param colorBackgroundText     Background color for text
     * @param colorAccent             Color Accent
     * @param colorAccentText         Color Accent for text
     * @param shadow                  Is shadow enabled
     * @param colorControlHighlight   Color on highlight
     * @param colorHint               Color Hint for EditText
     * @param colorPrimaryTint        Imageview tint color
     * @param colorBackgroundTint     Background Tint color
     * @param colorPrimaryCard        Card Color
     * @param colorBackgroundCard     Card Background Color
     * @param colorPrimaryCardText    Color for Text on card
     * @param colorBackgroundCardText Color for Text Background on card
     * @param colorPrimaryCardTint    Tint for imageview on card
     * @param colorBackgroundCardTint Tint for imageview on card
     * @param colorDialog             Background color for dialog & bottomsheet
     * @param colorDialogText         Text color for textview on dialog & bottomsheet
     * @param colorDialogTint         Tint color for imageview on dialog & bottomsheet
     * @param themesname              Theme name
     * @param themesinfo              Theme info/ description
     * @param themesauthor            Theme Author
     * @param themeversion            Theme version
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                                int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                                int colorAccent, int colorAccentText, int shadow,
                                int colorControlHighlight, int colorHint, int colorPrimaryTint,
                                int colorBackgroundTint, int colorPrimaryCard, int colorBackgroundCard,
                                int colorPrimaryCardText, int colorBackgroundCardText, int colorPrimaryCardTint,
                                int colorBackgroundCardTint, int colorDialog, int colorDialogText,
                                int colorDialogTint,

                                String themesname, String themesinfo,
                                String themesauthor, int themeversion) throws osthmException {
        // Add new theme using given hex colors and generate new UUID

        addTheme(colorPrimary, colorPrimaryText, colorPrimaryDark, colorStatusbarTint, colorBackground,
                colorBackgroundText, colorAccent, colorAccentText, shadow, colorControlHighlight, colorHint,
                colorPrimaryTint, colorBackgroundTint, colorPrimaryCard, colorBackgroundCard,
                colorPrimaryCardText, colorBackgroundCardText, colorPrimaryCardTint, colorBackgroundCardTint,
                colorDialog, colorDialogText, colorDialogTint, themesname, themesinfo, themesauthor, themeversion,
                UUID.randomUUID().toString());
    }

    /**
     * This method is used to add theme into the theme list
     *
     * @param colorPrimary            Primary Color
     * @param colorPrimaryText        Primary Text Color
     * @param colorPrimaryDark        Primary Dark Color
     * @param colorStatusbarTint      Statusbar Color
     * @param colorBackground         Background color for root
     * @param colorBackgroundText     Background color for text
     * @param colorAccent             Color Accent
     * @param colorAccentText         Color Accent for text
     * @param shadow                  Is shadow enabled
     * @param colorControlHighlight   Color on highlight
     * @param colorHint               Color Hint for EditText
     * @param colorPrimaryTint        Imageview tint color
     * @param colorBackgroundTint     Background Tint color
     * @param colorPrimaryCard        Card Color
     * @param colorBackgroundCard     Card Background Color
     * @param colorPrimaryCardText    Color for Text on card
     * @param colorBackgroundCardText Color for Text Background on card
     * @param colorPrimaryCardTint    Tint for imageview on card
     * @param colorBackgroundCardTint Tint for imageview on card
     * @param colorDialog             Background color for dialog & bottomsheet
     * @param colorDialogText         Text color for textview on dialog & bottomsheet
     * @param colorDialogTint         Tint color for imageview on dialog & bottomsheet
     * @param themesname              Theme name
     * @param themesinfo              Theme info/ description
     * @param themesauthor            Theme Author
     * @param themeversion            Theme version
     * @param UUIDvar                 UUID for the Theme
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                                int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                                int colorAccent, int colorAccentText, int shadow,
                                int colorControlHighlight, int colorHint, int colorPrimaryTint,
                                int colorBackgroundTint, int colorPrimaryCard, int colorBackgroundCard,
                                int colorPrimaryCardText, int colorBackgroundCardText, int colorPrimaryCardTint,
                                int colorBackgroundCardTint, int colorDialog, int colorDialogText,
                                int colorDialogTint,

                                String themesname, String themesinfo, String themesauthor,
                                int themeversion, String UUIDvar) throws osthmException {

        initializeData();

        if (osthmManager.containsTheme(UUIDvar) || isExistInDefaultTheme(UUIDvar))
            throw new osthmException("Theme with same UUID is exist!");
        else {
            HashMap<String, Integer> themearray = new HashMap<>();

            themearray.put("colorPrimary", colorPrimary);
            themearray.put("colorPrimaryText", colorPrimaryText);
            themearray.put("colorPrimaryDark", colorPrimaryDark);
            themearray.put("colorStatusbarTint", colorStatusbarTint);
            themearray.put("colorBackground", colorBackground);
            themearray.put("colorBackgroundText", colorBackgroundText);
            themearray.put("colorAccent", colorAccent);
            themearray.put("colorAccentText", colorAccentText);
            themearray.put("shadow", shadow);
            themearray.put("colorControlHighlight", colorControlHighlight);
            themearray.put("colorHint", colorHint);
            themearray.put("colorPrimaryTint", colorPrimaryTint);
            themearray.put("colorBackgroundTint", colorBackgroundTint);
            themearray.put("colorPrimaryCard", colorPrimaryCard);
            themearray.put("colorBackgroundCard", colorBackgroundCard);
            themearray.put("colorPrimaryCardText", colorPrimaryCardText);
            themearray.put("colorBackgroundCardText", colorBackgroundCardText);
            themearray.put("colorPrimaryCardTint", colorPrimaryCardTint);
            themearray.put("colorBackgroundCardTint", colorBackgroundCardTint);
            themearray.put("colorDialog", colorDialog);
            themearray.put("colorDialogText", colorDialogText);
            themearray.put("colorDialogTint", colorDialogTint);

            HashMap<String, Object> metadataarray = new HashMap<>();

            metadataarray.put("themesjson", new Gson().toJson(themearray));

            metadataarray.put("themesname", themesname);
            metadataarray.put("themesinfo", themesinfo);
            metadataarray.put("themesauthor", themesauthor);
            metadataarray.put("os-thm-version", metadataVersion);
            metadataarray.put("uuid", UUIDvar);
            metadataarray.put("theme-version", themeversion);

            osthmManager.setTheme(metadataarray);
        }
    }

    /**
     * This method is used to edit theme using OsThmTheme Object
     *
     * @param themeData    OsThmTheme Object
     * @param themesname   Theme Name
     * @param themesinfo   Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @param UUIDvar      Requested Theme UUID that you want to edit
     * @throws osthmException Os-Thm Exception
     */

    public static void editTheme(OsThmTheme themeData, String themesname, String themesinfo,
                                 String themesauthor, int themeversion, String UUIDvar)
            throws osthmException {
        editTheme(themeData.toHashMap(), themesname, themesinfo, themesauthor, themeversion, UUIDvar);
    }

    /**
     * This method is used to edit theme
     *
     * @param colorPrimary            Primary Color
     * @param colorPrimaryText        Primary Text Color
     * @param colorPrimaryDark        Primary Dark Color
     * @param colorStatusbarTint      Statusbar Color
     * @param colorBackground         Background color for root
     * @param colorBackgroundText     Background color for text
     * @param colorAccent             Color Accent
     * @param colorAccentText         Color Accent for text
     * @param shadow                  Is shadow enabled
     * @param colorControlHighlight   Color on highlight
     * @param colorHint               Color Hint for EditText
     * @param colorPrimaryTint        Imageview tint color
     * @param colorBackgroundTint     Background Tint color
     * @param colorPrimaryCard        Card Color
     * @param colorBackgroundCard     Card Background Color
     * @param colorPrimaryCardText    Color for Text on card
     * @param colorBackgroundCardText Color for Text Background on card
     * @param colorPrimaryCardTint    Tint for imageview on card
     * @param colorBackgroundCardTint Tint for imageview on card
     * @param colorDialog             Background color for dialog & bottomsheet
     * @param colorDialogText         Text color for textview on dialog & bottomsheet
     * @param colorDialogTint         Tint color for imageview on dialog & bottomsheet
     * @param themesname              Theme name
     * @param themesinfo              Theme info/ description
     * @param themesauthor            Theme Author
     * @param themeversion            Theme version
     * @param UUIDvar                 Requested Theme UUID that you want to edit
     * @throws osthmException Os-Thm Exception
     */

    public static void editTheme(int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                                 int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                                 int colorAccent, int colorAccentText, int shadow,
                                 int colorControlHighlight, int colorHint, int colorPrimaryTint,
                                 int colorBackgroundTint, int colorPrimaryCard, int colorBackgroundCard,
                                 int colorPrimaryCardText, int colorBackgroundCardText, int colorPrimaryCardTint,
                                 int colorBackgroundCardTint, int colorDialog, int colorDialogText,
                                 int colorDialogTint,

                                 String themesname, String themesinfo, String themesauthor,
                                 int themeversion, String UUIDvar) throws osthmException {
        HashMap<String, Integer> themearray = new HashMap<>();

        themearray.put("colorPrimary", colorPrimary);
        themearray.put("colorPrimaryText", colorPrimaryText);
        themearray.put("colorPrimaryDark", colorPrimaryDark);
        themearray.put("colorStatusbarTint", colorStatusbarTint);
        themearray.put("colorBackground", colorBackground);
        themearray.put("colorBackgroundText", colorBackgroundText);
        themearray.put("colorAccent", colorAccent);
        themearray.put("colorAccentText", colorAccentText);
        themearray.put("shadow", shadow);
        themearray.put("colorControlHighlight", colorControlHighlight);
        themearray.put("colorHint", colorHint);
        themearray.put("colorPrimaryTint", colorPrimaryTint);
        themearray.put("colorBackgroundTint", colorBackgroundTint);
        themearray.put("colorPrimaryCard", colorPrimaryCard);
        themearray.put("colorBackgroundCard", colorBackgroundCard);
        themearray.put("colorPrimaryCardText", colorPrimaryCardText);
        themearray.put("colorBackgroundCardText", colorBackgroundCardText);
        themearray.put("colorPrimaryCardTint", colorPrimaryCardTint);
        themearray.put("colorBackgroundCardTint", colorBackgroundCardTint);
        themearray.put("colorDialog", colorDialog);
        themearray.put("colorDialogText", colorDialogText);
        themearray.put("colorDialogTint", colorDialogTint);

        editTheme(themearray, themesname, themesinfo, themesauthor, themeversion, UUIDvar);
    }

    /**
     * This method is used to edit theme using HashMap
     *
     * @param themeData    Theme Data stored in HashMap
     * @param themesname   Theme name
     * @param themesinfo   Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @param UUIDvar      Requested Theme UUID that you want to edit
     * @throws osthmException Os-Thm Exception
     */

    public static void editTheme(HashMap<String, Integer> themeData,
                                 String themesname, String themesinfo, String themesauthor,
                                 int themeversion, String UUIDvar) throws osthmException {
        initializeData();

        if (osthmManager.containsTheme(UUIDvar)) {
            HashMap<String, Object> metadataarray = new HashMap<>();
            metadataarray.put("themesjson", new Gson().toJson(themeData));
            metadataarray.put("themesname", themesname);
            metadataarray.put("themesinfo", themesinfo);
            metadataarray.put("themesauthor", themesauthor);
            metadataarray.put("os-thm-version", metadataVersion);
            metadataarray.put("uuid", UUIDvar);
            metadataarray.put("theme-version", themeversion);
            osthmManager.setTheme(metadataarray);
        } else {
            if (isExistInDefaultTheme(UUIDvar))
                throw new osthmException("You can't edit a default theme!");
            else
                throw new osthmException("Theme with given UUID doesn't exist!");
        }
    }

    /**
     * This method is used to get the current theme
     *
     * @return CurrentTheme
     */

    public static OsThmTheme getCurrentTheme() {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return new OsThmTheme((HashMap<String, Integer>)
                new Gson().fromJson(metadataarray.get(indexUUID.indexOf(osthmManager
                                .getConf("currentTheme", "default")))
                                .get("themesjson").toString(),
                        new TypeToken<HashMap<String, Integer>>() {
                        }.getType()));
    }

    /**
     * This method is used to apply current theme
     * using from the requested UUID
     *
     * @param UUIDvar Theme UUID
     * @throws osthmException osThmException
     */

    public static void setCurrentTheme(String UUIDvar) throws osthmException {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        if (indexUUID.contains(UUIDvar)) {
            if ((int) metadataarray.get(indexUUID.indexOf(UUIDvar))
                    .get("os-thm-version") == metadataVersion) {
                osthmManager.setConf("currentTheme", UUIDvar);
            } else
                throw new osthmException("Incompatible theme metadata version!");
        } else
            throw new osthmException("No matching theme with the given UUID!");
    }

    /**
     * This method is used to get the current theme as HashMap<String, Integer>
     *
     * @return CurrentTheme
     */

    public static HashMap<String, Integer> getCurrentThemeAsHashMap() {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return new Gson().fromJson(metadataarray.get(indexUUID.indexOf(osthmManager
                        .getConf("currentTheme", "default")))
                        .get("themesjson").toString(),
                new TypeToken<HashMap<String, Integer>>() {
                }.getType());
    }

    /**
     * This method is used to get the current theme metadata
     *
     * @return CurrentThemeMetadata
     */

    public static OsThmMetadata getCurrentThemeMetadata() {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return new OsThmMetadata(metadataarray.get(indexUUID.indexOf(osthmManager
                .getConf("currentTheme", "default"))));
    }

    /**
     * This method is used to get the current theme contents
     *
     * @return CurrentThemeContents
     */

    public static HashMap<String, Object> getCurrentThemeContents() {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return metadataarray.get(indexUUID.indexOf(osthmManager
                .getConf("currentTheme", "default")));
    }

    /**
     * This method is used to get the theme with specified UUID
     *
     * @param UUIDvar UUID
     * @return Theme
     */

    public static OsThmTheme getTheme(String UUIDvar) {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return new OsThmTheme((HashMap<String, Integer>)
                new Gson().fromJson(metadataarray.get(indexUUID.indexOf(UUIDvar))
                                .get("themesjson").toString(),
                        new TypeToken<HashMap<String, Integer>>() {
                        }.getType()));
    }

    /**
     * This method is used to get the theme as HashMap<String, Integer> with specified UUID
     *
     * @param UUIDvar UUID
     * @return Theme
     */

    public static HashMap<String, Integer> getThemeAsHashMap(String UUIDvar) {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return new Gson().fromJson(metadataarray.get(indexUUID.indexOf(UUIDvar))
                        .get("themesjson").toString(),
                new TypeToken<HashMap<String, Integer>>() {
                }.getType());
    }

    /**
     * This method is used to get the theme metadata with specified UUID
     *
     * @param UUIDvar UUID
     * @return ThemeMetadata
     */

    public static OsThmMetadata getThemeMetadata(String UUIDvar) {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return new OsThmMetadata(metadataarray.get(indexUUID.indexOf(UUIDvar)));
    }

    /**
     * This method is used to get the theme contents with specified UUID
     *
     * @param UUIDvar UUID
     * @return ThemeContents
     */

    public static HashMap<String, Object> getThemeContents(String UUIDvar) {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return metadataarray.get(indexUUID.indexOf(UUIDvar));
    }

    /**
     * This method is used to get the current theme UUID
     *
     * @return String of the current theme UUID
     */

    public static String getCurrentThemeUUID() {
        initializeData();

        return osthmManager.getConf("currentTheme", "default");
    }

    /**
     * This method is used to import
     * theme and save it
     *
     * @param json Theme in string JSON
     * @throws osthmException osThmException
     */

    public static void importThemes(String json) throws osthmException {
        initializeData();

        GsonBuilder gsonBuilder = new GsonBuilder();

        if (isArrayJSONValid(json)) {
            gsonBuilder.registerTypeAdapter(new TypeToken<ArrayList<HashMap<String, Object>>>() {
                    }.getType(),
                    new ArrayMapDeserializerFix());

            Gson gson = gsonBuilder.create();

            ArrayList<HashMap<String, Object>> thmarray = gson.fromJson(
                    json,
                    new TypeToken<ArrayList<HashMap<String, Object>>>() {
                    }.getType());

            if (thmarray.size() > 0) {
                for (HashMap<String, Object> theme : thmarray) {
                    if (theme.containsKey("os-thm-version")) {
                        if (theme.get("os-thm-version") instanceof Integer) {
                            if ((int) theme.get("os-thm-version") > metadataVersion) {
                                throw new osthmException("Sorry, this theme version is newer than the current theme engine can handle.");
                            } else {
                                if ((int) theme.get("os-thm-version") < metadataVersion) {
                                    theme.putAll(migrateOldThemePrivate(theme));
                                }
                            }
                        } else {
                            theme.putAll(migrateOlderThemePrivate(theme));
                        }
                    } else {
                        theme.putAll(migrateOlderThemePrivate(theme));
                    }
                    if (isExistInDefaultTheme(theme.get("uuid").toString())) {
                        throw new osthmException("Theme(s) can't be imported because the theme(s) are already exist!");
                    } else {
                        osthmManager.setTheme(theme);
                    }
                }
            } else {
                throw new osthmException("This JSON things is empty, what do you hope for? ._.");
            }
        } else if (isObjectJSONValid(json)) {
            gsonBuilder.registerTypeAdapter(new TypeToken<HashMap<String, Object>>() {
                    }.getType(),
                    new HashMapDeserializerFix());

            Gson gson = gsonBuilder.create();

            HashMap<String, Object> theme = gson.fromJson(
                    json,
                    new TypeToken<HashMap<String, Object>>() {
                    }.getType());

            if (theme.containsKey("os-thm-version")) {
                if (theme.get("os-thm-version") instanceof Integer) {
                    if ((int) theme.get("os-thm-version") > metadataVersion) {
                        throw new osthmException("Sorry, this theme version is newer than the current theme engine can handle.");
                    } else {
                        if ((int) theme.get("os-thm-version") < metadataVersion) {
                            theme.putAll(migrateOldThemePrivate(theme));
                        }
                    }
                } else {
                    theme.putAll(migrateOlderThemePrivate(theme));
                }
            } else {
                theme.putAll(migrateOlderThemePrivate(theme));
            }
            if (osthmManager.containsTheme(theme.get("uuid").toString()) || isExistInDefaultTheme(theme.get("uuid").toString())) {
                throw new osthmException("Theme(s) can't be imported because the theme(s) are already exist!");
            } else {
                osthmManager.setTheme(theme);
            }
        } else
            throw new osthmException("Invalid JSON!");
    }

    /**
     * This method is used to export themes as JSON string
     *
     * @param UUIDvars List of themes UUID that you want to export
     * @return Exported theme as JSON format
     * @throws osthmException osthmException
     */

    public static String exportThemes(String[] UUIDvars) throws osthmException {
        if (UUIDvars.length > 0) {
            initializeData();

            ArrayList<String> indexUUID = new ArrayList<>();
            ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

            for (int i = 0; i < metadataarray.size(); i++)
                indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

            if (UUIDvars.length > 1) {
                ArrayList<HashMap<String, Object>> thmarray = new ArrayList<>();

                for (int i = 0; i < UUIDvars.length; i++) {
                    if (indexUUID.contains(UUIDvars[i])) {
                        thmarray.add(metadataarray.get(indexUUID.indexOf(UUIDvars[i])));
                    } else
                        throw new osthmException("Theme(s) aren't exists!");
                }
                return new Gson().toJson(thmarray);
            } else {
                return new Gson().toJson(metadataarray.get(indexUUID.indexOf(UUIDvars[0])));
            }

        } else {
            throw new osthmException("There is no UUID given, what do you hope for? ._.");
        }
    }

    /**
     * This method removes a theme specified by the given UUID
     *
     * @param UUIDvar UUID of a theme that will be deleted
     * @throws osthmException osthmException
     */

    public static void removeTheme(String UUIDvar) throws osthmException {
        initializeData();

        if (osthmManager.containsTheme(UUIDvar)) {
            if (osthmManager.getConf("currentTheme", "default").equals(UUIDvar)) {
                throw new osthmException("Theme is in use!");
            } else {
                osthmManager.removeTheme(UUIDvar);
            }
        } else {
            if (isExistInDefaultTheme(UUIDvar))
                throw new osthmException("You can't delete a default theme!");
            else
                throw new osthmException("Theme with given UUID isn't exist!");
        }
    }

    /**
     * This method will clear all of your themes
     *
     * @throws osthmException osthmException
     */

    public static void removeAllThemes() throws Exception {
        initializeData();

        if (isExistInDefaultTheme(osthmManager.getConf("currentTheme", "default")))
            osthmManager.clearThemes();
        else
            throw new osthmException("Theme is in use!");
    }

    // Utilites
    // =============================================================================================

    private static boolean isArrayJSONValid(String test) {
        try {
            new JSONArray(test);
        } catch (JSONException ex1) {
            return false;
        }
        return true;
    }

    private static boolean isObjectJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    /**
     * This method converts ARGB colors to HEX code
     * from given ARGB Integer value. Used as Util in
     * osthm
     *
     * @param a Alpha color value
     * @param r Red color value
     * @param g Green color value
     * @param b Blue color value
     * @return String containing hex color code
     */

    public static String argbToHex(int a, int r, int g, int b) {
        if (a != 255) {
            return String.format("#%02x%02x%02x%02x", a, r, g, b).toUpperCase();
        } else {
            return String.format("#%02x%02x%02x", r, g, b).toUpperCase();
        }
    }

    /**
     * This method converts Integer color to HEX code
     * from a given Integer value. Used as Util in
     * osthm
     *
     * @param color Integer color
     * @return String containing hex color code
     */

    public static String colorToHex(int color) {
        return argbToHex(Color.alpha(color),
                Color.red(color),
                Color.green(color),
                Color.blue(color));
    }
}
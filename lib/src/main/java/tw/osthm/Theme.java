package tw.osthm;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

/**
 * <h1>Theme object model</h1>
 * This theme object model is used to
 * represent a theme in an object
 *
 * @author Iyxan23
 * @since 2020
 * @version 1.0
 */

public class Theme {
    Context mContext;
    public int colorPrimary = -14575885;
    public int colorPrimaryText = -1;
    public int colorPrimaryDark = -15242838;
    public int colorStatusbarTint = 1;
    public int colorBackground = -1;
    public int colorBackgroundText = -16777216;
    public int colorAccent = -720809;
    public int colorAccentText = -1;
    public int shadow = 1;
    public int colorControlHighlight = 1073741824;
    public int colorHint = -5723992;
    public int colorPrimaryTint = -1;
    public int colorBackgroundTint = -14575885;
    public int colorPrimaryCard = -1;
    public int colorBackgroundCard = 0xFFFFFF;
    public int colorPrimaryCardText = -16777216;
    public int colorBackgroundCardText = -16777216;
    public int colorPrimaryCardTint = -16777216;
    public int colorBackgroundCardTint = -16777216;

    public String themesname;
    public String themesinfo;
    public String themesauthor;
    public int themeversion;

    /**
     * Constructor with JSON as String
     * @param mContext Context
     * @param json_string JSON as String
     */
    public Theme(Context mContext, String json_string) {
        this.mContext = mContext;
        HashMap<String, Object> json_data = new Gson().fromJson(
                json_string, new TypeToken<HashMap<String, Object>>() {}.getType()
        );

        this.setData(json_data);
    }

    /**
     * Constructor with HashMap as data
     * @param mContext Context
     * @param data Theme Data
     */
    public Theme(Context mContext, HashMap<String, Object> data) {
        this.mContext = mContext;

        this.setData(data);
    }

    /**
     * Constructor with default theme
     * @param mContext Context
     */
    public Theme(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Constructor with manually picked colors
     * @param mContext Context
     * @param colorPrimary Primary Color
     * @param colorPrimaryText Primary Text Color
     * @param colorPrimaryDark Primary Dark Color
     * @param colorStatusbarTint Statusbar Color
     * @param colorBackground Background color for root
     * @param colorBackgroundText Background color for text
     * @param colorAccent Color Accent
     * @param colorAccentText Color Accent for text
     * @param shadow Is shadow enabled
     * @param colorControlHighlight Color on highlight
     * @param colorHint Color Hint for EditText
     * @param colorPrimaryTint Imageview tint color
     * @param colorBackgroundTint Background Tint color
     * @param colorPrimaryCard Card Color
     * @param colorBackgroundCard Card Background Color
     * @param colorPrimaryCardText Color for Text on card
     * @param colorBackgroundCardText Color for Text Background on card
     * @param colorPrimaryCardTint Tint for imageview on card
     * @param colorBackgroundCardTint Background color for card tint
     * @param themesname Theme name
     * @param themesinfo Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     */
    public Theme(Context mContext, int colorPrimary, int colorPrimaryText,
                 int colorPrimaryDark, int colorStatusbarTint, int colorBackground,
                 int colorBackgroundText, int colorAccent, int colorAccentText,
                 int shadow, int colorControlHighlight, int colorHint,
                 int colorPrimaryTint, int colorBackgroundTint, int colorPrimaryCard,
                 int colorBackgroundCard, int colorPrimaryCardText,  int colorBackgroundCardText,
                 int colorPrimaryCardTint, int colorBackgroundCardTint,

                 String themesname, String themesinfo,
                 String themesauthor, int themeversion) {

        this.mContext = mContext;
        this.colorPrimary = colorPrimary;
        this.colorPrimaryText = colorPrimaryText;
        this.colorPrimaryDark = colorPrimaryDark;
        this.colorStatusbarTint = colorStatusbarTint;
        this.colorBackground = colorBackground;
        this.colorBackgroundText = colorBackgroundText;
        this.colorAccent = colorAccent;
        this.colorAccentText = colorAccentText;
        this.shadow = shadow;
        this.colorControlHighlight = colorControlHighlight;
        this.colorHint = colorHint;
        this.colorPrimaryTint = colorPrimaryTint;
        this.colorBackgroundTint = colorBackgroundTint;
        this.colorPrimaryCard = colorPrimaryCard;
        this.colorBackgroundCard = colorBackgroundCard;
        this.colorPrimaryCardText = colorPrimaryCardText;
        this.colorBackgroundCardText = colorBackgroundCardText;
        this.colorPrimaryCardTint = colorPrimaryCardTint;
        this.colorBackgroundCardTint = colorBackgroundCardTint;
        this.themesname = themesname;
        this.themesinfo = themesinfo;
        this.themesauthor = themesauthor;
        this.themeversion = themeversion;
    }

    /**
     * Set theme using HashMap
     * @param data Theme HashMap
     */
    public void setData(HashMap<String, Object> data) {
        this.colorPrimary = (int) data.get("colorPrimary");
        this.colorPrimaryText = (int) data.get("colorPrimaryText");
        this.colorPrimaryDark = (int) data.get("colorPrimaryDark");
        this.colorStatusbarTint = (int) data.get("colorStatusbarTint");
        this.colorBackground = (int) data.get("colorBackground");
        this.colorBackgroundText = (int) data.get("colorBackgroundText");
        this.colorAccent = (int) data.get("colorAccent");
        this.colorAccentText = (int) data.get("colorAccentText");
        this.shadow = (int) data.get("shadow");
        this.colorControlHighlight = (int) data.get("colorControlHighlight");
        this.colorHint = (int) data.get("colorHint");
        this.colorPrimaryTint = (int) data.get("colorPrimaryTint");
        this.colorBackgroundTint = (int) data.get("colorBackgroundTint");
        this.colorPrimaryCard = (int) data.get("colorPrimaryCard");
        this.colorBackgroundCard = (int) data.get("colorBackgroundCard");
        this.colorPrimaryCardText = (int) data.get("colorPrimaryCardText");
        this.colorBackgroundCardText = (int) data.get("colorBackgroundCardText");
        this.colorPrimaryCardTint = (int) data.get("colorPrimaryCardTint");
        this.colorBackgroundCardTint = (int) data.get("colorBackgroundCardTint");
        this.themesname = (String) data.get("themesname");
        this.themesinfo = (String) data.get("themesinfo");
        this.themesauthor = (String) data.get("themesauthor");
        this.themeversion = (int) data.get("themeversion");
    }

    /**
     * This method is used to convert
     * this theme into a HashMap
     * @return Theme HashMap
     */
    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("colorPrimary", colorPrimary);
        result.put("colorPrimaryText", colorPrimaryText);
        result.put("colorPrimaryDark", colorPrimaryDark);
        result.put("colorStatusbarTint", colorStatusbarTint);
        result.put("colorBackground", colorBackground);
        result.put("colorBackgroundText", colorBackgroundText);
        result.put("colorAccent", colorAccent);
        result.put("colorAccentText", colorAccentText);
        result.put("shadow", shadow);
        result.put("colorControlHighlight", colorControlHighlight);
        result.put("colorHint", colorHint);
        result.put("colorPrimaryTint", colorPrimaryTint);
        result.put("colorBackgroundTint", colorBackgroundTint);
        result.put("colorPrimaryCard", colorPrimaryCard);
        result.put("colorBackgroundCard", colorBackgroundCard);
        result.put("colorPrimaryCardText", colorPrimaryCardText);
        result.put("colorBackgroundCardText", colorBackgroundCardText);
        result.put("colorPrimaryCardTint", colorPrimaryCardTint);
        result.put("colorBackgroundCardTint", colorBackgroundCardTint);
        result.put("themesname", themesname);
        result.put("themesinfo", themesinfo);
        result.put("themesauthor", themesauthor);
        result.put("themeversion", themeversion);
        return result;
    }

    /**
     * Convert this theme into a
     * json string
     * @return JSON String
     */
    public String toJsonString() {
        /*  // Easier Version:
            HashMap<String, Object> result = this.toHashMap();
            Gson gson = new Gson();
            return gson.toJson(result)
         */

        // Faster version:
        return new Gson().toJson(this.toHashMap());
    }
}

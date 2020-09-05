package tw.osthm;

import android.content.Context;

import com.google.gson.Gson;

import java.util.HashMap;

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
    public int colorBackgroundCard;
    public int colorPrimaryCardText = -16777216;
    public int colorBackgroundCardText = -16777216;
    public int colorPrimaryCardTint = -16777216;
    public int colorBackgroundCardTint = -16777216;

    public String themesname;
    public String themesinfo;
    public String themesauthor;
    public int themeversion;

    public Theme(Context mContext) {
        this.mContext = mContext;
    }

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

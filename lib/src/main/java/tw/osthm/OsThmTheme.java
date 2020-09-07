package tw.osthm;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

/**
 * <h1>Theme colors object model</h1>
 * This theme object model is used to
 * represent a theme colors in an object
 *
 * @author ThatCakeID Team
 * @since 2020
 * @version 1.0
 */

public class OsThmTheme {
    Context mContext;

    public int colorPrimary             = -14575885 ;
    public int colorPrimaryText         = -1        ;
    public int colorPrimaryDark         = -15242838 ;
    public int colorStatusbarTint       = 1         ;
    public int colorBackground          = -1        ;
    public int colorBackgroundText      = -16777216 ;
    public int colorAccent              = -720809   ;
    public int colorAccentText          = -1        ;
    public int shadow                   = 1         ;
    public int colorControlHighlight    = 1073741824;
    public int colorHint                = -5723992  ;
    public int colorPrimaryTint         = -1        ;
    public int colorBackgroundTint      = -14575885 ;
    public int colorPrimaryCard         = -1        ;
    public int colorBackgroundCard      = 0xFFFFFF  ;
    public int colorPrimaryCardText     = -16777216 ;
    public int colorBackgroundCardText  = -16777216 ;
    public int colorPrimaryCardTint     = -16777216 ;
    public int colorBackgroundCardTint  = -16777216 ;

    /**
     * Constructor with default theme (Vanilla Theme)
     * @param mContext Context
     */
    public OsThmTheme(@NonNull Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Constructor with JSON as String
     * @param mContext Context
     * @param json_string JSON as String
     */
    public OsThmTheme(@NonNull Context mContext, @NonNull String json_string) throws osthmException {
        this.mContext = mContext;
        HashMap<String, Integer> json_data = new Gson().fromJson(
                json_string, new TypeToken<HashMap<String, Object>>() {}.getType()
        );

        this.setData(json_data);
    }

    /**
     * Constructor with HashMap as data
     * @param mContext Context
     * @param data Theme Data
     */
    public OsThmTheme(@NonNull Context mContext, @NonNull HashMap<String, Integer> data) throws osthmException {
        this.mContext = mContext;

        this.setData(data);
    }

    /**
     * Set theme using HashMap
     * @param data Theme HashMap
     */
    public void setData(@NonNull HashMap<String, Integer> data) throws osthmException {
        try {
            this.colorPrimary = data.get("colorPrimary");
            this.colorPrimaryText = data.get("colorPrimaryText");
            this.colorPrimaryDark = data.get("colorPrimaryDark");
            this.colorStatusbarTint = data.get("colorStatusbarTint");
            this.colorBackground = data.get("colorBackground");
            this.colorBackgroundText = data.get("colorBackgroundText");
            this.colorAccent = data.get("colorAccent");
            this.colorAccentText = data.get("colorAccentText");
            this.shadow = data.get("shadow");
            this.colorControlHighlight = data.get("colorControlHighlight");
            this.colorHint = data.get("colorHint");
            this.colorPrimaryTint = data.get("colorPrimaryTint");
            this.colorBackgroundTint = data.get("colorBackgroundTint");
            this.colorPrimaryCard = data.get("colorPrimaryCard");
            this.colorBackgroundCard = data.get("colorBackgroundCard");
            this.colorPrimaryCardText = data.get("colorPrimaryCardText");
            this.colorBackgroundCardText = data.get("colorBackgroundCardText");
            this.colorPrimaryCardTint = data.get("colorPrimaryCardTint");
            this.colorBackgroundCardTint = data.get("colorBackgroundCardTint");
        } catch (NullPointerException e) {
            throw new osthmException("Inserted theme data corrupted");
        }
    }

    @NonNull
    public HashMap<String, Integer> toHashMap() {
        HashMap<String, Integer> result = new HashMap<>();
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
        return result;
    }

    @NonNull
    public String toJsonString() {
        /*  // Easier-to-understand Version:
            HashMap<String, Object> result = this.toHashMap();
            Gson gson = new Gson();
            return gson.toJson(result)
         */

        // Less-space version:
        return new Gson().toJson(this.toHashMap());
    }
}
package tw.osthm;

import android.content.Context;

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

public class osthmModel {
    Context mContext;
    public int colorPrimary;
    public int colorPrimaryText;
    public int colorPrimaryDark;
    public int colorStatusbarTint;
    public int colorBackground;
    public int colorBackgroundText;
    public int colorAccent;
    public int colorAccentText;
    public int shadow;
    public int colorControlHighlight;
    public int colorHint;
    public int colorPrimaryTint;
    public int colorBackgroundTint;
    public int colorPrimaryCard;
    public int colorBackgroundCard;
    public int colorPrimaryCardText;
    public int colorBackgroundCardText;
    public int colorPrimaryCardTint;
    public int colorBackgroundCardTint;

    public osthmModel(Context mContext) {
        this.mContext = mContext;
        HashMap<String, Integer> json_data = osthmEngine.getCurrentTheme(mContext);

        this.setData(json_data);
    }

    /**
     * Constructor with JSON as String
     * @param mContext Context
     * @param json_string JSON as String
     */
    public osthmModel(Context mContext, String json_string) {
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
    public osthmModel(Context mContext, HashMap<String, Integer> data) {
        this.mContext = mContext;

        this.setData(data);
    }

    /**
     * Set theme using HashMap
     * @param data Theme HashMap
     */
    public void setData(HashMap<String, Integer> data) {
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
    }


}
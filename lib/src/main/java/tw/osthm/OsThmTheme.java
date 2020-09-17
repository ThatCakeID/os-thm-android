package tw.osthm;

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
 * @version 1.0
 * @since 2020
 */

public class OsThmTheme {
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

    /**
     * Empty constructor with default theme (Vanilla Theme)
     *
     */
    public OsThmTheme() {
    }

    /**
     * Constructor with JSON as String
     *
     * @param json_string JSON as String
     */
    public OsThmTheme(@NonNull String json_string) {
        HashMap<String, Integer> json_data = new Gson().fromJson(
                json_string, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );

        this.setData(json_data);
    }

    /**
     * Constructor with HashMap as data
     *
     * @param data     Theme Data
     */
    public OsThmTheme(@NonNull HashMap<String, Integer> data) {
        this.setData(data);
    }

    /**
     * Constructor with int values as data
     *
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
     */

    public OsThmTheme(int colorPrimary, int colorPrimaryText, int colorPrimaryDark, int colorStatusbarTint, int colorBackground, int colorBackgroundText, int colorAccent, int colorAccentText, int shadow, int colorControlHighlight, int colorHint, int colorPrimaryTint, int colorBackgroundTint, int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText, int colorBackgroundCardText, int colorPrimaryCardTint, int colorBackgroundCardTint) {
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
    }

    /**
     * Set theme using HashMap
     *
     * @param data Theme HashMap
     */
    public void setData(@NonNull HashMap<String, Integer> data) {
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
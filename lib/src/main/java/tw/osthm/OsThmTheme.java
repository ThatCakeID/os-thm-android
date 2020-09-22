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
    public int colorDialog;
    public int colorDialogText;
    public int colorDialogTint;

    /**
     * Constructor with default theme
     *
     */
    public OsThmTheme() {
        HashMap<String, Integer> data = new Gson().fromJson(DefaultThemes.getDefaultThemes()
                .get(0).get("themesjson").toString(),
                new TypeToken<HashMap<String, Object>>(){}.getType());
        setData(data);
    }

    /**
     * Constructor with JSON as String
     *
     * @param json_string JSON as String
     */
    public OsThmTheme(@NonNull String json_string) {
        HashMap<String, Integer> json_data = new Gson().fromJson(
                json_string, new TypeToken<HashMap<String, Integer>>() {
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
     * @param colorBackgroundCardTint Tint for imageview on card
     * @param colorDialog Background color for dialog & bottomsheet
     * @param colorDialogText Text color for textview on dialog & bottomsheet
     * @param colorDialogTint Tint color for imageview on dialog & bottomsheet
     */

    public OsThmTheme(int colorPrimary, int colorPrimaryText, int colorPrimaryDark, int colorStatusbarTint, int colorBackground, int colorBackgroundText, int colorAccent, int colorAccentText, int shadow, int colorControlHighlight, int colorHint, int colorPrimaryTint, int colorBackgroundTint, int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText, int colorBackgroundCardText, int colorPrimaryCardTint, int colorBackgroundCardTint, int colorDialog, int colorDialogText, int colorDialogTint) {
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
        this.colorDialog = colorDialog;
        this.colorDialogText = colorDialogText;
        this.colorDialogTint = colorDialogTint;
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
        this.colorDialog = data.get("colorDialog");
        this.colorDialogText = data.get("colorDialogText");
        this.colorDialogTint = data.get("colorDialogTint");
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
        result.put("colorDialog", colorDialog);
        result.put("colorDialogText", colorDialogText);
        result.put("colorDialogTint", colorDialogTint);
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
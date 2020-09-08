package tw.osthm.ui.fragments;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;

import tw.osthm.R;
import tw.osthm.osthmEngine;

import static tw.osthm.ui.ThemeEditorActivity.COLOR_ACCENT_TEXT_DIALOG_ID;
import static tw.osthm.ui.ThemeEditorActivity.COLOR_BACKGROUND_DIALOG_ID;
import static tw.osthm.ui.ThemeEditorActivity.COLOR_BACKGROUND_TEXT_DIALOG_ID;
import static tw.osthm.ui.ThemeEditorActivity.COLOR_CONTROL_HIGHLIGHT_DIALOG_ID;
import static tw.osthm.ui.ThemeEditorActivity.COLOR_HINT_DIALOG_ID;
import static tw.osthm.ui.ThemeEditorActivity.COLOR_PRIMARY_DIALOG_ID;
import static tw.osthm.ui.ThemeEditorActivity.COLOR_PRIMARY_TEXT_DIALOG_ID;

public class FragmentThmEdit2 extends Fragment {

    // Color Cards
    private ConstraintLayout    constraint_color_background_text, constraint_color_background   , constraint_color_primary_text , constraint_color_accent_text  , constraint_color_hint , constraint_color_control_highlight;

    // Color Texts
    private TextView            text_color_background_text      , text_color_background         , text_color_primary_text       , text_color_accent_text        , text_color_hint       , text_color_control_highlight      ;
    
    // Subtitles
    private TextView            sub_color_background_text       , sub_color_background          , sub_color_primary_text         , sub_color_accent_text         , sub_color_hint        , sub_color_control_highlight       ;

    // Pick Color Icon
    private ImageView           image_color_background_text     , image_color_background        , image_color_primary_text      , image_color_accent_text       , image_color_hint      , image_color_control_highlight     ;

    // Enable Shadow
    private CheckBox enable_shadow;

    // Statusbar tint
    private ConstraintLayout constraint_black;
    private ConstraintLayout constraint_white;
    private ImageView selected_black;
    private ImageView selected_white;

    // Other variables
    SharedPreferences sp;

    // Demo Views
    private ConstraintLayout color_primary_app_bar;
    private ConstraintLayout color_primary_dark_status_bar;
    private ConstraintLayout fake_edit_text_accent;
    private ConstraintLayout background;
    private TextView fake_edit_text_hint, text_demo;
    private ImageView statusbar_icon1, statusbar_icon2, statusbar_icon3;
    private TextView statusbar_text;
    private FloatingActionButton fab;
    private TextView app_bar_title;
    
    // Default Colors
    private final int default_color_background_text = -16777216;
    private final int default_color_background = -1;
    private final int default_color_primary_text = -1;
    private final int default_color_accent_text = -1;
    private final int default_color_hint = -5723992;
    private final int default_color_control_highlight = 1073741824;

    public FragmentThmEdit2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thm_edit2, container, false);

        // Assign views ============================================================================
        // ConstraintLayouts/ Cards
        constraint_color_background_text = root.findViewById(R.id.constraint_colorBackgroundText);
        constraint_color_background = root.findViewById(R.id.constraint_colorBackground);
        constraint_color_primary_text = root.findViewById(R.id.constraint_colorPrimaryText);
        constraint_color_accent_text = root.findViewById(R.id.constraint_colorAccentText);
        constraint_color_hint = root.findViewById(R.id.constraint_colorHint);
        constraint_color_control_highlight = root.findViewById(R.id.constraint_colorControlHighlight);

        // Texts
        text_color_background_text = root.findViewById(R.id.text_colorBackgroundText);
        text_color_background = root.findViewById(R.id.text_colorBackground);
        text_color_primary_text = root.findViewById(R.id.text_colorPrimaryText);
        text_color_accent_text = root.findViewById(R.id.text_colorAccentText);
        text_color_hint = root.findViewById(R.id.text_colorHint);
        text_color_control_highlight = root.findViewById(R.id.text_colorControlHighlight);
        
        // Subtitles
        sub_color_background_text = root.findViewById(R.id.text_clr_colorBackgroundText);
        sub_color_background = root.findViewById(R.id.text_clr_colorBackground);
        sub_color_primary_text = root.findViewById(R.id.text_clr_colorPrimaryText);
        sub_color_accent_text = root.findViewById(R.id.text_clr_colorAccentText);
        sub_color_hint = root.findViewById(R.id.text_clr_colorHint);
        sub_color_control_highlight = root.findViewById(R.id.text_clr_colorControlHighlight);

        // Images
        image_color_background_text = root.findViewById(R.id.image_colorBackgroundText);
        image_color_background = root.findViewById(R.id.image_colorBackground);
        image_color_primary_text = root.findViewById(R.id.image_colorPrimaryText);
        image_color_accent_text = root.findViewById(R.id.image_colorAccentText);
        image_color_hint = root.findViewById(R.id.image_colorHint);
        image_color_control_highlight = root.findViewById(R.id.image_colorControlHighlight);

        // Demo views
        color_primary_app_bar = root.findViewById(R.id.view_colorPrimary);
        color_primary_dark_status_bar = root.findViewById(R.id.view_colorPrimaryDark);
        statusbar_icon1 = root.findViewById(R.id.statusbar_icon_fragment2_1);
        statusbar_text = root.findViewById(R.id.statusbar_text_fragment2_2);
        statusbar_icon2 = root.findViewById(R.id.statusbar_icon_fragment2_3);
        statusbar_icon3 = root.findViewById(R.id.statusbar_icon_fragment2_4);
        fab = root.findViewById(R.id.fab);
        text_demo = root.findViewById(R.id.view_colorBackgroundText);
        fake_edit_text_accent = root.findViewById(R.id.view_colorAccentET);
        fake_edit_text_hint = root.findViewById(R.id.view_colorHint);
        app_bar_title = root.findViewById(R.id.view_colorPrimaryText);
        background = root.findViewById(R.id.view_colorBackground);

        // Other
        enable_shadow = root.findViewById(R.id.enable_shadow);

        constraint_white = root.findViewById(R.id.statusbar_tint_white);
        constraint_black = root.findViewById(R.id.statusbar_tint_black);
        selected_white = root.findViewById(R.id.statusbar_tint_white_check);
        selected_black = root.findViewById(R.id.statusbar_tint_black_check);

        // =========================================================================================

        // StatusBar color switch
        constraint_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putInt("colorStatusbarTint", 1).apply();
                refreshViews();
            }
        });

        constraint_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putInt("colorStatusbarTint", -1).apply();
                refreshViews();
            }
        });

        // Enable shadow
        enable_shadow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sp.edit().putInt("shadow", b ? 1 : -1).apply();
                refreshViews();
            }
        });


        // ColorPickers onClick
        constraint_color_background_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_BACKGROUND_TEXT_DIALOG_ID)
                        .setColor(sp.getInt("colorBackgroundText", default_color_background_text))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_BACKGROUND_DIALOG_ID)
                        .setColor(sp.getInt("colorBackground", default_color_background))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_primary_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_PRIMARY_TEXT_DIALOG_ID)
                        .setColor(sp.getInt("colorPrimaryText", default_color_primary_text))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_accent_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_ACCENT_TEXT_DIALOG_ID)
                        .setColor(sp.getInt("colorAccentText", default_color_accent_text))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_HINT_DIALOG_ID)
                        .setColor(sp.getInt("colorHint", default_color_hint))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_control_highlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_CONTROL_HIGHLIGHT_DIALOG_ID)
                        .setColor(sp.getInt("colorControlHighlight", default_color_control_highlight))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });

        return root;
    }

    public void refreshViews() {
        constraint_color_background_text.setBackgroundColor(sp.getInt("colorBackgroundText", default_color_background_text));
        constraint_color_background.setBackgroundColor(sp.getInt("colorBackground", default_color_background));
        constraint_color_primary_text.setBackgroundColor(sp.getInt("colorPrimaryText", default_color_primary_text));
        constraint_color_accent_text.setBackgroundColor(sp.getInt("colorAccentText", default_color_accent_text));
        constraint_color_hint.setBackgroundColor(sp.getInt("colorHint", default_color_hint));
        constraint_color_control_highlight.setBackgroundColor(sp.getInt("colorControlHighlight", default_color_control_highlight));

        // Check if statusbar tint is 1
        if (sp.getInt("colorStatusbarTint", 1) == 1) {
            // white
            selected_white.setVisibility(View.VISIBLE);
            selected_black.setVisibility(View.GONE);
        } else {
            // Black
            selected_white.setVisibility(View.GONE);
            selected_black.setVisibility(View.VISIBLE);
        }

        // Set TextView to the current picked hex color
        text_color_background_text.setText(osthmEngine.argbToHex(Color.alpha(
                sp.getInt("colorBackgroundText", default_color_background_text)), Color.red(
                sp.getInt("colorBackgroundText", default_color_background_text)), Color.green(
                sp.getInt("colorBackgroundText", default_color_background_text)), Color.blue(
                sp.getInt("colorBackgroundText", default_color_background_text))));

        text_color_background.setText(osthmEngine.argbToHex(Color.alpha(
                sp.getInt("colorBackground", default_color_background)), Color.red(
                sp.getInt("colorBackground", default_color_background)), Color.green(
                sp.getInt("colorBackground", default_color_background)), Color.blue(
                sp.getInt("colorBackground", default_color_background))));

        text_color_primary_text.setText(osthmEngine.argbToHex(Color.alpha(
                sp.getInt("colorPrimaryText", default_color_primary_text)), Color.red(
                sp.getInt("colorPrimaryText", default_color_primary_text)), Color.green(
                sp.getInt("colorPrimaryText", default_color_primary_text)), Color.blue(
                sp.getInt("colorPrimaryText", default_color_primary_text))));

        text_color_accent_text.setText(osthmEngine.argbToHex(Color.alpha(
                sp.getInt("colorAccentText", default_color_accent_text)), Color.red(
                sp.getInt("colorAccentText", default_color_accent_text)), Color.green(
                sp.getInt("colorAccentText", default_color_accent_text)), Color.blue(
                sp.getInt("colorAccentText", default_color_accent_text))));

        text_color_hint.setText(osthmEngine.argbToHex(Color.alpha(
                sp.getInt("colorHint", default_color_hint)), Color.red(
                sp.getInt("colorHint", default_color_hint)), Color.green(
                sp.getInt("colorHint", default_color_hint)), Color.blue(
                sp.getInt("colorHint", default_color_hint))));

        text_color_control_highlight.setText(osthmEngine.argbToHex(Color.alpha(
                sp.getInt("colorControlHighlight", default_color_control_highlight)), Color.red(
                sp.getInt("colorControlHighlight", default_color_control_highlight)), Color.green(
                sp.getInt("colorControlHighlight", default_color_control_highlight)), Color.blue(
                sp.getInt("colorControlHighlight", default_color_control_highlight))));
        
        // Set TextView and ImageView color
        if (ColorUtils.calculateLuminance(sp.getInt("colorBackgroundText", default_color_background_text)) < 0.5) {
            text_color_background_text  .setTextColor(0xFFFFFFFF);
            sub_color_background_text   .setTextColor(0xFFFFFFFF);
            image_color_background_text .setColorFilter(0xFFFFFFFF);
        } else {
            text_color_background_text  .setTextColor(0xFF000000);
            sub_color_background_text   .setTextColor(0xFF000000);
            image_color_background_text .setColorFilter(0xFF000000);
        }

        if (ColorUtils.calculateLuminance(sp.getInt("colorBackground", default_color_background)) < 0.5) {
            text_color_background  .setTextColor(0xFFFFFFFF);
            sub_color_background   .setTextColor(0xFFFFFFFF);
            image_color_background .setColorFilter(0xFFFFFFFF);
        } else {
            text_color_background  .setTextColor(0xFF000000);
            sub_color_background   .setTextColor(0xFF000000);
            image_color_background .setColorFilter(0xFF000000);
        }

        if (ColorUtils.calculateLuminance(sp.getInt("colorPrimaryText", default_color_primary_text)) < 0.5) {
            text_color_primary_text  .setTextColor(0xFFFFFFFF);
            sub_color_primary_text   .setTextColor(0xFFFFFFFF);
            image_color_primary_text .setColorFilter(0xFFFFFFFF);
        } else {
            text_color_primary_text  .setTextColor(0xFF000000);
            sub_color_primary_text   .setTextColor(0xFF000000);
            image_color_primary_text .setColorFilter(0xFF000000);
        }

        if (ColorUtils.calculateLuminance(sp.getInt("colorAccentText", default_color_accent_text)) < 0.5) {
            text_color_accent_text  .setTextColor(0xFFFFFFFF);
            sub_color_accent_text   .setTextColor(0xFFFFFFFF);
            image_color_accent_text .setColorFilter(0xFFFFFFFF);
        } else {
            text_color_accent_text  .setTextColor(0xFF000000);
            sub_color_accent_text   .setTextColor(0xFF000000);
            image_color_accent_text .setColorFilter(0xFF000000);
        }

        if (ColorUtils.calculateLuminance(sp.getInt("colorHint", default_color_hint)) < 0.5) {
            text_color_hint  .setTextColor(0xFFFFFFFF);
            sub_color_hint   .setTextColor(0xFFFFFFFF);
            image_color_hint .setColorFilter(0xFFFFFFFF);
        } else {
            text_color_hint  .setTextColor(0xFF000000);
            sub_color_hint   .setTextColor(0xFF000000);
            image_color_hint .setColorFilter(0xFF000000);
        }

        if (ColorUtils.calculateLuminance(sp.getInt("colorControlHighlight", default_color_control_highlight)) < 0.5) {
            text_color_control_highlight  .setTextColor(0xFFFFFFFF);
            sub_color_control_highlight   .setTextColor(0xFFFFFFFF);
            image_color_control_highlight .setColorFilter(0xFFFFFFFF);
        } else {
            text_color_control_highlight  .setTextColor(0xFF000000);
            sub_color_control_highlight   .setTextColor(0xFF000000);
            image_color_control_highlight .setColorFilter(0xFF000000);
        }

        // Change demo view
        // Local changes

        // colorControlHighlight
        fab.setRippleColor(sp.getInt("colorControlHighlight", default_color_control_highlight));

        // colorAccentText
        fab.setColorFilter(sp.getInt("colorAccentText", default_color_accent_text));

        // colorBackground
        background.setBackgroundColor(sp.getInt("colorBackground", -1));

        // colorStatusbarTint
        if (sp.getInt("colorStatusbarTint", 1) == 1) {
            statusbar_text.setTextColor(0xFFFFFFFF);
            statusbar_icon1.setColorFilter(0xFFFFFFFF);
            statusbar_icon2.setColorFilter(0xFFFFFFFF);
            statusbar_icon3.setColorFilter(0xFFFFFFFF);
        } else {
            statusbar_text.setTextColor(0xFF000000);
            statusbar_icon1.setColorFilter(0xFF000000);
            statusbar_icon2.setColorFilter(0xFF000000);
            statusbar_icon3.setColorFilter(0xFF000000);
        }

        if (sp.getInt("shadow", 1) == 1)
            color_primary_app_bar.setElevation(5f);
        else
            color_primary_app_bar.setElevation(0f);

        // Other changes
        fab.setBackgroundTintList(ColorStateList.valueOf(sp.getInt("colorAccent", -720809)));
        color_primary_app_bar.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
        color_primary_dark_status_bar.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
        app_bar_title.setTextColor(sp.getInt("colorPrimaryText", -1));
    }
}
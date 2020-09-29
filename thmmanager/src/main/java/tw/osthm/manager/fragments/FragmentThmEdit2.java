package tw.osthm.manager.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;

import tw.osthm.manager.R;
import tw.osthm.manager.ThmMgrUtils;
import tw.osthm.osthmEngine;

import static tw.osthm.manager.ThemeEditorActivity.ACCENT_COLOR;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_ACCENT_TEXT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_BACKGROUND_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_BACKGROUND_TEXT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_CONTROL_HIGHLIGHT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_HINT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_PRIMARY_TEXT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.TEXT_COLOR;
import static tw.osthm.manager.ThemeEditorActivity.refreshFragments;

public class FragmentThmEdit2 extends Fragment {

    // Color Cards
    private ConstraintLayout constraint_color_background_text, constraint_color_background, constraint_color_primary_text, constraint_color_accent_text, constraint_color_hint, constraint_color_control_highlight;

    // Color Texts
    private TextView text_color_background_text, text_color_background, text_color_primary_text, text_color_accent_text, text_color_hint, text_color_control_highlight;

    // Subtitles
    private TextView sub_color_background_text, sub_color_background, sub_color_primary_text, sub_color_accent_text, sub_color_hint, sub_color_control_highlight;

    // Pick Color Icon
    private ImageView image_color_background_text, image_color_background, image_color_primary_text, image_color_accent_text, image_color_hint, image_color_control_highlight;

    // Enable Shadow
    private CheckBox enable_shadow;

    // Statusbar tint
    private ConstraintLayout statusbar_tint;
    private TextView text_statusbar_tint;

    // Other variables
    SharedPreferences sp;

    // Demo Views
    private ConstraintLayout color_primary_app_bar;
    private ConstraintLayout color_primary_dark_status_bar;
    private ConstraintLayout fake_edit_text_accent;
    private ConstraintLayout background;
    private TextView fake_edit_text_hint;
    private ImageView statusbar_icon1, statusbar_icon2, statusbar_icon3;
    private TextView statusbar_text;
    private FloatingActionButton fab;
    private TextView app_bar_title, background_text;
    private TextView textView3;

    // Default Colors
    private final int default_color_background_text = -16777216;
    private final int default_color_background = -1;
    private final int default_color_primary_text = -1;
    private final int default_color_accent_text = -1;
    private final int default_color_hint = -5723992;
    private final int default_color_control_highlight = 1073741824;

    private View root;

    public FragmentThmEdit2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The SharedPreference we're using
        sp = getActivity().getSharedPreferences("colordata", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate a placeholder view while we're inflating the actual layout in background
        ViewGroup placeholder = (ViewGroup) inflater.inflate(R.layout.placeholder_layout, container, false);
        AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(getActivity());
        asyncLayoutInflater.inflate(R.layout.fragment_thm_edit2, placeholder, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
                // Add the actual layout to the placeholder
                parent.addView(view);
                root = view;

                // Initialize views
                initializeViews();

                // Apply previous applied colors
                refreshViews();

                // =========================================================================================

                // StatusBar color switch
                statusbar_tint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sp.edit().putInt("colorStatusbarTint", sp.getInt(
                                "colorStatusbarTint", 1) == 1 ? 0 : 1).apply();
                        refreshFragments();
                    }
                });

                // Enable shadow
                enable_shadow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        sp.edit().putInt("shadow", b ? 1 : 0).apply();
                        refreshFragments();
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
            }
        });

        return placeholder;
    }

    private void initializeViews() {
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
        fake_edit_text_accent = root.findViewById(R.id.view_colorAccentET);
        fake_edit_text_hint = root.findViewById(R.id.view_colorHint);
        app_bar_title = root.findViewById(R.id.view_colorPrimaryText);
        background = root.findViewById(R.id.view_colorBackground);
        background_text = root.findViewById(R.id.view_colorBackgroundText);

        // Other
        enable_shadow = root.findViewById(R.id.enable_shadow);
        textView3 = root.findViewById(R.id.textView3);

        statusbar_tint = root.findViewById(R.id.statusbar_tint);
        text_statusbar_tint = root.findViewById(R.id.text_statusbar_tint);
    }

    public void refreshViews() {
        if (sp != null && root != null) {
            statusbar_tint.setBackgroundColor(sp.getInt("colorStatusbarTint", 1) == 1 ?
                    0xFFFFFFFF : 0xFF000000);
            text_statusbar_tint.setText(sp.getInt("colorStatusbarTint", 1) == 1 ?
                    "White" : "Black");
            text_statusbar_tint.setTextColor(sp.getInt("colorStatusbarTint", 1) == 1 ?
                    0xFF808080 : 0xFFFFFFFF);

            // Refresh cards
            refreshCard(sp.getInt("colorBackgroundText", default_color_background_text), text_color_background_text, sub_color_background_text, image_color_background_text, constraint_color_background_text);
            refreshCard(sp.getInt("colorBackground", default_color_background), text_color_background, sub_color_background, image_color_background, constraint_color_background);
            refreshCard(sp.getInt("colorPrimaryText", default_color_primary_text), text_color_primary_text, sub_color_primary_text, image_color_primary_text, constraint_color_primary_text);
            refreshCard(sp.getInt("colorAccentText", default_color_accent_text), text_color_accent_text, sub_color_accent_text, image_color_accent_text, constraint_color_accent_text);
            refreshCard(sp.getInt("colorHint", default_color_hint), text_color_hint, sub_color_hint, image_color_hint, constraint_color_hint);
            refreshCard(sp.getInt("colorControlHighlight", default_color_control_highlight), text_color_control_highlight, sub_color_control_highlight, image_color_control_highlight, constraint_color_control_highlight);
            // Change demo view
            // Local changes ===========================================================================

            // colorControlHighlight
            fab.setRippleColor(sp.getInt("colorControlHighlight", default_color_control_highlight));

            // colorAccent
            fab.setBackgroundTintList(ColorStateList.valueOf(sp.getInt("colorAccent", -720809)));
            fake_edit_text_accent.setBackgroundColor(sp.getInt("colorAccent", -720809));

            // colorAccentText
            fab.setColorFilter(sp.getInt("colorAccentText", default_color_accent_text));

            // colorBackground
            background.setBackgroundColor(sp.getInt("colorBackground", -1));

            //colorBackgroundText
            background_text.setTextColor(sp.getInt("colorBackgroundText", -16777216));

            // colorHint
            fake_edit_text_hint.setTextColor(sp.getInt("colorHint", -5723992));

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

            // shadow
            if (sp.getInt("shadow", 1) == 1) {
                color_primary_app_bar.setElevation(ThmMgrUtils.toDip(getContext(), 5f));
                fab.setCompatElevation(ThmMgrUtils.toDip(getContext(), 6f));
            } else {
                color_primary_app_bar.setElevation(0f);
                fab.setCompatElevation(0f);
            }

            // Other changes ===========================================================================
            color_primary_app_bar.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
            color_primary_dark_status_bar.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
            app_bar_title.setTextColor(sp.getInt("colorPrimaryText", -1));
            enable_shadow.setChecked(sp.getInt("shadow", 1) == 1);
            textView3.setTextColor(TEXT_COLOR);
            enable_shadow.setTextColor(TEXT_COLOR);
            enable_shadow.setButtonTintList(ColorStateList.valueOf(ACCENT_COLOR));
        }
    }

    // Helper
    private void refreshCard(int color, TextView header, TextView subtitle, ImageView eyedropper,
                             ConstraintLayout card) {
        boolean isColorDark = ColorUtils.calculateLuminance(color) < 0.5;
        header.setTextColor(isColorDark ? 0xFFFFFFFF : 0xFF000000);
        subtitle.setTextColor(isColorDark ? 0xFFFFFFFF : 0xFF000000);
        eyedropper.setColorFilter(isColorDark ? 0xFFFFFFFF : 0xFF000000);

        /* If you confused what the function does, this is the longer version
        if (ColorUtils.calculateLuminance(color) < 0.5) {
            header  .setTextColor(0xFFFFFFFF);
            subtitle   .setTextColor(0xFFFFFFFF);
            eyedropper .setColorFilter(0xFFFFFFFF);
        } else {
            header  .setTextColor(0xFF000000);
            subtitle   .setTextColor(0xFF000000);
            eyedropper .setColorFilter(0xFF000000);
        }
         */

        header.setText(osthmEngine.colorToHex(color));
        card.setBackgroundColor(color);
    }
}
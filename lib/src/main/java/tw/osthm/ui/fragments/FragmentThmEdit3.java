package tw.osthm.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;

import tw.osthm.R;
import tw.osthm.osthmEngine;

import static tw.osthm.ui.ThemeEditorActivity.*;

public class FragmentThmEdit3 extends Fragment {

    // Color picker views
    private ConstraintLayout constraint_colorPrimaryTint;
    private ConstraintLayout constraint_colorBackgroundTint;
    private ConstraintLayout constraint_colorPrimaryCard;
    private ConstraintLayout constraint_colorBackgroundCard;
    private ConstraintLayout constraint_colorPrimaryCardText;
    private ConstraintLayout constraint_colorBackgroundCardText;
    private ConstraintLayout constraint_colorPrimaryCardTint;
    private ConstraintLayout constraint_colorBackgroundCardTint;

    private ConstraintLayout title_colorPrimaryTint;
    private ConstraintLayout title_colorBackgroundTint;
    private ConstraintLayout title_colorPrimaryCard;
    private ConstraintLayout title_colorBackgroundCard;
    private ConstraintLayout title_colorPrimaryCardText;
    private ConstraintLayout title_colorBackgroundCardText;
    private ConstraintLayout title_colorPrimaryCardTint;
    private ConstraintLayout title_colorBackgroundCardTint;

    private ConstraintLayout subtitle_colorPrimaryTint;
    private ConstraintLayout subtitle_colorBackgroundTint;
    private ConstraintLayout subtitle_colorPrimaryCard;
    private ConstraintLayout subtitle_colorBackgroundCard;
    private ConstraintLayout subtitle_colorPrimaryCardText;
    private ConstraintLayout subtitle_colorBackgroundCardText;
    private ConstraintLayout subtitle_colorPrimaryCardTint;
    private ConstraintLayout subtitle_colorBackgroundCardTint;

    private ConstraintLayout image_colorPrimaryTint;
    private ConstraintLayout image_colorBackgroundTint;
    private ConstraintLayout image_colorPrimaryCard;
    private ConstraintLayout image_colorBackgroundCard;
    private ConstraintLayout image_colorPrimaryCardText;
    private ConstraintLayout image_colorBackgroundCardText;
    private ConstraintLayout image_colorPrimaryCardTint;
    private ConstraintLayout image_colorBackgroundCardTint;

    // Demo views
    private ConstraintLayout appbar, statusbar, background_demo,
                             bottombar, bottombarcard;
    private ImageView statusbar_icon_1, statusbar_icon_2, statusbar_icon_3,
                      appbar_backbutton;

    private TextView statusbar_clock,
                     appbar_title;

    private View root;
    private SharedPreferences sp;

    public FragmentThmEdit3() {
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
        if (root == null) {
            // Inflate the layout for this fragment
            View root = inflater.inflate(R.layout.fragment_thm_edit3, container, false);

            // Initialize views
            initializeViews();

            // Apply previous applied colors
            refreshViews();

            // Set onClicks
            constraint_colorPrimaryTint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorPickerDialog.newBuilder()
                            .setDialogId(COLOR_PRIMARY_TINT_DIALOG_ID)
                            .setColor(sp.getInt("colorPrimaryTint", -1))
                            .setShowAlphaSlider(true)
                            .show(getActivity());
                }
            });

            constraint_colorBackgroundTint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorPickerDialog.newBuilder()
                            .setDialogId(COLOR_BACKGROUND_TINT_DIALOG_ID)
                            .setColor(sp.getInt("colorBackgroundTint", -14575885))
                            .setShowAlphaSlider(true)
                            .show(getActivity());
                }
            });

            constraint_colorPrimaryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorPickerDialog.newBuilder()
                            .setDialogId(COLOR_PRIMARY_CARD_DIALOG_ID)
                            .setColor(sp.getInt("colorPrimaryCard", -1))
                            .setShowAlphaSlider(true)
                            .show(getActivity());
                }
            });

            constraint_colorBackgroundCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorPickerDialog.newBuilder()
                            .setDialogId(COLOR_BACKGROUND_CARD_DIALOG_ID)
                            .setColor(sp.getInt("colorBackgroundCard", -1))
                            .setShowAlphaSlider(true)
                            .show(getActivity());
                }
            });

            constraint_colorPrimaryCardText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorPickerDialog.newBuilder()
                            .setDialogId(COLOR_PRIMARY_CARD_TEXT_DIALOG_ID)
                            .setColor(sp.getInt("colorPrimaryCardText", -16777216))
                            .setShowAlphaSlider(true)
                            .show(getActivity());
                }
            });

            constraint_colorBackgroundCardText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorPickerDialog.newBuilder()
                            .setDialogId(COLOR_BACKGROUND_CARD_TEXT_DIALOG_ID)
                            .setColor(sp.getInt("colorBackgroundCardText", -16777216))
                            .setShowAlphaSlider(true)
                            .show(getActivity());
                }
            });

            constraint_colorPrimaryCardTint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorPickerDialog.newBuilder()
                            .setDialogId(COLOR_PRIMARY_CARD_TINT_DIALOG_ID)
                            .setColor(sp.getInt("colorPrimaryCardTint", -16777216))
                            .setShowAlphaSlider(true)
                            .show(getActivity());
                }
            });

            constraint_colorBackgroundCardTint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorPickerDialog.newBuilder()
                            .setDialogId(COLOR_BACKGROUND_CARD_TINT_DIALOG_ID)
                            .setColor(sp.getInt("colorBackgroundCardTint", -16777216))
                            .setShowAlphaSlider(true)
                            .show(getActivity());
                }
            });
        }

        return root;
    }

    private void initializeViews() {
        // Color picker views
        constraint_colorPrimaryTint = root.findViewById(R.id.constraint_colorPrimaryTint);
        constraint_colorBackgroundTint = root.findViewById(R.id.constraint_colorBackgroundTint);
        constraint_colorPrimaryCard = root.findViewById(R.id.constraint_colorPrimaryCard);
        constraint_colorBackgroundCard = root.findViewById(R.id.constraint_colorBackgroundCard);
        constraint_colorPrimaryCardText = root.findViewById(R.id.constraint_colorPrimaryCardText);
        constraint_colorBackgroundCardText = root.findViewById(R.id.constraint_colorBackgroundCardText);
        constraint_colorPrimaryCardTint = root.findViewById(R.id.constraint_colorPrimaryCardTint);
        constraint_colorBackgroundCardTint = root.findViewById(R.id.constraint_colorBackgroundCardTint);

        title_colorPrimaryTint = root.findViewById(R.id.text_clr_colorPrimaryTint);
        title_colorBackgroundTint = root.findViewById(R.id.title_colorBackgroundTint);
        title_colorPrimaryCard = root.findViewById(R.id.textView__bgcolor_);
        title_colorBackgroundCard = root.findViewById(R.id.textView__);
        title_colorPrimaryCardText = root.findViewById(R.id.textView26);
        title_colorBackgroundCardText = root.findViewById(R.id.textView20);
        title_colorPrimaryCardTint = root.findViewById(R.id.textView9_);
        title_colorBackgroundCardTint = root.findViewById(R.id.textViewcolortextviewcolor);

        subtitle_colorPrimaryTint = root.findViewById(R.id.text_colorPrimaryTint);
        subtitle_colorBackgroundTint = root.findViewById(R.id.subtitle_colorBackgroundTint);
        subtitle_colorPrimaryCard = root.findViewById(R.id.textView_bgcolor_);
        subtitle_colorBackgroundCard = root.findViewById(R.id.textView_);
        subtitle_colorPrimaryCardText = root.findViewById(R.id.textView27);
        subtitle_colorBackgroundCardText = root.findViewById(R.id.textView23);
        subtitle_colorPrimaryCardTint = root.findViewById(R.id.textView8_);
        subtitle_colorBackgroundCardTint = root.findViewById(R.id.textViewtextcolor);

        image_colorPrimaryTint = root.findViewById(R.id.image_colorPrimaryTint);
        image_colorBackgroundTint = root.findViewById(R.id.image_colorBackgroundTint);
        image_colorPrimaryCard = root.findViewById(R.id.imageView24_);
        image_colorBackgroundCard = root.findViewById(R.id.imageView13_);
        image_colorPrimaryCardText = root.findViewById(R.id.imageView2);
        image_colorBackgroundCardText = root.findViewById(R.id.imageView16);
        image_colorPrimaryCardTint = root.findViewById(R.id.imageView134_);
        image_colorBackgroundCardTint = root.findViewById(R.id.imageView23);
    }

    public void refreshViews() {
        if (sp != null && root != null) {
            // Global changes ===========================================================================
            // fab.setRippleColor(sp.getInt("colorControlHighlight", default_color_control_highlight));
            // fab.setBackgroundTintList(ColorStateList.valueOf(sp.getInt("colorAccent", -720809)));
            // fab.setColorFilter(sp.getInt("colorAccentText", default_color_accent_text));

            // fake_edit_text_accent.setBackgroundColor(sp.getInt("colorAccent", -720809));
            background.setBackgroundColor(sp.getInt("colorBackground", -1));
            // background_text.setTextColor(sp.getInt("colorBackgroundText", -16777216));
            // fake_edit_text_hint.setTextColor(sp.getInt("colorHint", -5723992));

            color_primary_app_bar.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
            color_primary_dark_status_bar.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
            app_bar_title.setTextColor(sp.getInt("colorPrimaryText", -1));

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

            if (sp.getInt("shadow", 1) == 1) {
                color_primary_app_bar.setElevation(5f);
                fab.setElevation(6f);
            } else {
                color_primary_app_bar.setElevation(0f);
                fab.setElevation(0f);
            }
        }
    }
}
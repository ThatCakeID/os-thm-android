package tw.osthm.manager.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;

import tw.osthm.manager.R;
import tw.osthm.manager.ThmMgrUtils;
import tw.osthm.osthmEngine;

import static tw.osthm.manager.ThemeEditorActivity.COLOR_BACKGROUND_CARD_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_BACKGROUND_CARD_TEXT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_BACKGROUND_CARD_TINT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_BACKGROUND_TINT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_PRIMARY_CARD_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_PRIMARY_CARD_TEXT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_PRIMARY_CARD_TINT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_PRIMARY_TINT_DIALOG_ID;

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

    private TextView title_colorPrimaryTint;
    private TextView title_colorBackgroundTint;
    private TextView title_colorPrimaryCard;
    private TextView title_colorBackgroundCard;
    private TextView title_colorPrimaryCardText;
    private TextView title_colorBackgroundCardText;
    private TextView title_colorPrimaryCardTint;
    private TextView title_colorBackgroundCardTint;

    private TextView subtitle_colorPrimaryTint;
    private TextView subtitle_colorBackgroundTint;
    private TextView subtitle_colorPrimaryCard;
    private TextView subtitle_colorBackgroundCard;
    private TextView subtitle_colorPrimaryCardText;
    private TextView subtitle_colorBackgroundCardText;
    private TextView subtitle_colorPrimaryCardTint;
    private TextView subtitle_colorBackgroundCardTint;

    private ImageView image_colorPrimaryTint;
    private ImageView image_colorBackgroundTint;
    private ImageView image_colorPrimaryCard;
    private ImageView image_colorBackgroundCard;
    private ImageView image_colorPrimaryCardText;
    private ImageView image_colorBackgroundCardText;
    private ImageView image_colorPrimaryCardTint;
    private ImageView image_colorBackgroundCardTint;

    // Demo views
    private ConstraintLayout appbar, statusbar, background_demo,
            bottombar, bottombarcard;

    private CardView bcard;

    private ImageView statusbar_icon1, statusbar_icon2, statusbar_icon3,
            appbar_backbutton, background_item1, background_item2,
            background_item3, background_item4, bcard_img, pcard_img;

    private TextView statusbar_clock, appbar_title, bcard_text,
            pcard_text;

    // Other components
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
        // Inflate a placeholder view while we're inflating the actual layout in background
        ViewGroup placeholder = (ViewGroup) inflater.inflate(R.layout.placeholder_layout, container, false);
        AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(getActivity());
        asyncLayoutInflater.inflate(R.layout.fragment_thm_edit3, placeholder, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
                parent.addView(view);
                root = view;

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
                                .setColor(sp.getInt("colorBackgroundTint", -16777216))
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

                pcard_text.setSelected(true);
            }
        });

        return placeholder;
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

        title_colorPrimaryTint = root.findViewById(R.id.text_colorPrimaryTint);
        title_colorBackgroundTint = root.findViewById(R.id.title_colorBackgroundTint);
        title_colorPrimaryCard = root.findViewById(R.id.textView__bgcolor_);
        title_colorBackgroundCard = root.findViewById(R.id.textView__);
        title_colorPrimaryCardText = root.findViewById(R.id.textView26);
        title_colorBackgroundCardText = root.findViewById(R.id.textView20);
        title_colorPrimaryCardTint = root.findViewById(R.id.textView9_);
        title_colorBackgroundCardTint = root.findViewById(R.id.textViewcolortextviewcolor);

        subtitle_colorPrimaryTint = root.findViewById(R.id.text_clr_colorPrimaryTint);
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

        // Demo views
        background_demo = root.findViewById(R.id.demo_display_fragment3);
        appbar = root.findViewById(R.id.view_colorPrimary);
        statusbar = root.findViewById(R.id.view_colorPrimaryDark);

        bcard = root.findViewById(R.id.view_colorBackgroundCard);

        bottombar = root.findViewById(R.id.constraintLayout7);
        bottombarcard = root.findViewById(R.id.view_colorPrimaryCard);

        appbar_title = root.findViewById(R.id.view_colorPrimaryText);
        statusbar_clock = root.findViewById(R.id.text_clock);
        bcard_text = root.findViewById(R.id.textView22);
        pcard_text = root.findViewById(R.id.textView24);

        statusbar_icon1 = root.findViewById(R.id.view_colorStatusbarTint1);
        statusbar_icon2 = root.findViewById(R.id.view_colorStatusbarTint2);
        statusbar_icon3 = root.findViewById(R.id.view_colorStatusbarTint3);
        appbar_backbutton = root.findViewById(R.id.view_colorPrimaryTint);
        background_item1 = root.findViewById(R.id.imageView17);
        background_item2 = root.findViewById(R.id.imageView20);
        background_item3 = root.findViewById(R.id.imageView21);
        background_item4 = root.findViewById(R.id.imageView22);
        bcard_img = root.findViewById(R.id.imageView18);
        pcard_img = root.findViewById(R.id.imageView19);
    }

    public void refreshViews() {
        if (sp != null && root != null) {
            // Refresh cards =========================
            refreshCard(sp.getInt("colorPrimaryTint", -1), title_colorPrimaryTint,
                    subtitle_colorPrimaryTint, image_colorPrimaryTint, constraint_colorPrimaryTint);

            refreshCard(sp.getInt("colorBackgroundTint", -16777216), title_colorBackgroundTint,
                    subtitle_colorBackgroundTint, image_colorBackgroundTint, constraint_colorBackgroundTint);

            refreshCard(sp.getInt("colorPrimaryCard", -1), title_colorPrimaryCard,
                    subtitle_colorPrimaryCard, image_colorPrimaryCard, constraint_colorPrimaryCard);

            refreshCard(sp.getInt("colorBackgroundCard", -1), title_colorBackgroundCard,
                    subtitle_colorBackgroundCard, image_colorBackgroundCard, constraint_colorBackgroundCard);

            refreshCard(sp.getInt("colorPrimaryCardText", -16777216), title_colorPrimaryCardText,
                    subtitle_colorPrimaryCardText, image_colorPrimaryCardText, constraint_colorPrimaryCardText);

            refreshCard(sp.getInt("colorBackgroundCardText", -16777216), title_colorBackgroundCardText,
                    subtitle_colorBackgroundCardText, image_colorBackgroundCardText, constraint_colorBackgroundCardText);

            refreshCard(sp.getInt("colorPrimaryCardTint", -16777216), title_colorPrimaryCardTint,
                    subtitle_colorPrimaryCardTint, image_colorPrimaryCardTint, constraint_colorPrimaryCardTint);

            refreshCard(sp.getInt("colorBackgroundCardTint", -16777216), title_colorBackgroundCardTint,
                    subtitle_colorBackgroundCardTint, image_colorBackgroundCardTint, constraint_colorBackgroundCardTint);
            // =======================================

            // Refresh demo views
            background_demo.setBackgroundColor(sp.getInt("colorBackground", -1));

            appbar.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
            statusbar.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
            appbar_title.setTextColor(sp.getInt("colorPrimaryText", -1));

            bottombar.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
            bottombarcard.setBackgroundColor(sp.getInt("colorPrimaryCard", -1));

            bcard.setCardBackgroundColor(sp.getInt("colorBackgroundCard", -1));

            background_item1.setColorFilter(sp.getInt("colorBackgroundTint", -16777216));
            background_item2.setColorFilter(sp.getInt("colorBackgroundTint", -16777216));
            background_item3.setColorFilter(sp.getInt("colorBackgroundTint", -16777216));
            background_item4.setColorFilter(sp.getInt("colorBackgroundTint", -16777216));

            bcard_img.setColorFilter(sp.getInt("colorBackgroundCardTint", -16777216));
            pcard_img.setColorFilter(sp.getInt("colorPrimaryCardTint", -16777216));
            bcard_text.setTextColor(sp.getInt("colorBackgroundCardText", -16777216));
            pcard_text.setTextColor(sp.getInt("colorPrimaryCardText", -16777216));

            if (sp.getInt("colorStatusbarTint", 1) == 1) {
                statusbar_clock.setTextColor(0xFFFFFFFF);
                statusbar_icon1.setColorFilter(0xFFFFFFFF);
                statusbar_icon2.setColorFilter(0xFFFFFFFF);
                statusbar_icon3.setColorFilter(0xFFFFFFFF);
            } else {
                statusbar_clock.setTextColor(0xFF000000);
                statusbar_icon1.setColorFilter(0xFF000000);
                statusbar_icon2.setColorFilter(0xFF000000);
                statusbar_icon3.setColorFilter(0xFF000000);
            }

            appbar.setElevation((sp.getInt("shadow", 1) == 1) ? ThmMgrUtils.toDip(getContext(), 5f) : 0f);
            bcard.setCardElevation((sp.getInt("shadow", 1) == 1) ? ThmMgrUtils.toDip(getContext(), 5f) : 0f);
            appbar_backbutton.setColorFilter(sp.getInt("colorPrimaryTint", -1));
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
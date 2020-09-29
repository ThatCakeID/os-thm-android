package tw.osthm.manager.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static tw.osthm.manager.ThemeEditorActivity.COLOR_ACCENT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_PRIMARY_DARK_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_PRIMARY_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.TEXT_COLOR;

public class FragmentThmEdit1 extends Fragment {

    private SharedPreferences sp;

    //Color cards
    private ConstraintLayout constraint_colorPrimary, constraint_colorPrimaryDark, constraint_colorAccent;

    //---- Color card texts and images ----
    //colorPrimary
    private TextView text_colorPrimary, text_clr_colorPrimary;
    private ImageView image_colorPrimary;

    //colorPrimaryDark
    private TextView text_colorPrimaryDark, text_clr_colorPrimaryDark;
    private ImageView image_colorPrimaryDark;

    //colorAccent
    private TextView text_colorAccent, text_clr_colorAccent;
    private ImageView image_colorAccent;
    //-------------------------------------

    //Preview
    private ConstraintLayout view_colorPrimary, view_colorPrimaryDark, view_colorBackground;
    private FloatingActionButton fab;
    private TextView view_colorPrimaryText, text_clock;
    private ImageView view_colorStatusbarTint1, view_colorStatusbarTint2, view_colorStatusbarTint3;

    private View root;
    private TextView text_maincolors;

    public FragmentThmEdit1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The SharedPreference we're using
        sp = getActivity().getSharedPreferences("colordata", Context.MODE_PRIVATE);
    }

    private void initializeViews() {
        //Initialize color cards
        constraint_colorPrimary = root.findViewById(R.id.constraint_colorPrimary);
        constraint_colorPrimaryDark = root.findViewById(R.id.constraint_colorPrimaryDark);
        constraint_colorAccent = root.findViewById(R.id.constraint_colorAccent);

        //Initialize color card texts and images
        text_colorPrimary = root.findViewById(R.id.text_colorPrimary);
        text_clr_colorPrimary = root.findViewById(R.id.text_clr_colorPrimary);
        image_colorPrimary = root.findViewById(R.id.image_colorPrimary);

        text_colorPrimaryDark = root.findViewById(R.id.text_colorPrimaryDark);
        text_clr_colorPrimaryDark = root.findViewById(R.id.text_clr_colorPrimaryDark);
        image_colorPrimaryDark = root.findViewById(R.id.image_colorPrimaryDark);

        text_colorAccent = root.findViewById(R.id.text_colorAccent);
        text_clr_colorAccent = root.findViewById(R.id.text_clr_colorAccent);
        image_colorAccent = root.findViewById(R.id.image_colorAccent);
        //----------------------------------------

        //Initialize preview
        view_colorPrimary = root.findViewById(R.id.view_colorPrimary);
        view_colorPrimaryDark = root.findViewById(R.id.view_colorPrimaryDark);
        view_colorBackground = root.findViewById(R.id.view_colorBackground);
        fab = root.findViewById(R.id.fab);
        view_colorPrimaryText = root.findViewById(R.id.view_colorPrimaryText);
        text_clock = root.findViewById(R.id.text_clock);
        view_colorStatusbarTint1 = root.findViewById(R.id.view_colorStatusbarTint1);
        view_colorStatusbarTint2 = root.findViewById(R.id.view_colorStatusbarTint2);
        view_colorStatusbarTint3 = root.findViewById(R.id.view_colorStatusbarTint3);
        text_maincolors = root.findViewById(R.id.text_maincolors);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate a placeholder view while we're inflating the actual layout in background
        ViewGroup placeholder = (ViewGroup) inflater.inflate(R.layout.placeholder_layout, container, false);
        AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(getActivity());
        asyncLayoutInflater.inflate(R.layout.fragment_thm_edit1, placeholder, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
                // Add the actual layout to the placeholder
                parent.addView(view);
                root = view;

                // Initialize views
                initializeViews();

                // Apply previous applied colors
                refreshViews();

                // Set onClickListeners
                constraint_colorPrimary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ColorPickerDialog.newBuilder()
                                .setDialogId(COLOR_PRIMARY_DIALOG_ID)
                                .setColor(sp.getInt("colorPrimary", -14575885))
                                .setShowAlphaSlider(true)
                                .show(getActivity());
                    }
                });

                constraint_colorPrimaryDark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ColorPickerDialog.newBuilder()
                                .setDialogId(COLOR_PRIMARY_DARK_DIALOG_ID)
                                .setColor(sp.getInt("colorPrimaryDark", -15242838))
                                .setShowAlphaSlider(true)
                                .show(getActivity());
                    }
                });

                constraint_colorAccent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ColorPickerDialog.newBuilder()
                                .setDialogId(COLOR_ACCENT_DIALOG_ID)
                                .setColor(sp.getInt("colorAccent", -720809))
                                .setShowAlphaSlider(true)
                                .show(getActivity());
                    }
                });
            }
        });

        return placeholder;
    }

    public void refreshViews() {
        if (sp != null && root != null) {
            refreshCard(sp.getInt("colorPrimary", -14575885), text_colorPrimary,
                    text_clr_colorPrimary, image_colorPrimary, constraint_colorPrimary);
            refreshCard(sp.getInt("colorPrimaryDark", -15242838), text_colorPrimaryDark,
                    text_clr_colorPrimaryDark, image_colorPrimaryDark, constraint_colorPrimaryDark);
            refreshCard(sp.getInt("colorAccent", -720809), text_colorAccent,
                    text_clr_colorAccent, image_colorAccent, constraint_colorAccent);

            fab.setBackgroundTintList(ColorStateList.valueOf(sp.getInt("colorAccent", -720809)));
            fab.setRippleColor(sp.getInt("colorControlHighlight", 1073741824));
            fab.setColorFilter(sp.getInt("colorAccentText", -1));

            view_colorPrimary.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
            view_colorPrimaryDark.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
            view_colorPrimaryText.setTextColor(sp.getInt("colorPrimaryText", -1));

            if (sp.getInt("shadow", 1) == 1) {
                view_colorPrimary.setElevation(ThmMgrUtils.toDip(getContext(), 5f));
                fab.setCompatElevation(ThmMgrUtils.toDip(getContext(), 6f));
            } else {
                view_colorPrimary.setElevation(0f);
                fab.setCompatElevation(0f);
            }

            view_colorBackground.setBackgroundColor(sp.getInt("colorBackground", -1));

            if (sp.getInt("colorStatusbarTint", 1) == 1) {
                text_clock.setTextColor(0xFFFFFFFF);
                view_colorStatusbarTint1.setColorFilter(0xFFFFFFFF);
                view_colorStatusbarTint2.setColorFilter(0xFFFFFFFF);
                view_colorStatusbarTint3.setColorFilter(0xFFFFFFFF);
            } else {
                text_clock.setTextColor(0xFF000000);
                view_colorStatusbarTint1.setColorFilter(0xFF000000);
                view_colorStatusbarTint2.setColorFilter(0xFF000000);
                view_colorStatusbarTint3.setColorFilter(0xFF000000);
            }

            text_maincolors.setTextColor(TEXT_COLOR);
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
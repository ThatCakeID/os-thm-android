package tw.osthm.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import tw.osthm.osthmEngine;

import tw.osthm.R;

import static tw.osthm.ui.ThemeEditorActivity.*;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            // Inflate the layout for this fragment
            root = inflater.inflate(R.layout.fragment_thm_edit1, container, false);

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

        return root;
    }

    public void refreshViews() {
        if (sp != null && root != null) {
            constraint_colorPrimary.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
            constraint_colorPrimaryDark.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
            constraint_colorAccent.setBackgroundColor(sp.getInt("colorAccent", -720809));

            text_colorPrimary.setText(osthmEngine.colorToHex(sp.getInt("colorPrimary", -14575885)));
            text_colorPrimaryDark.setText(osthmEngine.colorToHex(sp.getInt("colorPrimaryDark", -15242838)));
            text_colorAccent.setText(osthmEngine.colorToHex(sp.getInt("colorAccent", -720809)));

            calculateColorBrightness(sp.getInt("colorPrimary", -14575885), text_colorPrimary,
                    text_clr_colorPrimary, image_colorPrimary);
            calculateColorBrightness(sp.getInt("colorPrimaryDark", -15242838), text_colorPrimaryDark,
                    text_clr_colorPrimaryDark, image_colorPrimaryDark);
            calculateColorBrightness(sp.getInt("colorAccent", -720809), text_colorAccent,
                    text_clr_colorAccent, image_colorAccent);

            fab.setBackgroundTintList(ColorStateList.valueOf(sp.getInt("colorAccent", -720809)));
            fab.setRippleColor(sp.getInt("colorControlHighlight", 1073741824));
            fab.setColorFilter(sp.getInt("colorAccentText", -1));

            view_colorPrimary.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
            view_colorPrimaryDark.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
            view_colorPrimaryText.setTextColor(sp.getInt("colorPrimaryText", -1));

            if (sp.getInt("shadow", 1) == 1) {
                view_colorPrimary.setElevation(5f);
                fab.setElevation(6f);
            } else {
                view_colorPrimary.setElevation(0f);
                fab.setElevation(0f);
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
        }
    }

    // Helper
    private void calculateColorBrightness(int color, TextView header, TextView subtitle, ImageView eyedropper) {
        boolean isDark = ColorUtils.calculateLuminance(color) < 0.5;
        header.setTextColor(isDark ? 0xFFFFFFFF : 0xFF000000);
        subtitle.setTextColor(isDark ? 0xFFFFFFFF : 0xFF000000);
        eyedropper.setColorFilter(isDark ? 0xFFFFFFFF : 0xFF000000);

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
    }


}
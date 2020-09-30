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

import com.jaredrummler.android.colorpicker.ColorPickerDialog;

import tw.osthm.manager.R;
import tw.osthm.osthmEngine;

import static tw.osthm.manager.ThemeEditorActivity.COLOR_DIALOG_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_DIALOG_TEXT_DIALOG_ID;
import static tw.osthm.manager.ThemeEditorActivity.COLOR_DIALOG_TINT_DIALOG_ID;

public class FragmentThmEdit4 extends Fragment {
    private SharedPreferences sp;
    private View root;

    private ConstraintLayout constraint_colorDialog, constraint_colorDialogText, constraint_colorDialogTint;
    private TextView text_colorDialog, text_colorDialogText, text_colorDialogTint;
    private TextView text_clr_colorDialog, text_clr_colorDialogText, text_clr_colorDialogTint;
    private ImageView image_colorDialog, image_colorDialogText, image_colorDialogTint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The SharedPreference we're using
        sp = getActivity().getSharedPreferences("colordata", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup placeholder = (ViewGroup) inflater.inflate(R.layout.placeholder_layout,
                container, false);
        AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(getActivity());
        asyncLayoutInflater.inflate(R.layout.fragment_thm_edit4, placeholder, new AsyncLayoutInflater.OnInflateFinishedListener() {
            @Override
            public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {
                parent.addView(view);
                root = view;

                // Initialize views
                initializeViews();

                // Apply previous applied colors
                refreshViews();

                // Set onClicks
                constraint_colorDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ColorPickerDialog.newBuilder()
                                .setDialogId(COLOR_DIALOG_DIALOG_ID)
                                .setColor(sp.getInt("colorDialog", -1))
                                .setShowAlphaSlider(true)
                                .show(getActivity());
                    }
                });

                constraint_colorDialogText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ColorPickerDialog.newBuilder()
                                .setDialogId(COLOR_DIALOG_TEXT_DIALOG_ID)
                                .setColor(sp.getInt("colorDialogText", -16777216))
                                .setShowAlphaSlider(true)
                                .show(getActivity());
                    }
                });

                constraint_colorDialogTint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ColorPickerDialog.newBuilder()
                                .setDialogId(COLOR_DIALOG_TINT_DIALOG_ID)
                                .setColor(sp.getInt("colorDialogTint", -16777216))
                                .setShowAlphaSlider(true)
                                .show(getActivity());
                    }
                });
            }
        });
        return placeholder;
    }

    private void initializeViews() {
        constraint_colorDialog = root.findViewById(R.id.constraint_colorDialog);
        constraint_colorDialogText = root.findViewById(R.id.constraint_colorDialogText);
        constraint_colorDialogTint = root.findViewById(R.id.constraint_colorDialogTint);

        text_colorDialog = root.findViewById(R.id.text_colorDialog);
        text_colorDialogText = root.findViewById(R.id.text_colorDialogText);
        text_colorDialogTint = root.findViewById(R.id.text_colorDialogTint);

        text_clr_colorDialog = root.findViewById(R.id.text_clr_colorDialog);
        text_clr_colorDialogText = root.findViewById(R.id.text_clr_colorDialogText);
        text_clr_colorDialogTint = root.findViewById(R.id.text_clr_colorDialogTint);

        image_colorDialog = root.findViewById(R.id.image_colorDialog);
        image_colorDialogText = root.findViewById(R.id.image_colorDialogText);
        image_colorDialogTint = root.findViewById(R.id.image_colorDialogTint);
    }

    public void refreshViews() {
        if (sp != null && root != null) {
            refreshCard(sp.getInt("colorDialog", -1), text_colorDialog,
                    text_clr_colorDialog, image_colorDialog, constraint_colorDialog);
            refreshCard(sp.getInt("colorDialogText", -16777216), text_colorDialogText,
                    text_clr_colorDialogText, image_colorDialogText, constraint_colorDialogText);
            refreshCard(sp.getInt("colorDialogTint", -16777216), text_colorDialogTint,
                    text_clr_colorDialogTint, image_colorDialogTint, constraint_colorDialogTint);

            root.findViewById(R.id.view_dialog).setBackgroundTintList(ColorStateList
                    .valueOf(sp.getInt("colorDialog", -1)));
            ((ImageView) root.findViewById(R.id.image_delete)).setColorFilter(
                    sp.getInt("colorDialogTint", -16777216));
            ((ImageView) root.findViewById(R.id.image_edit)).setColorFilter(
                    sp.getInt("colorDialogTint", -16777216));
            ((TextView) root.findViewById(R.id.text_title)).setTextColor(
                    sp.getInt("colorDialogText", -16777216));
            ((TextView) root.findViewById(R.id.text_subtitle)).setTextColor(
                    sp.getInt("colorDialogText", -16777216));
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

package tw.osthm.ui.fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.osthm.R;

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

    // Demo view

    public FragmentThmEdit3() {
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
        return inflater.inflate(R.layout.fragment_thm_edit3, container, false);
    }

    public void refreshViews() {

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
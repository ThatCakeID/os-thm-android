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
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import tw.osthm.R;

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

    public FragmentThmEdit1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The SharedPreference we're using
        sp = getActivity().getSharedPreferences("colordata", Context.MODE_PRIVATE);
    }

    private void initializeViews(View root) {
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thm_edit1, container, false);

        //Initialize views
        initializeViews(root);

        //Refresh the views, coz why not
        //refreshViews();

        // Set onClickListeners
        constraint_colorPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(0)
                        .setColor(sp.getInt("colorPrimary", -14575885))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });

        constraint_colorPrimaryDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(1)
                        .setColor(sp.getInt("colorPrimaryDark", -15242838))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });

        constraint_colorAccent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(2)
                        .setColor(sp.getInt("colorAccent", -720809))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });

        return root;
    }

    public void refreshViews() {
        constraint_colorPrimary.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
        constraint_colorPrimaryDark.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
        constraint_colorAccent.setBackgroundColor(sp.getInt("colorAccent", -720809));

        if (ColorUtils.calculateLuminance(sp.getInt("colorPrimary", -14575885)) < 0.5) {
            text_colorPrimary.setTextColor(0xFFFFFFFF);
            text_clr_colorPrimary.setTextColor(0xFFFFFFFF);
            image_colorPrimary.setColorFilter(0xFFFFFFFF);
        } else {
            text_colorPrimary.setTextColor(0xFF000000);
            text_clr_colorPrimary.setTextColor(0xFF000000);
            image_colorPrimary.setColorFilter(0xFF000000);
        }

        if (ColorUtils.calculateLuminance(sp.getInt("colorPrimaryDark", -15242838)) < 0.5) {
            text_colorPrimaryDark.setTextColor(0xFFFFFFFF);
            text_clr_colorPrimaryDark.setTextColor(0xFFFFFFFF);
            image_colorPrimaryDark.setColorFilter(0xFFFFFFFF);
        } else {
            text_colorPrimaryDark.setTextColor(0xFF000000);
            text_clr_colorPrimaryDark.setTextColor(0xFF000000);
            image_colorPrimaryDark.setColorFilter(0xFF000000);
        }

        if (ColorUtils.calculateLuminance(sp.getInt("colorAccent", -720809)) < 0.5) {
            text_colorAccent.setTextColor(0xFFFFFFFF);
            text_clr_colorAccent.setTextColor(0xFFFFFFFF);
            image_colorAccent.setColorFilter(0xFFFFFFFF);
        } else {
            text_colorAccent.setTextColor(0xFF000000);
            text_clr_colorAccent.setTextColor(0xFF000000);
            image_colorAccent.setColorFilter(0xFF000000);
        }

        fab.setBackgroundTintList(ColorStateList.valueOf(sp.getInt("colorAccent", -720809)));
        fab.setRippleColor(sp.getInt("colorControlHighlight", 1073741824));
        fab.setColorFilter(sp.getInt("colorAccentText", -1));
        view_colorPrimary.setBackgroundColor(sp.getInt("colorPrimary", -14575885));
        view_colorPrimaryDark.setBackgroundColor(sp.getInt("colorPrimaryDark", -15242838));
        view_colorPrimaryText.setTextColor(sp.getInt("colorPrimaryText", -1));
        if (sp.getInt("shadow", 1) == 1)
            view_colorPrimary.setElevation(5f);
        else
            view_colorPrimary.setElevation(0f);
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
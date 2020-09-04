package tw.osthm.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.print.PrinterId;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import tw.osthm.R;

public class FragmentThmEdit1 extends Fragment implements ColorPickerDialogListener {

    SharedPreferences sp;
    int primary;         // Color Primary
    int primary_dark;    // Color Primary Dark
    int accent;          // Color Accent

    // Dialog IDs for recognizing what dialog is which
    private static final int DIALOG_COLOR_PRIMARY       = 1;
    private static final int DIALOG_COLOR_PRIMARY_DARK  = 2;
    private static final int DIALOG_COLOR_ACCENT        = 3;

    // Demo views
    FloatingActionButton    fab_demo    ;
    ConstraintLayout        status_bar  ;
    ConstraintLayout        app_bar     ;

    public FragmentThmEdit1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The SharedPreference we're using
        sp = getActivity().getSharedPreferences("colordata", Context.MODE_PRIVATE);

        // Check if user has tweaked anything here
        if (sp.getBoolean("changedFragment1", false)) {
            primary         =   sp.getInt("colorPrimary",       getResources().getColor(R.color.colorPrimary));
            primary_dark    =   sp.getInt("colorPrimaryDark",   getResources().getColor(R.color.colorPrimaryDark));
            accent          =   sp.getInt("colorAccent",        getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Check if user has tweaked anything here
        if (sp.getBoolean("changedFragment1", false)) {
            primary         =   sp.getInt("colorPrimary",       getResources().getColor(R.color.colorPrimary));
            primary_dark    =   sp.getInt("colorPrimaryDark",   getResources().getColor(R.color.colorPrimaryDark));
            accent          =   sp.getInt("colorAccent",        getResources().getColor(R.color.colorAccent));
        }

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thm_edit1, container, false);

        // Init views

        // Buttons/ Pickers
        View color_primary = root.findViewById(R.id.color_primary_fragment1);
        View color_primary_dark = root.findViewById(R.id.color_primary_dark_fragment1);
        View color_accent = root.findViewById(R.id.color_accent_fragment1);

        // Demo views
        fab_demo = root.findViewById(R.id.fab_fragment1);
        status_bar = root.findViewById(R.id.status_bar_fragment1);
        app_bar = root.findViewById(R.id.app_bar_fragment1);

        // Set onClickListeners
        color_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                        .setAllowPresets(false)
                        .setDialogId(DIALOG_COLOR_PRIMARY)
                        .setColor(primary)
                        .setShowAlphaSlider(false)
                        .show(getActivity());
            }
        });

        color_primary_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                        .setAllowPresets(false)
                        .setDialogId(DIALOG_COLOR_PRIMARY_DARK)
                        .setColor(primary_dark)
                        .setShowAlphaSlider(false)
                        .show(getActivity());
            }
        });

        color_accent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                        .setAllowPresets(false)
                        .setDialogId(DIALOG_COLOR_ACCENT)
                        .setColor(accent)
                        .setShowAlphaSlider(false)
                        .show(getActivity());
            }
        });

        return root;
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        // Update the demo view
        fab_demo    .setBackgroundColor(color);
        status_bar  .setBackgroundColor(color);
        app_bar     .setBackgroundColor(color);

        // When color is selected, set the variables to the color selected
        switch (dialogId) {
            case DIALOG_COLOR_PRIMARY:
                primary = color;
                break;
            case DIALOG_COLOR_PRIMARY_DARK:
                primary_dark = color;
                break;
            case DIALOG_COLOR_ACCENT:
                accent = color;
                break;
            default:
        }

        // Save the colors to SharedPreferences
        sp.edit()
                .putBoolean("changedFragment1", true)   // To check if user has done any changes to this fragment or not
                .putInt("colorPrimary", primary)
                .putInt("colorPrimaryDark", primary_dark)
                .putInt("colorAccent", accent)
                .apply();
    }

    @Override
    public void onDialogDismissed(int dialogId) {
        // Do nothing
    }
}
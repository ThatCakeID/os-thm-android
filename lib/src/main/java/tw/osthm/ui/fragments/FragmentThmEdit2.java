package tw.osthm.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;

import tw.osthm.R;

import static tw.osthm.ui.ThemeEditorActivity.COLOR_PRIMARY_DIALOG_ID;

public class FragmentThmEdit2 extends Fragment {

    // Color Cards
    private ConstraintLayout constraint_color_background_text, constraint_color_background, constraint_color_primary_text, constraint_color_accent_text, constraint_color_hint, constraint_color_control_highlight;

    // Color Texts
    private TextView text_color_background_text, text_color_background, text_color_primary_text, text_color_accent_text, text_color_hint, text_color_control_highlight;

    // Pick Color Icon
    private ImageView image_color_background_text, image_color_background, image_color_primary_text, image_color_accent_text, image_color_hint, image_color_control_highlight;

    // Other variables
    SharedPreferences sp;

    // Demo Views
    private ConstraintLayout color_primary_app_bar;
    private ConstraintLayout color_primary_dark_status_bar;
    private View statusbar_icon1, statusbar_icon2, statusbar_icon3, statusbar_text;

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

        // Assign viewsjulhoh8kh7iuyty9jt89iff9u7ohk
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

        // Images
        image_color_background_text = root.findViewById(R.id.image_colorBackgroundText);
        image_color_background = root.findViewById(R.id.image_colorBackground);
        image_color_primary_text = root.findViewById(R.id.image_colorPrimaryText);
        image_color_accent_text = root.findViewById(R.id.image_colorAccentText);
        image_color_hint = root.findViewById(R.id.image_colorHint);
        image_color_control_highlight = root.findViewById(R.id.image_colorControlHighlight);


        // Put OnClickListeners
        constraint_color_background_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_PRIMARY_DIALOG_ID)
                        .setColor(sp.getInt("colorPrimary", -14575885))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_PRIMARY_DIALOG_ID)
                        .setColor(sp.getInt("colorPrimary", -14575885))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_primary_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_PRIMARY_DIALOG_ID)
                        .setColor(sp.getInt("colorPrimary", -14575885))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_accent_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_PRIMARY_DIALOG_ID)
                        .setColor(sp.getInt("colorPrimary", -14575885))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_PRIMARY_DIALOG_ID)
                        .setColor(sp.getInt("colorPrimary", -14575885))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });
        constraint_color_control_highlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder()
                        .setDialogId(COLOR_PRIMARY_DIALOG_ID)
                        .setColor(sp.getInt("colorPrimary", -14575885))
                        .setShowAlphaSlider(true)
                        .show(getActivity());
            }
        });

        return root;
    }

    public void refreshViews() {

    }
}
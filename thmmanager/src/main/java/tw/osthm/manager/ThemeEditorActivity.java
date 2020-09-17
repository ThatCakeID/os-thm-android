package tw.osthm.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import tw.osthm.osthmEngine;
import tw.osthm.osthmException;
import tw.osthm.manager.fragments.FragmentThmEdit1;
import tw.osthm.manager.fragments.FragmentThmEdit2;
import tw.osthm.manager.fragments.FragmentThmEdit3;

public class ThemeEditorActivity extends AppCompatActivity implements ColorPickerDialogListener {
    private ViewPager2 mPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    private ImageView image_back;
    private ImageView image_save;
    private SharedPreferences sp;
    private static FragmentThmEdit1 fragment1;
    private static FragmentThmEdit2 fragment2;
    private static FragmentThmEdit3 fragment3;

    private View bottomsheetview;
    private BottomSheetDialog bottomSheetDialog;
    private ImageView image_saveb, image_close;
    private TextView til1, til2, til3, til4;

    // FRAGMENT 1
    public static final int COLOR_PRIMARY_DIALOG_ID = 0;
    public static final int COLOR_PRIMARY_DARK_DIALOG_ID = 1;
    public static final int COLOR_ACCENT_DIALOG_ID = 2;

    // FRAGMENT 2
    public static final int COLOR_BACKGROUND_TEXT_DIALOG_ID = 3;
    public static final int COLOR_BACKGROUND_DIALOG_ID = 4;
    public static final int COLOR_PRIMARY_TEXT_DIALOG_ID = 5;
    public static final int COLOR_ACCENT_TEXT_DIALOG_ID = 6;
    public static final int COLOR_HINT_DIALOG_ID = 7;
    public static final int COLOR_CONTROL_HIGHLIGHT_DIALOG_ID = 8;

    // FRAGMENT 3
    public static final int COLOR_PRIMARY_TINT_DIALOG_ID = 9;
    public static final int COLOR_BACKGROUND_TINT_DIALOG_ID = 10;
    public static final int COLOR_BACKGROUND_CARD_DIALOG_ID = 11;
    public static final int COLOR_BACKGROUND_CARD_TINT_DIALOG_ID = 12;
    public static final int COLOR_PRIMARY_CARD_DIALOG_ID = 13;
    public static final int COLOR_PRIMARY_CARD_TINT_DIALOG_ID = 14;
    public static final int COLOR_PRIMARY_CARD_TEXT_DIALOG_ID = 15;
    public static final int COLOR_BACKGROUND_CARD_TEXT_DIALOG_ID = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_editor);

        initializeViews();
        sp.edit().clear().apply();

        if (getIntent().getBooleanExtra("isEditing", false))
            loadColors();

        fragment1 = new FragmentThmEdit1();
        fragment2 = new FragmentThmEdit2();
        fragment3 = new FragmentThmEdit3();

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        image_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomsheetview != null) {
                    bottomSheetDialog.show();
                }
            }
        });

        image_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        image_saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!til1.getText().toString().equals("") && !til2.getText().toString().equals("") && !til3
                        .getText().toString().equals("") && !til4.getText().toString().equals("")) {
                    try {
                        osthmEngine.addTheme(sp.getInt("colorPrimary", -14575885),
                                sp.getInt("colorPrimaryText", -1), sp.getInt("colorPrimaryDark", -15242838),
                                sp.getInt("colorStatusbarTint", 1), sp.getInt("colorBackground", -1),
                                sp.getInt("colorBackgroundText", -16777216), sp.getInt("colorAccent", -720809),
                                sp.getInt("colorAccentText", -1), sp.getInt("shadow", 1),
                                sp.getInt("colorControlHighlight", 1073741824), sp.getInt("colorHint", -5723992),
                                sp.getInt("colorPrimaryTint", -1), sp.getInt("colorBackgroundTint", -14575885),
                                sp.getInt("colorPrimaryCard", -1), sp.getInt("colorBackgroundCard", -1),
                                sp.getInt("colorPrimaryCardText", -16777216), sp.getInt("colorBackgroundCardText", -16777216),
                                sp.getInt("colorPrimaryCardTint", -16777216), sp.getInt("colorBackgroundCardTint", -16777216),
                                til1.getText().toString(), til3.getText().toString(), til2.getText().toString(),
                                Integer.parseInt(til4.getText().toString()));
                        bottomSheetDialog.dismiss();
                        finish();
                    } catch (osthmException err) {
                        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),
                                err.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),
                            "Please fill all the blanks", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        pagerAdapter = new ScreenSlidePagerAdapter(this);
        mPager.setOffscreenPageLimit(3);
        mPager.setAdapter(pagerAdapter);
    }

    private void initializeViews() {
        mPager = findViewById(R.id.viewPager_thmedit);
        image_back = findViewById(R.id.image_back);
        image_save = findViewById(R.id.image_save);
        sp = getSharedPreferences("colordata", Context.MODE_PRIVATE);
        bottomSheetDialog = new BottomSheetDialog(ThemeEditorActivity.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bottomsheetview = inflater.inflate(R.layout.bottomsheet_newtheme, null);
        image_saveb = bottomsheetview.findViewById(R.id.image_save);
        image_close = bottomsheetview.findViewById(R.id.image_close);
        bottomSheetDialog.setContentView(bottomsheetview);
        til1 = bottomsheetview.findViewById(R.id.til1);
        til2 = bottomsheetview.findViewById(R.id.til2);
        til3 = bottomsheetview.findViewById(R.id.til3);
        til4 = bottomsheetview.findViewById(R.id.til4);
    }

    private void loadColors() {
        sp.edit().putInt("colorPrimary", -14575885).putInt("colorPrimaryText", -1)
                .putInt("colorPrimaryDark", -15242838).putInt("colorStatusbarTint", 1)
                .putInt("colorBackground", -1).putInt("colorBackgroundText", -16777216)
                .putInt("colorAccent", -720809).putInt("colorAccentText", -1)
                .putInt("shadow", 1).putInt("colorControlHighlight", 1073741824)
                .putInt("colorHint", -5723992).putInt("colorPrimaryTint", -1)
                .putInt("colorBackgroundTint", -14575885).putInt("colorPrimaryCard", -1)
                .putInt("colorBackgroundCard", -1).putInt("colorPrimaryCardText", -16777216)
                .putInt("colorBackgroundCardText", -16777216).putInt("colorPrimaryCardTint", -16777216)
                .putInt("colorBackgroundCardTint", -16777216).apply();
    }

    private void loadDefaultColors() {
        sp.edit().putInt("colorPrimary", -14575885).putInt("colorPrimaryText", -1)
                .putInt("colorPrimaryDark", -15242838).putInt("colorStatusbarTint", 1)
                .putInt("colorBackground", -1).putInt("colorBackgroundText", -16777216)
                .putInt("colorAccent", -720809).putInt("colorAccentText", -1)
                .putInt("shadow", 1).putInt("colorControlHighlight", 1073741824)
                .putInt("colorHint", -5723992).putInt("colorPrimaryTint", -1)
                .putInt("colorBackgroundTint", -14575885).putInt("colorPrimaryCard", -1)
                .putInt("colorBackgroundCard", -1).putInt("colorPrimaryCardText", -16777216)
                .putInt("colorBackgroundCardText", -16777216).putInt("colorPrimaryCardTint", -16777216)
                .putInt("colorBackgroundCardTint", -16777216).apply();
        /*
         * Default colors (more readable than the code above):
         * colorPrimary:            -14575885
         * colorPrimaryText:        -1
         * colorPrimaryDark:        -15242838
         * colorStatusbarTint:       1
         * colorBackground:         -1
         * colorBackgroundText:     -16777216
         * colorAccent:             -720809
         * colorAccentText:         -1
         * shadow:                   1
         * colorControlHighlight:    1073741824
         * colorHint:               -5723992
         * colorPrimaryTint:        -1
         * colorBackgroundTint:     -14575885
         * colorPrimaryCard:        -1
         * colorBackgroundCard:     -1
         * colorPrimaryCardText:    -16777216
         * colorBackgroundCardText: -16777216
         * colorPrimaryCardTint:    -16777216
         * colorBackgroundCardTint: -16777216
         */
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        switch (dialogId) {
            case COLOR_PRIMARY_DIALOG_ID:
                // colorPrimary
                sp.edit().putInt("colorPrimary", color).apply();
                break;

            case COLOR_PRIMARY_DARK_DIALOG_ID:
                // colorPrimaryDark
                sp.edit().putInt("colorPrimaryDark", color).apply();
                break;

            case COLOR_ACCENT_DIALOG_ID:
                // colorAccent
                sp.edit().putInt("colorAccent", color).apply();
                break;

            case COLOR_BACKGROUND_TEXT_DIALOG_ID:
                // colorBackgroundText
                sp.edit().putInt("colorBackgroundText", color).apply();
                break;

            case COLOR_BACKGROUND_DIALOG_ID:
                // colorBackground
                sp.edit().putInt("colorBackground", color).apply();
                break;

            case COLOR_PRIMARY_TEXT_DIALOG_ID:
                // colorPrimaryText
                sp.edit().putInt("colorPrimaryText", color).apply();
                break;

            case COLOR_ACCENT_TEXT_DIALOG_ID:
                // colorAccentText
                sp.edit().putInt("colorAccentText", color).apply();
                break;

            case COLOR_HINT_DIALOG_ID:
                // colorHint
                sp.edit().putInt("colorHint", color).apply();
                break;

            case COLOR_CONTROL_HIGHLIGHT_DIALOG_ID:
                // colorControlHighlight
                sp.edit().putInt("colorControlHighlight", color).apply();
                break;

            case COLOR_PRIMARY_TINT_DIALOG_ID:
                // colorPrimaryTint
                sp.edit().putInt("colorPrimaryTint", color).apply();
                break;

            case COLOR_BACKGROUND_TINT_DIALOG_ID:
                // colorBackgroundTint
                sp.edit().putInt("colorBackgroundTint", color).apply();
                break;

            case COLOR_BACKGROUND_CARD_DIALOG_ID:
                // colorBackgroundCard
                sp.edit().putInt("colorBackgroundCard", color).apply();
                break;

            case COLOR_BACKGROUND_CARD_TINT_DIALOG_ID:
                // colorBackgroundCardTint
                sp.edit().putInt("colorBackgroundCardTint", color).apply();
                break;

            case COLOR_PRIMARY_CARD_DIALOG_ID:
                // colorPrimaryCard
                sp.edit().putInt("colorPrimaryCard", color).apply();
                break;

            case COLOR_PRIMARY_CARD_TINT_DIALOG_ID:
                // colorPrimaryCardTint
                sp.edit().putInt("colorPrimaryCardTint", color).apply();
                break;

            case COLOR_PRIMARY_CARD_TEXT_DIALOG_ID:
                // colorPrimaryCardText
                sp.edit().putInt("colorPrimaryCardText", color).apply();
                break;

            case COLOR_BACKGROUND_CARD_TEXT_DIALOG_ID:
                // colorBackgroundCardText
                sp.edit().putInt("colorBackgroundCardText", color).apply();
                break;
        }
        refreshFragments();
    }

    public static void refreshFragments() {
        fragment1.refreshViews();
        fragment2.refreshViews();
        fragment3.refreshViews();
    }

    @Override
    public void onDialogDismissed(int dialogId) {
        //Do nothing
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        private Fragment[] childFragments;

        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
            childFragments = new Fragment[]{
                    fragment1, // 0
                    fragment2, // 1
                    fragment3 // 2
            };
        }

        @Override
        public Fragment createFragment(int position) {
            return childFragments[position];
        }

        @Override
        public int getItemCount() {
            return childFragments.length;
        }

    }

}
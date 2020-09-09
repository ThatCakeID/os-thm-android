package tw.osthm.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import tw.osthm.R;
import tw.osthm.ui.fragments.FragmentThmEdit1;
import tw.osthm.ui.fragments.FragmentThmEdit2;
import tw.osthm.ui.fragments.FragmentThmEdit3;

public class ThemeEditorActivity extends AppCompatActivity implements ColorPickerDialogListener {
    private ViewPager2 mPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    private ImageView image_back;
    private ImageView image_save;
    private SharedPreferences sp;
    private static FragmentThmEdit1 fragment1;
    private static FragmentThmEdit2 fragment2;
    private static FragmentThmEdit3 fragment3;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_editor);
        initializeViews();
        loadDefaultColors();
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

            }
        });
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        mPager.setAdapter(pagerAdapter);
    }

    private void initializeViews() {
        mPager = findViewById(R.id.viewPager_thmedit);
        image_back = findViewById(R.id.image_back);
        image_save = findViewById(R.id.image_save);
        sp = getSharedPreferences("colordata", Context.MODE_PRIVATE);
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
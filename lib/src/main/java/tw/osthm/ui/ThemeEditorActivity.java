package tw.osthm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import tw.osthm.ui.fragments.FragmentThmEdit1;
import tw.osthm.ui.fragments.FragmentThmEdit2;
import tw.osthm.ui.fragments.FragmentThmEdit3;

import tw.osthm.R;

public class ThemeEditorActivity extends AppCompatActivity implements ColorPickerDialogListener {
    private ViewPager2 mPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    private ImageView image_back;
    private ImageView image_save;
    private SharedPreferences sp;
    private static FragmentThmEdit1 fragment1;
    private static FragmentThmEdit2 fragment2;
    private static FragmentThmEdit3 fragment3;

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
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        switch(dialogId) {
            case 0:
                //colorPrimary
                sp.edit().putInt("colorPrimary", color).apply();
                break;
            case 1:
                //colorPrimaryDark
                sp.edit().putInt("colorPrimaryDark", color).apply();
                break;
            case 2:
                //colorAccent
                sp.edit().putInt("colorAccent", color).apply();
                break;
        }
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
            childFragments = new Fragment[] {
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
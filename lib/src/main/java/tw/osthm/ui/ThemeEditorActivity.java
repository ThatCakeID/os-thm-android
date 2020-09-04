package tw.osthm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tw.osthm.ui.fragments.FragmentThmEdit1;
import tw.osthm.ui.fragments.FragmentThmEdit2;
import tw.osthm.ui.fragments.FragmentThmEdit3;

import tw.osthm.R;

public class ThemeEditorActivity extends AppCompatActivity {
    private ViewPager2 mPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    private ImageView image_back;
    private ImageView image_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_editor);
        initializeViews();
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        mPager.setAdapter(pagerAdapter);
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
    }

    private void initializeViews() {
        mPager = findViewById(R.id.viewPager_thmedit);
        image_back = findViewById(R.id.image_back);
        image_save = findViewById(R.id.image_save);
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        private Fragment[] childFragments;

        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
            childFragments = new Fragment[] {
                    new FragmentThmEdit1(), // 0
                    new FragmentThmEdit2(), // 1
                    new FragmentThmEdit3() // 2
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
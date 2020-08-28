package tw.osthm.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import tw.osthm.ui.fragments.FragmentThmEdit1;
import tw.osthm.ui.fragments.FragmentThmEdit2;
import tw.osthm.ui.fragments.FragmentThmEdit3;

import tw.osthm.R;

public class ThemeEditorActivity extends AppCompatActivity {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_editor);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.viewPager_thmedit);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

    }

    private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private Fragment[] childFragments;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            childFragments = new Fragment[] {
                    new FragmentThmEdit1(), // 0
                    new FragmentThmEdit2(), // 1
                    new FragmentThmEdit3(), // 2
            };
        }

        @Override
        public Fragment getItem(int position) {
            return childFragments[position];
        }

        @Override
        public int getCount() {
            return childFragments.length;
        }
    }

}
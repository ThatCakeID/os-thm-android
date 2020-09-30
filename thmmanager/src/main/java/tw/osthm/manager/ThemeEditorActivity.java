package tw.osthm.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import tw.osthm.OsThmMetadata;
import tw.osthm.OsThmTheme;
import tw.osthm.manager.fragments.FragmentThmEdit1;
import tw.osthm.manager.fragments.FragmentThmEdit2;
import tw.osthm.manager.fragments.FragmentThmEdit3;
import tw.osthm.manager.fragments.FragmentThmEdit4;
import tw.osthm.osthmEngine;
import tw.osthm.osthmException;

public class ThemeEditorActivity extends AppCompatActivity implements ColorPickerDialogListener {
    private ViewPager2 mPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    private ImageView image_back;
    private ImageView image_save;
    private SharedPreferences sp;
    private static FragmentThmEdit1 fragment1;
    private static FragmentThmEdit2 fragment2;
    private static FragmentThmEdit3 fragment3;
    private static FragmentThmEdit4 fragment4;

    private View bottomsheetview;
    private BottomSheetDialog bottomSheetDialog;
    private ImageView image_saveb;
    private TextView til1, til2, til3, til4;

    private OsThmTheme theme;

    public static int TEXT_COLOR = -16777216;
    public static int ACCENT_COLOR = -720809;

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

    // FRAGMENT 4
    public static final int COLOR_DIALOG_DIALOG_ID = 17;
    public static final int COLOR_DIALOG_TEXT_DIALOG_ID = 18;
    public static final int COLOR_DIALOG_TINT_DIALOG_ID = 19;

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
        fragment4 = new FragmentThmEdit4();

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
                    if (getIntent().getBooleanExtra("isEditing", false)) {
                        OsThmMetadata thmMetadata = osthmEngine.getThemeMetadata(getIntent()
                                .getStringExtra("theme"));
                        til1.setText(thmMetadata.themesname);
                        til2.setText(thmMetadata.themesauthor);
                        til3.setText(thmMetadata.themesinfo);
                        til4.setText(Integer.toString(thmMetadata.themeversion));
                    }
                    bottomSheetDialog.show();
                }
            }
        });

        image_saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                if (!til1.getText().toString().equals("") && !til2.getText().toString().equals("") && !til3
                        .getText().toString().equals("") && !til4.getText().toString().equals("")) {
                    try {
                        if (getIntent().getBooleanExtra("isEditing", false)) {
                            osthmEngine.editTheme(sp.getInt("colorPrimary", -14575885),
                                    sp.getInt("colorPrimaryText", -1), sp.getInt("colorPrimaryDark", -15242838),
                                    sp.getInt("colorStatusbarTint", 1), sp.getInt("colorBackground", -1),
                                    sp.getInt("colorBackgroundText", -16777216), sp.getInt("colorAccent", -720809),
                                    sp.getInt("colorAccentText", -1), sp.getInt("shadow", 1),
                                    sp.getInt("colorControlHighlight", 1073741824), sp.getInt("colorHint", -5723992),
                                    sp.getInt("colorPrimaryTint", -1), sp.getInt("colorBackgroundTint", -16777216),
                                    sp.getInt("colorPrimaryCard", -1), sp.getInt("colorBackgroundCard", -1),
                                    sp.getInt("colorPrimaryCardText", -16777216), sp.getInt("colorBackgroundCardText", -16777216),
                                    sp.getInt("colorPrimaryCardTint", -16777216), sp.getInt("colorBackgroundCardTint", -16777216),
                                    sp.getInt("colorDialog", -1), sp.getInt("colorDialogText", -16777216), sp.getInt("colorDialogTint", -16777216),
                                    til1.getText().toString(), til3.getText().toString(), til2.getText().toString(),
                                    Integer.parseInt(til4.getText().toString()),
                                    osthmEngine.getThemeMetadata(getIntent().getStringExtra("theme")).uuid);
                        } else {
                            osthmEngine.addTheme(sp.getInt("colorPrimary", -14575885),
                                    sp.getInt("colorPrimaryText", -1), sp.getInt("colorPrimaryDark", -15242838),
                                    sp.getInt("colorStatusbarTint", 1), sp.getInt("colorBackground", -1),
                                    sp.getInt("colorBackgroundText", -16777216), sp.getInt("colorAccent", -720809),
                                    sp.getInt("colorAccentText", -1), sp.getInt("shadow", 1),
                                    sp.getInt("colorControlHighlight", 1073741824), sp.getInt("colorHint", -5723992),
                                    sp.getInt("colorPrimaryTint", -1), sp.getInt("colorBackgroundTint", -16777216),
                                    sp.getInt("colorPrimaryCard", -1), sp.getInt("colorBackgroundCard", -1),
                                    sp.getInt("colorPrimaryCardText", -16777216), sp.getInt("colorBackgroundCardText", -16777216),
                                    sp.getInt("colorPrimaryCardTint", -16777216), sp.getInt("colorBackgroundCardTint", -16777216),
                                    sp.getInt("colorDialog", -1), sp.getInt("colorDialogText", -16777216), sp.getInt("colorDialogTint", -16777216),
                                    til1.getText().toString(), til3.getText().toString(), til2.getText().toString(),
                                    Integer.parseInt(til4.getText().toString()));
                        }
                        finish();
                    } catch (osthmException err) {
                        makeSnackbar(err.getMessage(), 0xFFD32F2F,
                                0xFFFFFFFF, R.drawable.ic_close_white);
                    }
                } else {
                    makeSnackbar("Please fill all the blanks", 0xFFD32F2F,
                            0xFFFFFFFF, R.drawable.ic_close_white);
                }
            }
        });

        pagerAdapter = new ScreenSlidePagerAdapter(this);
        mPager.setOffscreenPageLimit(4);
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
        bottomSheetDialog.setContentView(bottomsheetview);

        ((View) bottomsheetview.getParent()).setBackgroundColor(Color.TRANSPARENT);
        til1 = bottomsheetview.findViewById(R.id.til1);
        til2 = bottomsheetview.findViewById(R.id.til2);
        til3 = bottomsheetview.findViewById(R.id.til3);
        til4 = bottomsheetview.findViewById(R.id.til4);

        /* Apply the theme to bottom_sheet_save_theme (disabled coz TIL is cancerous to customize)

        // Apply colors
        bottomsheetview.findViewById(R.id.bottomsheet_bar).setBackgroundTintList(ColorStateList.valueOf(theme.colorDialog));
        bottomsheetview.findViewById(R.id.bottomsheet_root).setBackgroundColor(theme.colorDialog);

        ((TextView)bottomsheetview.findViewById(R.id.text_title)).setTextColor(theme.colorDialogText);

        image_saveb.setColorFilter(theme.colorDialogTint);
        image_saveb.setColorFilter(theme.colorControlHighlight);

        // til1.setBackgroundColor(theme.colorAccent);
        til1.setHintTextColor(theme.colorHint);
        til1.setTextColor(theme.colorDialogText);

        // til2.setBackgroundColor(theme.colorAccent);
        til2.setHintTextColor(theme.colorHint);
        til2.setTextColor(theme.colorDialogText);

        // til3.setBackgroundColor(theme.colorAccent);
        til3.setHintTextColor(theme.colorHint);
        til3.setTextColor(theme.colorDialogText);

        // til4.setBackgroundColor(theme.colorAccent);
        til4.setHintTextColor(theme.colorHint);
        til4.setTextColor(theme.colorDialogText);
         */
    }

    private void loadColors() {
        OsThmTheme parsedTheme = osthmEngine.getTheme(getIntent().getStringExtra("theme"));

        sp.edit()
                .putInt("colorPrimary", parsedTheme.colorPrimary)
                .putInt("colorPrimaryText", parsedTheme.colorPrimaryText)
                .putInt("colorPrimaryDark", parsedTheme.colorPrimaryDark)
                .putInt("colorStatusbarTint", parsedTheme.colorStatusbarTint)
                .putInt("colorBackground", parsedTheme.colorBackground)
                .putInt("colorBackgroundText", parsedTheme.colorBackgroundText)
                .putInt("colorAccent", parsedTheme.colorAccent)
                .putInt("colorAccentText", parsedTheme.colorAccentText)
                .putInt("shadow", parsedTheme.shadow)
                .putInt("colorControlHighlight", parsedTheme.colorControlHighlight)
                .putInt("colorHint", parsedTheme.colorHint)
                .putInt("colorPrimaryTint", parsedTheme.colorPrimaryTint)
                .putInt("colorBackgroundTint", parsedTheme.colorBackgroundTint)
                .putInt("colorPrimaryCard", parsedTheme.colorPrimaryCard)
                .putInt("colorBackgroundCard", parsedTheme.colorBackgroundCard)
                .putInt("colorPrimaryCardText", parsedTheme.colorPrimaryCardText)
                .putInt("colorBackgroundCardText", parsedTheme.colorBackgroundCardText)
                .putInt("colorPrimaryCardTint", parsedTheme.colorPrimaryCardTint)
                .putInt("colorBackgroundCardTint", parsedTheme.colorBackgroundCardTint)
                .putInt("colorDialog", parsedTheme.colorDialog)
                .putInt("colorDialogText", parsedTheme.colorDialogText)
                .putInt("colorDialogTint", parsedTheme.colorDialogTint).apply();
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
            case COLOR_DIALOG_DIALOG_ID:
                // colorDialog
                sp.edit().putInt("colorDialog", color).apply();
                break;
            case COLOR_DIALOG_TEXT_DIALOG_ID:
                // colorDialogText
                sp.edit().putInt("colorDialogText", color).apply();
                break;
            case COLOR_DIALOG_TINT_DIALOG_ID:
                // colorDialogTint
                sp.edit().putInt("colorDialogTint", color).apply();
                break;
        }

        refreshFragments();
    }

    public static void refreshFragments() {
        fragment1.refreshViews();
        fragment2.refreshViews();
        fragment3.refreshViews();
        fragment4.refreshViews();
    }

    private void makeSnackbar(String msg, int bcolor, int tcolor, int image) {
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        Snackbar snackbar = Snackbar.make(viewGroup, "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout snacklayout = (Snackbar.SnackbarLayout) snackbar.getView();

        View snackview = getLayoutInflater().inflate(R.layout.snackbar_layout, null);
        View snackroot = snackview.findViewById(R.id.root);

        TextView textView2 = snackview.findViewById(R.id.textView2);
        ImageView imageView3 = snackview.findViewById(R.id.imageView3);

        snackroot.setBackgroundColor(bcolor);

        textView2.setText(msg);
        textView2.setTextColor(tcolor);

        imageView3.setColorFilter(tcolor);
        imageView3.setImageResource(image);

        snacklayout.setPadding(0, 0, 0, 0);
        snacklayout.addView(snackview);

        snackbar.show();
        /* Red : #D32F2F
         * Blue : #1976D2
         * Green : #43A047
         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadTheme();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void loadTheme() {
        theme = osthmEngine.getCurrentTheme();
        findViewById(R.id.rootView).setBackgroundColor(theme.colorBackground);
        findViewById(R.id.linear_title).setBackgroundColor(theme.colorPrimary);

        getWindow().setStatusBarColor(theme.colorPrimaryDark);
        if (theme.colorStatusbarTint == 0
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ((TextView) findViewById(R.id.text_back)).setTextColor(theme.colorPrimaryText);

        image_back.setColorFilter(theme.colorPrimaryTint);
        image_back.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, null));
        image_save.setColorFilter(theme.colorPrimaryTint);
        image_save.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, null));

        findViewById(R.id.linear_title).setElevation(theme.shadow == 1 ? ThmMgrUtils.toDip(getApplicationContext(), 5f) : 0f);

        TEXT_COLOR = theme.colorBackgroundText;
        ACCENT_COLOR = theme.colorAccent;

        fragment1.refreshViews();
        fragment2.refreshViews();
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
                    fragment3, // 2
                    fragment4  // 3
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
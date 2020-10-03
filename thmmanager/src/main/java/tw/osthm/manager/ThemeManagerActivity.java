package tw.osthm.manager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import tw.osthm.OsThmMetadata;
import tw.osthm.OsThmTheme;
import tw.osthm.osthmEngine;
import tw.osthm.osthmException;
import tw.osthm.osthmManager;

public class ThemeManagerActivity extends AppCompatActivity {
    private ImageView image_help;

    private FloatingActionButton fab;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;

    private Animation fab_open;
    private Animation fab_close;
    private Animation fab_clock;
    private Animation fab_anticlock;

    private TextView textview_create;
    private TextView textview_import;

    private GridView gridview1;
    private ArrayList<HashMap<String, Object>> arrayList;

    private View bottomsheetView;

    private LinearLayout linear_export;
    private LinearLayout linear_info;
    private LinearLayout linear_clone;

    private ImageView image_edit;
    private ImageView image_delete;

    private TextView text_title;
    private TextView text_subtitle;

    private boolean isOpen;
    private BottomSheetDialog bottomSheetDialog;
    private int selectedNum = 0;

    private SharedPreferences sp;
    private OsThmTheme theme;

    public static final int OPEN_REQUEST_CODE = 1945; //hmm =w=
    public static String currentThemeUUID = "";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_thememgr);

        initializeViews();

        image_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThemeManagerActivity.this, DocumentationActivity.class));
            }
        });

        // FAB methods
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFabs();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFabs();
                showSelectDialog();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFabs();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ThemeEditorActivity.class);
                intent.putExtra("isEditing", false);

                startActivity(intent);
            }
        });

        arrayList = new ArrayList<>();
        gridview1.setAdapter(new ThemeGridPreview(getApplicationContext(), arrayList));

        gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    osthmEngine.setCurrentTheme(arrayList.get(i).get("uuid").toString());

                    makeSnackbar("Theme set!", 0xFF43A047, 0xFFFFFFFF,
                            R.drawable.ic_done_white);

                    loadTheme();

                    currentThemeUUID = osthmEngine.getCurrentThemeUUID();

                    ((BaseAdapter) gridview1.getAdapter()).notifyDataSetChanged();

                } catch (osthmException err) {
                    makeSnackbar(err.getMessage(), 0xFFD32F2F, 0xFFFFFFFF,
                            R.drawable.ic_close_white);
                }
            }
        });

        gridview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedNum = i;

                OsThmMetadata themeMetadata = osthmEngine.getThemeMetadata(arrayList
                        .get(i).get("uuid").toString());
                text_title.setText(themeMetadata.themesname);
                text_subtitle.setText(themeMetadata.themesauthor);

                bottomSheetDialog.show();
                return true;
            }
        });

        // BottomSheet thingy-------------------------------------------------

        image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();

                try {
                    osthmEngine.removeTheme(arrayList.get(selectedNum).get("uuid").toString());
                    makeSnackbar("Theme deleted!", 0xFF43A047, 0xFFFFFFFF,
                            R.drawable.ic_done_white);
                    refreshTheme();

                } catch (osthmException err) {
                    makeSnackbar(err.getMessage(), 0xFFD32F2F, 0xFFFFFFFF,
                            R.drawable.ic_close_white);
                    refreshTheme();
                }
            }
        });

        linear_clone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();

                View bottom_sheet_save_theme = getLayoutInflater().inflate(R.layout.bottomsheet_newtheme, null);

                final EditText name = bottom_sheet_save_theme.findViewById(R.id.til1);
                final EditText author = bottom_sheet_save_theme.findViewById(R.id.til2);
                final EditText description = bottom_sheet_save_theme.findViewById(R.id.til3);
                final EditText version = bottom_sheet_save_theme.findViewById(R.id.til4);

                OsThmMetadata thmMetadata = new OsThmMetadata(arrayList.get(selectedNum));

                name.setText(thmMetadata.themesname);
                author.setText(thmMetadata.themesauthor);
                description.setText(thmMetadata.themesinfo);
                version.setText(Integer.toString(thmMetadata.themeversion));

                ImageView save = bottom_sheet_save_theme.findViewById(R.id.image_save);

                /* Apply the theme to bottom_sheet_save_theme (disabled coz TIL is cancerous to customize)

                // Apply colors
                bottom_sheet_save_theme.findViewById(R.id.bottomsheet_bar).setBackgroundTintList(ColorStateList.valueOf(theme.colorDialog));
                bottom_sheet_save_theme.findViewById(R.id.bottomsheet_root).setBackgroundColor(theme.colorDialog);

                ((TextView)bottom_sheet_save_theme.findViewById(R.id.text_title)).setTextColor(theme.colorDialogText);

                save.setColorFilter(theme.colorDialogTint);
                save.setBackground(new RippleDrawable(ColorStateList.valueOf(theme.colorControlHighlight), null, null));

                name.setBackgroundTintList(ColorStateList.valueOf(theme.colorAccent));
                name.setHighlightColor(theme.colorAccent);
                ((TextInputLayout)bottom_sheet_save_theme.findViewById(R.id.cancerousTil1)).setDefaultHintTextColor(ColorStateList.valueOf(theme.colorHint));
                name.setTextColor(theme.colorDialogText);

                author.setBackgroundTintList(ColorStateList.valueOf(theme.colorAccent));
                author.setHintTextColor(theme.colorHint);
                author.setTextColor(theme.colorDialogText);

                description.setBackgroundTintList(ColorStateList.valueOf(theme.colorAccent));
                description.setHintTextColor(theme.colorHint);
                description.setTextColor(theme.colorDialogText);

                version.setBackgroundTintList(ColorStateList.valueOf(theme.colorAccent));
                version.setHintTextColor(theme.colorHint);
                version.setTextColor(theme.colorDialogText);
                 */

                final BottomSheetDialog bsd = new BottomSheetDialog(ThemeManagerActivity.this);
                bsd.setContentView(bottom_sheet_save_theme);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OsThmTheme theme = new OsThmTheme((HashMap<String, Integer>) new
                                Gson().fromJson(arrayList.get(selectedNum).get("themesjson")
                                .toString(), new TypeToken<HashMap<String, Integer>>() {
                        }.getType()));

                        String name_data = name.getText().toString();
                        String author_data = author.getText().toString();
                        String description_data = description.getText().toString();
                        String version_data = version.getText().toString();

                        try {
                            osthmEngine.addTheme(theme, name_data, description_data, author_data, Integer.parseInt(version_data));
                            makeSnackbar("Successfully cloned theme!", 0xFF43A047, 0xFFFFFFFF,
                                    R.drawable.ic_done_white);
                            refreshTheme();
                        } catch (osthmException e) {
                            makeSnackbar(e.getMessage(), 0xFFD32F2F, 0xFFFFFFFF,
                                    R.drawable.ic_close_white);
                        }
                        bsd.dismiss();
                    }
                });

                ((View) bottom_sheet_save_theme.getParent()).setBackgroundColor(Color.TRANSPARENT);

                bsd.show();
            }
        });

        linear_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                try {
                    makeSnackbar("Saved in " + osthmManager.exportThemeFile(osthmEngine
                                    .exportThemes(new String[]{arrayList.get(selectedNum)
                                            .get("uuid").toString()})), 0xFF43A047,
                            0xFFFFFFFF, R.drawable.ic_done_white);
                } catch (osthmException err) {
                    makeSnackbar(err.getMessage(), 0xFFD32F2F, 0xFFFFFFFF,
                            R.drawable.ic_close_white);
                }
            }
        });

        linear_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();

                final BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(ThemeManagerActivity.this);
                View bottomsheetView1 = getLayoutInflater().inflate(R.layout.bottomsheet_info, null);

                TextView text_name = bottomsheetView1.findViewById(R.id.text_name);
                TextView text_description = bottomsheetView1.findViewById(R.id.text_description);
                TextView text_author = bottomsheetView1.findViewById(R.id.text_author);
                TextView text_version = bottomsheetView1.findViewById(R.id.text_version);
                TextView text_osthm = bottomsheetView1.findViewById(R.id.text_osthm);

                bottomsheetView1.findViewById(R.id.bottomsheet_bar).setBackgroundTintList(ColorStateList.valueOf(theme.colorDialog));
                bottomsheetView1.findViewById(R.id.bottomsheet_root).setBackgroundColor(theme.colorDialog);
                ((TextView)bottomsheetView1.findViewById(R.id.text_title)).setTextColor(theme.colorDialogText);

                text_name.setTextColor(theme.colorDialogText);
                text_description.setTextColor(theme.colorDialogText);
                text_author.setTextColor(theme.colorDialogText);
                text_version.setTextColor(theme.colorDialogText);
                text_osthm.setTextColor(theme.colorDialogText);

                ((ImageView)bottomsheetView1.findViewById(R.id.image_name)).setColorFilter(theme.colorDialogTint);
                ((ImageView)bottomsheetView1.findViewById(R.id.image_description)).setColorFilter(theme.colorDialogTint);
                ((ImageView)bottomsheetView1.findViewById(R.id.image_author)).setColorFilter(theme.colorDialogTint);
                ((ImageView)bottomsheetView1.findViewById(R.id.image_version)).setColorFilter(theme.colorDialogTint);

                OsThmMetadata themeMetadata = osthmEngine.getThemeMetadata(arrayList
                        .get(selectedNum).get("uuid").toString());

                text_name.setText("Name : " + themeMetadata.themesname);
                text_description.setText("Description : " + themeMetadata.themesinfo);
                text_author.setText("Author : " + themeMetadata.themesauthor);
                text_version.setText("Version : " + themeMetadata.themeversion);
                text_osthm.setText("Engine Version : " + themeMetadata.os_thm_version);

                bottomSheetDialog1.setContentView(bottomsheetView1);
                ((View) bottomsheetView1.getParent()).setBackgroundColor(Color.TRANSPARENT);
                bottomSheetDialog1.show();
            }
        });

        image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();

                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ThemeEditorActivity.class);
                intent.putExtra("isEditing", true);
                intent.putExtra("theme", arrayList.get(selectedNum).get("uuid").toString());
                startActivity(intent);
            }
        });
        // -----------------------------------------------------------------

        image_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ThemeManagerActivity.this, DocumentationActivity.class);
                startActivity(i);
            }
        });

        if (!sp.getBoolean("taptargetview", false)) {
            final TapTarget fab_open_taptarget = TapTarget.forView(fab, "Add themes", "Click here to add themes into your theme list");
            final TapTarget fab2_taptarget = TapTarget.forView(fab2, "Create Theme", "Click here to create a theme yourself!");

            fab_open_taptarget.textTypeface(ResourcesCompat.getFont(this, R.font.googlesans));
            fab2_taptarget.textTypeface(ResourcesCompat.getFont(this, R.font.googlesans));

            TapTargetSequence sequence = new TapTargetSequence(this).targets(
                    fab_open_taptarget,
                    TapTarget.forView(fab1, "Import theme", "Click here to import a theme into your theme list")
                            .textTypeface(ResourcesCompat.getFont(this, R.font.googlesans)),
                    fab2_taptarget,
                    TapTarget.forView(image_help, "Documentation", "Click here to read the documentation on how to use os-thm")
                            .textTypeface(ResourcesCompat.getFont(this, R.font.googlesans))
                    // Sad, doesn't work :(
                    // TapTarget.forView(gridview1.getChildAt(1), "Set your theme", "Click on this Dark theme to set your theme into the dark theme."),
                    // TapTarget.forView(gridview1.getChildAt(1), "Get theme info", "Click hold on this Dark theme to get the info of the theme.")
            );

            sequence.listener(new TapTargetSequence.Listener() {
                @Override
                public void onSequenceFinish() {
                    sp.edit().putBoolean("taptargetview", true).apply();
                }

                @Override
                public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                    if (lastTarget == fab_open_taptarget || lastTarget == fab2_taptarget) {
                        toggleFabs();
                    }
                }

                @Override
                public void onSequenceCanceled(TapTarget lastTarget) {
                }
            });

            sequence.start();
        }
    }

    private void toggleFabs() {
        textview_create.setVisibility(isOpen ? View.INVISIBLE : View.VISIBLE);
        textview_import.setVisibility(isOpen ? View.INVISIBLE : View.VISIBLE);

        fab2.startAnimation(isOpen ? fab_close : fab_open);
        fab1.startAnimation(isOpen ? fab_close : fab_open);

        fab.startAnimation(isOpen ? fab_anticlock : fab_clock);

        fab2.setClickable(!isOpen);
        fab1.setClickable(!isOpen);

        isOpen = !isOpen;

        /*
        if (isOpen) {
            textview_create.setVisibility(View.INVISIBLE);
            textview_import.setVisibility(View.INVISIBLE);
            textview_online.setVisibility(View.INVISIBLE);
            fab3.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.startAnimation(fab_close);
            fab.startAnimation(fab_anticlock);
            fab3.setClickable(false);
            fab2.setClickable(false);
            fab1.setClickable(false);
            isOpen = false;
        } else {
            textview_create.setVisibility(View.VISIBLE);
            textview_import.setVisibility(View.VISIBLE);
            textview_online.setVisibility(View.VISIBLE);
            fab3.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.startAnimation(fab_open);
            fab.startAnimation(fab_clock);
            fab3.setClickable(true);
            fab2.setClickable(true);
            fab1.setClickable(true);
            isOpen = true;
        }
         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshTheme();
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

        image_help.setColorFilter(theme.colorPrimaryTint);
        image_help.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, null));

        findViewById(R.id.linear_title).setElevation(theme.shadow == 1 ? ThmMgrUtils.toDip(getApplicationContext(), 5f) : 0f);
        fab.setCompatElevation(theme.shadow == 1 ? ThmMgrUtils.toDip(getApplicationContext(), 6f) : 0f);
        fab1.setCompatElevation(theme.shadow == 1 ? ThmMgrUtils.toDip(getApplicationContext(), 6f) : 0f);
        fab2.setCompatElevation(theme.shadow == 1 ? ThmMgrUtils.toDip(getApplicationContext(), 6f) : 0f);

        fab.setRippleColor(theme.colorControlHighlight);
        fab1.setRippleColor(theme.colorControlHighlight);
        fab2.setRippleColor(theme.colorControlHighlight);

        fab.setBackgroundTintList(ColorStateList.valueOf(theme.colorAccent));
        fab1.setBackgroundTintList(ColorStateList.valueOf(theme.colorAccent));
        fab2.setBackgroundTintList(ColorStateList.valueOf(theme.colorAccent));

        fab.setColorFilter(theme.colorAccentText);
        fab1.setColorFilter(theme.colorAccentText);
        fab2.setColorFilter(theme.colorAccentText);

        gridview1.setSelector(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, new ShapeDrawable()));

        bottomsheetView.findViewById(R.id.bottomsheet_bar).setBackgroundTintList(ColorStateList.valueOf(theme.colorDialog));
        bottomsheetView.findViewById(R.id.bottomsheet_root).setBackgroundColor(theme.colorDialog);

        image_delete.setColorFilter(theme.colorDialogTint);
        image_edit.setColorFilter(theme.colorDialogTint);

        ((ImageView) bottomsheetView.findViewById(R.id.image_clone)).setColorFilter(theme.colorDialogTint);
        ((ImageView) bottomsheetView.findViewById(R.id.image_export)).setColorFilter(theme.colorDialogTint);
        ((ImageView) bottomsheetView.findViewById(R.id.image_info)).setColorFilter(theme.colorDialogTint);

        image_delete.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, null));
        image_edit.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, null));
        linear_clone.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, new ShapeDrawable()));
        linear_export.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, new ShapeDrawable()));
        linear_info.setBackground(new RippleDrawable(ColorStateList.valueOf(theme
                .colorControlHighlight), null, new ShapeDrawable()));

        ((TextView) bottomsheetView.findViewById(R.id.text_title)).setTextColor(theme.colorDialogText);
        ((TextView) bottomsheetView.findViewById(R.id.text_subtitle)).setTextColor(theme.colorDialogText);
        ((TextView) bottomsheetView.findViewById(R.id.text_clone)).setTextColor(theme.colorDialogText);
        ((TextView) bottomsheetView.findViewById(R.id.text_export)).setTextColor(theme.colorDialogText);
        ((TextView) bottomsheetView.findViewById(R.id.text_info)).setTextColor(theme.colorDialogText);

        textview_create.setTextColor(theme.colorDialogText);
        textview_create.setBackgroundTintList(ColorStateList.valueOf(theme.colorDialog));
        textview_create.setElevation(theme.shadow == 1 ? ThmMgrUtils.toDip(getApplicationContext(), 2.5f) : 0f);
        textview_import.setTextColor(theme.colorDialogText);
        textview_import.setBackgroundTintList(ColorStateList.valueOf(theme.colorDialog));
        textview_import.setElevation(theme.shadow == 1 ? ThmMgrUtils.toDip(getApplicationContext(), 2.5f) : 0f);
    }

    private void refreshTheme() {
        currentThemeUUID = osthmEngine.getCurrentThemeUUID();
        arrayList.clear();
        arrayList.addAll(osthmEngine.getThemeList());
        ((BaseAdapter) gridview1.getAdapter()).notifyDataSetChanged();
    }

    private void initializeViews() {
        image_help = findViewById(R.id.image_help);

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);

        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);

        textview_create = findViewById(R.id.textview_create);
        textview_import = findViewById(R.id.textview_import);

        gridview1 = findViewById(R.id.gridview1);
        sp = getSharedPreferences("mgrdata", Context.MODE_PRIVATE);

        bottomsheetView = getLayoutInflater().inflate(R.layout.bottomsheet_multichoices, null);

        image_delete = bottomsheetView.findViewById(R.id.image_delete);
        linear_export = bottomsheetView.findViewById(R.id.linear_export);
        linear_info = bottomsheetView.findViewById(R.id.linear_info);
        linear_clone = bottomsheetView.findViewById(R.id.linear_clone);
        image_edit = bottomsheetView.findViewById(R.id.image_edit);
        text_title = bottomsheetView.findViewById(R.id.text_title);
        text_subtitle = bottomsheetView.findViewById(R.id.text_subtitle);

        bottomSheetDialog = new BottomSheetDialog(ThemeManagerActivity.this);
        bottomSheetDialog.setContentView(bottomsheetView);
        ((View) bottomsheetView.getParent()).setBackgroundColor(Color.TRANSPARENT);
    }

    private void makeSnackbar(String msg, int bcolor, int tcolor, int image) {
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

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

    private void showSelectDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThemeManagerActivity.this);
        alertDialog.setTitle("Import a theme");
        String[] items = {"Import .os-thm file", "Paste json theme data"};

        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("*/*");
                        startActivityForResult(intent, OPEN_REQUEST_CODE);
                        break;
                    case 1:
                        dialog.dismiss();
                        showJsonPasteDialog();
                        break;
                }
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void showJsonPasteDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThemeManagerActivity.this);
        alertDialog.setTitle("Paste your json data");
        final EditText editText = new EditText(this);
        alertDialog.setView(editText);

        alertDialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    dialogInterface.dismiss();
                    osthmEngine.importThemes(editText.getText().toString());
                    makeSnackbar("Theme(s) imported!", 0xFF43A047, 0xFFFFFFFF, R.drawable.ic_done_white);
                } catch (osthmException e) {
                    makeSnackbar(e.getMessage(), 0xFFD32F2F, 0xFFFFFFFF, R.drawable.ic_close_white);
                }
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();

    }

    private String readFile(Uri uri) throws IOException {

        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String currentline;

        while ((currentline = reader.readLine()) != null) {
            stringBuilder.append(currentline + "\n");
        }

        inputStream.close();
        return stringBuilder.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == OPEN_REQUEST_CODE) {
                if (data != null) {
                    Uri uri = data.getData();
                    try {
                        String content = readFile(uri);
                        osthmEngine.importThemes(content);
                        makeSnackbar("Theme(s) imported!", 0xFF43A047, 0xFFFFFFFF, R.drawable.ic_done_white);

                    } catch (IOException e) {
                        makeSnackbar(e.toString(), 0xFFD32F2F, 0xFFFFFFFF, R.drawable.ic_close_white);

                    } catch (osthmException e) {
                        makeSnackbar(e.getMessage(), 0xFFD32F2F, 0xFFFFFFFF, R.drawable.ic_close_white);
                    }
                }
            }
        }
    }
}

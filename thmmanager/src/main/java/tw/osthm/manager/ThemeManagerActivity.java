package tw.osthm.manager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import tw.osthm.OsThmMetadata;
import tw.osthm.osthmEngine;
import tw.osthm.osthmException;

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
    private boolean isOpen;
    private GridView gridview1;
    private ArrayList<HashMap<String, Object>> arrayList;
    private SharedPreferences sp;

    private View bottomsheetView;
    private LinearLayout linear_export;
    private LinearLayout linear_info;
    private ImageView image_edit;
    private ImageView image_delete;
    private TextView text_title;
    private TextView text_subtitle;
    private BottomSheetDialog bottomSheetDialog;
    private int selectedNum = -1;

    public static final int OPEN_REQUEST_CODE = 1945;

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
        //FAB method
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFabs();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectDialog();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ThemeEditorActivity.class);
                intent.putExtra("isEditing", false);
                startActivity(intent);
            }
        });
        refreshTheme();
        gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    osthmEngine.setCurrentTheme(arrayList.get(i).get("uuid").toString());
                    makeSnackbar("Theme set!", 0xFF43A047, 0xFFFFFFFF,
                            R.drawable.ic_done_white);
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
        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                selectedNum = -1;
            }
        });
        image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                if (selectedNum != -1) {
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
                    selectedNum = -1;
                }
            }
        });
        linear_export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
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
                OsThmMetadata themeMetadata = osthmEngine.getThemeMetadata(arrayList
                        .get(selectedNum).get("uuid").toString());
                text_name.setText("Name : " + themeMetadata.themesname);
                text_description.setText("Description : " + themeMetadata.themesinfo);
                text_author.setText("Author : " + themeMetadata.themesauthor);
                text_version.setText("Version : " +themeMetadata.themeversion);
                text_osthm.setText("Engine Version : " +themeMetadata.os_thm_version);
                bottomSheetDialog1.setContentView(bottomsheetView1);
                ((View) bottomsheetView1.getParent()).setBackgroundColor(Color.TRANSPARENT);
                bottomSheetDialog1.show();
                selectedNum = -1;
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
                selectedNum = -1;
            }
        });
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
                    //TapTarget.forView(gridview1.getChildAt(1), "Set your theme", "Click on this Dark theme to set your theme into the dark theme."),
                    //TapTarget.forView(gridview1.getChildAt(1), "Get theme info", "Click hold on this Dark theme to get the info of the theme.")
            );

            sequence.listener(new TapTargetSequence.Listener() {
                @Override
                public void onSequenceFinish() {
                    sp.edit().putBoolean("taptargetview", true);
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
        if (isOpen) {
            textview_create.setVisibility(View.INVISIBLE);
            textview_import.setVisibility(View.INVISIBLE);
            fab2.startAnimation(fab_close);
            fab1.startAnimation(fab_close);
            fab.startAnimation(fab_anticlock);
            fab2.setClickable(false);
            fab1.setClickable(false);
            isOpen = false;
        } else {
            textview_create.setVisibility(View.VISIBLE);
            textview_import.setVisibility(View.VISIBLE);
            fab2.startAnimation(fab_open);
            fab1.startAnimation(fab_open);
            fab.startAnimation(fab_clock);
            fab2.setClickable(true);
            fab1.setClickable(true);
            isOpen = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshTheme();
    }

    private void refreshTheme() {
        arrayList = osthmEngine.getThemeList();
        gridview1.setAdapter(new ThemeGridPreview(getApplicationContext(), arrayList));
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
        sp = getSharedPreferences("colordata", Context.MODE_PRIVATE);
        bottomsheetView = getLayoutInflater().inflate(R.layout.bottomsheet_multichoices, null);
        image_delete = bottomsheetView.findViewById(R.id.image_delete);
        linear_export = bottomsheetView.findViewById(R.id.linear_export);
        linear_info = bottomsheetView.findViewById(R.id.linear_info);
        image_edit = bottomsheetView.findViewById(R.id.image_edit);
        text_title = bottomsheetView.findViewById(R.id.text_title);
        text_subtitle = bottomsheetView.findViewById(R.id.text_subtitle);
        bottomSheetDialog = new BottomSheetDialog(ThemeManagerActivity.this);
        bottomSheetDialog.setContentView(bottomsheetView);
        ((View) bottomsheetView.getParent()).setBackgroundColor(Color.TRANSPARENT);
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

    private void showSelectDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThemeManagerActivity.this);
        alertDialog.setTitle("Import a theme");
        String[] items = {".json File","Paste json data"};
        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case 0:
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("application/json");
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
                    osthmEngine.importThemes(editText.getText().toString());
                    Toast.makeText(ThemeManagerActivity.this, "Import Successful", Toast.LENGTH_LONG).show();
                    dialogInterface.dismiss();
                } catch (osthmException e) {
                    Toast.makeText(ThemeManagerActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();

    }

    private String readFile(Uri uri) throws IOException {

        InputStream inputStream =
                getContentResolver().openInputStream(uri);
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(
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
                    } catch (IOException e) {
                        // Handle error here
                        e.printStackTrace();
                    } catch (osthmException e) {
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}

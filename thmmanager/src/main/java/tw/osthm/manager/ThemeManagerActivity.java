package tw.osthm.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import tw.osthm.osthmEngine;

public class ThemeManagerActivity extends AppCompatActivity {
    private ImageView image_back;
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

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_thememgr);
        initializeViews();
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        image_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : add something here for @Iyxan23
            }
        });
        //FAB method
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        gridview1.setAdapter(new ThemeGridPreview(getApplicationContext(),
                osthmEngine.getThemeList()));
    }

    private void initializeViews() {
        image_back = findViewById(R.id.image_back);
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
    }
}

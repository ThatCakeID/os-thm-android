package tw.osthm.manager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_PERM_CODE = 107;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(0xFFFFFFFF);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            getWindow().setStatusBarColor(0xFF252525);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, REQUEST_PERM_CODE);
        else
            continueActivity();
    }

    private void continueActivity() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ThemeManagerActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                continueActivity();
            } else
                makeSnackbar("Task failed successfully", 0xFFD32F2F, 0xFFFFFFFF,
                        R.drawable.ic_close_white);
        }
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
}

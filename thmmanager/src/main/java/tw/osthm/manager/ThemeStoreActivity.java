package tw.osthm.manager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThemeStoreActivity extends AppCompatActivity {

    private OkHttpClient client;
    private Request request;
    private JSONArray themes;
    private ThemeStoreItemAdapter adapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_store);

        client = new OkHttpClient();
        rv = findViewById(R.id.recycler_view_theme_store);

        // Check if the server is open or not
        request = new Request.Builder()
                .url("https://thatcakeid.com/api/osthm/v1/status.php")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    JSONObject status = new JSONObject(response.body().string());

                    if (!status.getBoolean("open")) {
                        // Server is closed
                        Toast.makeText(getApplicationContext(), status.getString("info"), Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        // Server is open!
                        // Get themes
                        request = new Request.Builder()
                                .url("https://thatcakeid.com/api/osthm/v1/get_themes.php")
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                try {
                                    themes = new JSONArray(response.body().string());
                                    adapter = new ThemeStoreItemAdapter(themes, getApplicationContext());
                                    rv.setAdapter(adapter);
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}
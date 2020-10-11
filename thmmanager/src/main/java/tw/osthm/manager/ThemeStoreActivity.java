package tw.osthm.manager;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import tw.osthm.ArrayMapDeserializerFix;

public class ThemeStoreActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, Object>> themes;
    private ThemeStoreItemAdapter adapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_store);

        rv = findViewById(R.id.recycler_view_theme_store);

        themes = new ArrayList<>();
        adapter = new ThemeStoreItemAdapter(themes, getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        final OkHttpUtil okHttpUtil = new OkHttpUtil(this);
        okHttpUtil.startRequestNetwork(OkHttpUtilController.GET,
                "https://thatcakeid.com/api/os-thm/v1/status.php", "", new OkHttpUtil.RequestListener() {
            @Override
            public void onResponse(String tag, String response) {
                try {
                    JSONObject status = new JSONObject(response);

                    if (!status.getBoolean("open")) {
                        // Server is closed
                        Toast.makeText(getApplicationContext(), status.getString("info"), Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        // Server is open!
                        // Get themes
                        okHttpUtil.startRequestNetwork(OkHttpUtilController.GET,
                                "https://thatcakeid.com/api/os-thm/v1/get_themes.php", "", new OkHttpUtil.RequestListener() {
                                    @Override
                                    public void onResponse(String tag, String response) {
                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                        gsonBuilder.registerTypeAdapter(new TypeToken<ArrayList<HashMap<String, Object>>>() {
                                                }.getType(),
                                                new ArrayMapDeserializerFix());
                                        Gson myGson = gsonBuilder.create();
                                        themes.addAll((ArrayList<HashMap<String, Object>>)
                                                myGson.fromJson(response,
                                                        new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType()));
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onErrorResponse(String tag, String message) {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
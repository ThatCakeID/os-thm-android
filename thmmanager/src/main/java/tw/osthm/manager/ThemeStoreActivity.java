package tw.osthm.manager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThemeStoreActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_store);

        boolean isOpen = false;
        String close_reason;

        // Check if the server is open or not
        try {
            JSONObject status = new JSONObject(get("https://thatcakeid.com/api/osthm/v1/status.php"));
            isOpen = status.getBoolean("open");

            if (!isOpen) {
                // Server is closed
                close_reason = status.getString("info");
                Toast.makeText(this, close_reason, Toast.LENGTH_LONG).show();

                finish();
            }

        } catch (JSONException | IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

            finish();
        }

        // Server is open!
        // Get themes
        JSONArray themes = null;
        try {
            themes = new JSONArray(get("https://thatcakeid.com/api/osthm/v1/get_themes.php"));
        } catch (JSONException | IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

            finish();
            return;
        }

        ThemeStoreItemAdapter adapter = new ThemeStoreItemAdapter(themes, this);
        RecyclerView rv = findViewById(R.id.recycler_view_theme_store);
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }

    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }
}
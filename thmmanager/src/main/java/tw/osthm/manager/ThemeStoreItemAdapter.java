package tw.osthm.manager;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThemeStoreItemAdapter extends RecyclerView.Adapter<ThemeStoreItemAdapter.ViewHolder> {
    private static final String TAG = "ThemeStoreItemAdapter";

    JSONArray datas;

    public ThemeStoreItemAdapter(JSONArray datas) {
        this.datas = datas;
    }

    public void updateView(JSONArray datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_theme_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        JSONObject theme;
        JSONObject themedata;
        try {
            theme = new JSONObject(datas.getString(position));
            themedata = new JSONObject(theme.getString("themesjson"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return datas.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView theme_name;
        TextView theme_author;

        LinearLayout linear_base;
        LinearLayout linear_title;
        ImageView imageview_back;
        ImageView imageview_fab;
        TextView textview_title;
        TextView textview_name;
        ImageView indicator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            theme_name = itemView.findViewById(R.id.theme_name_);
            theme_author = itemView.findViewById(R.id.theme_author_);

            View theme_preview = itemView.findViewById(R.id.theme_preview);

            linear_base = theme_preview.findViewById(R.id.linear_base);
            linear_title = theme_preview.findViewById(R.id.linear_title);
            imageview_back = theme_preview.findViewById(R.id.imageview_back);
            imageview_fab = theme_preview.findViewById(R.id.imageview_fab);
            textview_name = theme_preview.findViewById(R.id.textview_name);
            textview_title = theme_preview.findViewById(R.id.textview_title);
            indicator = theme_preview.findViewById(R.id.theme_selected_indicator);
        }
    }
}
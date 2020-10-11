package tw.osthm.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
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
    Context mContext;

    public ThemeStoreItemAdapter(JSONArray datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
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

        holder.indicator.setVisibility(View.INVISIBLE);

        try {
            theme = new JSONObject(datas.getString(position));
            themedata = new JSONObject(theme.getString("themesjson"));
        } catch (JSONException e) {
            return;
        }

        try {
            holder.theme_name.setText(themedata.getString("themesname"));
            holder.theme_author.setText(themedata.getString("themesauthor"));
    
            holder.textview_name.setTextColor(themedata.getInt("colorBackgroundText"));
            holder.textview_title.setTextColor(themedata.getInt("colorPrimaryText"));
            holder.linear_base.setBackgroundTintList(ColorStateList.valueOf(themedata.getInt("colorBackground")));
            holder.linear_title.setBackgroundTintList(ColorStateList.valueOf(themedata.getInt("colorPrimary")));
            holder.imageview_fab.setBackgroundTintList(ColorStateList.valueOf(themedata.getInt("colorAccent")));
            holder.imageview_fab.setColorFilter(themedata.getInt("colorAccentText"));
            
            holder.imageview_back.setColorFilter(themedata.getInt("colorPrimaryTint"));
    
            holder.linear_title.setElevation(themedata.getInt("shadow") == 1 ? ThmMgrUtils.toDip(mContext, 5f) : 0f);
            holder.imageview_fab.setElevation(themedata.getInt("shadow") == 1 ? ThmMgrUtils.toDip(mContext, 6f) : 0f);
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
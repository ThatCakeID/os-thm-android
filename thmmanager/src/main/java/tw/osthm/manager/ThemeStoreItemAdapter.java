package tw.osthm.manager;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class ThemeStoreItemAdapter extends RecyclerView.Adapter<ThemeStoreItemAdapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> datas;
    Context mContext;

    public ThemeStoreItemAdapter(ArrayList<HashMap<String, Object>> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ThemeStoreItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_theme_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeStoreItemAdapter.ViewHolder holder, int position) {
        holder.theme_name.setText(datas.get(position).get("themesname").toString());
        holder.theme_author.setText(datas.get(position).get("themesauthor").toString());

        HashMap<String, Integer> list2 = new Gson().fromJson(datas.get(position).get("themesjson").toString(), new TypeToken<HashMap<String, Integer>>() {
        }.getType());

        holder.textview_name.setTextColor(list2.get("colorBackgroundText"));
        holder.textview_title.setTextColor(list2.get("colorPrimaryText"));
        holder.linear_base.setBackgroundTintList(ColorStateList.valueOf(list2.get("colorBackground")));
        holder.linear_title.setBackgroundTintList(ColorStateList.valueOf(list2.get("colorPrimary")));
        holder.imageview_fab.setBackgroundTintList(ColorStateList.valueOf(list2.get("colorAccent")));
        holder.imageview_fab.setColorFilter(list2.get("colorAccentText"));
        holder.imageview_back.setColorFilter(list2.get("colorPrimaryTint"));

        holder.linear_title.setElevation(list2.get("shadow") == 1 ? ThmMgrUtils.toDip(mContext, 5f) : 0f);
        holder.imageview_fab.setElevation(list2.get("shadow") == 1 ? ThmMgrUtils.toDip(mContext, 6f) : 0f);
    }

    @Override
    public int getItemCount() {
        return datas.size();
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
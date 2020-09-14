package tw.osthm.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import tw.osthm.R;

public class ThemeGridPreview extends BaseAdapter {
    ArrayList<HashMap<String, Object>> list;
    LayoutInflater inflater;
    private LinearLayout linear_base;
    private LinearLayout linear_title;
    private ImageView imageview_back;
    private ImageView imageview_fab;
    private TextView textview_title;
    private TextView textview_name;

    public ThemeGridPreview(Context mContext, ArrayList<HashMap<String, Object>> list) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HashMap<String, Object> getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.theme_preview, viewGroup);
        linear_base = view.findViewById(R.id.linear_base);
        linear_title = view.findViewById(R.id.linear_title);
        imageview_back = view.findViewById(R.id.imageview_back);
        imageview_fab = view.findViewById(R.id.imageview_fab);
        textview_name = view.findViewById(R.id.textview_name);
        textview_title = view.findViewById(R.id.textview_title);

        textview_name.setText(list.get(i).get("themesname").toString());
        textview_name.setTextColor(((HashMap<String, Integer>)list.get(i).get("themesjson")).get("colorBackgroundText"));
        textview_title.setTextColor(((HashMap<String, Integer>)list.get(i).get("themesjson")).get("colorPrimaryText"));
        linear_base.setBackgroundTintList(ColorStateList.valueOf(((HashMap<String, Integer>)list.get(i).get("themesjson")).get("colorBackground")));
        linear_title.setBackgroundTintList(ColorStateList.valueOf(((HashMap<String, Integer>)list.get(i).get("themesjson")).get("colorPrimary")));
        imageview_fab.setBackgroundTintList(ColorStateList.valueOf(((HashMap<String, Integer>)list.get(i).get("themesjson")).get("colorAccent")));
        imageview_fab.setColorFilter(((HashMap<String, Integer>)list.get(i).get("themesjson")).get("colorAccentText"));
        imageview_back.setColorFilter(((HashMap<String, Integer>)list.get(i).get("themesjson")).get("colorPrimaryTint"));
        return view;
    }
}
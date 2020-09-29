package tw.osthm.manager;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.graphics.ColorUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class ThemeGridPreview extends BaseAdapter {
    ArrayList<HashMap<String, Object>> list;
    LayoutInflater inflater;
    private LinearLayout linear_base;
    private LinearLayout linear_title;
    private ImageView imageview_back;
    private ImageView imageview_fab;
    private TextView textview_title;
    private TextView textview_name;
    private ImageView indicator;
    private Context mContext;

    public ThemeGridPreview(Context mContext, ArrayList<HashMap<String, Object>> list) {
        this.mContext = mContext;
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
            view = inflater.inflate(R.layout.theme_preview, null);
        linear_base = view.findViewById(R.id.linear_base);
        linear_title = view.findViewById(R.id.linear_title);
        imageview_back = view.findViewById(R.id.imageview_back);
        imageview_fab = view.findViewById(R.id.imageview_fab);
        textview_name = view.findViewById(R.id.textview_name);
        textview_title = view.findViewById(R.id.textview_title);
        indicator = view.findViewById(R.id.theme_selected_indicator);

        textview_name.setText(list.get(i).get("themesname").toString());
        HashMap<String, Integer> list2 = new Gson().fromJson(list.get(i).get("themesjson").toString(), new TypeToken<HashMap<String, Integer>>() {
        }.getType());

        textview_name.setTextColor(list2.get("colorBackgroundText"));
        textview_title.setTextColor(list2.get("colorPrimaryText"));
        linear_base.setBackgroundTintList(ColorStateList.valueOf(list2.get("colorBackground")));
        linear_title.setBackgroundTintList(ColorStateList.valueOf(list2.get("colorPrimary")));
        imageview_fab.setBackgroundTintList(ColorStateList.valueOf(list2.get("colorAccent")));
        imageview_fab.setColorFilter(list2.get("colorAccentText"));
        imageview_back.setColorFilter(list2.get("colorPrimaryTint"));

        linear_title.setElevation(list2.get("shadow") == 1 ? ThmMgrUtils.toDip(mContext, 5f) : 0f);
        imageview_fab.setElevation(list2.get("shadow") == 1 ? ThmMgrUtils.toDip(mContext, 6f) : 0f);

        if (ThemeManagerActivity.currentThemeUUID.equals(list.get(i).get("uuid").toString())) {
            indicator.setVisibility(View.VISIBLE);

            if (ColorUtils.calculateLuminance(list2.get("colorBackground")) < 0.5)
                indicator.setColorFilter(0xFFFFFFFF);

            else
                indicator.setColorFilter(0xFF000000);

        } else
            indicator.setVisibility(View.INVISIBLE);

        return view;
    }
}
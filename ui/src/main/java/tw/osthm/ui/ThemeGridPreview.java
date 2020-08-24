package tw.osthm.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class ThemeGridPreview extends RecyclerView.Adapter<ThemeGridPreview.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String, Object>> list;

    public ThemeGridPreview(Context mContext, ArrayList<HashMap<String, Object>> list) {
        mInflater = LayoutInflater.from(mContext);
        this.list = list;
    }

    @NonNull
    @Override
    public ThemeGridPreview.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.theme_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeGridPreview.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview_name;

        ViewHolder(View itemView) {
            super(itemView);
            textview_name = itemView.findViewById(R.id.textview_name);
        }
    }
}

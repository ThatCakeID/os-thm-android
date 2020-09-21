package tw.osthm.manager;

import android.content.Context;
import android.util.TypedValue;

public class ThmMgrUtils {
    public static float toDip(Context mContext, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mContext.getResources().getDisplayMetrics());
    }
}

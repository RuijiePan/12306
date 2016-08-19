package ruijie.com.my12306.util;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/8/19.
 */

public class TextUtil {

    public static boolean isEmpty(String s){
        return s == null || TextUtils.isEmpty(s);
    }
}

package cc.ricksimon.android.filteringplurk.utils;

/**
 * Created by Simon on 2017/10/31.
 */

public class Log {
    public static final boolean PRINT_LOG = true;

    public static void v(String tag, String message){
        if(PRINT_LOG){
            android.util.Log.v(tag,message);
        }
    }

    public static void i(String tag, String message){
        if(PRINT_LOG){
            android.util.Log.i(tag,message);
        }
    }

    public static void e(String tag, String message){
        if(PRINT_LOG){
            android.util.Log.e(tag,message);
        }
    }
}

package com.benefitsoft.recorder.Utils;

import android.util.Log;

public class Dlog {

    static final String TAG = "####";

    /** Log Level Error **/
    public static final void e(String message) {
        if (AppInfo.DEBUG) Log.e(TAG, buildLogMsg(message));
    }
    /** Log Level Warning **/
    public static final void w(String message) {
        if (AppInfo.DEBUG) Log.w(TAG, buildLogMsg(message));
    }
    /** Log Level Information **/
    public static final void i(String message) {
        if (AppInfo.DEBUG) Log.i(TAG, buildLogMsg(message));
    }
    /** Log Level Debug **/
    public static final void d(String message) {
        if (AppInfo.DEBUG) Log.d(TAG, buildLogMsg(message));
    }
    /** Log Level Verbose **/
    public static final void v(String message) {
        if (AppInfo.DEBUG) Log.v(TAG, buildLogMsg(message));
    }


    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);

        return sb.toString();

    }
}

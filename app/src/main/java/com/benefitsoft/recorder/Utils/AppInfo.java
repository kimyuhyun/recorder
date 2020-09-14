package com.benefitsoft.recorder.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AppInfo extends MultiDexApplication {
    public static boolean DEBUG = true;
    private static AppInfo mInstance;


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DEBUG = isDebuggable(this);
        mInstance = this;

    }

    public static AppInfo getInstance() {
        return mInstance;
    }


    public static boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
        }

        return debuggable;
    }



    /**
     * 자바용 dateDiff
     * @param time
     * @return
     */
    public static String utilConvertToMillis(String v) {
        if (v.contains("+")) {
            return v;
        }

        try {
            Dlog.d(v);
            String strTmp = v.replace("T", " ");
            strTmp = strTmp.replace("Z", "");
            //long 타입으로 변경
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = null;
            long time = 0;
            date1 = dateFormat.parse(strTmp);
            time = date1.getTime() / 1000;
            //time 값은 substring(0,10)
            long currentTime = (new Date().getTime() / 1000) - (9 * 3600);  //9시간 빼기
            long inputTime = time;
            long diffTime = currentTime - inputTime;
            String postTime = "";
            long tmp;

            Dlog.d(currentTime + " - " + inputTime + " = " + diffTime);
            if (diffTime < 60) {
                postTime = "방금";
            } else if(diffTime < 3600) {
                tmp = diffTime / 60;
                postTime = (int) tmp + "분 전";
            } else if(diffTime < 86400) {
                tmp = diffTime / 3600;
                postTime = (int) tmp + "시간 전";
            } else if(diffTime < 604800) {
                tmp = diffTime / 86400;
                postTime = (int) tmp + "일 전";
            } else if(diffTime > 604800) {
                Date date = new Date(time*1000);
                postTime = (date.getYear() - 100) + "/" + date.getMonth() + "/" + date.getDate();
            }
            return postTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }



}



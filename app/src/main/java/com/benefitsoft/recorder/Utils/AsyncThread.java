package com.benefitsoft.recorder.Utils;

import android.os.AsyncTask;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class AsyncThread extends AsyncTask<Void, Void, String> {
    private String mURL = "";
    private HashMap mHashMap;
    private String mResult = "";
    private Handler mHandler;

    public AsyncThread(Handler handler, String action, HashMap hashMap){
        this.mHandler = handler;
        this.mURL += action;
        this.mHashMap = hashMap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL(mURL);
            Dlog.d(mURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
            conn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(2000);
            conn.setDoOutput(true);

            String data = "";
            try {
                data = URLMaker.hashToUrl(mHashMap);
            }catch (Exception e){
                e.printStackTrace();
            }

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
            outputStreamWriter.write(data);
            outputStreamWriter.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            String str = "";
            while ((line = reader.readLine()) != null){
                str += line;
            }
            return str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            mResult = result;
            mHandler.sendEmptyMessage(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getResult(){
        return mResult;
    }
}

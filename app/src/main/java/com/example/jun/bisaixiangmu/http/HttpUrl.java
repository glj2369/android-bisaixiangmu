package com.example.jun.bisaixiangmu.http;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUrl {
    public interface ResultHttpLisenter {
        void response(String result);
    }

    @NonNull
    public static void postHttpUrl(final String urlString, final String jsonStr, final ResultHttpLisenter lisenter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String posthttp = null;
                try {
                    posthttp = posthttp(urlString, jsonStr);
                    lisenter.response(posthttp);
                } catch (IOException e) {
                    e.printStackTrace();
                    lisenter.response(e.toString());
                }
            }
        }).start();
    }

    public static String posthttp2(String urlString, String jsonStr) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("Content-Type","text/html;charset=utf-8");
            connection.setRequestMethod("POST");
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os);
            writer.write(jsonStr);
            writer.flush();
            writer.close();
            os.close();

            InputStream is = connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            Log.e("posthttp2","测试自己写的http访问好不好使"+stringBuilder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String posthttp(String urlString, String jsonStr) throws IOException {
        URL mUrl = new URL(urlString);
        HttpURLConnection mConnection = (HttpURLConnection) mUrl.openConnection();
        mConnection.addRequestProperty("accept", "*/*");
        mConnection.setRequestProperty("connection", "Keep-Alive");
        mConnection.addRequestProperty("Content-Type", "text/html;charset=utf-8");
        mConnection.setConnectTimeout(3000);
        mConnection.setReadTimeout(3000);
        mConnection.setDoOutput(true);
        mConnection.setDoInput(true);
        mConnection.setRequestMethod("POST");

        OutputStream os = mConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        writer.write(jsonStr);
        writer.flush();
        writer.close();
        os.close();

        InputStream in = mConnection.getInputStream();
        BufferedReader mReader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = mReader.readLine()) != null) {
            result.append(line);
        }
        if (in != null) {
            in.close();
        }
        if (mReader != null) {
            mReader.close();
        }
        return result.toString();

    }

    public static void postJsonUrl(final String urlString, final String jsonStr, final ResultHttpLisenter lisenter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = getStringOkhttp(urlString, jsonStr);
                    lisenter.response(result);
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    public static String getStringOkhttp(String urlString, String jsonStr) throws IOException {
        URL url = new URL(urlString);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);
        Request request = new Request.Builder()
                .url(url)
                .header("accept", "*/*")
                .header("connection", "Keep-Alive")
                .header("Content-Type", "text/html;charset=utf-8")
                .method("POST", requestBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void demoHttp(String url, String json) {
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json;charset=utf-8"), json);
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("accept", "*/*")
                    .addHeader("connection", "Keep-Alive")
                    .addHeader("Content-Type", "text/html;charset=utf-8")
                    .method("POST", requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            String string = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


package com.example.jun.bisaixiangmu.http;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络连接工具类
 * 
 * @author asus
 */

public class NetUtil1 {

    private static final String TAG = "Test";
    private static final int READ_TIME = 1000 * 3;
    private static final int CONNECT_TIME = 1000 * 3;
    private boolean isDebug = false;
    private Handler handler = new Handler(Looper.getMainLooper());

    public interface ResponseListener {
        void success(String result);

        void error(String msg);
    }

    public interface OnResponseListener {
        void complete(String result);
    }

    /**
     * 判断网络是否连接
     * 
     * @param context 上下文对象
     * @return 网络是否连接
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean isNetworkOK = false;
        try {
            ConnectivityManager conn = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == conn || null == conn.getActiveNetworkInfo()) {
                isNetworkOK = false;
            } else {
                isNetworkOK = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetworkOK;
    }

    private void asynsPost(final String urlString, final String params,
            final ResponseListener listener) {
        new Thread() {
            public void run() {
                if (listener != null) {
                    String result = "";
                    try {
                        result = postData(urlString, params);
                    } catch (MalformedURLException e) {
                        listener.error(e + "");
                        e.printStackTrace();
                    } catch (IOException e) {
                        listener.error(e + "");
                        e.printStackTrace();
                    } catch (JSONException e) {
                        listener.error(e + "");
                        e.printStackTrace();
                    }
                    listener.success(result);

                }
            };
        }.start();
    }

    public void asynPost(final String urlString, final String params,
            final ResponseListener listener) {
        asynsPost(urlString, params, new ResponseListener() {

            @Override
            public void success(final String result) {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        listener.success(result);
                    }
                });
            }

            @Override
            public void error(final String msg) {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        listener.error(msg);
                    }
                });
            }
        });
    }

    public String postData(String urlString, String params) throws MalformedURLException,
            IOException, JSONException {
        String result = "";
        if (isDebug) {
            Log.d(TAG + " url:", urlString);
            Log.d(TAG + " body:", params);
        }
        HttpURLConnection mConnection = initURL(urlString);

        setURLParams(mConnection);

        sendData(params, mConnection);

        result = readData(mConnection);
        if (isDebug) {
            Log.d(TAG + "response_code:", mConnection.getResponseCode() + "");
            Log.d(TAG + "response:", result);
        }

        result = new JSONObject(result).getString("serverinfo");
        return result;
    }

    private String readData(HttpURLConnection mConnection) throws IOException {
        InputStream in = null;
        InputStreamReader is = null;
        BufferedReader mReader = null;
        String result = "";

        try {
            in = mConnection.getInputStream();
            is = new InputStreamReader(in);
            mReader = new BufferedReader(is);
            result = "";
            String line;
            while ((line = mReader.readLine()) != null) {
                if (result.equals("")) {
                    result += line;
                } else {
                    result += "\n" + line;
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (is != null) {
                is.close();
            }
            if (mReader != null) {
                mReader.close();
            }
        }
        return result;
    }
    //两种传递参数访问网络方法，，优先使用HttpURLConnection ，okhttp有一些问题
    public static String postHttp(String urlString,String params) {

        try {
            HttpURLConnection connection;
            URL url=new URL("http://192.168.43.29:8090/test/Test");
            connection= (HttpURLConnection) url.openConnection();

            /**
             * 请求头参数
             */
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
            writer.write(params);
            writer.flush();
            outputStream.close();
            writer.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String result = "";
            String line;
            while ((line = reader.readLine()) != null) {
                if (result.equals("")) {
                    result += line;
                } else {
                    result += "\n" + line;
                }
            }
            inputStream.close();
            reader.close();
            Log.e("MainActivity","read"+result);
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }
    public static String postJson(String url,String params) {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
//        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)"application/json; charset=utf-8"
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("CarId", 1);
//            jsonObject.put("Money", 200);
//            jsonObject.put("UserName", "user1");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        Log.e("NetUtil", "我发送的网络" + params);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),
                params);
        Log.e("requestBody", "" + requestBody.toString());
        //创建一个请求对象
        Request request = new Request.Builder()
                .url("http://192.168.43.29:8090/TestJson/Test")
                .post(requestBody)
                .build();
        //发送请求获取响应
        try {
            Response response=okHttpClient.newCall(request).execute();
            Log.e("NetUtil", "我接收的网络" + response.body().toString());
            return response.body().toString();
            //判断请求是否成功
//            if(response.isSuccessful()){
//                //打印服务端返回结果
//                Log.i(TAG,response.body().string());
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

    }

    private void sendData(final String params, HttpURLConnection mConnection) throws IOException,
            UnsupportedEncodingException {
        OutputStream os = null;
        OutputStreamWriter osw = null;
        try {
            os = mConnection.getOutputStream();
            osw = new OutputStreamWriter(os, "utf-8");
            osw.write(params);
            osw.flush();
        } finally {
            if (os != null) {
                os.close();
            }
            if (osw != null) {
                osw.close();
            }
        }
    }

    private void setURLParams(HttpURLConnection mConnection) throws ProtocolException {
        mConnection.setRequestProperty("accept", "*/*");
        mConnection.setRequestProperty("connection", "Keep-Alive");
        mConnection.setRequestMethod("POST");
        mConnection.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
        mConnection.setConnectTimeout(CONNECT_TIME);
        mConnection.setReadTimeout(READ_TIME);
        mConnection.setDoOutput(true);
        mConnection.setDoInput(true);
    }

    private HttpURLConnection initURL(String urlString) throws MalformedURLException,
            IOException {
        URL mUrl;
        HttpURLConnection mConnection;

        mUrl = new URL(urlString);
        mConnection = (HttpURLConnection) mUrl.openConnection();
        return mConnection;
    }

}

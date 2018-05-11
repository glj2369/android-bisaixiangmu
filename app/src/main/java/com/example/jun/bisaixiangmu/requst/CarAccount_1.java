package com.example.jun.bisaixiangmu.requst;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class CarAccount_1 extends BaseRequest {
    private int carId;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public CarAccount_1(Context context,String url,int carId) {
        super(context);
        this.url=url;
        this.carId=carId;
    }

    @Override
    protected String getAddr() {
        return url;
    }

    @Override
    protected String getParams() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("carId",carId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        int monry=0;
        try {
            JSONObject jsonObject= new JSONObject(responseString);
            monry= jsonObject.getInt("monry");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return monry;
    }
}

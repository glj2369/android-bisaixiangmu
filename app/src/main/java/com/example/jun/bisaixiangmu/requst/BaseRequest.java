
package com.example.jun.bisaixiangmu.requst;

import android.content.Context;

import com.example.jun.bisaixiangmu.http.NetUtil1;
public abstract class BaseRequest {
    public interface OnGetDataListener {
        void onReturn(Object data);
    }

    private NetUtil1 mNetUtil1;
    private String url;
    private Context context;

    public BaseRequest(Context context) {
        this.context = context;
        url = "";
    }

    public void connec(final OnGetDataListener listener) {
        mNetUtil1 = new NetUtil1();
        mNetUtil1.asynPost(url + getAddr(), getParams(), new NetUtil1.ResponseListener() {

            @Override
            public void success(String result) {
                if (listener != null) {
                    if (!result.isEmpty()) {
                        listener.onReturn(anaylizeResponse(result));
                    }
                }
            }

            @Override
            public void error(String msg) {
                // MyToast.getToastLong(context, msg);
            }
        });
    }

    protected abstract String getAddr();

    protected abstract String getParams();

    protected abstract Object anaylizeResponse(String responseString);
}

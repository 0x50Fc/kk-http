package cn.kkmofang.http.okhttp.callback;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by jiasen on 2018/4/2.
 */

public abstract class CommonCallback<T> implements ICallback<T> {
    @Override
    public void onStart() {
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void onProcess(long value, long maxValue, Object weakObject) {
    }

    public static CommonCallback CALLBACK_DEFAULT = new CommonCallback() {
        @Override
        public void onError(Exception e, Object weakObject) {
        }

        @Override
        public void onSucess(Object response, int code, String status, Map headers, Object weakObject) {
        }

        @Override
        public Object parseNetworkResponse(Response response) throws Exception {
            return null;
        }
    };



}

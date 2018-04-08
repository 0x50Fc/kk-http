package cn.kkmofang.http.okhttp.callback;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiasen on 2018/4/2.
 */

public interface ICallback<T> {

    void onStart();

    void onProcess(long value, long maxValue, Object weakObject);

    void onError(Exception e, Object weakObject);

    void onSucess(T response, int code, String status, Map<String, Object> headers, Object weakObject);

    void onFinish();

    /**
     * Thread Pool Thread(okhttp onResponse Thread，not main thread)
     *
     * @param response
     */
    T parseNetworkResponse(Response response) throws Exception;

}

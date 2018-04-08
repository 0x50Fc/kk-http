package cn.kkmofang.http.okhttp;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kkmofang.http.HttpOptions;
import cn.kkmofang.http.IHttpTask;
import cn.kkmofang.http.okhttp.callback.DataCallback;
import cn.kkmofang.http.okhttp.callback.FileCallback;
import cn.kkmofang.http.okhttp.callback.ICallback;
import cn.kkmofang.http.okhttp.callback.JsonCallback;
import cn.kkmofang.http.okhttp.callback.StringCallback;
import cn.kkmofang.http.okhttp.test.TestConst;
import cn.kkmofang.http.okhttp.test.TestTencentBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jiasen on 2018/3/30.
 */

public class HttpUtil {


    public static final String TAG = HttpUtil.class.getSimpleName();
    private Handler mHandler;

    private HttpUtil() {
        mHandler = HandlerUtil.getHandler();
    }

    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    public static final HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public IHttpTask send(HttpOptions options, Object weakObject) {
        //FIXME test --- start
        options.url = TestConst.mUrl;
        options.method = HttpOptions.METHOD_GET;
        options.type = HttpOptions.TYPE_JSON;
        options.type = HttpOptions.TYPE_TEXT;

        options.url = "http://58.87.67.196:8000/10m.txt";
        //options.url = "http://58.87.67.196:8000/30m.txt";
        options.type = HttpOptions.TYPE_URI;
        //FIXME test --- end

        IHttpTask task = null;
        if (options == null || weakObject == null) {
            //TODO do something
//             return null;
        }
        switch (options.method) {
            case HttpOptions.METHOD_GET:
                task = get(options, weakObject);
                break;
            case HttpOptions.METHOD_POST:
                task = post(options, weakObject);
                break;
            default:
                task = get(options, weakObject);
                break;
        }
        return task;
    }


    /**
     * 提交Json数据
     * Posting a String -- Json数据  text ...
     * Post Streaming
     * Posting a File
     * Posting form parameters -- form 表单 键值对
     * Posting a multipart request
     *
     * @param options
     * @param weakObject
     * @return
     * @see <a href="https://github.com/square/okhttp/wiki/Recipes">okhttp3 官网</a>
     */
    private IHttpTask post(HttpOptions options, Object weakObject) {
        IHttpTask task = null;
        if (options == null || weakObject == null) {
            //TODO do something
//             return null;
        }

        //FIXME 这里的 url 如果不需要像 get 方式拼接的话，直接使用 url
        String url = options.url;
        String type = options.type;

        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(type)) {
            return null;
        }


        final ICallback callback = getCallback(type);
        OkHttpClient client = OkHttpCLientHolder.getInstance();
        //如果设置了超时时间，那么此次请求不适用默认超时时间
        if (options.timeout != 0) {
            //TODO
//            OkHttpClient client_1 = client.newBuilder()
//                    .connectTimeout()
//                    .writeTimeout()
//                    .readTimeout()
//                    .build();
        }
        //TODO  需要确认需求，post参数是否通过 data 传递
        Object data = options.data;
        RequestBody requestBody = getPostRequestBody(data);

        final Request request = new Request.Builder()
                .post(requestBody)
                // if has extra headers then create your map。
                .headers(getHeaders(null))
                .url(url)
                .build();

        Call call = client.newCall(request);

        doRequestAsync(callback, call, options, weakObject);

        return new HttpTaskImpl(call);
    }

    private Headers getHeaders(HashMap<String, String> headerMap) {
        Headers headers;
        Headers.Builder defaultHeadersBuilder = getDefaultHeadersBuilder();
        if (headerMap == null || headerMap.isEmpty()) {
            // do nothing
        } else {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
                    // do nothing
                } else {
                    defaultHeadersBuilder.add(key, value);
                }
            }
        }
        return defaultHeadersBuilder.build();
    }

    private Headers.Builder getDefaultHeadersBuilder() {
        HashMap<String, String> map = new HashMap<>();
        Headers.Builder builder = new Headers.Builder();
        builder.add(HttpSettingConst.HEADER_USER_AGENT, HttpSettingConst.HEADER_USER_AGENT_VALUE);
        return builder;
    }

    @NonNull
    private RequestBody getPostRequestBody(Object data) {
        // type json
        RequestBody body;
        String jsonString = "{  \n" +
                "   \"username\":\"\",\n" +
                "   \"age\":0,\n" +
                "   \"password\":\"\"\n" +
                "}";
        body = RequestBody.create(HttpSettingConst.JSON_MEDIA_TYPE, jsonString);
        // type Map<String,String>key value
        body = new FormBody.Builder()
                .add("platform", "android")
                .add("name", "bug")
                .add("subject", "XXXXXXXXXXXXXXX")
                .build();

        return body;
    }

    /**
     * @param options
     * @param weakObject
     * @return
     */
    private IHttpTask get(HttpOptions options, Object weakObject) {
        IHttpTask task = null;
        if (options == null || weakObject == null) {
            //TODO do something
//             return null;
        }

        String url = options.absoluteUrl();
        String type = options.type;

        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(type)) {
            return null;
        }

        final ICallback callback = getCallback(type);
        OkHttpClient client = OkHttpCLientHolder.getInstance();
        //如果设置了超时时间，那么此次请求不适用默认超时时间
        if (options.timeout != 0) {
            //TODO
//            OkHttpClient client_1 = client.newBuilder()
//                    .connectTimeout()
//                    .writeTimeout()
//                    .readTimeout()
//                    .build();
        }
        //TODO
        // Refactor CommonRequest After...
        // 当然也可以 不创建 CommonRequest 也可以通过 intercepter 来修改 Request （比如：添加 header 等）
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .headers(getHeaders(null))
                .build();

        Call call = client.newCall(request);

        doRequestAsync(callback, call, options, weakObject);

        return new HttpTaskImpl(call);
    }


    /////////////////---------------------通用方法-------------start
    /////////////////---------------------通用方法-------------start
    /////////////////---------------------通用方法-------------start
    /////////////////---------------------通用方法-------------start


    @NonNull
    private ICallback getCallback(String type) {
        ICallback callback;
        switch (type) {
            case HttpOptions.TYPE_TEXT:
                Log.e(TAG, "StringCallback case ");
                callback = new StringCallback() {
                    @Override
                    public void onError(Exception e, Object weakObject) {
                        Log.e(TAG, "StringCallback onError response = " + e.getMessage());
                    }

                    @Override
                    public void onSucess(String response, int code, String status, Map<String, Object> headers, Object weakObject) {
                        Log.e(TAG, "StringCallback onSucess response = " + response);
                    }
                };
                break;
            case HttpOptions.TYPE_JSON:
                Log.e(TAG, "JsonCallback case ");
                callback = new JsonCallback<TestTencentBean>() {
                    @Override
                    public void onError(Exception e, Object weakObject) {
                        Log.e(TAG, "JsonCallback onError response = " + e.getMessage());
                    }

                    @Override
                    public void onSucess(TestTencentBean response, int code, String status, Map<String, Object> headers, Object weakObject) {
                        Log.e(TAG, "JsonCallback onSucess response = " + response.toString());
                    }
                };
                break;
            case HttpOptions.TYPE_DATA:
                //FIXME need to 需求明确之后，再修改对应callback
                callback = new DataCallback() {
                    @Override
                    public void onError(Exception e, Object weakObject) {
                    }

                    @Override
                    public void onSucess(byte[] response, int code, String status, Map<String, Object> headers, Object weakObject) {
                    }
                };
                break;
            case HttpOptions.TYPE_URI:
                callback = new FileCallback("temp_file") {
                    @Override
                    public void onError(Exception e, Object weakObject) {
                        Log.e(TAG, "FileCallback onError response = " + e.getMessage());
                    }

                    @Override
                    public void onSucess(File response, int code, String status, Map<String, Object> headers, Object weakObject) {
                        Log.e(TAG, "FileCallback onSucess response = " + response);
                        URI uri = response.toURI();
                    }

                    @Override
                    public void onProcess(long value, long maxValue, Object weakObject) {
                        super.onProcess(value, maxValue, weakObject);
                        Log.e(TAG, "FileCallback onProcess value = " + value + " maxValue = " + maxValue);
                    }
                };
                break;
            default:
                callback = new StringCallback() {
                    @Override
                    public void onError(Exception e, Object weakObject) {
                        Log.e(TAG, "default StringCallback onError response = " + e.getMessage());
                    }

                    @Override
                    public void onSucess(String response, int code, String status, Map<String, Object> headers, Object weakObject) {
                        Log.e(TAG, "default StringCallback onSucess response = " + response);
                    }
                };
                break;
        }
        return callback;
    }

    private void doRequestAsync(final ICallback callback, Call call, HttpOptions options, final Object weakObject) {
        //        OkHttpUtils.get()
        //                .tag("");
        //        OkHttpUtils.getInstance().cancelTag("s");

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure");
                sendFailResultCallback(call, e, callback, weakObject);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse");
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(call, new IOException("Canceled!"), callback, weakObject);
                        return;
                    }
                    if (!response.isSuccessful()) {
                        sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), callback, weakObject);
                        return;
                    }
                    Object o = callback.parseNetworkResponse(response);
                    Headers headers = response.headers();
                    Map<String, List<String>> stringListMap = headers.toMultimap();
                    //FIXME code 和 status 等需求确定，传递正确的值
                    sendSuccessResultCallback(o, callback, 0, "", stringListMap, weakObject);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, callback, weakObject);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }
            }
        });
    }

    private void sendSuccessResultCallback(final Object resoponse, final ICallback callback, final int code, final String status, final Map<String, List<String>> headers, final Object weakObject) {
        if (callback == null) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSucess(resoponse, code, status, headers, weakObject);
            }
        });
    }

    private void sendFailResultCallback(Call call, final Exception e, final ICallback callback, final Object weakObject) {
        if (callback == null) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(e, weakObject);
            }
        });
    }
}

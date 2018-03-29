package cn.kkmofang.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hailong11 on 2018/3/21.
 */

public class Http implements IHttp {

    @Override
    public IHttpTask send(final HttpOptions options, final Object weakObject) {
        long timeout = options.timeout;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)//秒
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build();
        String url = options.absoluteUrl();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                options.onfail.on(e, weakObject);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().toString();
                Headers headers = response.networkResponse().request().headers();
                int code = response.code();
                //options.onresponse.on(code, "",headers, weakObject);
            }
        });
        return new HttpTask();
    }

    @Override
    public void cancel(Object weakObject) {

    }

    private static class HttpTask implements IHttpTask {

        @Override
        public void cancel() {

        }
    }
}

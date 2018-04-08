package cn.kkmofang.http.okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by jiasen on 2018/4/2.
 */

public class OkHttpCLientHolder {

    private OkHttpCLientHolder() {
    }

    private static class SingletonHolder {
        private static final OkHttpClient INSTANCE = new OkHttpClient
                .Builder()
                .connectTimeout(HttpSettingConst.CONNECT_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                .writeTimeout(HttpSettingConst.WRITE_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                .readTimeout(HttpSettingConst.READ_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                .build();
    }

    public static final OkHttpClient getInstance() {
        return SingletonHolder.INSTANCE;
    }


}

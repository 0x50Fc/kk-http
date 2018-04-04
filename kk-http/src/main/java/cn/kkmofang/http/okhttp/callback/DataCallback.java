package cn.kkmofang.http.okhttp.callback;

import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by jiasen on 2018/4/3.
 */

public abstract class DataCallback extends CommonCallback<byte[]> {

    @Override
    public byte[] parseNetworkResponse(Response response) throws Exception {
//        BitmapFactory.decodeStream(response.body().byteStream());
        byte[] bytes = response.body().bytes();
        return bytes;
    }
}

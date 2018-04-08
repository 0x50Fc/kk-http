package cn.kkmofang.http.okhttp.callback;

import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import cn.kkmofang.http.okhttp.callback.ICallback;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by jiasen on 2018/4/2.
 */

public abstract class StringCallback extends CommonCallback<String> {

    @Override
    public String parseNetworkResponse(Response response) throws Exception {
        // TODO need to check 文本过大，可能内存泄漏，和nullpointerException
        // NB: does not close inputStream, you can use IOUtils.closeQuietly for that
//        InputStream inputStream = response.body().byteStream();
//        String theString = IOUtils.toString(inputStream, encoding);
//        IOUtils.closeQuietly(inputStream);
        return response.body().string();
    }
}

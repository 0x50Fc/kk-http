package cn.kkmofang.http.okhttp;

import cn.kkmofang.http.IHttp;
import cn.kkmofang.http.IHttpTask;
import okhttp3.Call;

/**
 * Created by jiasen on 2018/4/2.
 */

public class HttpTaskImpl implements IHttpTask {

    private Call mCall;

    public HttpTaskImpl(Call call){
        mCall = call;
    }
    @Override
    public void cancel() {
        if (mCall == null){
            return;
        }
        mCall.cancel();
    }
}

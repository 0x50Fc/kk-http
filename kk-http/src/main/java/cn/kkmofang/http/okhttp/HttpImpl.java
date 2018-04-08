package cn.kkmofang.http.okhttp;

import cn.kkmofang.http.HttpOptions;
import cn.kkmofang.http.IHttp;
import cn.kkmofang.http.IHttpTask;

/**
 * Created by jiasen on 2018/3/30.
 */

public class HttpImpl implements IHttp {

    @Override
    public IHttpTask send(HttpOptions options, Object weakObject) {
        return HttpUtil.getInstance().send(options, weakObject);
    }

    @Override
    public void cancel(Object weakObject) {

    }
}

package cn.kkmofang.http.ok;

import cn.kkmofang.http.HttpOptions;
import cn.kkmofang.http.IHttp;
import cn.kkmofang.http.IHttpTask;

/**
 * Created by hailong11 on 2018/3/21.
 */

public class Http implements IHttp {

    @Override
    public IHttpTask send(HttpOptions options, Object weakObject) {
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

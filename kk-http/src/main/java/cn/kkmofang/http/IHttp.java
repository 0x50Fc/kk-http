package cn.kkmofang.http;

/**
 * Created by hailong11 on 2018/3/13.
 */

public interface IHttp {

    IHttpTask send(HttpOptions options,Object weakObject);

    void cancel(Object weakObject);

    String encodeJSON(Object object);

    Object decodeJSON(String text);

}

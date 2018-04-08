package cn.kkmofang.http.okhttp.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

import cn.kkmofang.http.okhttp.test.TestConst;
import okhttp3.Response;

/**
 * Created by jiasen on 2018/4/3.
 */

public abstract class JsonCallback<T> extends CommonCallback<T> {

    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        //TODO need to check
        String string = response.body().string();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) string;
        }
        // FIXME test case
        string = TestConst.mTestJsonString;
        T bean = new Gson().fromJson(string, entityClass);
        return bean;
    }
}

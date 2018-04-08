package cn.kkmofang.http.okhttp;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import cn.kkmofang.http.HttpOptions;
import okhttp3.MediaType;

/**
 * Created by jiasen on 2018/3/30.
 */

public class HttpSettingConst {

    /**
     * 连接超时时间，默认10秒
     */
    public static final long CONNECT_TIME_OUT_DEFAULT = 10;

    /**
     * 连接超时时间，默认10秒
     */
    public static final long WRITE_TIME_OUT_DEFAULT = 10;

    /**
     * 连接超时时间，默认10秒
     */
    public static final long READ_TIME_OUT_DEFAULT = 10;

    public static final String METHOD_DEFAULT = HttpOptions.METHOD_GET;
    public static final String TYPE_DEFAULT = HttpOptions.TYPE_TEXT;

    public static final String FILE_DOWNLOAD_DEFAULT_DIR = getDownloadFilesDir();



    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");


    public static final String HEADER_USER_AGENT = "User-Agent";
    //TODO 需要替换为真正的 ua
    public static final String HEADER_USER_AGENT_VALUE = "xxxx";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";


    /**
     * //FIXME need to check ，if sd card path，maybe need permission
     * @return
     */
    private static String getDownloadFilesDir() {
        //getExternalStorageState();
//        context.getExternalFilesDir()
//      /storage/sdcard0/Android/data/packageName/files

        //  /storage/emulated/0/
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.e(HttpUtil.TAG,"getDownloadFilesDir = "+dir);
        return dir;
    }
}

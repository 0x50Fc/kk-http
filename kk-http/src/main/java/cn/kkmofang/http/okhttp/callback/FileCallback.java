package cn.kkmofang.http.okhttp.callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.kkmofang.http.okhttp.HttpSettingConst;
import cn.kkmofang.http.okhttp.HttpUtil;
import okhttp3.Response;

/**
 * Created by jiasen on 2018/4/3.
 */

public abstract class FileCallback extends CommonCallback<File> {

    private String mDestFileDir;
    private String mDestFileName;

    public FileCallback() {
        this("temp");
    }

    public FileCallback(String destFileName) {
        this(HttpSettingConst.FILE_DOWNLOAD_DEFAULT_DIR, destFileName);
    }

    public FileCallback(String destFileDir, String destFileName) {
        mDestFileDir = destFileDir;
        mDestFileName = destFileName;
    }

    @Override
    public File parseNetworkResponse(Response response) throws Exception {
        return saveFile(response);
    }

    /**
     * //FIXME need to check,
     *
     * @param response
     * @return
     */
    protected File saveFile(Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();

            long sum = 0;

            File dir = new File(mDestFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, mDestFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;

                //FIXME weakObject 看需求是否需要传入参数
                onProcess(finalSum,total,null);
            }
            fos.flush();

            return file;

        } finally {
            try {
                response.body().close();
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }

        }
    }
}

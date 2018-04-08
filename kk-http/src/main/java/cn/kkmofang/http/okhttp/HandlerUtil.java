package cn.kkmofang.http.okhttp;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by jiasen on 2018/4/3.
 */

public class HandlerUtil {

//    private Handler mHandler;
    //避免activity销毁时，messageQueue中的消息未处理完；故此时应把对应的message给清除出队列
    //handler.removeCallbacks(postRunnable);   //清除runnable对应的message
    //handler.removeMessage(what)  //清除what对应的message
    //2.子线程使用Handler时，如果Handler不再需要发送和处理消息，那么一定要退出子线程的消息轮询
    // Looper.myLooper().quitSafely();

    public static Handler getHandler() {
        return getHandlerBindedWithCurrentThreadLooperSafety(null);
    }

    /**
     * 安全的获取一个和当前线程Looper绑定的 Handler 实例。
     * 在同一个线程里，Looper.prepare()方法不能被调用两次。
     * 因为同一个线程里，最多只能有一个Looper对象。
     * @return
     */
    private static Handler getHandlerBindedWithCurrentThreadLooperSafety(Activity activity) {
        Handler handler;
        //当前线程没有 looper，调用prepare，创建looper
        if (Looper.myLooper() == null) {
            Looper.prepare();
            handler = new MyHandler(activity);
            Looper.loop();
        } else {
            handler = new MyHandler(activity);
        }
        return handler;
    }

    private static class MyHandler extends Handler {

        private final WeakReference<Activity> mWeakReference;

        private MyHandler() {
            mWeakReference = null;
        }

        private MyHandler(Activity activity) {
            mWeakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }

            super.handleMessage(msg);
            if (mWeakReference != null) {
                Activity activity = mWeakReference.get();
                if (activity != null) {

                }
            }
            switch (msg.what) {
                case 1:
                    break;
                default:
                    break;
            }
        }
    }

}

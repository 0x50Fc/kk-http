package cn.kkmofang.http.okhttp.test;

import java.util.List;

/**
 * Created by jiasen on 2018/4/3.
 */

public class TestTencentBean {

    /**
     * PlaylistItem : {"asyncParam":"","btnList":[],"btnPlayUrl":"http://v.qq.com/x/cover/ju3r2n4tele726t.html","btnTitle":"","displayType":2,"indexList":["1-100","101-131"],"name":"qq","needAsync":false,"payType":1,"pl_video_type":1,"realName":"腾讯视频","title":"","totalEpisode":3,"videoPlayList":[{"episode_number":"1","id":"8e2SKKlpDsF","markLabelList":[],"payType":0,"pic":"http://puui.qpic.cn/qqvideo_ori/0/8e2SKKlpDsF_360_204/0","playUrl":"http://v.qq.com/x/cover/ju3r2n4tele726t.html?vid=8e2SKKlpDsF","title":"1","type":"1"},{"episode_number":"2","id":"8I7upnpsz2u","markLabelList":[],"payType":0,"pic":"http://puui.qpic.cn/qqvideo_ori/0/8I7upnpsz2u_360_204/0","playUrl":"http://v.qq.com/x/cover/ju3r2n4tele726t.html?vid=8I7upnpsz2u","title":"2","type":"1"},{"episode_number":"3","id":"8WMyyS486Xs","markLabelList":[],"payType":0,"pic":"http://puui.qpic.cn/qqvideo_ori/0/8WMyyS486Xs_360_204/0","playUrl":"http://v.qq.com/x/cover/ju3r2n4tele726t.html?vid=8WMyyS486Xs","title":"3","type":"1"}]}
     * error : 0
     * msg : 
     */

    public PlaylistItemBean PlaylistItem;
    public int error;
    public String msg;

    @Override
    public String toString() {
        return "TestTencentBean{" +
                "PlaylistItem=" + PlaylistItem +
                ", error=" + error +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static class PlaylistItemBean {
        /**
         * asyncParam : 
         * btnList : []
         * btnPlayUrl : http://v.qq.com/x/cover/ju3r2n4tele726t.html
         * btnTitle : 
         * displayType : 2
         * indexList : ["1-100","101-131"]
         * name : qq
         * needAsync : false
         * payType : 1
         * pl_video_type : 1
         * realName : 腾讯视频
         * title : 
         * totalEpisode : 3
         * videoPlayList : [{"episode_number":"1","id":"8e2SKKlpDsF","markLabelList":[],"payType":0,"pic":"http://puui.qpic.cn/qqvideo_ori/0/8e2SKKlpDsF_360_204/0","playUrl":"http://v.qq.com/x/cover/ju3r2n4tele726t.html?vid=8e2SKKlpDsF","title":"1","type":"1"},{"episode_number":"2","id":"8I7upnpsz2u","markLabelList":[],"payType":0,"pic":"http://puui.qpic.cn/qqvideo_ori/0/8I7upnpsz2u_360_204/0","playUrl":"http://v.qq.com/x/cover/ju3r2n4tele726t.html?vid=8I7upnpsz2u","title":"2","type":"1"},{"episode_number":"3","id":"8WMyyS486Xs","markLabelList":[],"payType":0,"pic":"http://puui.qpic.cn/qqvideo_ori/0/8WMyyS486Xs_360_204/0","playUrl":"http://v.qq.com/x/cover/ju3r2n4tele726t.html?vid=8WMyyS486Xs","title":"3","type":"1"}]
         */

        public String asyncParam;
        public String btnPlayUrl;
        public String btnTitle;
        public int displayType;
        public String name;
        public boolean needAsync;
        public int payType;
        public int pl_video_type;
        public String realName;
        public String title;
        public int totalEpisode;
        public List<?> btnList;
        public List<String> indexList;
        public List<VideoPlayListBean> videoPlayList;

        @Override
        public String toString() {
            return "PlaylistItemBean{" +
                    "asyncParam='" + asyncParam + '\'' +
                    ", btnPlayUrl='" + btnPlayUrl + '\'' +
                    ", btnTitle='" + btnTitle + '\'' +
                    ", displayType=" + displayType +
                    ", name='" + name + '\'' +
                    ", needAsync=" + needAsync +
                    ", payType=" + payType +
                    ", pl_video_type=" + pl_video_type +
                    ", realName='" + realName + '\'' +
                    ", title='" + title + '\'' +
                    ", totalEpisode=" + totalEpisode +
                    ", btnList=" + btnList +
                    ", indexList=" + indexList +
                    ", videoPlayList=" + videoPlayList +
                    '}';
        }

        public static class VideoPlayListBean {
            /**
             * episode_number : 1
             * id : 8e2SKKlpDsF
             * markLabelList : []
             * payType : 0
             * pic : http://puui.qpic.cn/qqvideo_ori/0/8e2SKKlpDsF_360_204/0
             * playUrl : http://v.qq.com/x/cover/ju3r2n4tele726t.html?vid=8e2SKKlpDsF
             * title : 1
             * type : 1
             */

            public String episode_number;
            public String id;
            public int payType;
            public String pic;
            public String playUrl;
            public String title;
            public String type;
            public List<?> markLabelList;

            @Override
            public String toString() {
                return "VideoPlayListBean{" +
                        "episode_number='" + episode_number + '\'' +
                        ", id='" + id + '\'' +
                        ", payType=" + payType +
                        ", pic='" + pic + '\'' +
                        ", playUrl='" + playUrl + '\'' +
                        ", title='" + title + '\'' +
                        ", type='" + type + '\'' +
                        ", markLabelList=" + markLabelList +
                        '}';
            }
        }
    }
}

package com.leichui.shortviedeo.bean;

import java.util.List;

public class MyCollectionBean {

    /**
     * code : 2000
     * data : [{"video_id":"8","is_video_friend":"0","video_collect_count":"1","video_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/abe3e1b95285890806952179268/JRInfQ4WQZoA.mp4","video_img_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/abe3e1b95285890806952179268/5285890806952179269.jpg","fileId":"5285890806952179268"},{"video_id":"7","is_video_friend":"0","video_collect_count":"1","video_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/S6wfsK7IHA4A.mp4","video_img_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/5285890806951732425.jpg","fileId":"5285890806951732424"}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1599009976
     */

    private int code;
    private MsgBean msg;
    private int timestamp;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class MsgBean {
        /**
         * dialog :
         * str : success
         */

        private String dialog;
        private String str;

        public String getDialog() {
            return dialog;
        }

        public void setDialog(String dialog) {
            this.dialog = dialog;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    public static class DataBean {
        /**
         * video_id : 8
         * is_video_friend : 0
         * video_collect_count : 1
         * video_url : http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/abe3e1b95285890806952179268/JRInfQ4WQZoA.mp4
         * video_img_url : http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/abe3e1b95285890806952179268/5285890806952179269.jpg
         * fileId : 5285890806952179268
         */

        private String video_id;
        private String is_video_friend;
        private String video_collect_count;
        private String video_url;
        private String video_img_url;
        private String fileId;

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getIs_video_friend() {
            return is_video_friend;
        }

        public void setIs_video_friend(String is_video_friend) {
            this.is_video_friend = is_video_friend;
        }

        public String getVideo_collect_count() {
            return video_collect_count;
        }

        public void setVideo_collect_count(String video_collect_count) {
            this.video_collect_count = video_collect_count;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getVideo_img_url() {
            return video_img_url;
        }

        public void setVideo_img_url(String video_img_url) {
            this.video_img_url = video_img_url;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }
    }
}

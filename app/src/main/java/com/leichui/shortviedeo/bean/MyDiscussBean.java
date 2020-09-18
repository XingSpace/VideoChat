package com.leichui.shortviedeo.bean;

import java.io.Serializable;
import java.util.List;

public class MyDiscussBean implements Serializable{

    /**
     * code : 2000
     * data : [{"is_video_friend":"0","user_id":"6","video_id":"6","createtime":"5天前","video_discuss_id":"8","video_discuss":"赞","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg","fileId":"5285890806926390091","video_img_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/1fd0e0cf5285890806926390091/5285890806926390092.jpg","video_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/1fd0e0cf5285890806926390091/hLuMEXaAA7gA.mp4","is_friend":"1"},{"is_video_friend":"0","user_id":"6","video_id":"7","createtime":"5天前","video_discuss_id":"7","video_discuss":"99999","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg","fileId":"5285890806951732424","video_img_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/5285890806951732425.jpg","video_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/S6wfsK7IHA4A.mp4","is_friend":"1"},{"is_video_friend":"0","user_id":"6","video_id":"7","createtime":"5天前","video_discuss_id":"6","video_discuss":"飞飞飞","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg","fileId":"5285890806951732424","video_img_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/5285890806951732425.jpg","video_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/S6wfsK7IHA4A.mp4","is_friend":"1"},{"is_video_friend":"0","user_id":"6","video_id":"7","createtime":"5天前","video_discuss_id":"5","video_discuss":"66666","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg","fileId":"5285890806951732424","video_img_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/5285890806951732425.jpg","video_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/S6wfsK7IHA4A.mp4","is_friend":"1"},{"is_video_friend":"0","user_id":"6","video_id":"7","createtime":"5天前","video_discuss_id":"4","video_discuss":"56788","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg","fileId":"5285890806951732424","video_img_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/5285890806951732425.jpg","video_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/6c9c680d5285890806951732424/S6wfsK7IHA4A.mp4","is_friend":"1"}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1599008445
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
         * is_video_friend : 0
         * user_id : 6
         * video_id : 6
         * createtime : 5天前
         * video_discuss_id : 8
         * video_discuss : 赞
         * user_name : 口水鸡
         * user_avatar : http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg
         * fileId : 5285890806926390091
         * video_img_url : http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/1fd0e0cf5285890806926390091/5285890806926390092.jpg
         * video_url : http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/1fd0e0cf5285890806926390091/hLuMEXaAA7gA.mp4
         * is_friend : 1
         */

        private String is_video_friend;
        private String user_id;
        private String video_id;
        private String createtime;
        private String video_discuss_id;
        private String video_discuss;
        private String user_name;
        private String user_avatar;
        private String fileId;
        private String video_img_url;
        private String video_url;
        private String is_friend;

        public String getIs_video_friend() {
            return is_video_friend;
        }

        public void setIs_video_friend(String is_video_friend) {
            this.is_video_friend = is_video_friend;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getVideo_discuss_id() {
            return video_discuss_id;
        }

        public void setVideo_discuss_id(String video_discuss_id) {
            this.video_discuss_id = video_discuss_id;
        }

        public String getVideo_discuss() {
            return video_discuss;
        }

        public void setVideo_discuss(String video_discuss) {
            this.video_discuss = video_discuss;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getVideo_img_url() {
            return video_img_url;
        }

        public void setVideo_img_url(String video_img_url) {
            this.video_img_url = video_img_url;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getIs_friend() {
            return is_friend;
        }

        public void setIs_friend(String is_friend) {
            this.is_friend = is_friend;
        }
    }
}

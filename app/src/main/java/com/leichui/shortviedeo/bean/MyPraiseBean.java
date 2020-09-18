package com.leichui.shortviedeo.bean;

import java.util.List;

public class MyPraiseBean {

    /**
     * code : 2000
     * data : [{"is_video_friend":"0","createtime":"2020-09-03 15:02","video_id":"7","user_id":"6","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_followed":"2"},{"is_video_friend":"0","createtime":"2020-09-03 14:55","video_id":"16","user_id":"6","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_followed":"2"},{"is_video_friend":"1","createtime":"2020-09-02 16:38","video_id":"4","user_id":"6","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_followed":"2"},{"is_video_friend":"1","createtime":"2020-09-02 16:32","video_id":"2","user_id":"6","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_followed":"2"},{"is_video_friend":"1","createtime":"2020-09-02 16:32","video_id":"3","user_id":"6","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_followed":"2"}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1599118567
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
         * createtime : 2020-09-03 15:02
         * video_id : 7
         * user_id : 6
         * user_name : 口水鸡
         * user_avatar : http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg
         * is_followed : 2
         */

        private String is_video_friend;
        private String createtime;
        private String video_id;
        private String user_id;
        private String user_name;
        private String user_avatar;
        private String is_followed;

        public String getIs_video_friend() {
            return is_video_friend;
        }

        public void setIs_video_friend(String is_video_friend) {
            this.is_video_friend = is_video_friend;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getIs_followed() {
            return is_followed;
        }

        public void setIs_followed(String is_followed) {
            this.is_followed = is_followed;
        }
    }
}

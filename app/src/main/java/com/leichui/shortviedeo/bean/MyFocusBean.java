package com.leichui.shortviedeo.bean;

import java.io.Serializable;
import java.util.List;

public class MyFocusBean implements Serializable{

    /**
     * code : 2000
     * data : [{"user_follow_id":"6","is_followed":"2","createtime":"2020/09/03","user_follow_name":"口水鸡","user_follow_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg"}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1599116726
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
         * user_follow_id : 6
         * is_followed : 2
         * createtime : 2020/09/03
         * user_follow_name : 口水鸡
         * user_follow_avatar : http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg
         */

        private String user_follow_id;
        private String is_followed;
        private String createtime;
        private String user_follow_name;
        private String user_follow_avatar;

        public String getUser_follow_id() {
            return user_follow_id;
        }

        public void setUser_follow_id(String user_follow_id) {
            this.user_follow_id = user_follow_id;
        }

        public String getIs_followed() {
            return is_followed;
        }

        public void setIs_followed(String is_followed) {
            this.is_followed = is_followed;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUser_follow_name() {
            return user_follow_name;
        }

        public void setUser_follow_name(String user_follow_name) {
            this.user_follow_name = user_follow_name;
        }

        public String getUser_follow_avatar() {
            return user_follow_avatar;
        }

        public void setUser_follow_avatar(String user_follow_avatar) {
            this.user_follow_avatar = user_follow_avatar;
        }
    }
}

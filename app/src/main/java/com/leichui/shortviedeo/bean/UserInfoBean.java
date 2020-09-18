package com.leichui.shortviedeo.bean;

import java.io.Serializable;
import java.util.List;

public class UserInfoBean implements Serializable{

    /**
     * code : 2000
     * data : {"user_id":"6","user_name":"","company_name":"","company_status":"3","user_mail":"","user_tel":"15900818005","user_department":"","user_tag":["88"],"user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg","is_qq":"0","is_wechat":"0","is_sina":"0"}
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1598595740
     */

    private int code;
    private DataBean data;
    private MsgBean msg;
    private int timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * user_id : 6
         * user_name :
         * company_name :
         * company_status : 3
         * user_mail :
         * user_tel : 15900818005
         * user_department :
         * user_tag : ["88"]
         * user_avatar : http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg
         * is_qq : 0
         * is_wechat : 0
         * is_sina : 0
         */

        private String user_id;
        private String user_name;
        private String company_name;
        private String company_status;
        private String user_mail;
        private String user_tel;
        private String user_department;
        private String user_avatar;
        private String is_qq;
        private String is_wechat;
        private String is_sina;
        private List<String> user_tag;

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

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_status() {
            return company_status;
        }

        public void setCompany_status(String company_status) {
            this.company_status = company_status;
        }

        public String getUser_mail() {
            return user_mail;
        }

        public void setUser_mail(String user_mail) {
            this.user_mail = user_mail;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getUser_department() {
            return user_department;
        }

        public void setUser_department(String user_department) {
            this.user_department = user_department;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public String getIs_qq() {
            return is_qq;
        }

        public void setIs_qq(String is_qq) {
            this.is_qq = is_qq;
        }

        public String getIs_wechat() {
            return is_wechat;
        }

        public void setIs_wechat(String is_wechat) {
            this.is_wechat = is_wechat;
        }

        public String getIs_sina() {
            return is_sina;
        }

        public void setIs_sina(String is_sina) {
            this.is_sina = is_sina;
        }

        public List<String> getUser_tag() {
            return user_tag;
        }

        public void setUser_tag(List<String> user_tag) {
            this.user_tag = user_tag;
        }
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
}

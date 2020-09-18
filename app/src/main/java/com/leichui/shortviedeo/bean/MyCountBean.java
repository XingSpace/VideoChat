package com.leichui.shortviedeo.bean;

public class MyCountBean {

    /**
     * code : 2000
     * data : {"followed_count":"0","followe_count":"0","praise_count":"0","friend_count":"0","collect_count":"0","video_count":"3","video_friend_count":"0","user_name":"口水鸡","company_name":"经济技术公司","company_status":"1","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg"}
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1598950669
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
         * followed_count : 0
         * followe_count : 0
         * praise_count : 0
         * friend_count : 0
         * collect_count : 0
         * video_count : 3
         * video_friend_count : 0
         * user_name : 口水鸡
         * company_name : 经济技术公司
         * company_status : 1
         * user_avatar : http://goldenhaian.com/DouYin/uploadfile/photo/5f4896f74cd70.jpg
         */

        private String followed_count;
        private String followe_count;
        private String praise_count;
        private String friend_count;
        private String collect_count;
        private String video_count;
        private String video_friend_count;
        private String user_name;
        private String company_name;
        private String company_status;
        private String user_avatar;

        public String getFollowed_count() {
            return followed_count;
        }

        public void setFollowed_count(String followed_count) {
            this.followed_count = followed_count;
        }

        public String getFollowe_count() {
            return followe_count;
        }

        public void setFollowe_count(String followe_count) {
            this.followe_count = followe_count;
        }

        public String getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(String praise_count) {
            this.praise_count = praise_count;
        }

        public String getFriend_count() {
            return friend_count;
        }

        public void setFriend_count(String friend_count) {
            this.friend_count = friend_count;
        }

        public String getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(String collect_count) {
            this.collect_count = collect_count;
        }

        public String getVideo_count() {
            return video_count;
        }

        public void setVideo_count(String video_count) {
            this.video_count = video_count;
        }

        public String getVideo_friend_count() {
            return video_friend_count;
        }

        public void setVideo_friend_count(String video_friend_count) {
            this.video_friend_count = video_friend_count;
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

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
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

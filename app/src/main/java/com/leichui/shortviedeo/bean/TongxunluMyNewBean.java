package com.leichui.shortviedeo.bean;

import java.util.List;

/**
 * Created by Luke on 2018/1/31.
 */

public class TongxunluMyNewBean {


    /**
     * code : 2000
     * data : [{"key_word":"D","list":[{"user_id":"65","avatar":"/uploadfile/photo/5ca594827327c.jpg","user_tel":"18521354744","user_name":"大海","rong_UserId":"weichat18521354744","is_chat":"1","remarks":""}]},{"key_word":"L","list":[{"user_id":"63","avatar":"/uploadfile/photo/5ccfa96c85935.jpg","user_tel":"15900818005","user_name":"老鸨子","rong_UserId":"weichat15900818005","is_chat":"1","remarks":""}]},{"key_word":"M","list":[{"user_id":"100","avatar":"","user_tel":"18715604660","user_name":"猫咪1","rong_UserId":"weichat18715604660","is_chat":"0","remarks":"dapeng1"}]}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1559717845
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
         * key_word : D
         * list : [{"user_id":"65","avatar":"/uploadfile/photo/5ca594827327c.jpg","user_tel":"18521354744","user_name":"大海","rong_UserId":"weichat18521354744","is_chat":"1","remarks":""}]
         */

        private String key_word;
        private List<ListBean> list;

        public String getKey_word() {
            return key_word;
        }

        public void setKey_word(String key_word) {
            this.key_word = key_word;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * user_id : 65
             * avatar : /uploadfile/photo/5ca594827327c.jpg
             * user_tel : 18521354744
             * user_name : 大海
             * rong_UserId : weichat18521354744
             * is_chat : 1
             * remarks :
             */

            private String user_id;
            private String avatar;
            private String user_tel;
            private String user_name;
            private String rong_UserId;
            private String is_chat;
            private String remarks;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUser_tel() {
                return user_tel;
            }

            public void setUser_tel(String user_tel) {
                this.user_tel = user_tel;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getRong_UserId() {
                return rong_UserId;
            }

            public void setRong_UserId(String rong_UserId) {
                this.rong_UserId = rong_UserId;
            }

            public String getIs_chat() {
                return is_chat;
            }

            public void setIs_chat(String is_chat) {
                this.is_chat = is_chat;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }
        }
    }
}

package com.leichui.shortviedeo.bean;

import java.io.Serializable;
import java.util.List;

public class DiscussBean implements Serializable{

    /**
     * code : 2000
     * data : {"all_count":"2","list":[{"video_discuss_id":"5","video_discuss":"66666","user_id":"6","createtime":"3分钟前","user_name":"","user_avatar":""},{"video_discuss_id":"4","video_discuss":"56788","user_id":"6","createtime":"4分钟前","user_name":"","user_avatar":""}]}
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1598584547
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
         * all_count : 2
         * list : [{"video_discuss_id":"5","video_discuss":"66666","user_id":"6","createtime":"3分钟前","user_name":"","user_avatar":""},{"video_discuss_id":"4","video_discuss":"56788","user_id":"6","createtime":"4分钟前","user_name":"","user_avatar":""}]
         */

        private String all_count;
        private List<ListBean> list;

        public String getAll_count() {
            return all_count;
        }

        public void setAll_count(String all_count) {
            this.all_count = all_count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * video_discuss_id : 5
             * video_discuss : 66666
             * user_id : 6
             * createtime : 3分钟前
             * user_name :
             * user_avatar :
             */

            private String video_discuss_id;
            private String video_discuss;
            private String user_id;
            private String createtime;
            private String user_name;
            private String user_avatar;

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

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
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

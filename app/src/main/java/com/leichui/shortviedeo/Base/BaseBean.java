package com.leichui.shortviedeo.Base;


public class BaseBean {

    /**
     * code : 2002
     * msg : {"dialog":"该账户尚未审核，请联系学院老师!","str":"failure"}
     * timestamp : 1502252102
     */

    private int code;
    private MsgBean msg;
    private int timestamp;

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

    public static class MsgBean {
        /**
         * dialog : 该账户尚未审核，请联系学院老师!
         * str : failure
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

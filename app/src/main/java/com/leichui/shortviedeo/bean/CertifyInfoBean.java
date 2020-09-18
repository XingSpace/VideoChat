package com.leichui.shortviedeo.bean;

import java.io.Serializable;

public class CertifyInfoBean implements Serializable{

    /**
     * code : 2000
     * data : {"company_status":"0","company_name":"","company_img_0":"http://goldenhaian.com/DouYin/uploadfile/photo/5f48ac9193751.jpg","company_img_1":"http://goldenhaian.com/DouYin/uploadfile/photo/5f48ac919cb68.jpg","company_img_2":"http://goldenhaian.com/DouYin/uploadfile/photo/5f48ac91a886e.jpg"}
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1598598735
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
         * company_status : 0
         * company_name :
         * company_img_0 : http://goldenhaian.com/DouYin/uploadfile/photo/5f48ac9193751.jpg
         * company_img_1 : http://goldenhaian.com/DouYin/uploadfile/photo/5f48ac919cb68.jpg
         * company_img_2 : http://goldenhaian.com/DouYin/uploadfile/photo/5f48ac91a886e.jpg
         */

        private String company_status;
        private String company_name;
        private String company_img_0;
        private String company_img_1;
        private String company_img_2;

        public String getCompany_status() {
            return company_status;
        }

        public void setCompany_status(String company_status) {
            this.company_status = company_status;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_img_0() {
            return company_img_0;
        }

        public void setCompany_img_0(String company_img_0) {
            this.company_img_0 = company_img_0;
        }

        public String getCompany_img_1() {
            return company_img_1;
        }

        public void setCompany_img_1(String company_img_1) {
            this.company_img_1 = company_img_1;
        }

        public String getCompany_img_2() {
            return company_img_2;
        }

        public void setCompany_img_2(String company_img_2) {
            this.company_img_2 = company_img_2;
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

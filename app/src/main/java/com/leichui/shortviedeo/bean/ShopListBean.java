package com.leichui.shortviedeo.bean;

import java.io.Serializable;
import java.util.List;

public class ShopListBean implements Serializable{

    /**
     * code : 2000
     * data : [{"good_id":"2","good_name":"商品名称1","good_one_pic":"http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg","good_price":"2.00","sell_count":"0"},{"good_id":"1","good_name":"商品名称1","good_one_pic":"http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg","good_price":"2.00","sell_count":"0"}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1598773116
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
         * good_id : 2
         * good_name : 商品名称1
         * good_one_pic : http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg
         * good_price : 2.00
         * sell_count : 0
         */

        private String good_id;
        private String good_name;
        private String good_one_pic;
        private String good_price;
        private String sell_count;

        public String getGood_id() {
            return good_id;
        }

        public void setGood_id(String good_id) {
            this.good_id = good_id;
        }

        public String getGood_name() {
            return good_name;
        }

        public void setGood_name(String good_name) {
            this.good_name = good_name;
        }

        public String getGood_one_pic() {
            return good_one_pic;
        }

        public void setGood_one_pic(String good_one_pic) {
            this.good_one_pic = good_one_pic;
        }

        public String getGood_price() {
            return good_price;
        }

        public void setGood_price(String good_price) {
            this.good_price = good_price;
        }

        public String getSell_count() {
            return sell_count;
        }

        public void setSell_count(String sell_count) {
            this.sell_count = sell_count;
        }
    }
}

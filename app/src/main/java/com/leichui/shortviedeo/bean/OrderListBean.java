package com.leichui.shortviedeo.bean;

import java.util.List;

public class OrderListBean {

    /**
     * code : 2000
     * data : [{"order_id":"5","good_id":"2","order_price":"2.00","order_number":"2009011555533132","good_sum":"1","good_specs":"规格1","good_name":"商品名称1","createtime":"2020-09-01 15:55","pay_status":"0","order_status":"0","good_one_pic":"http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg"}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1598948282
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
         * order_id : 5
         * good_id : 2
         * order_price : 2.00
         * order_number : 2009011555533132
         * good_sum : 1
         * good_specs : 规格1
         * good_name : 商品名称1
         * createtime : 2020-09-01 15:55
         * pay_status : 0
         * order_status : 0
         * good_one_pic : http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg
         */

        private String order_id;
        private String good_id;
        private String order_price;
        private String order_number;
        private String good_sum;
        private String good_specs;
        private String good_name;
        private String createtime;
        private String pay_status;
        private String order_status;
        private String good_one_pic;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getGood_id() {
            return good_id;
        }

        public void setGood_id(String good_id) {
            this.good_id = good_id;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getGood_sum() {
            return good_sum;
        }

        public void setGood_sum(String good_sum) {
            this.good_sum = good_sum;
        }

        public String getGood_specs() {
            return good_specs;
        }

        public void setGood_specs(String good_specs) {
            this.good_specs = good_specs;
        }

        public String getGood_name() {
            return good_name;
        }

        public void setGood_name(String good_name) {
            this.good_name = good_name;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getGood_one_pic() {
            return good_one_pic;
        }

        public void setGood_one_pic(String good_one_pic) {
            this.good_one_pic = good_one_pic;
        }
    }
}

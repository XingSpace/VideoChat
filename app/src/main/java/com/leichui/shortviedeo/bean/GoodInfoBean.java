package com.leichui.shortviedeo.bean;

import java.io.Serializable;
import java.util.List;

public class GoodInfoBean implements Serializable{

    /**
     * code : 2000
     * data : {"good_id":"2","good_name":"商品名称1","good_price":"2.00","good_desc":"商品简介1","good_pics":["http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg","http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6ed067de.jpg"],"good_specs":["规格1","规格4"],"good_html":" 商品描述2<\/span><\/p>","good_list":[{"good_id":"1","good_name":"商品名称1","good_one_pic":"http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg","good_price":"2.00","sell_count":"0"}]}
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1598775439
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

    public static class DataBean implements Serializable {
        /**
         * good_id : 2
         * good_name : 商品名称1
         * good_price : 2.00
         * good_desc : 商品简介1
         * good_pics : ["http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg","http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6ed067de.jpg"]
         * good_specs : ["规格1","规格4"]
         * good_html :  商品描述2</span></p>
         * good_list : [{"good_id":"1","good_name":"商品名称1","good_one_pic":"http://goldenhaian.com/DouYin/uploadfile/Goods/5f2bc6e8eb564.jpg","good_price":"2.00","sell_count":"0"}]
         */

        private String good_id;
        private String good_name;
        private String good_price;
        private String good_desc;
        private String good_html;
        private String good_one_pic;
        private List<String> good_pics;
        private List<String> good_specs;
        private List<GoodListBean> good_list;


        public String getGood_one_pic() {
            return good_one_pic;
        }

        public void setGood_one_pic(String good_one_pic) {
            this.good_one_pic = good_one_pic;
        }

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

        public String getGood_price() {
            return good_price;
        }

        public void setGood_price(String good_price) {
            this.good_price = good_price;
        }

        public String getGood_desc() {
            return good_desc;
        }

        public void setGood_desc(String good_desc) {
            this.good_desc = good_desc;
        }

        public String getGood_html() {
            return good_html;
        }

        public void setGood_html(String good_html) {
            this.good_html = good_html;
        }

        public List<String> getGood_pics() {
            return good_pics;
        }

        public void setGood_pics(List<String> good_pics) {
            this.good_pics = good_pics;
        }

        public List<String> getGood_specs() {
            return good_specs;
        }

        public void setGood_specs(List<String> good_specs) {
            this.good_specs = good_specs;
        }

        public List<GoodListBean> getGood_list() {
            return good_list;
        }

        public void setGood_list(List<GoodListBean> good_list) {
            this.good_list = good_list;
        }

        public static class GoodListBean  implements Serializable{
            /**
             * good_id : 1
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

    public static class MsgBean implements Serializable{
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

package com.leichui.shortviedeo.bean;

import java.io.Serializable;
import java.util.List;

public class AddressListBean {
    /**
     * code : 2000
     * data : [{"address_id":"2","name":"刘越","tel":"156000854655","address_info":"上海金山","is_default":"1"},{"address_id":"6","name":"张飞","tel":"1546767272434","address_info":"哈哈哈","is_default":"0"},{"address_id":"7","name":"帐篷","tel":"45467372724","address_info":"好喝的","is_default":"0"},{"address_id":"8","name":"好的","tel":"15988475463454","address_info":"上海查明后","is_default":"0"}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1552044709
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

    public static class DataBean implements Serializable {
        /**
         * address_id : 2
         * name : 刘越
         * tel : 156000854655
         * address_info : 上海金山
         * is_default : 1
         */

        private String address_id;
        private String name;
        private String tel;
        private String address_info;
        private String is_default;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress_info() {
            return address_info;
        }

        public void setAddress_info(String address_info) {
            this.address_info = address_info;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }
}

package com.leichui.shortviedeo.bean;

/**
 * Created by Luke on 2018/1/31.
 */

public class TongxunBean {
    /**
     * code : 2000
     * data : [{"food_id":"2","food_name":"菠菜","sum":"1克"},{"food_id":"4","food_name":"胡萝卜","sum":"1克"}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1517378632
     */

    private String name;
    private String tel;

//    public TongxunBean(String name, String tel) {
//        this.name = name;
//        this.tel = tel;
//    }

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
}

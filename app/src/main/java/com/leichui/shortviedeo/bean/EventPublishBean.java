package com.leichui.shortviedeo.bean;

import java.io.Serializable;

public class EventPublishBean implements Serializable{

    public String imgPath;

    public EventPublishBean(String ip){
        this.imgPath = ip;
    }

}

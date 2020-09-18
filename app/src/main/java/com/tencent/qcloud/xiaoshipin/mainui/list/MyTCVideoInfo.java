package com.tencent.qcloud.xiaoshipin.mainui.list;

import com.leichui.shortviedeo.bean.SomeBodyVideoBean;
import com.leichui.shortviedeo.config.GloBalKt;

import java.io.Serializable;
import java.util.List;

public class MyTCVideoInfo implements Serializable {

    /**
     * code : 2000
     * data : [{"video_id":"6","video_desc":"糊糊涂涂","fileId":"5285890806926390091","video_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/1fd0e0cf5285890806926390091/hLuMEXaAA7gA.mp4","video_img_url":"http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/1fd0e0cf5285890806926390091/5285890806926390092.jpg","video_address":"1234","video_discuss_count":"0","video_praise_count":"0","video_share_count":"0","video_collect_count":"0","is_have_good":"0","is_good_app":"0","is_show_car":"0","good_img_url":"","good_id":"0","good_name":"","good_price":"0.00","user_id":"6","user_name":"","user_avatar":""},{"video_id":"5","video_desc":"","fileId":"","video_url":"","video_img_url":"","video_address":"","video_discuss_count":"0","video_praise_count":"0","video_share_count":"0","video_collect_count":"0","is_have_good":"0","is_good_app":"0","is_show_car":"0","good_img_url":"","good_id":"0","good_name":"","good_price":"0.00","user_id":"5","user_name":"","user_avatar":""},{"video_id":"4","video_desc":"","fileId":"","video_url":"","video_img_url":"","video_address":"","video_discuss_count":"0","video_praise_count":"0","video_share_count":"0","video_collect_count":"0","is_have_good":"0","is_good_app":"0","is_show_car":"0","good_img_url":"","good_id":"0","good_name":"","good_price":"0.00","user_id":"5","user_name":"","user_avatar":""},{"video_id":"3","video_desc":"","fileId":"","video_url":"","video_img_url":"","video_address":"","video_discuss_count":"0","video_praise_count":"0","video_share_count":"0","video_collect_count":"0","is_have_good":"0","is_good_app":"0","is_show_car":"0","good_img_url":"","good_id":"0","good_name":"","good_price":"0.00","user_id":"5","user_name":"","user_avatar":""},{"video_id":"2","video_desc":"","fileId":"","video_url":"","video_img_url":"","video_address":"","video_discuss_count":"0","video_praise_count":"0","video_share_count":"0","video_collect_count":"0","is_have_good":"0","is_good_app":"0","is_show_car":"0","good_img_url":"","good_id":"0","good_name":"","good_price":"0.00","user_id":"5","user_name":"","user_avatar":""},{"video_id":"1","video_desc":"11","fileId":"","video_url":"http://1258224839.vod2.myqcloud.com/190e026evodcq1258224839/ba9eb0305285890806405655582/f4L3n0ni4R0A.mp4","video_img_url":"http://1258224839.vod2.myqcloud.com/190e026evodcq1258224839/ba9eb0305285890806405655582/5285890806405655583.jpg","video_address":"1","video_discuss_count":"0","video_praise_count":"1","video_share_count":"0","video_collect_count":"0","is_have_good":"0","is_good_app":"0","is_show_car":"0","good_img_url":"","good_id":"0","good_name":"","good_price":"0.00","user_id":"0","user_name":"","user_avatar":""}]
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1598518223
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
         * video_id : 6
         * video_desc : 糊糊涂涂
         * fileId : 5285890806926390091
         * video_url : http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/1fd0e0cf5285890806926390091/hLuMEXaAA7gA.mp4
         * video_img_url : http://1302896845.vod2.myqcloud.com/30e296b0vodcq1302896845/1fd0e0cf5285890806926390091/5285890806926390092.jpg
         * video_address : 1234
         * video_discuss_count : 0
         * video_praise_count : 0
         * video_share_count : 0
         * video_collect_count : 0
         * is_have_good : 0
         * is_good_app : 0
         * is_show_car : 0
         * good_img_url :
         * good_id : 0
         * good_name :
         * good_price : 0.00
         * user_id : 6
         * user_name :
         * user_avatar :
         */

        private String video_id;
        private String video_desc;
        private String fileId;
        private String video_url;
        private String video_img_url;
        private String video_address;
        private String video_discuss_count;
        private String video_praise_count;
        private String video_share_count;
        private String video_collect_count;
        private String is_have_good;
        private String is_good_app;
        private String is_show_car;
        private String good_img_url;
        private String good_id;
        private String good_name;
        private String good_price;
        private String user_id;
        private String user_name;
        private String user_avatar;
        private String is_collect;
        private String is_praise;
        private String is_follow;
        private String company_name;
        private String company_url;
        private String good_app_url;

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_url() {
            return company_url;
        }

        public void setCompany_url(String company_url) {
            this.company_url = company_url;
        }

        public String getGood_app_url() {
            return good_app_url;
        }

        public void setGood_app_url(String good_app_url) {
            this.good_app_url = good_app_url;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }



        public String getIs_praise() {
            return is_praise;
        }

        public void setIs_praise(String is_praise) {
            this.is_praise = is_praise;
        }

        public String getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(String is_collect) {
            this.is_collect = is_collect;
        }


        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getVideo_desc() {
            return video_desc;
        }

        public void setVideo_desc(String video_desc) {
            this.video_desc = video_desc;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getVideo_img_url() {
            return video_img_url;
        }

        public void setVideo_img_url(String video_img_url) {
            this.video_img_url = video_img_url;
        }

        public String getVideo_address() {
            return video_address;
        }

        public void setVideo_address(String video_address) {
            this.video_address = video_address;
        }

        public String getVideo_discuss_count() {
            return video_discuss_count;
        }

        public void setVideo_discuss_count(String video_discuss_count) {
            this.video_discuss_count = video_discuss_count;
        }

        public String getVideo_praise_count() {
            return video_praise_count;
        }

        public void setVideo_praise_count(String video_praise_count) {
            this.video_praise_count = video_praise_count;
        }

        public String getVideo_share_count() {
            return video_share_count;
        }

        public void setVideo_share_count(String video_share_count) {
            this.video_share_count = video_share_count;
        }

        public String getVideo_collect_count() {
            return video_collect_count;
        }

        public void setVideo_collect_count(String video_collect_count) {
            this.video_collect_count = video_collect_count;
        }

        public String getIs_have_good() {
            return is_have_good;
        }

        public void setIs_have_good(String is_have_good) {
            this.is_have_good = is_have_good;
        }

        public String getIs_good_app() {
            return is_good_app;
        }

        public void setIs_good_app(String is_good_app) {
            this.is_good_app = is_good_app;
        }

        public String getIs_show_car() {
            return is_show_car;
        }

        public void setIs_show_car(String is_show_car) {
            this.is_show_car = is_show_car;
        }

        public String getGood_img_url() {
            return good_img_url;
        }

        public void setGood_img_url(String good_img_url) {
            this.good_img_url = good_img_url;
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

    public static TCVideoInfo convertToTCVideoInfo(MyTCVideoInfo.DataBean myTCVideoInfo){
        TCVideoInfo tcVideoInfo = new TCVideoInfo();
        tcVideoInfo.review_status = 1;
        tcVideoInfo.userid = myTCVideoInfo.getUser_id();
        tcVideoInfo.fileid = myTCVideoInfo.getFileId();
        tcVideoInfo.title = myTCVideoInfo.getVideo_desc();
        tcVideoInfo.video_desc = myTCVideoInfo.getVideo_desc();
        tcVideoInfo.frontcover = myTCVideoInfo.getVideo_img_url();
        tcVideoInfo.location = "未知";
        tcVideoInfo.playurl = myTCVideoInfo.getVideo_url();
        tcVideoInfo.createTime = "";
        tcVideoInfo.nickname = myTCVideoInfo.getUser_name();
        tcVideoInfo.headpic = myTCVideoInfo.getUser_avatar();
        tcVideoInfo.video_id = myTCVideoInfo.getVideo_id();
        tcVideoInfo.video_address = myTCVideoInfo.video_address;
        tcVideoInfo.video_discuss_count = myTCVideoInfo.video_discuss_count;
        tcVideoInfo.video_praise_count = myTCVideoInfo.video_praise_count;
        tcVideoInfo.video_share_count = myTCVideoInfo.video_share_count;
        tcVideoInfo.video_collect_count = myTCVideoInfo.video_collect_count;
        tcVideoInfo.is_have_good = myTCVideoInfo.is_have_good;
        tcVideoInfo.is_good_app = myTCVideoInfo.is_good_app;
        tcVideoInfo.is_show_car = myTCVideoInfo.is_show_car;
        tcVideoInfo.good_img_url = myTCVideoInfo.good_img_url;
        tcVideoInfo.good_id = myTCVideoInfo.good_id;
        tcVideoInfo.good_name =myTCVideoInfo.good_name;
        tcVideoInfo.good_price = myTCVideoInfo.good_price;
        tcVideoInfo.video_praise= myTCVideoInfo.is_praise;
        tcVideoInfo.is_follow= myTCVideoInfo.is_follow;
        tcVideoInfo.company_name= myTCVideoInfo.company_name;
        tcVideoInfo.company_url=myTCVideoInfo.company_url;
        tcVideoInfo.good_app_url= myTCVideoInfo.good_app_url;
        return tcVideoInfo;
    }
    public static TCVideoInfo convertToTCVideoInfo(SomeBodyVideoBean.DataBean.ListBean myTCVideoInfo){
        TCVideoInfo tcVideoInfo = new TCVideoInfo();
        tcVideoInfo.review_status = 1;
        tcVideoInfo.userid = myTCVideoInfo.getUser_id();
        tcVideoInfo.fileid = myTCVideoInfo.getFileId();
        tcVideoInfo.title = myTCVideoInfo.getVideo_desc();
        tcVideoInfo.video_desc = myTCVideoInfo.getVideo_desc();
        tcVideoInfo.frontcover = myTCVideoInfo.getVideo_img_url();
        tcVideoInfo.location = "未知";
        tcVideoInfo.playurl = myTCVideoInfo.getVideo_url();
        tcVideoInfo.createTime = "";
        tcVideoInfo.nickname = myTCVideoInfo.getUser_name();
        tcVideoInfo.headpic = myTCVideoInfo.getUser_avatar();
        tcVideoInfo.video_id = myTCVideoInfo.getVideo_id();
        tcVideoInfo.video_address = myTCVideoInfo.getVideo_address();
        tcVideoInfo.video_discuss_count = myTCVideoInfo.getVideo_discuss_count();
        tcVideoInfo.video_praise_count = myTCVideoInfo.getVideo_praise_count();
        tcVideoInfo.video_share_count = myTCVideoInfo.getVideo_share_count();
        tcVideoInfo.video_collect_count = myTCVideoInfo.getVideo_collect_count();
        tcVideoInfo.is_have_good = myTCVideoInfo.getIs_have_good();
        tcVideoInfo.is_good_app = myTCVideoInfo.getIs_good_app();
        tcVideoInfo.is_show_car = myTCVideoInfo.getIs_show_car();
        tcVideoInfo.good_img_url = myTCVideoInfo.getGood_img_url();
        tcVideoInfo.good_id = myTCVideoInfo.getGood_id();
        tcVideoInfo.good_name =myTCVideoInfo.getGood_name();
        tcVideoInfo.good_price = myTCVideoInfo.getGood_price();
        tcVideoInfo.video_praise= myTCVideoInfo.getIs_praise();
        tcVideoInfo.is_follow= myTCVideoInfo.getIs_follow();
        tcVideoInfo.company_name= myTCVideoInfo.getCompany_name();
        tcVideoInfo.company_url=myTCVideoInfo.getCompany_url();
        tcVideoInfo.good_app_url= myTCVideoInfo.getGood_app_url();
        return tcVideoInfo;
    }
}

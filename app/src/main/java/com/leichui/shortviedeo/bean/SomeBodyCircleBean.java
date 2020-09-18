package com.leichui.shortviedeo.bean;

import java.util.List;

public class SomeBodyCircleBean {

    /**
     * code : 2000
     * data : {"friend_video_status":"2","list":[{"video_id":"3","video_desc":"而对方他夫","fileId":"0","video_url":"","video_img_url":["http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9f73e12.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9f7783b.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9fa6c9d.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9fb0d13.jpg"],"video_address":"123","video_discuss_count":"0","video_praise_count":"0","video_share_count":"0","video_collect_count":"0","user_id":"6","createtime":"2020-09-02 12:29","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_praise":"1","video_praise_list":[{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg"},{"user_name":"大鹏1","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5e943143516b7.png"}],"video_discuss_list":[{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"赞"},{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"66666"},{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"123"},{"user_name":"大鹏","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5e8fe766b5558.png","video_discuss":"评论内容评论内容评论内容评论内容评论内容"}]},{"video_id":"2","video_desc":"而对方他夫","fileId":"0","video_url":"","video_img_url":[],"video_address":"123","video_discuss_count":"0","video_praise_count":"1","video_share_count":"0","video_collect_count":"0","user_id":"6","createtime":"2020-09-02 12:29","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_praise":"1","video_praise_list":[{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg"}],"video_discuss_list":[]}]}
     * msg : {"dialog":"","str":"success"}
     * timestamp : 1599124833
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
         * friend_video_status : 2
         * list : [{"video_id":"3","video_desc":"而对方他夫","fileId":"0","video_url":"","video_img_url":["http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9f73e12.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9f7783b.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9fa6c9d.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9fb0d13.jpg"],"video_address":"123","video_discuss_count":"0","video_praise_count":"0","video_share_count":"0","video_collect_count":"0","user_id":"6","createtime":"2020-09-02 12:29","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_praise":"1","video_praise_list":[{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg"},{"user_name":"大鹏1","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5e943143516b7.png"}],"video_discuss_list":[{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"赞"},{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"66666"},{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"123"},{"user_name":"大鹏","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5e8fe766b5558.png","video_discuss":"评论内容评论内容评论内容评论内容评论内容"}]},{"video_id":"2","video_desc":"而对方他夫","fileId":"0","video_url":"","video_img_url":[],"video_address":"123","video_discuss_count":"0","video_praise_count":"1","video_share_count":"0","video_collect_count":"0","user_id":"6","createtime":"2020-09-02 12:29","user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","is_praise":"1","video_praise_list":[{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg"}],"video_discuss_list":[]}]
         */

        private String friend_video_status;
        private List<ListBean> list;

        public String getFriend_video_status() {
            return friend_video_status;
        }

        public void setFriend_video_status(String friend_video_status) {
            this.friend_video_status = friend_video_status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * video_id : 3
             * video_desc : 而对方他夫
             * fileId : 0
             * video_url :
             * video_img_url : ["http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9f73e12.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9f7783b.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9fa6c9d.jpg","http://goldenhaian.com/DouYin/uploadfile/photo/5f4f1f9fb0d13.jpg"]
             * video_address : 123
             * video_discuss_count : 0
             * video_praise_count : 0
             * video_share_count : 0
             * video_collect_count : 0
             * user_id : 6
             * createtime : 2020-09-02 12:29
             * user_name : 口水鸡
             * user_avatar : http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg
             * is_praise : 1
             * video_praise_list : [{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg"},{"user_name":"大鹏1","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5e943143516b7.png"}]
             * video_discuss_list : [{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"赞"},{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"66666"},{"user_name":"口水鸡","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg","video_discuss":"123"},{"user_name":"大鹏","user_avatar":"http://goldenhaian.com/DouYin/uploadfile/photo/5e8fe766b5558.png","video_discuss":"评论内容评论内容评论内容评论内容评论内容"}]
             */

            private String video_id;
            private String video_desc;
            private String fileId;
            private String video_url;
            private String video_address;
            private String video_discuss_count;
            private String video_praise_count;
            private String video_share_count;
            private String video_collect_count;
            private String user_id;
            private String createtime;
            private String user_name;
            private String user_avatar;
            private String is_praise;
            private List<String> video_img_url;
            private List<VideoPraiseListBean> video_praise_list;
            private List<VideoDiscussListBean> video_discuss_list;

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

            public String getIs_praise() {
                return is_praise;
            }

            public void setIs_praise(String is_praise) {
                this.is_praise = is_praise;
            }

            public List<String> getVideo_img_url() {
                return video_img_url;
            }

            public void setVideo_img_url(List<String> video_img_url) {
                this.video_img_url = video_img_url;
            }

            public List<VideoPraiseListBean> getVideo_praise_list() {
                return video_praise_list;
            }

            public void setVideo_praise_list(List<VideoPraiseListBean> video_praise_list) {
                this.video_praise_list = video_praise_list;
            }

            public List<VideoDiscussListBean> getVideo_discuss_list() {
                return video_discuss_list;
            }

            public void setVideo_discuss_list(List<VideoDiscussListBean> video_discuss_list) {
                this.video_discuss_list = video_discuss_list;
            }

            public static class VideoPraiseListBean {
                /**
                 * user_name : 口水鸡
                 * user_avatar : http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg
                 */

                private String user_name;
                private String user_avatar;

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

            public static class VideoDiscussListBean {
                /**
                 * user_name : 口水鸡
                 * user_avatar : http://goldenhaian.com/DouYin/uploadfile/photo/5f4f0cb7610f6.jpg
                 * video_discuss : 赞
                 */

                private String user_name;
                private String user_avatar;
                private String video_discuss;

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

                public String getVideo_discuss() {
                    return video_discuss;
                }

                public void setVideo_discuss(String video_discuss) {
                    this.video_discuss = video_discuss;
                }
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

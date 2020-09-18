package com.leichui.shortviedeo.mapper

import com.leichui.shortviedeo.config.BASEURL
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.Callback
import java.io.File

object ApiMapper {
    fun sendVaild(user_tel: String, type: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Login/sendVaild")
                .params("user_tel", user_tel)
                .params("type", type)
                .execute(callback)
    }
    fun codeLogin(user_tel: String, code: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Login/CodeLogin")
                .params("user_tel", user_tel)
                .params("code", code)
                .execute(callback)
    }
    fun register(user_tel: String, code: String, user_pwd: String, user_pwd1: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Login/Register")
                .params("user_tel", user_tel)
                .params("code", code)
                .params("user_pwd", user_pwd)
                .params("user_pwd1", user_pwd1)
                .execute(callback)
    }
    fun addVideo(user_token: String, video_desc: String, fileId: String, video_url: String,
                 video_img_url: String,video_address: String,video_trade_tag: String,video_product_tag: String,
                 is_have_good: String,is_good_app: String,is_show_car: String,good_id: String,good_name: String,good_price: String,good_app_url: String,
                 callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/addVideo")
                .params("user_token", user_token)
                .params("video_desc", video_desc)
                .params("fileId", fileId)
                .params("video_url", video_url)
                .params("video_img_url", video_img_url)
                .params("video_address", video_address)
                .params("video_trade_tag", video_trade_tag)
                .params("video_product_tag", video_product_tag)
                .params("is_have_good", is_have_good)
                .params("is_good_app", is_good_app)
                .params("is_show_car", is_show_car)
                .params("good_id", good_id)
                .params("good_name", good_name)
                .params("good_price", good_price)
                .params("good_app_url", good_app_url)
                .execute(callback)
    }

    fun getVideoList(user_token: String, key_word: String, is_follow: String, page: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/getVideoList")
                .params("user_token", user_token)
                .params("key_word", key_word)
                .params("is_follow", is_follow)
                .params("page", page)
                .execute(callback)
    }

    fun addVideoDiscuss(user_token: String, video_discuss: String, video_id: String, is_video_friend: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/addVideoDiscuss")
                .params("user_token", user_token)
                .params("video_discuss", video_discuss)
                .params("video_id", video_id)
                .params("is_video_friend", is_video_friend)
                .execute(callback)
    }

    fun getVideoDiscussList(user_token: String,  video_id: String, is_video_friend: String,page: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/getVideoDiscussList")
                .params("user_token", user_token)
                .params("video_id", video_id)
                .params("is_video_friend", is_video_friend)
                .params("page", page)
                .execute(callback)
    }
    fun addVideoPraise(user_token: String, video_id: String, is_video_friend: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/addVideoPraise")
                .params("user_token", user_token)
                .params("video_id", video_id)
                .params("is_video_friend", is_video_friend)
                .execute(callback)
    }
    fun addVideoCollect(user_token: String, video_id: String, is_video_friend: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/addVideoCollect")
                .params("user_token", user_token)
                .params("video_id", video_id)
                .params("is_video_friend", is_video_friend)
                .execute(callback)
    }

    fun upUserAvatar(user_token: String, file: File, callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/upUserAvatar")
                .params("user_token", user_token)
                .params("image", file)
                .execute(callback)
    }

    fun getMyInfo(user_token: String,  callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/getMyInfo")
                .params("user_token", user_token)
                .execute(callback)
    }
    fun updateMyInfo(user_token: String, user_name: String, company_name: String,
                     user_mail: String, user_department: String, user_tag: String,  callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/updateMyInfo")
                .params("user_token", user_token)
                .params("user_name", user_name)
                .params("company_name", company_name)
                .params("user_mail", user_mail)
                .params("user_department", user_department)
                .params("user_tag", user_tag)
                .execute(callback)
    }
    fun upImg( file: File,  callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/upImg")
                .params("image", file)
                .execute(callback)
    }
    fun addMyCompany(user_token: String, company_name: String, company_img_0: String, company_img_1: String,  company_img_2: String,  callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/addMyCompany")
                .params("user_token", user_token)
                .params("company_name", company_name)
                .params("company_img_0", company_img_0)
                .params("company_img_1", company_img_1)
                .params("company_img_2", company_img_2)
                .execute(callback)
    }

    fun getMyCompanyInfo(user_token: String,  callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/getMyCompanyInfo")
                .params("user_token", user_token)
                .execute(callback)
    }

    fun updateUserTel(user_token: String, user_tel: String, code: String,  callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/updateUserTel")
                .params("user_token", user_token)
                .params("user_tel", user_tel)
                .params("code", code)
                .execute(callback)
    }

    fun findPwd(user_tel: String, code: String, user_pwd: String, user_pwd1: String,  callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Login/findPwd")
                .params("user_tel", user_tel)
                .params("code", code)
                .params("user_pwd", user_pwd)
                .params("user_pwd1", user_pwd1)
                .execute(callback)
    }
    fun getGoodList(user_token: String, shop_user_id: String, key_word: String, page: String,  callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Shop/getGoodList")
                .params("user_token", user_token)
                .params("shop_user_id", shop_user_id)
                .params("key_word", key_word)
                .params("page", page)
                .execute(callback)
    }
    fun getGoodInfo(user_token: String, good_id: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Shop/getGoodInfo")
                .params("user_token", user_token)
                .params("good_id", good_id)
                .execute(callback)
    }
    fun addOrder(user_token: String, address_id: String,good_id: String,good_sum: String,good_specs: String,video_id: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Order/addOrder")
                .params("user_token", user_token)
                .params("address_id", address_id)
                .params("good_id", good_id)
                .params("good_sum", good_sum)
                .params("good_specs", good_specs)
                .params("video_id", video_id)
                .execute(callback)
    }
    fun getMyAddressList(user_token: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Shop/getMyAddressList")
                .params("user_token", user_token)
                .execute(callback)
    }
    fun getAreaList(pid: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Shop/getAreaList")
                .params("pid", pid)
                .execute(callback)
    }
    fun addMyAddress(user_token: String, name: String, tel: String, address_info: String,is_default: String, province_id: String,city_id: String, area_id: String, callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}?s=/Home/Shop/addMyAddress")
                .params("user_token", user_token)
                .params("name", name)
                .params("tel", tel)
                .params("address_info", address_info)
                .params("is_default", is_default)
                .params("province_id", province_id)
                .params("city_id", city_id)
                .params("area_id", area_id)
                .execute(callback)
    }

    fun delMyAddress(user_token: String,address_id: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Shop/delMyAddress")
                .params("user_token", user_token)
                .params("address_id", address_id)
                .execute(callback)
    }
    fun getMyaddressInfo(user_token: String,address_id: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Shop/getMyaddressInfo")
                .params("user_token", user_token)
                .params("address_id", address_id)
                .execute(callback)
    }


    fun updateMyAddress(user_token: String,address_id: String, name: String, tel: String, address_info: String,is_default: String, province_id: String,city_id: String, area_id: String, callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}?s=/Home/Shop/updateMyAddress")
                .params("user_token", user_token)
                .params("address_id", address_id)
                .params("name", name)
                .params("tel", tel)
                .params("address_info", address_info)
                .params("is_default", is_default)
                .params("province_id", province_id)
                .params("city_id", city_id)
                .params("area_id", area_id)
                .execute(callback)
    }
    fun getOrderList(user_token: String,page: String,order_type: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Order/getOrderList")
                .params("user_token", user_token)
                .params("page", page)
                .params("order_type", order_type)
                .execute(callback)
    }

    fun delOrder(user_token: String,order_id: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Order/delOrder")
                .params("user_token", user_token)
                .params("order_id", order_id)
                .execute(callback)
    }
    fun getMyCount(user_token: String,user_id: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/getMyCount")
                .params("user_token", user_token)
                .params("user_id", user_id)
                .execute(callback)
    }
    fun getMyDiscussList(user_token: String,page: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/getMyDiscussList")
                .params("user_token", user_token)
                .params("page", page)
                .execute(callback)
    }
    fun getMyCollectList(user_token: String,page: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/getMyCollectList")
                .params("user_token", user_token)
                .params("page", page)
                .execute(callback)
    }
    fun addFriendVideo(user_token: String,video_desc: String,video_img_url: String,video_address: String,video_trade_tag: String,video_product_tag: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/addFriendVideo")
                .params("user_token", user_token)
                .params("video_desc", video_desc)
                .params("video_img_url", video_img_url)
                .params("video_address", video_address)
                .params("video_trade_tag", video_trade_tag)
                .params("video_product_tag", video_product_tag)
                .execute(callback)
    }
    fun getFriendVideoList(user_token: String,is_follow: String,page: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/getFriendVideoList")
                .params("user_token", user_token)
                .params("is_follow", is_follow)
                .params("page", page)
                .execute(callback)
    }

    fun addUserFollow(user_token: String,follow_user_id: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/addUserFollow")
                .params("user_token", user_token)
                .params("follow_user_id", follow_user_id)
                .execute(callback)
    }

    fun getUserFollow(user_token: String,page: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/getUserFollow")
                .params("user_token", user_token)
                .params("page", page)
                .execute(callback)
    }
    fun getUserFollowed(user_token: String,page: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/getUserFollowed")
                .params("user_token", user_token)
                .params("page", page)
                .execute(callback)
    }
    fun getVideoPraiseList(user_token: String,page: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/Video/getVideoPraiseList")
                .params("user_token", user_token)
                .params("page", page)
                .execute(callback)
    }

    fun getMyList(user_token: String,  page: String, is_follow: String,user_id: String,callback: Callback<String>) {
        OkGo.post<String>("${BASEURL}index.php?s=/Home/My/getMyList")
                .params("user_token", user_token)
                .params("page", page)
                .params("is_video_friend", is_follow)
                .params("user_id", user_id)
                .execute(callback)
    }
}
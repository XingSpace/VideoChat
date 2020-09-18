package com.leichui.shortviedeo.http

import android.content.Context
import android.util.Log
import com.alibaba.fastjson.JSON
import com.leichui.conghua.utils.L
import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.Base.BaseBean


fun <S> json(context: Context, result: String, clazz: Class<S>,showtoast:Boolean,checkData:Boolean): S? {
    val baseBean: BaseBean
    try {
        if(checkData){
            baseBean = JSON.parseObject(result, BaseBean::class.java)
            if (checkJsonBean(context, baseBean,showtoast)) {
                return JSON.parseObject(result, clazz)
            }
        }else{
            return JSON.parseObject(result, clazz)
        }

    } catch (e: Exception) {
        L("json parse exception")
        L(Log.getStackTraceString(e))
        T(context, "服务器繁忙，请稍后再试")
    }
    return null
}


fun <S> jsonNative(result: String, clazz: Class<S>): S? {
    try {
        return JSON.parseObject(result, clazz)
    } catch (e: Exception) {
        L("json parse exception")
        L(Log.getStackTraceString(e))
        return null
    }

}

private fun checkJsonBean(context: Context, baseBean: BaseBean,showtoast: Boolean): Boolean {
    if (baseBean.code == 2000) {
        return true
    } else {
        if(showtoast){
            T(context, baseBean.msg.dialog)
        }
        return false
    }
}

package com.leichui.shortviedeo.http

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * Created by Administrator on 2017/7/24.
 */

object AppClient {

    fun initOkGo(context: Application) {
        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setColorLevel(Level.WARNING)
        builder.addInterceptor(loggingInterceptor)

        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)

        OkGo.getInstance().init(context)
                .setOkHttpClient(builder.build())
                .setRetryCount(0)
    }


}
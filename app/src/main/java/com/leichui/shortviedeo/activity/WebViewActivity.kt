package com.leichui.shortviedeo.activity


import android.webkit.WebView
import android.webkit.WebViewClient
import com.leichui.conghua.utils.L
import com.leichui.shortviedeo.base.BaseActivity
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_webview.*


class WebViewActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_webview
    }

    override fun startAction() {
        setTitleCenter("详情")
      if(null!=  intent.getStringExtra("title")){
          setTitleCenter(  intent.getStringExtra("title"))
      }
        showLeftBackButton()
        var url = intent.getStringExtra("url")
        L("-------url:"+url)
        val webSettings = webview.settings
        //设置支持javaScript脚本语言
        webSettings.javaScriptEnabled = true
        webview.webViewClient = webClient
        //加载网络资源
        webview!!.loadUrl(url)

    }
    private val webClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view!!.loadUrl(url)
            return true
        }
    }

}

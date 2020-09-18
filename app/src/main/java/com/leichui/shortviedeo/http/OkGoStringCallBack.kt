package  com.leichui.shortviedeo.http

import android.content.Context
import com.leichui.conghua.utils.L
import com.leichui.conghua.utils.dissMissDialog
import com.leichui.conghua.utils.showWiteDialog
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request


abstract class OkGoStringCallBack<S>(val context: Context, val clazz: Class<S>, val openDialog: Boolean = true, val showtoast: Boolean = true,val checkData: Boolean = true ) : StringCallback() {

    override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
        super.onStart(request)
        if (openDialog) {
            showWiteDialog(context)
        }

    }

    override fun onSuccess(response: Response<String>?) {
        if (null == response) {
            onError(null)
            return
        }
        val bean = json(context, response!!.body(), clazz,showtoast,checkData)
        if (null != bean) {
            onSuccess2Bean(bean)
        } else {
            response.exception = IllegalStateException("json 解析出错---返回---${response.body()}")
            onError(response)
        }
    }

    abstract fun onSuccess2Bean(bean: S)

    override fun onFinish() {
        super.onFinish()
        dissMissDialog()
    }

    override fun onError(response: Response<String>?) {
        super.onError(response)
        if (null != response) {
            L("请求出错---" + response.exception)
        } else {
            L("请求出错---返回null")
        }
    }




}
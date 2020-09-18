package com.leichui.conghua.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.leichui.shortviedeo.utils.SingleSelectCallBack
import com.tencent.qcloud.xiaoshipin.R
import java.text.SimpleDateFormat
import java.util.*


inline fun T(context: Context?, msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, msg, duration).show()
}

inline fun L(log: Any, tag: String = "asd") {
    Log.e(tag, log.toString())
}

var player: MediaPlayer? = null
var materialDialog: MaterialDialog? = null

fun startMyActivity(context: Context,clazz: Class<*>){
    var inn = Intent(context,clazz)
    context.startActivity(inn)
}
fun startMyActivityWithOneParm(context: Context,clazz: Class<*>,key:String,value:String){
    var inn = Intent(context,clazz)
    inn.putExtra(key,value)
    context.startActivity(inn)
}
fun showWiteDialog(context: Context) {
    dissMissDialog()
    materialDialog = MaterialDialog.Builder(context)
            .content("请稍等...")
            .backgroundColor(Color.WHITE)
            .cancelable(false)
            .canceledOnTouchOutside(false)
            .progress(true, 0)
            .progressIndeterminateStyle(false)
            .show()

}
//在请求接口返回中调用可能无效
fun showSingleSelectDialog(setV:Set<String>,title:String,context: Context,singleSelectCallBack: SingleSelectCallBack)  {
    dissMissDialog()
    materialDialog = MaterialDialog.Builder(context)
            .title(title)
            .items(setV)
            .itemsCallback(object : MaterialDialog.ListCallback{
                override fun onSelection(dialog: MaterialDialog, view: View, which: Int, text: CharSequence) {
                    singleSelectCallBack.getSelect(text.toString())
                }
            })
            .show()
}

fun showMultipleSelectDialog(setV:Set<String>,title:String,context: Context,singleSelectCallBack: SingleSelectCallBack)  {
    dissMissDialog()
    MaterialDialog.Builder(context)
        .title(title)
        .positiveText("确认")
        .items(setV)
        .itemsCallbackMultiChoice(null
        ) { dialog, which, text ->
            var result = ""
            text.forEach {
                result += "$it;"
            }
            if(result.isNullOrEmpty()){
                dialog.dismiss()
            }else{
                result = result.substring(0,result.length-1)
                singleSelectCallBack.getSelect(result)
                dialog.dismiss()
            }
            true }
        .show()
}

fun showInputDialog(title:String,context: Context,singleSelectCallBack: SingleSelectCallBack)  {
    dissMissDialog()
    materialDialog = MaterialDialog.Builder(context)
            .title(title)
            .inputRangeRes(1,10, R.color.textBlack)
            .input("请输入","",object : MaterialDialog.InputCallback{
                override fun onInput(dialog: MaterialDialog, input: CharSequence?) {
                    singleSelectCallBack.getSelect(input.toString())
                }

            })
            .positiveText("确认")
            .show()
}

fun dip2px(context: Context, dipValue: Float): Int {
    val scale = context.getResources().getDisplayMetrics().density
    return (dipValue * scale + 0.5f).toInt()
}

fun dissMissDialog() {
    materialDialog?.dismiss()
    materialDialog = null
}


fun getPhoneType() : String{
    return android.os.Build.MODEL +"-"+ android.os.Build.VERSION.RELEASE
}


fun toTime(time: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return sdf.format(Date(time))
}

fun copyText(context: Context,str:String){
    //获取剪贴版
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//创建ClipData对象
//第一个参数只是一个标记，随便传入。
//第二个参数是要复制到剪贴版的内容
    val clip = ClipData.newPlainText("fastApp", str)
//传入clipdata对象.
    clipboard.setPrimaryClip(clip)
    T(context,"已复制至剪贴板")
}

fun callPhone(context: Context,phoneNum:String){
    var inn = Intent(Intent.ACTION_DIAL)
    var data = Uri.parse("tel:" + phoneNum)
    inn.data = data
    context.startActivity(inn)
}

fun getVersion(context: Context): String {
    try {
        val manager = context.packageManager
        val info = manager.getPackageInfo(context.packageName, 0)
        val version = info.versionName
        return "版本号：$version"
    } catch (e: Exception) {

        e.printStackTrace()

        return "找不到版本号"

    }

}
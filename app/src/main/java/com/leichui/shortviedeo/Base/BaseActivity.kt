package com.leichui.shortviedeo.base


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leichui.shortviedeo.utils.KeyBoardUtils
import com.leichui.shortviedeo.utils.StatusBarUtil
import com.tencent.qcloud.xiaoshipin.R
import com.tencent.qcloud.xiaoshipin.TCApplication.activityList
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.main_title.*
import org.greenrobot.eventbus.EventBus


abstract class BaseActivity : AppCompatActivity() {

    var useEventBus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        windowInit()
        setContentView(R.layout.activity_base)
        StatusBarUtil.StatusBarLightMode(this)
        initView()
        initToolBar()
        setActionBarNoTitle()
        startAction()
        activityList.add(this)

        eventBusInit()

    }

    open fun windowInit(){}

    fun eventBusInit(){
        if(useEventBus){
            EventBus.getDefault().register(this)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if(useEventBus){
            EventBus.getDefault().unregister(this)
        }
    }

    /*隐藏actionBar的标题*/
    private fun setActionBarNoTitle() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    /*初始化toolbar*/
    private fun initToolBar() {
        setSupportActionBar(toolbar)
    }

    /*初始化布局*/
    private fun initView() {
        val view = LayoutInflater.from(this).inflate(setLayoutId(), null)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        root_view.addView(view, params)
    }

    /*显示左上角返回按钮*/
    fun showLeftBackButton() {
        base_left_icon.setOnClickListener {
            KeyBoardUtils.closeKeybord(closeed, this)
            finish()
        }
        base_left_icon.visibility = View.VISIBLE
    }


    /*设置标题*/
    fun setTitleCenter(title: String) {
        mainTitle.visibility = View.VISIBLE
        mainTitle.text = title
    }

    fun setRightIcon(icon: Int) {
        right_icon.visibility = View.VISIBLE
        right_icon.setImageResource(icon)
    }

    fun setRightText(text: String) {
        baseRightTv.visibility = View.VISIBLE
        baseRightTv.text = text
    }

    /*设置布局*/
    abstract fun setLayoutId(): Int

    /*开始*/
    abstract fun startAction()


    /*申请权限  callBack*/
    open fun doSomeThings(requestCode: Int) {

    }

    /*申请权限  start*/
    fun getPermissions(requestCode: Int, permissions: MutableList<String>) {
        if (AndPermission.hasPermission(this@BaseActivity, permissions)) {
            doSomeThings(requestCode)
        } else {
            var strings = arrayOfNulls<String>(permissions.size)
            for (i in permissions.indices){
                strings[i]=permissions[i]
            }
            AndPermission.with(this@BaseActivity)
                    .requestCode(requestCode)
                    .permission(*strings)
                    .rationale({ requestCode, rationale ->
                        Snackbar.make(toolbar, "需要权限", Snackbar.LENGTH_LONG).setAction("授权") {
                            rationale.resume()
                        }.show()
                    })
                    .callback(object : PermissionListener {
                        override fun onSucceed(requestCode: Int, grantPermissions: MutableList<String>) {
                            checkPermission(requestCode, permissions)
                        }

                        override fun onFailed(requestCode: Int, deniedPermissions: MutableList<String>) {
                            checkPermission(requestCode, permissions)
                        }
                    })
                    .start()
        }
    }

    private fun checkPermission(requestCode: Int, permissions: MutableList<String>) {
        if (AndPermission.hasPermission(this@BaseActivity, permissions)) {
            doSomeThings(requestCode)
        } else {
            showPermissionsDialog()
        }
    }


    private fun showPermissionsDialog() {
        Snackbar.make(toolbar, "需要权限", Snackbar.LENGTH_LONG).setAction("授权") {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:" + packageName) // 根据包名打开对应的设置界面
            startActivity(intent)
        }.show()
    }
}
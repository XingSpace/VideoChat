package com.leichui.shortviedeo.activity


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.fastjson.JSON
import com.leichui.conghua.utils.L
import com.leichui.conghua.utils.T
import com.leichui.conghua.utils.showInputDialog
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.UserInfoBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.leichui.shortviedeo.utils.SingleSelectCallBack
import com.nex3z.flowlayout.FlowLayout
import com.tencent.qalsdk.util.BaseApplication
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity
import kotlinx.android.synthetic.main.activity_myinfo.*
import kotlinx.android.synthetic.main.main_title.*
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File


class MyInfoActivity : BaseActivity() {
    lateinit var userInfoBean : UserInfoBean
    var etList = ArrayList<EditText>()
    var biaoqianList = ArrayList<String>()

    override fun setLayoutId(): Int {
        return R.layout.activity_myinfo
    }

    override fun startAction() {
        setTitleCenter("个人信息")
        setRightText("编辑")
        baseRightTv.setTextColor(ContextCompat.getColor(this,R.color.app_red))
        showLeftBackButton()

        getData()

        headLl.setOnClickListener {
            var list = arrayListOf<String>()
            list.add(Manifest.permission.CAMERA)
            list.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            getPermissions(111, list)
        }

        baseRightTv.setOnClickListener {
            if(baseRightTv.text == "编辑"){
                baseRightTv.text = "完成"
                etList.forEach {
                    it.isFocusableInTouchMode = true
                    it.isClickable = true
                    it.isFocusable = true
                }
            }else{
                baseRightTv.text = "编辑"
                etList.forEach {
                    it.isFocusableInTouchMode = false
                    it.isClickable = false
                    it.isFocusable = false
                }
                updataInfo()
            }

        }


    }
    private fun updataInfo(){
        ApiMapper.updateMyInfo(TCUserMgr.getInstance().userToken,etList[0].text.toString(),etList[1].text.toString(),etList[2].text.toString(),etList[3].text.toString(),
                JSON.toJSONString(biaoqianList),object : OkGoStringCallBack<BaseBean>(this@MyInfoActivity, BaseBean::class.java, true) {
            override fun onSuccess2Bean(bean: BaseBean) {

            }
        })
    }

    private fun setData(){
        head.setImageURI(userInfoBean.data.user_avatar)

        biaoqianList.addAll(userInfoBean.data.user_tag)

        addView("名字","","请输入名字",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
        addView("年龄","","请输入年龄",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
        addView("签名","","请输入签名",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
        addView("身高","","请输入身高",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
        addView("体重","","请输入体重",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
        addView("宗教","","请输入宗教",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
        addView("角色","","请输入角色",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
        addView("喜好","","请输入喜好",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
        addView("所在城市","","请输入城市",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {

            }
        })
    }

    private fun getData(){
        ApiMapper.getMyInfo(TCUserMgr.getInstance().userToken, object : OkGoStringCallBack<UserInfoBean>(this@MyInfoActivity, UserInfoBean::class.java, true) {
            override fun onSuccess2Bean(bean: UserInfoBean) {
                userInfoBean = bean
                setData()
            }
        })
    }


    private fun addView(title:String,value:String,hint:String,image:Int,click:SingleSelectCallBack){
        var inflate = LayoutInflater.from(this).inflate(R.layout.item_myinfo, null)
        var tv1: TextView = inflate.findViewById(R.id.tv1) as TextView
        var tv2: EditText = inflate.findViewById(R.id.tv2) as EditText
        var img: ImageView = inflate.findViewById(R.id.img) as ImageView
        var biaoqian_fl_xianshi: FlowLayout = inflate.findViewById(R.id.biaoqian_fl_xianshi) as FlowLayout

        tv1.text = title

        tv2.hint = hint

        tv2.isFocusableInTouchMode = false//不可编辑
        tv2.isClickable = false//不可点击，但是这个效果我这边没体现出来，不知道怎没用
        tv2.isFocusable = false//不可编辑
        if(title == "扫名片"){
            tv2.setText(value)
        }else if(title == "标签"){

            biaoqianList.forEach { biaoqian ->
                val wordlayout = LayoutInflater.from(this@MyInfoActivity).inflate(R.layout.item_tag_word, null)
                var tag_tv = wordlayout.findViewById<TextView>(R.id.tag_tv)
                var del = wordlayout.findViewById<ImageView>(R.id.del)
                tag_tv.text=biaoqian
                del.setOnClickListener {
                    biaoqian_fl_xianshi.removeView(wordlayout)
                    biaoqianList.remove(biaoqian)
                }
                biaoqian_fl_xianshi.addView(wordlayout)
            }

            inflate.setOnClickListener {
                showInputDialog("添加标签",this,object : SingleSelectCallBack{
                    override fun getSelect(sel: String?) {
                        if(!sel.isNullOrEmpty()){
                            val wordlayout = LayoutInflater.from(this@MyInfoActivity).inflate(R.layout.item_tag_word, null)
                            var tag_tv = wordlayout.findViewById<TextView>(R.id.tag_tv)
                            var del = wordlayout.findViewById<ImageView>(R.id.del)
                            tag_tv.text=sel
                            del.setOnClickListener {
                                biaoqian_fl_xianshi.removeView(wordlayout)
                                biaoqianList.remove(sel!!)
                            }
                            biaoqian_fl_xianshi.addView(wordlayout)
                            biaoqianList.add(sel!!)
                        }

                    }

                })
            }

        }else{
            tv2.setText(value)
            etList.add(tv2)
        }

        if(image != 0){
            img.setImageResource(image)
        }
        lin.addView(inflate)
    }


    override fun doSomeThings(requestCode: Int) {
        super.doSomeThings(requestCode)
        if (requestCode == 111) {
            getNewPic()
        }
    }

    private fun getNewPic(){
        val intent = Intent(this, PhotoSelectorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("limit", 1)//number是选择图片的数量
        startActivityForResult(intent, 666)

    }

    private fun luBanYaSuo(file: File) {
        Luban.with(this)
            .load(file)
            .setCompressListener(object : OnCompressListener {
                override fun onStart() {
                }
                override fun onSuccess(file: File?) {
                    val uri = Uri.parse("file://" + file!!.path)
                    head.setImageURI(uri)
                    ApiMapper.upUserAvatar(TCUserMgr.getInstance().userToken,file, object : OkGoStringCallBack<BaseBean>(this@MyInfoActivity, BaseBean::class.java, true) {
                        override fun onSuccess2Bean(bean: BaseBean) {

                        }
                    })
                }

                override fun onError(e: Throwable?) {
                    L(Log.getStackTraceString(e))
                    T(this@MyInfoActivity, "图片处理出现错误，请重试")
                }
            }).launch()

    }


    val IMAGE_FILE_LOCATION = "file:///sdcard/tempfast.png"//temp file
    val imageUri = Uri.parse(IMAGE_FILE_LOCATION)//The Uri to store the big bitmap
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 666){
                if (data != null) {
                    val paths = data.extras.getSerializable("photos") as List<String>//path是选择拍照或者图片的地址数组
                    //处理代码
                    luBanYaSuo(File(paths[0]))
                }
            }
        }

    }

}

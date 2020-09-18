package com.leichui.shortviedeo.activity


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.fastjson.JSON
import com.facebook.drawee.view.SimpleDraweeView
import com.leichui.conghua.utils.L
import com.leichui.conghua.utils.T
import com.leichui.conghua.utils.showInputDialog
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.adapter.ImageUpAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.EventPublishBean
import com.leichui.shortviedeo.bean.ImageUpBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.leichui.shortviedeo.utils.SingleSelectCallBack
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity
import kotlinx.android.synthetic.main.activity_publish.*
import kotlinx.android.synthetic.main.main_title.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File


class PublishActivity : BaseActivity() {
    var hangYeTagList = ArrayList<String>()
    var chanPingTagList = ArrayList<String>()
    var photoLuBanList = ArrayList<File>()
    var uploadImageCount = 9
    var uplist = ArrayList<String>()

    val mPicList = ArrayList<String>()
    lateinit var adapter: ImageUpAdapter
    override fun setLayoutId(): Int {
        return R.layout.activity_publish
    }

    override fun startAction() {
        useEventBus = true
        setRightText("发布")
        baseRightTv.setTextColor(ContextCompat.getColor(this,R.color.app_red))
        showLeftBackButton()

        initAdapter()


        add_hangye.setOnClickListener {
            showInputDialog("添加行业标签",this,object : SingleSelectCallBack {
                override fun getSelect(sel: String?) {
                    if(!sel.isNullOrEmpty()){
                        val wordlayout = LayoutInflater.from(this@PublishActivity).inflate(R.layout.item_tag_word, null)
                        var tag_tv = wordlayout.findViewById<TextView>(R.id.tag_tv)
                        var del = wordlayout.findViewById<ImageView>(R.id.del)
                        tag_tv.text=sel
                        del.setOnClickListener {
                            hangye_fl_xianshi.removeView(wordlayout)
                            hangYeTagList.remove(sel!!)
                        }
                        hangye_fl_xianshi.addView(wordlayout)
                        hangYeTagList.add(sel!!)
                    }

                }

            })
        }
        add_chanping.setOnClickListener {
            showInputDialog("添加产品标签",this,object : SingleSelectCallBack {
                override fun getSelect(sel: String?) {
                    if(!sel.isNullOrEmpty()){
                        val wordlayout = LayoutInflater.from(this@PublishActivity).inflate(R.layout.item_tag_word, null)
                        var tag_tv = wordlayout.findViewById<TextView>(R.id.tag_tv)
                        var del = wordlayout.findViewById<ImageView>(R.id.del)
                        tag_tv.text=sel
                        del.setOnClickListener {
                            chanping_fl_xianshi.removeView(wordlayout)
                            chanPingTagList.remove(sel!!)
                        }
                        chanping_fl_xianshi.addView(wordlayout)
                        chanPingTagList.add(sel!!)
                    }

                }

            })
        }
        baseRightTv.setOnClickListener {
            if(jianjie.text.toString().isNullOrEmpty()){
                T(this,"请输入简介")
            }else{
                var photoUpList = ArrayList<String>()
                if(photoLuBanList.size == 0){
                    ApiMapper.addFriendVideo(TCUserMgr.getInstance().userToken,jianjie.text.toString(),JSON.toJSONString(photoUpList),zhanweihao.text.toString(),
                            JSON.toJSONString(hangYeTagList),JSON.toJSONString(chanPingTagList), object : OkGoStringCallBack<BaseBean>(this@PublishActivity, BaseBean::class.java, true) {
                        override fun onSuccess2Bean(bean: BaseBean) {
                            finish()
                            T(this@PublishActivity,"发布成功")
                        }
                    })
                }else{

                    photoLuBanList.forEach {
                        ApiMapper.upImg(it, object : OkGoStringCallBack<ImageUpBean>(this, ImageUpBean::class.java, true) {
                            override fun onSuccess2Bean(bean: ImageUpBean) {
                                photoUpList.add(bean.data.img_url)
                                if(photoUpList.size == photoLuBanList.size){

                                    ApiMapper.addFriendVideo(TCUserMgr.getInstance().userToken,jianjie.text.toString(),JSON.toJSONString(photoUpList),zhanweihao.text.toString(),
                                            JSON.toJSONString(hangYeTagList),JSON.toJSONString(chanPingTagList), object : OkGoStringCallBack<BaseBean>(this@PublishActivity, BaseBean::class.java, true) {
                                        override fun onSuccess2Bean(bean: BaseBean) {
                                            finish()
                                            T(this@PublishActivity,"发布成功")
                                        }
                                    })
                                }
                            }
                        })
                    }
                }
            }

        }
    }

    private fun initAdapter(){
        adapter = ImageUpAdapter(this)
        gview_recyclerView.adapter = adapter

        val gridLayoutManager = GridLayoutManager(this, 4)
        gview_recyclerView.setLayoutManager(gridLayoutManager)
        addAdapterItem()
    }

    private fun request(code:Int) {
        val list = mutableListOf<String>()
        list.add(Manifest.permission.CAMERA)
        list.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        getPermissions(code, list)
    }
    private fun addAdapterItem(){
        mPicList.add("-1")
        adapter.addAll(mPicList)
    }

    private fun getNewPic(){
        var count = uploadImageCount - mPicList.size + 1
        val intent = Intent(this, PhotoSelectorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("limit", count)//number是选择图片的数量
        startActivityForResult(intent, 666)

    }

    override fun doSomeThings(requestCode: Int) {
        super.doSomeThings(requestCode)
        if (requestCode == 999) {
            getNewPic()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 666){
                if (data != null) {
                    val paths = data.extras.getSerializable("photos") as List<String>//path是选择拍照或者图片的地址数组
                    if(paths.isNotEmpty()){
                        paths.forEach {
                            luBanYaSuo(it)
                        }
                        mPicList.addAll(paths)
                        adapter.clear()
                        mPicList.remove("-1")
                        addAdapterItem()
                    }

                }
            }
        }

    }

    private fun luBanYaSuo(f: String) {
        var file  = File(f)
        Luban.with(this)
                .load(file)
                .setCompressListener(object : OnCompressListener {
                    override fun onStart() {
                    }
                    override fun onSuccess(file: File?) {
                        val uri = Uri.parse("file://" + file!!.path)
                        photoLuBanList.add(file)
                    }

                    override fun onError(e: Throwable?) {
                        L(Log.getStackTraceString(e))
                        T(this@PublishActivity, "图片处理出现错误，请重试")
                    }
                }).launch()

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEventMainThread(eventBean: EventPublishBean){
        if(eventBean.imgPath == "-1"){
            request(999)
        }else{
            mPicList.remove(eventBean.imgPath)
            adapter.clear()
            mPicList.remove("-1")
            addAdapterItem()
        }
    }
}

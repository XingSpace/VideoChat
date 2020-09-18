package com.leichui.shortviedeo.activity


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.leichui.conghua.utils.L
import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.Base.BaseBean
import com.tencent.qcloud.xiaoshipin.R
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.CertifyInfoBean
import com.leichui.shortviedeo.bean.ImageUpBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity
import kotlinx.android.synthetic.main.activity_enterprisecf.*
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File


class EnterpriseCertificationActivity : BaseActivity() {
    var photoList = ArrayList<String>()
    var photoLuBanList = ArrayList<File>()

    override fun setLayoutId(): Int {
        return R.layout.activity_enterprisecf
    }

    override fun startAction() {
        setTitleCenter("企业认证")
        showLeftBackButton()
        getData()
    }

    private fun getData(){
        ApiMapper.getMyCompanyInfo(TCUserMgr.getInstance().userToken, object : OkGoStringCallBack<CertifyInfoBean>(this, CertifyInfoBean::class.java, true) {
            override fun onSuccess2Bean(bean: CertifyInfoBean) {
                if(bean.data.company_status == "0"){//（0未审核，1通过审核，2拒绝审核,3未提交）
                    isDoing(bean)
                }else if(bean.data.company_status == "1"){
                    isPass(bean)
                }else{
                    notPass()
                }
            }
        })
    }

    private fun isPass(bean: CertifyInfoBean){
        name.setText(bean.data.company_name)

        name.isFocusableInTouchMode = false//不可编辑
        name.isClickable = false//不可点击，但是这个效果我这边没体现出来，不知道怎没用
        name.isFocusable = false//不可编辑
        commit.visibility = View.GONE
        takePhotoRl.visibility = View.GONE
        if(!bean.data.company_img_0.isNullOrEmpty()){
            setImageView(bean.data.company_img_0)
        }
        if(!bean.data.company_img_1.isNullOrEmpty()){
            setImageView(bean.data.company_img_1)
        }
        if(!bean.data.company_img_2.isNullOrEmpty()){
            setImageView(bean.data.company_img_2)
        }
        name.setText(bean.data.company_name)
        del.visibility = View.GONE
    }
    private fun isDoing(bean: CertifyInfoBean){
        name.setText(bean.data.company_name)
        name.isFocusableInTouchMode = false//不可编辑
        name.isClickable = false//不可点击，但是这个效果我这边没体现出来，不知道怎没用
        name.isFocusable = false//不可编辑
        commit.setTextColor(ContextCompat.getColor(this,R.color.white))
        commit.setBackgroundColor(ContextCompat.getColor(this,R.color.hint_color))
        takePhotoRl.visibility = View.GONE
        commit.text = "审核中"
        if(!bean.data.company_img_0.isNullOrEmpty()){
            setImageView(bean.data.company_img_0)
        }
        if(!bean.data.company_img_1.isNullOrEmpty()){
            setImageView(bean.data.company_img_1)
        }
        if(!bean.data.company_img_2.isNullOrEmpty()){
            setImageView(bean.data.company_img_2)
        }
        name.setText(bean.data.company_name)
        del.visibility = View.GONE
    }

    private fun setImageView(url :String){
        var inflate = LayoutInflater.from(this@EnterpriseCertificationActivity).inflate(R.layout.item_photo, null)
        var photoSdv = inflate.findViewById(R.id.photoSdv) as SimpleDraweeView
        var del = inflate.findViewById(R.id.del) as TextView
        del.visibility = View.GONE
        photoSdv.setImageURI(url)
        photoL.addView(inflate)
    }

    private fun notPass(){
        takePhotoRl.setOnClickListener {
            var list = arrayListOf<String>()
            list.add(Manifest.permission.CAMERA)
            list.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            getPermissions(111, list)
        }
        del.setOnClickListener {
            name.setText("")
        }
        commit.setOnClickListener {
            var photoUpList = ArrayList<String>()
            photoLuBanList.forEach {
                ApiMapper.upImg(it, object : OkGoStringCallBack<ImageUpBean>(this@EnterpriseCertificationActivity, ImageUpBean::class.java, true) {
                    override fun onSuccess2Bean(bean: ImageUpBean) {
                        photoUpList.add(bean.data.img_url)
                        if(photoUpList.size == photoLuBanList.size){
                            var im1 = ""
                            var im2 = ""
                            var im3 = ""
                            try {
                                im1 = photoUpList[0]
                            }catch (e:Exception){}
                            try {
                                im2 = photoUpList[1]
                            }catch (e:Exception){}
                            try {
                                im3 = photoUpList[2]
                            }catch (e:Exception){}
                            ApiMapper.addMyCompany(TCUserMgr.getInstance().userToken,name.text.toString(),im1,im2,im3, object : OkGoStringCallBack<BaseBean>(this@EnterpriseCertificationActivity, BaseBean::class.java, true) {
                                override fun onSuccess2Bean(bean: BaseBean) {
                                    finish()
                                    T(this@EnterpriseCertificationActivity,"已提交审核")
                                }
                            })
                        }
                    }
                })
            }

        }
    }



    override fun doSomeThings(requestCode: Int) {
        super.doSomeThings(requestCode)
        if (requestCode == 111) {
            getNewPic()
        }
    }

    private fun getNewPic(){
        var i = photoList.size
        val intent = Intent(this, PhotoSelectorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("limit", 3-i)//number是选择图片的数量
        startActivityForResult(intent, 666)

    }

    private fun luBanYaSuo(f: String) {
        var file  = File(f)
        Luban.with(this)
                .load(file)
                .setCompressListener(object : OnCompressListener {
                    override fun onStart() {
                    }
                    override fun onSuccess(file: File?) {
                        var inflate = LayoutInflater.from(this@EnterpriseCertificationActivity).inflate(R.layout.item_photo, null)
                        var photoSdv = inflate.findViewById(R.id.photoSdv) as SimpleDraweeView
                        var del = inflate.findViewById(R.id.del) as TextView
                        del.setOnClickListener {
                            photoL.removeView(inflate)
                            photoList.remove(f)
                            photoLuBanList.remove(file)
                        }
                        val uri = Uri.parse("file://" + file!!.path)
                        photoLuBanList.add(file)
                        photoSdv.setImageURI(uri)
                        photoL.addView(inflate)
                    }

                    override fun onError(e: Throwable?) {
                        L(Log.getStackTraceString(e))
                        T(this@EnterpriseCertificationActivity, "图片处理出现错误，请重试")
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
                    if(paths.isNotEmpty()){
                        paths.forEach {
                            luBanYaSuo(it)
                        }
                        photoList.addAll(paths)
                    }

                }
            }
        }

    }

}

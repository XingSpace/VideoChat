package npay.npay.yinmengyuan.fragment.adapter

import android.content.Intent
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.activity.EditAdressActivity
import com.leichui.shortviedeo.bean.AddressListBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R


/**
 * Created by Administrator on 2017/7/31.
 */

class DiZhiGuanLiHolder(parent: ViewGroup) : BaseViewHolder<AddressListBean.DataBean>(parent, R.layout.dizhi_list) {
    override fun setData(data: AddressListBean.DataBean) {
        super.setData(data)

        val name: TextView
        val phone: TextView
        val address: TextView
        val moren: CheckBox
        val edit: TextView
        val del: TextView

        name = itemView.findViewById<TextView>(R.id.name_text)
        phone = itemView.findViewById<TextView>(R.id.phone_text)
        address = itemView.findViewById<TextView>(R.id.address_txt)
        moren = itemView.findViewById<CheckBox>(R.id.checkbox)
        edit = itemView.findViewById<TextView>(R.id.edit_text)
        del = itemView.findViewById<TextView>(R.id.del_text)


        name.text = data.name
        phone.text = data.tel
        address.text = data.address_info
        moren.isChecked = data.is_default.equals("1")
        if (!data.is_default.equals("1")) {
            moren.setOnClickListener {
//                ApiMapper.EditMyDefaultList(BaseApplication.getUser(context)!!.user_token, data.address_id.toString(), object : OkGoStringCallBack<BaseBean>(context, BaseBean::class.java) {
//                    override fun onSuccess2Bean(bean: BaseBean) {
//                        //如果当前地址不是默认地址 当选择为默认按钮时  所有的 item 都处于不是默认的状态
//                        for (i in getOwnerAdapter<DiZhiGuanLiAdapter>()!!.allData.indices) {
//                            getOwnerAdapter<DiZhiGuanLiAdapter>()!!.allData[i].is_default = "0"
//                        }
//                        //而 当前选择的成为默认状态
//                        getOwnerAdapter<DiZhiGuanLiAdapter>()!!.allData[position].is_default = "1"
//                        //因为数据改变了  提醒一下 getOwnerAdapter  刷新一下数据
//                        getOwnerAdapter<DiZhiGuanLiAdapter>()!!.notifyDataSetChanged()
//                    }
//                })
            }
        }
        edit.setOnClickListener {

            var inn = Intent(context, EditAdressActivity::class.java)
            inn.putExtra("address_id", data.address_id)
            context.startActivity(inn)

        }
        del.setOnClickListener {
            MaterialDialog.Builder(context)
                    .content("删除地址")
                    .contentColorRes(R.color.textBlack)
                    .backgroundColorRes(R.color.white)
                    .positiveText("删除")
                    .negativeText("取消")
                    .onPositive({ dialog, which ->
                        ApiMapper.delMyAddress(TCUserMgr.getInstance().userToken, data.address_id, object : OkGoStringCallBack<BaseBean>(context, BaseBean::class.java) {
                            override fun onSuccess2Bean(bean: BaseBean) {
                                getOwnerAdapter<DiZhiGuanLiAdapter>()!!.remove(position)
                                getOwnerAdapter<DiZhiGuanLiAdapter>()!!.notifyDataSetChanged()
                            }
                        })
                    })
                    .show()

        }
    }
}

package com.leichui.shortviedeo.Fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import com.leichui.shortviedeo.view.RecyclerViewShouyeItem
import com.leichui.shortviedeo.view.ShouyeItem
import com.leichui.shortviedeo.view.SliderViewShouyeItem
import com.tencent.qcloud.ugckit.utils.ToastUtil
import com.tencent.qcloud.xiaoshipin.R
import com.yiw.circledemo.listener.OnClickEventListener
import kotlinx.android.synthetic.main.fragment_shouye.*
import java.util.ArrayList

/**
 * 首页的fragment
 */
class ShouyeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_shouye, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        //往首页顶部的banner中添加图片
        multiImage.addSlider(DefaultSliderView(activity).image(R.mipmap.ic_launcher).setOnSliderClickListener {
            ToastUtil.toastShortMessage("顶部banner的监听")
        })
        multiImage.addSlider(DefaultSliderView(activity).image(R.mipmap.ic_launcher))

        //热门场所
        var list:List<RecyclerViewShouyeItem.NormalData> = ArrayList()
        list+= RecyclerViewShouyeItem.NormalData("测试数据1","adsfasdaf")
        list+= RecyclerViewShouyeItem.NormalData("测试数据2","adsfasdaf")
        list+= RecyclerViewShouyeItem.NormalData("测试数据3","adsfasdaf")
        list+= RecyclerViewShouyeItem.NormalData("测试数据4","adsfasdaf")
        list+= RecyclerViewShouyeItem.NormalData("测试数据5","adsfasdaf")
        remenchangsuo.setNormalStyle(list)
        remenchangsuo.onItemClickListener = object : ShouyeItem.OnItemClickListener {
            override fun onItemClick(position: Int, event: Int) {
                ToastUtil.toastShortMessage("被点到的position->"+position + " evnet->"+event)
            }
        }

        //推荐活动
        var listTuijian:List<SliderViewShouyeItem.ItemData> = ArrayList()
        listTuijian += SliderViewShouyeItem.ItemData()
                .face("http//")
                .address("中山路")
                .canjia("已经参加人数")
                .name("萌宠")
                .people("十个人")
                .price("2000币")
                .time("早上十点")
                .tips("截至报名时间：2020-8-16-18:00 235人查看")
                .title("MOST CLUB派对酒吧 周末PARTY")

        listTuijian += SliderViewShouyeItem.ItemData()
                .face("http//")
                .address("中山路")
                .canjia("已经参加人数")
                .name("萌宠")
                .people("十个人")
                .price("2000币")
                .time("早上十点")
                .tips("截至报名时间：2020-8-16-18:00 235人查看")
                .title("MOST CLUB派对酒吧 周末PARTY")

        listTuijian += SliderViewShouyeItem.ItemData()
                .face("http//")
                .address("中山路")
                .canjia("已经参加人数")
                .name("萌宠")
                .people("十个人")
                .price("2000币")
                .time("早上十点")
                .tips("截至报名时间：2020-8-16-18:00 235人查看")
                .title("MOST CLUB派对酒吧 周末PARTY")

        tuijianhuodong.setList(listTuijian)
        tuijianhuodong.onItemClickListener(OnClickEventListener { view, position, event ->
            ToastUtil.toastShortMessage("点击到了热门活动position->"+position)
        })


        //火爆攻略
        var listGonglue:List<RecyclerViewShouyeItem.GongLueData> = ArrayList()
        listGonglue+= RecyclerViewShouyeItem.GongLueData("测试数据1","adsfasdaf","sdf",19,true,"876756543433tgg")
        listGonglue+= RecyclerViewShouyeItem.GongLueData("测试数据2","adsfasdaf","sdf",99,true,"876756543433tgg")
        listGonglue+= RecyclerViewShouyeItem.GongLueData("测试数据3","adsfasdaf","sdf",1009,true,"876756543433tgg")
        listGonglue+= RecyclerViewShouyeItem.GongLueData("测试数据4","adsfasdaf","sdf",19,true,"876756543433tgg")
        listGonglue+= RecyclerViewShouyeItem.GongLueData("测试数据5","adsfasdaf","sdf",999,true,"876756543433tgg")
        huobaogonglue.setGongLueStyle(listGonglue)
        huobaogonglue.onItemClickListener = object : ShouyeItem.OnItemClickListener{
            override fun onItemClick(position: Int, event: Int) {
                ToastUtil.toastShortMessage("被点到的position->"+position + " evnet->"+event)
            }
        }

        //月老
        var listFace:List<RecyclerViewShouyeItem.FaceData> = ArrayList()
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据1","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据2","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据3","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据4","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据5","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据5","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据5","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据5","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据5","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据5","adsfasdaf")
        listFace+= RecyclerViewShouyeItem.FaceData("测试数据5","adsfasdaf")
        yuelao.setFaceStyle(listFace)
        yuelao.onItemClickListener = object : ShouyeItem.OnItemClickListener{
            override fun onItemClick(position: Int, event: Int) {
                ToastUtil.toastShortMessage("被点到的position->"+position + " evnet->"+event)
            }
        }

        //模特
        var listMote:List<RecyclerViewShouyeItem.NormalData> = ArrayList()
        listMote+= RecyclerViewShouyeItem.NormalData("测试数据1","adsfasdaf")
        listMote+= RecyclerViewShouyeItem.NormalData("测试数据2","adsfasdaf")
        listMote+= RecyclerViewShouyeItem.NormalData("测试数据3","adsfasdaf")
        listMote+= RecyclerViewShouyeItem.NormalData("测试数据4","adsfasdaf")
        listMote+= RecyclerViewShouyeItem.NormalData("测试数据5","adsfasdaf")
        mote.setMoteStyle(list)
        mote.onItemClickListener = object : ShouyeItem.OnItemClickListener{
            override fun onItemClick(position: Int, event: Int) {
                ToastUtil.toastShortMessage("被点到的position->"+position + " evnet->"+event)
            }
        }

    }

}
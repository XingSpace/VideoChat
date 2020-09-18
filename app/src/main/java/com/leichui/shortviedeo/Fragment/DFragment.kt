package com.leichui.shortviedeo.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leichui.conghua.utils.L
import com.leichui.conghua.utils.startMyActivity
import com.leichui.conghua.utils.startMyActivityWithOneParm
import com.leichui.shortviedeo.Fragment.MyBFragment
import com.leichui.shortviedeo.activity.*
import com.leichui.shortviedeo.bean.MyCountBean
import com.leichui.shortviedeo.bean.SomeBodyCircleBean
import com.leichui.shortviedeo.bean.SomeBodyVideoBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.leichui.shortviedeo.view.XStagGridLayoutManager
import com.tencent.qcloud.ugckit.UGCKitConstants
import com.tencent.qcloud.ugckit.module.effect.BaseRecyclerAdapter
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import com.tencent.qcloud.xiaoshipin.mainui.list.*
import com.tencent.qcloud.xiaoshipin.mainui.list.TCUGCListFragment.START_LIVE_PLAY
import com.tencent.qcloud.xiaoshipin.play.TCVodPlayerActivity
import com.yiw.circledemo.bean.MyCircleItem
import kotlinx.android.synthetic.main.fragment_d.*
import java.io.Serializable
import java.util.ArrayList


open class DFragment : Fragment() {
    var page = 0
    var userId = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_d, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        otherInit()
        L("----userId:" + userId)
        if(userId.isNullOrEmpty()){
            settingIv.setOnClickListener {
                startMyActivity(context!!, SettingActivity::class.java)
            }
//            myFansL.setOnClickListener {
//                startMyActivity(context!!, MyFansActivity::class.java)
//            }
//            myFocusL.setOnClickListener {
//                startMyActivity(context!!, MyFocusActivity::class.java)
//            }
//            friendsL.setOnClickListener {
//                startMyActivity(context!!, FriendsActivity::class.java)
//            }
//            myPraiseL.setOnClickListener {
//                startMyActivity(context!!, MyPraiseActivity::class.java)
//            }
//            myCollectL.setOnClickListener {
//                startMyActivity(context!!, MyCollectionActivity::class.java)
//            }
        }else{
            settingIv.visibility = View.GONE
            dianpuName.text = "他的店铺"
        }

        myShop.setOnClickListener {
            startMyActivityWithOneParm(context!!, ShopActivity::class.java,"userId",userId)
        }
        zuoping.setOnClickListener {
            zuoping.setTextColor(ContextCompat.getColor(context,R.color.black))
            dongtai.setTextColor(ContextCompat.getColor(context,R.color.app_grey))
            mRvVideoList.visibility = View.VISIBLE
        }
        dongtai.setOnClickListener {
            zuoping.setTextColor(ContextCompat.getColor(context,R.color.app_grey))
            dongtai.setTextColor(ContextCompat.getColor(context,R.color.black))
            mRvVideoList.visibility = View.GONE
        }

        initVideoListView()
        customVideoList()
        setFragment()
    }

    override fun onResume() {
        super.onResume()

        getMyCount()
    }
    open fun otherInit(){}
    protected fun setMyUserId(id :String){
        userId = id
    }

    private fun setFragment(){
        /*FragmentManager要管理fragment（添加，替换以及其他的执行动作）
    *的一系列的事务变化，需要通过fragmentTransaction来操作执行
     */
        var fragmentTransaction = fragmentManager.beginTransaction()
        //实例化要管理的fragment
        var myBFragment = MyBFragment.newInstance(userId)
        //通过添加（事务处理的方式）将fragment加到对应的布局中
        fragmentTransaction.add(R.id.myf,myBFragment)
        //事务处理完需要提交
        fragmentTransaction.commit()
    }

    private fun getMyCount(){

        ApiMapper.getMyCount(TCUserMgr.getInstance().userToken, userId, object : OkGoStringCallBack<MyCountBean>(context!!, MyCountBean::class.java, false) {
            override fun onSuccess2Bean(bean: MyCountBean) {
                zuoping.text = "作品 " + bean.data.video_count
                dongtai.text = "动态 " + bean.data.video_friend_count
            }

        })
    }

    lateinit var mVideoList: ArrayList<TCVideoInfo>
    private var mUGCListViewAdapter: TCUGCVideoListAdapter? = null
    private var mLastClickTime: Long = 0
    private fun initVideoListView(){
        mVideoList = ArrayList()
        mUGCListViewAdapter = TCUGCVideoListAdapter(activity, mVideoList)
        mUGCListViewAdapter!!.setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener { view, position ->
            try {
                if (0L == mLastClickTime || System.currentTimeMillis() - mLastClickTime > 1000) {
                    val item = mVideoList[position]
                    if (item == null) {
                        return@OnItemClickListener
                    }
                    startLivePlay(item, position)
                }
                mLastClickTime = System.currentTimeMillis()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRvVideoList.setLayoutManager(layoutManager)
        mRvVideoList.addItemDecoration(DividerGridItemDecoration(context))
        mRvVideoList.setAdapter(mUGCListViewAdapter)
    }

    /**
     * 开始播放视频
     *
     * @param item 视频数据
     */
    private fun startLivePlay(item: TCVideoInfo, position: Int) {
        val intent = Intent(activity, TCVodPlayerActivity::class.java)
        intent.putExtra(UGCKitConstants.PLAY_URL, item.playurl)
        intent.putExtra(UGCKitConstants.PUSHER_ID, item.userid)
        intent.putExtra(UGCKitConstants.PUSHER_NAME, if (item.nickname == null) item.userid else item.nickname)
        intent.putExtra(UGCKitConstants.PUSHER_AVATAR, item.headpic)
        intent.putExtra(UGCKitConstants.COVER_PIC, item.frontcover)
        intent.putExtra(UGCKitConstants.FILE_ID, if (item.fileid != null) item.fileid else "")
        intent.putExtra(UGCKitConstants.TCLIVE_INFO_LIST, mVideoList as Serializable)
        intent.putExtra(UGCKitConstants.TIMESTAMP, item.createTime)
        intent.putExtra(UGCKitConstants.TCLIVE_INFO_POSITION, position)
        startActivityForResult(intent, START_LIVE_PLAY)
    }

    override fun onDestroy() {
        super.onDestroy()
        TCVideoListMgr.getInstance().release()
    }

    private fun customVideoList() {
        ApiMapper.getMyList(TCUserMgr.getInstance().userToken, page.toString(), "0",userId, object : OkGoStringCallBack<SomeBodyVideoBean>(context, SomeBodyVideoBean::class.java, false, true, true) {
            override fun onSuccess2Bean(bean: SomeBodyVideoBean) {
                if (bean.data.list.size != 0) {
                    for (datum in bean.data.list) {
                        val tcVideoInfo = MyTCVideoInfo.convertToTCVideoInfo(datum)
                        mVideoList.add(tcVideoInfo)
                    }

                    mUGCListViewAdapter!!.notifyDataSetChanged()

                    page++
                    customVideoList()

                }
            }
        })

    }
}
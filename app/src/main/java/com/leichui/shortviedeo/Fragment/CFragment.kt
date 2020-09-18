package com.leichui.shortviedeo.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leichui.conghua.utils.startMyActivity
import com.leichui.shortviedeo.activity.FriendsActivity
import com.leichui.shortviedeo.activity.MyDiscussActivity
import com.leichui.shortviedeo.activity.MyFocusActivity
import com.leichui.shortviedeo.activity.MyPraiseActivity
import com.tencent.qcloud.xiaoshipin.R
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.fragment_c.*


class CFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var conversationListFragment = ConversationListFragment()
        // 此处设置 Uri. 通过 appendQueryParameter 去设置所要支持的会话类型. 例如
        // .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(),"false")
        // 表示支持单聊会话, false 表示不聚合显示, true 则为聚合显示
        var uri = Uri.parse("rong://" +
                getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build()

        conversationListFragment.uri = uri
        var  manager = fragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(R.id.container, conversationListFragment)
        transaction.commit()


        dianzanT.setOnClickListener {
            startMyActivity(context,MyPraiseActivity::class.java)
        }
        pinglunT.setOnClickListener {
            startMyActivity(context,MyDiscussActivity::class.java)
        }
        guanzhuT.setOnClickListener {
            startMyActivity(context,MyFocusActivity::class.java)
        }
        haoyouT.setOnClickListener {
            startMyActivity(context,FriendsActivity::class.java)
//            RongIM.getInstance().startPrivateChat(context , "weichat15900474254", "123456789")
        }

    }

}
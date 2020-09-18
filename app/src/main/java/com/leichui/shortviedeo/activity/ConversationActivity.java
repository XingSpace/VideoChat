package com.leichui.shortviedeo.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leichui.conghua.utils.UtKt;
import com.leichui.shortviedeo.mapper.ApiMapper;
import com.leichui.shortviedeo.utils.AndroidBug5497Workaround;
import com.leichui.shortviedeo.utils.StatusBarUtil;
import com.tencent.qcloud.xiaoshipin.R;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;


public class ConversationActivity extends AppCompatActivity {

    private String targetId = "";
    private ArrayList<Integer> deleList = new ArrayList<Integer>();
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_conversationnew);

        ImageView rightIcon = (ImageView)findViewById(R.id.conver_right_icon);
        ImageView leftIcon = (ImageView)findViewById(R.id.conver_left_icon);
        TextView title = (TextView)findViewById(R.id.conver_title_tv);
        View bacbar = findViewById(R.id.bacbar);

        int statusresult = StatusBarUtil.StatusBarLightMode(this);
        if (statusresult != 0) {
            StatusBarUtil.setStatusBarColor(this, R.color.white);
            if(statusresult == 3){
                AndroidBug5497Workaround.assistActivity(this);
                bacbar.setVisibility(View.VISIBLE);
            }
        }
        title.setText(getIntent().getData().getQueryParameter("title"));
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        targetId = getIntent().getData().getQueryParameter("targetId");
        UtKt.L("asd","targetId:"+targetId);
        FragmentManager fragmentManage = getSupportFragmentManager();
        String appendPath ;
        if(targetId.startsWith("weichat")){
            appendPath = Conversation.ConversationType.PRIVATE.getName().toLowerCase();
        }else{
            appendPath =  Conversation.ConversationType.GROUP.getName().toLowerCase();
            atMethod();
        }
        ConversationFragment fragement = (ConversationFragment) fragmentManage.findFragmentById(R.id.conversation);
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(appendPath)
                .appendQueryParameter("targetId", targetId).build();

        fragement.setUri(uri);
        RongIM.getInstance().setSendMessageListener(new MySendMessageListener());
        RongIM.getInstance().getLatestMessages(Conversation.ConversationType.PRIVATE,
                targetId, 100,
                new RongIMClient.ResultCallback<List<Message>>() {
                    @Override
                    public void onSuccess(List<Message> messages) {
                        for (Message tmp : messages) {
                            if (tmp.getMessageDirection() == Message.MessageDirection.RECEIVE) {
                                if (Flush(tmp.getReceivedTime()) && tmp.getReceivedStatus().isRead()) {
                                    System.out.println("清理");
                                    deleList.add(tmp.getMessageId());
                                }
                            } else {
                                if (Flush(tmp.getSentTime())) {
                                    System.out.println("清理");
                                    deleList.add(tmp.getMessageId());
                                }
                            }
                        }
                        int[] array = new int[deleList.size()];
                        for (int i = 0; i < deleList.size(); i++) {
                            array[i] = deleList.get(i);
                        }
                        deleteChat(array);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
//        openChat();
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(targetId.startsWith("weichat")){
//                    Intent inn = new Intent(ConversationActivity.this, XiangXiZiLiaoActivity.class);
//                    inn.putExtra("targetId",targetId);
//                    inn.putExtra("type","1");
//                    startActivity(inn);
                }else{
//                    Intent inn = new Intent(ConversationActivity.this, ChatDetailsActivity.class);
//                    inn.putExtra("targetId",targetId);
//                    startActivityForResult(inn,990);
                }
            }
        });
    }


    private void atMethod(){

//        ApiMapper.INSTANCE.getNewRongGroupUserInfo(BaseApplication.Companion.getUser(this).getUser_token(), targetId, new OkGoStringCallBack<GroupUserInfoBean>(this,GroupUserInfoBean.class,false,true){
//            @Override
//            public void onSuccess2Bean(GroupUserInfoBean bean) {
//                List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//
//                for(int i = 0;i<bean.getData().size();i++){
//
//                    Uri portraitUri = Uri.parse(GloBalKt.IURL +bean.getData().get(i).getAvatar());
//                    String name = UtKt.getTrueName(bean.getData().get(i).getChat_user_name(),bean.getData().get(i).getGroup_user_name(),bean.getData().get(i).getUser_name());
//                    UserInfo userInfo = new UserInfo(bean.getData().get(i).getUser_id(),name,portraitUri);
//                    userInfoList.add(userInfo);
//                }
//                RongIM.getInstance().setGroupMembersProvider(new RongIM.IGroupMembersProvider() {
//                    @Override
//                    public void getGroupMembers(String s, RongIM.IGroupMemberCallback iGroupMemberCallback) {
//                        iGroupMemberCallback.onGetGroupMembersResult(userInfoList);
//                    }
//                });
//            }
//        });

    }


    private class MySendMessageListener implements RongIM.OnSendMessageListener {

        /**
         * 消息发送前监听器处理接口（是否发送成功可以从 SentStatus 属性获取）。
         *
         * @param message 发送的消息实例。
         * @return 处理后的消息实例。
         */
        @Override
        public Message onSend(Message message) {
//            RongIMClient.getInstance().sendImageMessage(Conversation.ConversationType.PRIVATE, targetId, message.getContent(), "", "", new RongIMClient.SendImageMessageCallback() {
//
//                @Override
//                public void onAttached(Message message) {
//                    Log.e("asd","RongIMClient onAttached");
//                    //保存数据库成功
//                }
//
//                @Override
//                public void onError(Message message, RongIMClient.ErrorCode code) {
//                    Log.e("asd","RongIMClient onError");
//                    //发送失败
//                }
//
//                @Override
//                public void onSuccess(Message message) {
//                    Log.e("asd","RongIMClient onSuccess");
//                    //发送成功
//                }
//
//                @Override
//                public void onProgress(Message message, int progress) {
//                    Log.e("asd","RongIMClient onProgress");
//                    //发送进度
//                }
//            });
            //开发者根据自己需求自行处理逻辑
            Log.e("asd","RongIMClient message" + message.getContent().toString());
            return message;
        }

        /**
         * 消息在 UI 展示后执行/自己的消息发出后执行,无论成功或失败。
         *
         * @param message              消息实例。
         * @param sentMessageErrorCode 发送消息失败的状态码，消息发送成功 SentMessageErrorCode 为 null。
         * @return true 表示走自己的处理方式，false 走融云默认处理方式。
         */
        @Override
        public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
            MessageContent messageContent = message.getContent();
            if (message.getSentStatus() == Message.SentStatus.FAILED) {
                if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_CHATROOM) {
                    //不在聊天室
                } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_DISCUSSION) {
                    //不在讨论组
                    finish();
                } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_GROUP) {
                    //不在群组
//                    T(getApplicationContext(), "不在群组", 1000);
                } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.REJECTED_BY_BLACKLIST) {
                    //你在他的黑名单中
                }
            } else {

//                if (messageContent instanceof TextMessage) {//文本消息
//                    TextMessage textMessage = (TextMessage) messageContent;
//                    initPoint(textMessage.getContent());
//                } else if (messageContent instanceof ImageMessage) {//图片消息
//                    ImageMessage imageMessage = (ImageMessage) messageContent;
//                    initPoint("[图片]");
//                } else if (messageContent instanceof VoiceMessage) {//语音消息
//                    VoiceMessage voiceMessage = (VoiceMessage) messageContent;
//                    initPoint("[语音]");
//                } else if (messageContent instanceof RichContentMessage) {//图文消息
//                    RichContentMessage richContentMessage = (RichContentMessage) messageContent;
//                    initPoint("[图片]" + richContentMessage.getContent());
//                } else {
//                    initPoint("新消息");
//                }

            }


            return false;
        }
    }



//    private void initPoint(String message) {
//        UserMapper.INSTANCE.postChat(Companion.getUser(getApplicationContext()).getRong_UserId(),targetId ,message, new OkGoStringCallBack<BaseBean>(ConversationActivity.this, BaseBean.class, false) {
//
//            @Override
//            public void onSuccess2Bean(BaseBean bean) {
//
//            }
//
//
//
//
//        });
//    }

    @Override
    protected void onDestroy() {
//        try {
//            unregisterReceiver(mReceiver);
//        } catch (Exception e) {
//        }
        super.onDestroy();
        RongIM.getInstance().setSendMessageListener(null);
//        openChat();
    }

    private Boolean Flush(long oldtime) {
        long s = (System.currentTimeMillis() - oldtime) / (1000 * 60);
        System.out.println(oldtime + "---" + System.currentTimeMillis());
        if (s > 60) {
            return true;
        }
        return false;
    }

    //
    private void deleteChat(final int[] messageIds) {
        RongIM.getInstance().deleteMessages(messageIds, new RongIMClient.ResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}

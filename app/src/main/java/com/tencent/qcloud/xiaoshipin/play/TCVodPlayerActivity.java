package com.tencent.qcloud.xiaoshipin.play;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.leichui.conghua.utils.UtKt;
import com.leichui.shortviedeo.Base.BaseBean;
import com.leichui.shortviedeo.activity.GoodsActivity;
import com.leichui.shortviedeo.activity.LoginActivity;
import com.leichui.shortviedeo.activity.WebViewActivity;
import com.leichui.shortviedeo.bean.DiscussBean;
import com.leichui.shortviedeo.http.OkGoStringCallBack;
import com.leichui.shortviedeo.mapper.ApiMapper;
import com.leichui.shortviedeo.utils.KeyBoardUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.qcloud.ugckit.UGCKitConstants;
import com.tencent.qcloud.xiaoshipin.R;
import com.tencent.qcloud.xiaoshipin.login.TCLoginActivity;
import com.tencent.qcloud.xiaoshipin.mainui.list.TCVideoInfo;
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr;
import com.tencent.qcloud.ugckit.utils.BitmapUtils;
import com.tencent.qcloud.ugckit.utils.LogReport;
import com.tencent.qcloud.ugckit.utils.TelephonyUtil;
import com.tencent.qcloud.ugckit.utils.ToastUtil;
import com.tencent.qcloud.xiaoshipin.userinfo.UserInfoUtil;
import com.tencent.qcloud.xiaoshipin.videorecord.FollowRecordDownloader;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLog;
import com.tencent.rtmp.TXVodPlayConfig;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import npay.npay.yinmengyuan.fragment.adapter.DiscussAdapter;

public class TCVodPlayerActivity extends Activity implements ITXVodPlayListener, TelephonyUtil.OnTelephoneListener {
    private static final String TAG = "TCVodPlayerActivity";
    private VerticalViewPager mVerticalViewPager;
    private MyPagerAdapter mPagerAdapter;
    private TXCloudVideoView mTXCloudVideoView;
    private TextView mTvBack;
    private ImageView mIvCover;
    // 合拍相关
    private ImageButton mImgBtnFollowShot;
    // 发布者id 、视频地址、 发布者名称、 头像URL、 封面URL
    private List<TCVideoInfo> mTCLiveInfoList;
    private int mInitTCLiveInfoPosition;
    private int mCurrentPosition;

    /**
     * SDK播放器以及配置
     */
    private TXVodPlayer mTXVodPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initDatas();
        initViews();
        initPlayerSDK();

        TelephonyUtil.getInstance().setOnTelephoneListener(this);
        TelephonyUtil.getInstance().initPhoneListener();

        //在这里停留，让列表界面卡住几百毫秒，给sdk一点预加载的时间，形成秒开的视觉效果
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initDatas() {
        Intent intent = getIntent();
        mTCLiveInfoList = (List<TCVideoInfo>) intent.getSerializableExtra(UGCKitConstants.TCLIVE_INFO_LIST);
        mInitTCLiveInfoPosition = intent.getIntExtra(UGCKitConstants.TCLIVE_INFO_POSITION, 0);
    }

    private void initViews() {
        mTXCloudVideoView = (TXCloudVideoView) findViewById(R.id.player_cloud_view);
        mIvCover = (ImageView) findViewById(R.id.player_iv_cover);
        mTvBack = (TextView) findViewById(R.id.player_tv_back);
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mImgBtnFollowShot = (ImageButton) findViewById(R.id.imgBtn_follow_shot);
        mImgBtnFollowShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin()) {
                    return;
                }
                // 上报合唱
                LogReport.getInstance().reportChorus();
                // 合拍之前先下载视频

                TCVideoInfo tcVideoInfo = mTCLiveInfoList.get(mCurrentPosition);
                FollowRecordDownloader downloader = new FollowRecordDownloader(TCVodPlayerActivity.this);
                downloader.setDuration(mTXVodPlayer.getDuration());
                downloader.downloadVideo(tcVideoInfo);
            }
        });
        mVerticalViewPager = (VerticalViewPager) findViewById(R.id.vertical_view_pager);
        mVerticalViewPager.setOffscreenPageLimit(2);
        mVerticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                TXLog.d(TAG, "mVerticalViewPager, onPageScrolled position = " + position);
            }

            @Override
            public void onPageSelected(int position) {
                TXLog.d(TAG, "mVerticalViewPager, onPageSelected position = " + position);
                mCurrentPosition = position;
                // 滑动界面，首先让之前的播放器暂停，并seek到0
                TXLog.d(TAG, "滑动后，让之前的播放器暂停，mTXVodPlayer = " + mTXVodPlayer);
                if (mTXVodPlayer != null) {
                    mTXVodPlayer.seek(0);
                    mTXVodPlayer.pause();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mVerticalViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                TXLog.d(TAG, "mVerticalViewPager, transformPage pisition = " + position + " mCurrentPosition" + mCurrentPosition);
                if (position != 0) {
                    return;
                }

                ViewGroup viewGroup = (ViewGroup) page;
                mIvCover = (ImageView) viewGroup.findViewById(R.id.player_iv_cover);
                mTXCloudVideoView = (TXCloudVideoView) viewGroup.findViewById(R.id.player_cloud_view);


                PlayerInfo playerInfo = mPagerAdapter.findPlayerInfo(mCurrentPosition);
                if (playerInfo != null) {
                    playerInfo.vodPlayer.resume();
                    mTXVodPlayer = playerInfo.vodPlayer;
                }
            }
        });

        mPagerAdapter = new MyPagerAdapter();
        mVerticalViewPager.setAdapter(mPagerAdapter);
    }

    private boolean isLogin() {
        if (!TCUserMgr.getInstance().hasUser()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    class MyPagerAdapter extends PagerAdapter {

        ArrayList<PlayerInfo> playerInfoList = new ArrayList<>();

        protected PlayerInfo instantiatePlayerInfo(int position) {
            TXCLog.d(TAG, "instantiatePlayerInfo " + position);

            PlayerInfo playerInfo = new PlayerInfo();
            TXVodPlayer vodPlayer = new TXVodPlayer(TCVodPlayerActivity.this);
            vodPlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);
            //FIXBUG:FULL_SCREEN 合唱显示不全，ADJUST_RESOLUTION黑边
            vodPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
            vodPlayer.setVodListener(TCVodPlayerActivity.this);
            TXVodPlayConfig config = new TXVodPlayConfig();

            File sdcardDir = getExternalFilesDir(null);
            if (sdcardDir != null) {
                config.setCacheFolderPath(sdcardDir.getAbsolutePath() + "/txcache");
            }
            config.setMaxCacheItems(5);
            vodPlayer.setConfig(config);
            vodPlayer.setAutoPlay(false);

            TCVideoInfo tcLiveInfo = mTCLiveInfoList.get(position);
            playerInfo.playURL = TextUtils.isEmpty(tcLiveInfo.hlsPlayUrl) ? tcLiveInfo.playurl : tcLiveInfo.hlsPlayUrl;
            playerInfo.vodPlayer = vodPlayer;
            playerInfo.reviewstatus = tcLiveInfo.review_status;
            playerInfo.pos = position;
            playerInfoList.add(playerInfo);

            return playerInfo;
        }

        protected void destroyPlayerInfo(int position) {
            while (true) {
                PlayerInfo playerInfo = findPlayerInfo(position);
                if (playerInfo == null) {
                    break;
                }
                playerInfo.vodPlayer.stopPlay(true);
                playerInfoList.remove(playerInfo);

                TXCLog.d(TAG, "destroyPlayerInfo " + position);
            }
        }

        public PlayerInfo findPlayerInfo(int position) {
            for (int i = 0; i < playerInfoList.size(); i++) {
                PlayerInfo playerInfo = playerInfoList.get(i);
                if (playerInfo.pos == position) {
                    return playerInfo;
                }
            }
            return null;
        }

        public PlayerInfo findPlayerInfo(TXVodPlayer player) {
            for (int i = 0; i < playerInfoList.size(); i++) {
                PlayerInfo playerInfo = playerInfoList.get(i);
                if (playerInfo.vodPlayer == player) {
                    return playerInfo;
                }
            }
            return null;
        }

        public void onDestroy() {
            for (PlayerInfo playerInfo : playerInfoList) {
                playerInfo.vodPlayer.stopPlay(true);
            }
            playerInfoList.clear();
        }

        @Override
        public int getCount() {
            return mTCLiveInfoList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TXCLog.i(TAG, "MyPagerAdapter instantiateItem, position = " + position);
            TCVideoInfo videoInfo = mTCLiveInfoList.get(position);

            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_player_content, null);
            view.setId(position);

            // 封面
            ImageView coverImageView = (ImageView) view.findViewById(R.id.player_iv_cover);
            if (videoInfo.review_status == TCVideoInfo.REVIEW_STATUS_PORN) { //涉黄的图片不显示
                coverImageView.setImageResource(R.drawable.bg2);
            } else {
                BitmapUtils.blurBgPic(TCVodPlayerActivity.this, coverImageView, videoInfo.frontcover, R.drawable.bg2);
            }
            // 头像
            CircleImageView ivAvatar = (CircleImageView) view.findViewById(R.id.player_civ_avatar);
            Glide.with(TCVodPlayerActivity.this).load(videoInfo.headpic).error(R.drawable.face).into(ivAvatar);
            // 姓名
            TextView tvName = (TextView) view.findViewById(R.id.player_tv_publisher_name);
            if (TextUtils.isEmpty(videoInfo.nickname) || "null".equals(videoInfo.nickname)) {
                tvName.setText("");
            } else {
                tvName.setText(videoInfo.nickname);
            }
            // 自定义信息

            SimpleDraweeView companyImg = (SimpleDraweeView) view.findViewById(R.id.companyImg);
            TextView mytitle = (TextView) view.findViewById(R.id.mytitle);
            TextView zhanweihao = (TextView) view.findViewById(R.id.zhanweihao);
            TextView miaoshu = (TextView) view.findViewById(R.id.miaoshu);
            TextView dianzan = (TextView) view.findViewById(R.id.dianzan);
            TextView pinglun = (TextView) view.findViewById(R.id.pinglun);
            TextView fenxiang = (TextView) view.findViewById(R.id.fenxiang);
            TextView mingpian = (TextView) view.findViewById(R.id.mingpian);
            TextView pinglunshu = (TextView) view.findViewById(R.id.pinglunshu);
            LinearLayout pinglunliebiao = (LinearLayout) view.findViewById(R.id.pinglunliebiao);
            RelativeLayout guanzhu = (RelativeLayout) view.findViewById(R.id.guanzhu);
            LinearLayout infoL = (LinearLayout) view.findViewById(R.id.infoL);
            LinearLayout gouwuche = (LinearLayout) view.findViewById(R.id.gouwuche);
            TextView gouwuchemoney = (TextView) view.findViewById(R.id.gouwuchemoney);
            TextView gouwucheshangpingming = (TextView) view.findViewById(R.id.gouwucheshangpingming);
            TextView zhanwaixiangqing = (TextView) view.findViewById(R.id.zhanwaixiangqing);

            RelativeLayout picRl = (RelativeLayout) view.findViewById(R.id.picRl);
            SimpleDraweeView picRlpic = (SimpleDraweeView) view.findViewById(R.id.picRlpic);
            TextView picRlcontent = (TextView) view.findViewById(R.id.picRlcontent);
            TextView picRlMoney = (TextView) view.findViewById(R.id.picRlMoney);
            TextView picRlBuy = (TextView) view.findViewById(R.id.picRlBuy);

            ImageView guanbi = (ImageView) view.findViewById(R.id.guanbi);
            EditText pinglunEt = (EditText) view.findViewById(R.id.pinglunEt);
            EasyRecyclerView pinglun_recyclerView = (EasyRecyclerView) view.findViewById(R.id.pinglun_recyclerView);

            DiscussAdapter discussAdapter = new DiscussAdapter(TCVodPlayerActivity.this);
            pinglun_recyclerView.setAdapter(discussAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TCVodPlayerActivity.this);
            pinglun_recyclerView.setLayoutManager(linearLayoutManager);

            companyImg.setImageURI(videoInfo.company_url);
            mytitle.setText(videoInfo.company_name);
            zhanweihao.setText("展位号：" + videoInfo.video_address );

            if("1".equals(videoInfo.is_have_good)){
                if("1".equals(videoInfo.is_good_app)){
                    if("1".equals(videoInfo.is_show_car)){
                        infoL.setVisibility(View.VISIBLE);
                        gouwuche.setVisibility(View.VISIBLE);
                        gouwuchemoney.setText("¥" + videoInfo.good_price);
                        gouwucheshangpingming.setText(videoInfo.good_name);
                        gouwuche.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent inn = new Intent(TCVodPlayerActivity.this, GoodsActivity.class);
                                inn.putExtra("good_id",videoInfo.good_id);
                                startActivity(inn);
                            }
                        });
                    }else{
                        picRl.setVisibility(View.VISIBLE);
                        gouwuche.setVisibility(View.GONE);
                        infoL.setVisibility(View.VISIBLE);
                        picRlpic.setImageURI(videoInfo.good_img_url);
                        picRlcontent.setText(videoInfo.good_name);
                        picRlMoney.setText("¥" + videoInfo.good_price);
                        picRl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent inn = new Intent(TCVodPlayerActivity.this, GoodsActivity.class);
                                inn.putExtra("good_id",videoInfo.good_id);
                                startActivity(inn);
                            }
                        });
                    }
                }else{
                    picRl.setVisibility(View.GONE);
                    gouwuche.setVisibility(View.GONE);
                    infoL.setVisibility(View.VISIBLE);
                    zhanwaixiangqing.setVisibility(View.VISIBLE);
                    zhanwaixiangqing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent inn = new Intent(TCVodPlayerActivity.this, WebViewActivity.class);
                            inn.putExtra("url",videoInfo.good_app_url);
                            startActivity(inn);
                        }
                    });
                }
            }else{
                picRl.setVisibility(View.GONE);
                gouwuche.setVisibility(View.GONE);
                infoL.setVisibility(View.VISIBLE);
                zhanwaixiangqing.setVisibility(View.GONE);
            }

            miaoshu.setText(videoInfo.video_desc);
            dianzan.setText(videoInfo.video_praise_count);
            pinglun.setText(videoInfo.video_discuss_count);
            fenxiang.setText(videoInfo.video_share_count);
            pinglunshu.setText(videoInfo.video_discuss_count + "条评论");
            if("1".equals(videoInfo.video_praise)){
                Drawable drawable = getResources().getDrawable(
                        R.mipmap.shouye_yidianzan);
                // 这一步必须要做，否则不会显示。
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                dianzan.setCompoundDrawables(null, drawable ,null, null);
            }else{
                Drawable drawable = getResources().getDrawable(
                        R.mipmap.shouye_shoucang);
                // 这一步必须要做，否则不会显示。
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                dianzan.setCompoundDrawables(null, drawable ,null, null);
            }
            if("1".equals(videoInfo.is_follow) ){
                guanzhu.setVisibility(View.INVISIBLE);
            }else{
                guanzhu.setVisibility(View.VISIBLE);
            }
            dianzan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiMapper.INSTANCE.addVideoPraise(TCUserMgr.getInstance().getUserToken(),videoInfo.video_id, "0", new OkGoStringCallBack<BaseBean>(TCVodPlayerActivity.this,BaseBean.class,false,true,true) {
                        @Override
                        public void onSuccess2Bean(BaseBean bean) {
                            Integer count = Integer.valueOf(videoInfo.video_praise_count);
                            Drawable drawable;
                            if(videoInfo.video_praise == "1"){
                                count--;
                                videoInfo.video_praise = "0";
                                // 使用代码设置drawableleft
                                drawable = getResources().getDrawable(
                                        R.mipmap.shouye_shoucang);
                            }else{
                                count++;
                                videoInfo.video_praise = "1";
                                // 使用代码设置drawableleft
                                drawable = getResources().getDrawable(
                                        R.mipmap.shouye_yidianzan);
                            }
                            // 这一步必须要做，否则不会显示。
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                                    drawable.getMinimumHeight());
                            dianzan.setCompoundDrawables(null, drawable ,null, null);

                            videoInfo.video_praise_count = String.valueOf(count);
                            dianzan.setText(videoInfo.video_praise_count);
                        }
                    });
                }
            });
            pinglun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pinglunliebiao.setVisibility(View.VISIBLE);
                    ApiMapper.INSTANCE.getVideoDiscussList(TCUserMgr.getInstance().getUserToken(),videoInfo.video_id, "0","0", new OkGoStringCallBack<DiscussBean>(TCVodPlayerActivity.this,DiscussBean.class,false,true,true) {
                        @Override
                        public void onSuccess2Bean(DiscussBean bean) {
                            discussAdapter.clear();
                            discussAdapter.addAll(bean.getData().getList());
                        }
                    });
                }
            });
            fenxiang.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Integer count = Integer.valueOf(videoInfo.video_share_count);
                    Integer currentCount = Integer.valueOf(fenxiang.getText().toString());
                    if(currentCount == count){
                        ApiMapper.INSTANCE.addVideoCollect(TCUserMgr.getInstance().getUserToken(),videoInfo.video_id, "0", new OkGoStringCallBack<BaseBean>(TCVodPlayerActivity.this,BaseBean.class,false,true,true) {
                            @Override
                            public void onSuccess2Bean(BaseBean bean) {
                                Integer count = Integer.valueOf(videoInfo.video_share_count);
                                count++;
                                fenxiang.setText("" + count);
                                UtKt.T(TCVodPlayerActivity.this,"收藏成功", Toast.LENGTH_SHORT);
                            }
                        });
                    }else{
                        UtKt.T(TCVodPlayerActivity.this,"已收藏", Toast.LENGTH_SHORT);
                    }

                }
            });
            guanbi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pinglunliebiao.setVisibility(View.GONE);
                    KeyBoardUtils.closeKeybord(pinglunEt,TCVodPlayerActivity.this);
                }
            });
            pinglunEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if(i == EditorInfo.IME_ACTION_DONE){
                        ApiMapper.INSTANCE.addVideoDiscuss(TCUserMgr.getInstance().getUserToken(), pinglunEt.getText().toString(), videoInfo.video_id, "0", new OkGoStringCallBack<BaseBean>(TCVodPlayerActivity.this,BaseBean.class,false,true,true) {
                            @Override
                            public void onSuccess2Bean(BaseBean bean) {
                                ApiMapper.INSTANCE.getVideoDiscussList(TCUserMgr.getInstance().getUserToken(),videoInfo.video_id, "0","0", new OkGoStringCallBack<DiscussBean>(TCVodPlayerActivity.this,DiscussBean.class,false,true,true) {
                                    @Override
                                    public void onSuccess2Bean(DiscussBean bean) {
                                        discussAdapter.clear();
                                        discussAdapter.addAll(bean.getData().getList());

                                        pinglunshu.setText(bean.getData().getList().size() + "条评论");
                                    }
                                });
                                pinglunEt.setText("");
                                KeyBoardUtils.closeKeybord(pinglunEt,TCVodPlayerActivity.this);
                            }
                        });
                    }
                    return true;
                }
            });
            guanzhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiMapper.INSTANCE.addUserFollow(TCUserMgr.getInstance().getUserToken(), videoInfo.userid, new OkGoStringCallBack<BaseBean>(TCVodPlayerActivity.this,BaseBean.class,false,true,true) {
                        @Override
                        public void onSuccess2Bean(BaseBean bean) {
                            guanzhu.setVisibility(View.INVISIBLE);
                            videoInfo.is_follow = "1";
                        }
                    });
                }
            });

            TextView tvStatus = (TextView) view.findViewById(R.id.tx_video_review_status);
            if (videoInfo.review_status == TCVideoInfo.REVIEW_STATUS_NOT_REVIEW) {
                tvStatus.setVisibility(View.VISIBLE);
                tvStatus.setText(R.string.video_not_review);
            } else if (videoInfo.review_status == TCVideoInfo.REVIEW_STATUS_PORN) {
                tvStatus.setVisibility(View.VISIBLE);
                tvStatus.setText(R.string.video_porn);
            } else if (videoInfo.review_status == TCVideoInfo.REVIEW_STATUS_NORMAL) {
                tvStatus.setVisibility(View.GONE);
            }

            // 获取此player
            TXCloudVideoView playView = (TXCloudVideoView) view.findViewById(R.id.player_cloud_view);
            PlayerInfo playerInfo = instantiatePlayerInfo(position);
            playerInfo.playerView = playView;
            playerInfo.vodPlayer.setPlayerView(playView);

            if (playerInfo.reviewstatus == TCVideoInfo.REVIEW_STATUS_NORMAL) {
                playerInfo.vodPlayer.startPlay(playerInfo.playURL);
            } else if (playerInfo.reviewstatus == TCVideoInfo.REVIEW_STATUS_NOT_REVIEW) { // 审核中
            } else if (playerInfo.reviewstatus == TCVideoInfo.REVIEW_STATUS_PORN) {       // 涉黄

            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            TXCLog.i(TAG, "MyPagerAdapter destroyItem, position = " + position);

            destroyPlayerInfo(position);

            container.removeView((View) object);
        }
    }

    private void initPlayerSDK() {
        mVerticalViewPager.setCurrentItem(mInitTCLiveInfoPosition);
    }

    private void restartPlay() {
        if (mTXVodPlayer != null) {
            mTXVodPlayer.resume();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTXCloudVideoView != null) {
            mTXCloudVideoView.onResume();
        }
        if (mTXVodPlayer != null) {
            mTXVodPlayer.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTXCloudVideoView != null) {
            mTXCloudVideoView.onPause();
        }
        if (mTXVodPlayer != null) {
            mTXVodPlayer.pause();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTXCloudVideoView != null) {
            mTXCloudVideoView.onDestroy();
            mTXCloudVideoView = null;
        }

        mPagerAdapter.onDestroy();
        stopPlay(true);
        mTXVodPlayer = null;

        TelephonyUtil.getInstance().uninitPhoneListener();
    }

    protected void stopPlay(boolean clearLastFrame) {
        if (mTXVodPlayer != null) {
            mTXVodPlayer.stopPlay(clearLastFrame);
        }
    }

    @Override
    public void onPlayEvent(TXVodPlayer player, int event, Bundle param) {
        if (event == TXLiveConstants.PLAY_EVT_CHANGE_RESOLUTION) {
            int width = param.getInt(TXLiveConstants.EVT_PARAM1);
            int height = param.getInt(TXLiveConstants.EVT_PARAM2);
            //FIXBUG:不能修改为横屏，合唱会变为横向的
        } else if (event == TXLiveConstants.PLAY_EVT_PLAY_END) {
            restartPlay();
        } else if (event == TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME) {// 视频I帧到达，开始播放

            PlayerInfo playerInfo = mPagerAdapter.findPlayerInfo(player);
            if (playerInfo != null) {
                playerInfo.isBegin = true;
            }
            if (mTXVodPlayer == player) {
                TXLog.i(TAG, "onPlayEvent, event I FRAME, player = " + player);
                mIvCover.setVisibility(View.GONE);

                LogReport.getInstance().reportVodPlaySucc(event);
            }
        } else if (event == TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED) {
            if (mTXVodPlayer == player) {
                TXLog.i(TAG, "onPlayEvent, event prepared, player = " + player);
                mTXVodPlayer.resume();
            }
        } else if (event == TXLiveConstants.PLAY_EVT_PLAY_BEGIN) {
            PlayerInfo playerInfo = mPagerAdapter.findPlayerInfo(player);
            if (playerInfo != null && playerInfo.isBegin) {
                mIvCover.setVisibility(View.GONE);
                TXCLog.i(TAG, "onPlayEvent, event begin, cover remove");
            }
        } else if (event < 0) {
            if (mTXVodPlayer == player) {
                TXLog.i(TAG, "onPlayEvent, event prepared, player = " + player);

                LogReport.getInstance().reportVodPlayFail(event);
            }

            ToastUtil.toastShortMessage("event:" + event);
        }
    }

    @Override
    public void onNetStatus(TXVodPlayer player, Bundle status) {

    }

    @Override
    public void onRinging() {
        if (mTXVodPlayer != null) {
            mTXVodPlayer.setMute(true);
        }
    }

    @Override
    public void onOffhook() {
        if (mTXVodPlayer != null) {
            mTXVodPlayer.setMute(true);
        }
    }

    @Override
    public void onIdle() {
        if (mTXVodPlayer != null) {
            mTXVodPlayer.setMute(false);
        }
    }

}

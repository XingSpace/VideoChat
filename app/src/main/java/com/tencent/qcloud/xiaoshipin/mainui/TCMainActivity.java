package com.tencent.qcloud.xiaoshipin.mainui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leichui.shortviedeo.Fragment.BFragment;
import com.leichui.shortviedeo.activity.LoginActivity;
import com.leichui.shortviedeo.bean.UserInfoBean;
import com.leichui.shortviedeo.fragment.CFragment;
import com.leichui.shortviedeo.fragment.DFragment;
import com.leichui.shortviedeo.http.OkGoStringCallBack;
import com.leichui.shortviedeo.mapper.ApiMapper;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.qcloud.ugckit.module.record.draft.RecordDraftInfo;
import com.tencent.qcloud.ugckit.module.record.draft.RecordDraftManager;
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr;
import com.tencent.qcloud.ugckit.utils.FileUtils;
import com.tencent.qcloud.ugckit.utils.NetworkUtil;
import com.tencent.qcloud.xiaoshipin.R;
import com.tencent.qcloud.xiaoshipin.common.ShortVideoDialog;
import com.tencent.qcloud.xiaoshipin.config.TCConfigManager;
import com.tencent.qcloud.xiaoshipin.mainui.list.TCUGCListFragment;
import com.tencent.qcloud.xiaoshipin.videorecord.TCVideoRecordActivity;

import java.util.List;
import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

import static com.tencent.qcloud.xiaoshipin.TCApplication.mainActivity;

/**
 * 主界面: 短视频列表，用户信息页
 */
public class TCMainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "TCMainActivity";

    private Button mBtnVideo, mBtnSelect, mBtnUser;
    private LinearLayout shouyeL,youquanL,xiaoxiL,woL,paisheL;
    private TextView shouyeT,youquanT,xiaoxiT,woT;
    private View shouyeV,youquanV,xiaoxiV,woV;
    private Fragment mCurrentFragment;
    private Fragment mTCLiveListFragment, circleFragment,myFragment;
    private Fragment messageFragment;
    private long mLastClickPubTS = 0;
    private ShortVideoDialog mShortVideoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLanguage();

        setContentView(R.layout.activity_tcmain);
        mainActivity = this;
        rongConnect();

        initView();

        showVideoFragment();

        if (checkPermission()) {
            return;
        }

        checkLastRecordPart();
        getMyInfo();
    }

    private void getMyInfo(){

            ApiMapper.INSTANCE.getMyInfo(TCUserMgr.getInstance().getUserToken(), new OkGoStringCallBack<UserInfoBean>(this,UserInfoBean.class,false,false,false) {
                @Override
                public void onSuccess2Bean(UserInfoBean bean) {
                    try{
                        TCUserMgr.getInstance().setNickName(bean.getData().getUser_name());
                        TCUserMgr.getInstance().setHeadPic(bean.getData().getUser_avatar());
                        TCUserMgr.getInstance().setUserId(bean.getData().getUser_id());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

    }

    private void checkLastRecordPart() {
        final RecordDraftManager recordDraftManager = new RecordDraftManager(this);
        RecordDraftInfo lastDraftInfo = recordDraftManager.getLastDraftInfo();
        if (lastDraftInfo == null) {
            return;
        }
        final List<RecordDraftInfo.RecordPart> recordPartList = lastDraftInfo.getPartList();
        if (recordPartList != null && recordPartList.size() > 0) {
            TXCLog.i(TAG, "checkLastRecordPart, recordPartList");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog alertDialog = builder.setCancelable(false).setMessage(R.string.record_part_exist)
                    .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startActivity(new Intent(TCMainActivity.this, TCVideoRecordActivity.class));
                        }
                    })
                    .setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            recordDraftManager.deleteLastRecordDraft();
                            for (final RecordDraftInfo.RecordPart recordPart : recordPartList) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        FileUtils.deleteFile(recordPart.getPath());
                                    }
                                }).start();
                            }
                        }
                    }).create();
            alertDialog.show();
        }
    }

    private void setLanguage() {
        int lang = TCConfigManager.SystemConfig.getLanguage();

        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();

        switch (lang) {
            case TCConfigManager.SystemConfig.Languages.FOLLOW_SYSTEM:
                configuration.locale = Locale.getDefault();
                break;
            case TCConfigManager.SystemConfig.Languages.SIMPLIFIED_CHINESE:
                configuration.locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case TCConfigManager.SystemConfig.Languages.TRADITIONAL_CHINESE:
                configuration.locale = Locale.TRADITIONAL_CHINESE;
                break;
            case TCConfigManager.SystemConfig.Languages.ENGLISH:
                configuration.locale = Locale.ENGLISH;
                break;
            default:
                configuration.locale = Locale.getDefault();
                break;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return true;
                }
            }
        }
        return false;
    }

    private void initView() {
        mShortVideoDialog = new ShortVideoDialog();

        mBtnVideo = (Button) findViewById(R.id.btn_home_left);
        mBtnSelect = (Button) findViewById(R.id.btn_home_select);
        mBtnUser = (Button) findViewById(R.id.btn_home_right);

        paisheL  = (LinearLayout) findViewById(R.id.paisheL);
        shouyeL  = (LinearLayout) findViewById(R.id.shouyeL);
        youquanL = (LinearLayout) findViewById(R.id.youquanL);
        xiaoxiL  = (LinearLayout) findViewById(R.id.xiaoxiL);
        woL      = (LinearLayout) findViewById(R.id.woL);
        shouyeT = (TextView) findViewById(R.id.shouyeT);
        youquanT = (TextView) findViewById(R.id.youquanT);
        xiaoxiT = (TextView) findViewById(R.id.xiaoxiT);
        woT = (TextView) findViewById(R.id.woT);


        shouyeV =  findViewById(R.id.shouyeV);
        youquanV =  findViewById(R.id.youquanV);
        xiaoxiV =  findViewById(R.id.xiaoxiV);
        woV = findViewById(R.id.woV);

        mBtnUser.setOnClickListener(this);
        mBtnVideo.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);

        paisheL.setOnClickListener(this);
        shouyeL.setOnClickListener(this);
        youquanL.setOnClickListener(this);
        xiaoxiL.setOnClickListener(this);
        woL.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (TextUtils.isEmpty(TCUserMgr.getInstance().getUserToken())) {
            if (NetworkUtil.isNetworkAvailable(this) && TCUserMgr.getInstance().hasUser()) {
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shouyeL:
                showVideoFragment();
                break;
            case R.id.youquanL:
                if (checkLogin()) {
                    showCircleFragment();
                }
                break;
            case R.id.xiaoxiL:
                if (checkLogin()) {
                    showMessageFragment();
                }
                break;
            case R.id.woL:
                if (checkLogin()) {
                    showMyFragment();
                }
                break;
            case R.id.paisheL:
                if (checkLogin()) {
                    showSelect();
                }
                break;

        }
    }

    private boolean checkLogin(){
        if (!TCUserMgr.getInstance().hasUser()) {
            Intent intent = new Intent(TCMainActivity.this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void showSelect() {
        if (checkLogin()) {
            // 防止多次点击
            if (System.currentTimeMillis() - mLastClickPubTS > 1000) {
                mLastClickPubTS = System.currentTimeMillis();
                if (mShortVideoDialog.isAdded()) {
                    mShortVideoDialog.dismiss();
                } else {
                    mShortVideoDialog.show(getFragmentManager(), "");
                }
            }
        }
    }
    private void resetTab(){
        shouyeV.setVisibility(View.GONE);
        youquanV.setVisibility(View.GONE);
        xiaoxiV.setVisibility(View.GONE);
        woV.setVisibility(View.GONE);
    }

    private void showCircleFragment() {
        resetTab();
        youquanV.setVisibility(View.VISIBLE);
        if (circleFragment == null) {
            circleFragment = new BFragment();
        }
        showFragment(circleFragment, "circle_fragment");
    }

    private void showMessageFragment() {
        resetTab();
        xiaoxiV.setVisibility(View.VISIBLE);
        if (messageFragment == null) {
            messageFragment = new CFragment();
        }
        showFragment(messageFragment, "message_fragment");
    }

    private void showMyFragment() {
        resetTab();
        woV.setVisibility(View.VISIBLE);
        if (myFragment == null) {
            myFragment = new DFragment();
        }
        showFragment(myFragment, "my_fragment");
    }

    private void showVideoFragment() {
        resetTab();
        shouyeV.setVisibility(View.VISIBLE);
        if (mTCLiveListFragment == null) {
            mTCLiveListFragment = new TCUGCListFragment();
        }
        showFragment(mTCLiveListFragment, "live_list_fragment");
    }

    private void showFragment(Fragment fragment, String tag) {
        if (fragment == mCurrentFragment) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (!fragment.isAdded()) {
            transaction.add(R.id.contentPanel, fragment, tag);
        } else {
            transaction.show(fragment);
        }
        mCurrentFragment = fragment;
        transaction.commit();
    }

    private void rongConnect() {
        String token = "kgyRl5z37eHRgwyccvbJCECjsPLtie83moDeAHa1WneNfoniS8uxswh6XXE5QCd4piqeEICj4p7LxS32NilfDTZFkx7jd/GzluKGlnktJ2I=";
        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                Log.d("LoginActivity", "--onerr" + connectionErrorCode);
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {
            }
        });

        RongIM.getInstance().setCurrentUserInfo(new UserInfo("weichat15900818005","kgyRl5z37eHRgwyccvbJCECjsPLtie83moDeAHa1WneNfoniS8uxswh6XXE5QCd4piqeEICj4p7LxS32NilfDTZFkx7jd/GzluKGlnktJ2I=",Uri.parse("http://www.goldenhaian.com/weiliao/uploadfile/photo/5eb2554194e29.jpg")));
        RongIM.getInstance().setMessageAttachedUserInfo(true);

//        RongCallKit.startSingleCall(Context context, String targetId, CallMediaType mediaType);

    }

}

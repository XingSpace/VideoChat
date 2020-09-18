package com.leichui.shortviedeo.Fragment;


import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr;
import com.tencent.qcloud.xiaoshipin.R;
import com.yiw.circledemo.adapter.CircleAdapter;
import com.yiw.circledemo.bean.CircleItem;
import com.yiw.circledemo.bean.CommentConfig;
import com.yiw.circledemo.bean.CommentItem;
import com.yiw.circledemo.bean.FavortItem;
import com.yiw.circledemo.mvp.presenter.CirclePresenter;
import com.yiw.circledemo.mvp.view.ICircleView;
import com.yiw.circledemo.utils.CommonUtils;
import com.yiw.circledemo.widgets.CommentListView;
import com.yiw.circledemo.widgets.DivItemDecoration;
import com.yiw.circledemo.widgets.TitleBar;
import com.yiw.circledemo.widgets.dialog.UpLoadDialog;

import java.util.List;

public class BFragment extends Fragment implements ICircleView {
	protected static final String TAG = "asd";
	protected CircleAdapter mAdapter;
	protected LinearLayout mEditTextBody;
	protected EditText mEditText;
	protected ImageView sendIv;

	protected int mScreenHeight;
	protected int mEditTextBodyHeight;
	protected int mCurrentKeyboardH;
	protected int mSelectCircleItemH;
	protected int mSelectCommentItemOffset;

	protected int page = 0;

	protected CirclePresenter mPresenter;
	protected CommentConfig mCommentConfig;
	protected RecyclerView recyclerView;
	protected RelativeLayout bodyLayout;
	protected LinearLayoutManager layoutManager;
	protected TitleBar titleBar;

	protected final static int TYPE_PULLREFRESH = 1;
	protected final static int TYPE_UPLOADREFRESH = 2;
	protected UpLoadDialog uploadDialog;
	protected View view;
	protected int BFragment_HEADVIEW_SIZE =1 ;

	protected Boolean isMyBFragment = false;
	protected String userId = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//通过参数中的布局填充获取对应布局
		view =inflater.inflate(R.layout.fragment_b,container,false);
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		myBMethod();
		mPresenter = new CirclePresenter();
		mPresenter.attachView(this);
		initView();
	}
	protected void myBMethod(){}

	@Override
	public void onResume() {
		super.onResume();
		page = 0;
		mPresenter.loadData(userId,isMyBFragment,TYPE_PULLREFRESH,page);
		page++;
	}


	@SuppressLint({ "ClickableViewAccessibility", "InlinedApi" })
	private void initView() {

		initTitle();
		initUploadDialog();

		recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.addItemDecoration(new DivItemDecoration(2, true));
//        recyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
		recyclerView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (mEditTextBody.getVisibility() == View.VISIBLE) {
					updateEditTextBodyVisible(View.GONE, null);
					return true;
				}
				return false;
			}
		});

//        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mPresenter.loadData(TYPE_PULLREFRESH);
//                        recyclerView.setRefreshing(false);
//                    }
//                }, 2000);
//            }
//        });

		recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				Glide.with(getContext()).resumeRequests();

				if(recyclerView.canScrollVertically(1)){
				}else {
					mPresenter.loadData(userId,isMyBFragment,TYPE_UPLOADREFRESH,page);
					page++;
					Log.i(TAG, "direction 1: false 滑动到底部");//滑动到底部
				}
				if(recyclerView.canScrollVertically(-1)){
				}else {
				}
			}

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if(newState != RecyclerView.SCROLL_STATE_IDLE){
					Glide.with(getContext()).pauseRequests();
				}

			}
		});


		createAdapter();

		mAdapter.setCirclePresenter(mPresenter);
		recyclerView.setAdapter(mAdapter);

		mEditTextBody = (LinearLayout) view.findViewById(R.id.editTextBodyLl);
		mEditText = (EditText) view.findViewById(R.id.circleEt);
		sendIv = (ImageView) view.findViewById(R.id.sendIv);
		sendIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mPresenter != null) {
					//发布评论
					String content = mEditText.getText().toString().trim();
					if(TextUtils.isEmpty(content)){
						Toast.makeText(getContext(), "评论内容不能为空...", Toast.LENGTH_SHORT).show();
						return;
					}
					mPresenter.addComment(content, mCommentConfig);
				}
				updateEditTextBodyVisible(View.GONE, null);
			}
		});

		setViewTreeObserver();
	}

	public void createAdapter(){
		mAdapter = new CircleAdapter(getContext());
	}

	private void initUploadDialog() {
		uploadDialog = new UpLoadDialog(getContext());
	}

	private void initTitle() {
		titleBar = (TitleBar) view.findViewById(R.id.main_title_bar);
		titleBar.setVisibility(View.GONE);
		titleBar.setTitle("朋友圈");
		titleBar.setTitleColor(getResources().getColor(R.color.white));
		titleBar.setBackgroundColor(getResources().getColor(R.color.title_bg));

		TextView textView = (TextView) titleBar.addAction(new TitleBar.TextAction("发布视频") {
			@Override
			public void performAction(View view) {
				//Toast.makeText(MainActivity.this, "敬请期待...", Toast.LENGTH_SHORT).show();

			}
		});
		textView.setTextColor(getResources().getColor(R.color.white));
	}


	private void setViewTreeObserver() {
		bodyLayout = (RelativeLayout) view.findViewById(R.id.bodyLayout);
		final ViewTreeObserver swipeRefreshLayoutVTO = bodyLayout.getViewTreeObserver();
		swipeRefreshLayoutVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {

				Rect r = new Rect();
				bodyLayout.getWindowVisibleDisplayFrame(r);
				int statusBarH =  getStatusBarHeight();//状态栏高度
				int screenH = bodyLayout.getRootView().getHeight();
				if(r.top != statusBarH ){
					//在这个demo中r.top代表的是状态栏高度，在沉浸式状态栏时r.top＝0，通过getStatusBarHeight获取状态栏高度
					r.top = statusBarH;
				}
				int keyboardH = screenH - (r.bottom - r.top);
				Log.d(TAG, "screenH＝ "+ screenH +" &keyboardH = " + keyboardH + " &r.bottom=" + r.bottom + " &top=" + r.top + " &statusBarH=" + statusBarH);

				if(keyboardH == mCurrentKeyboardH){//有变化时才处理，否则会陷入死循环
					return;
				}

				mCurrentKeyboardH = keyboardH;
				mScreenHeight = screenH;//应用屏幕的高度
				mEditTextBodyHeight = mEditTextBody.getHeight();

				//偏移listview
				if(layoutManager!=null && mCommentConfig != null){
					layoutManager.scrollToPositionWithOffset(mCommentConfig.circlePosition + BFragment_HEADVIEW_SIZE, getListviewOffset(mCommentConfig));
				}
			}
		});
	}

	/**
	 * 获取状态栏高度
	 * @return
	 */
	private int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}


	@Override
	public void update2DeleteCircle(String circleId) {
		List<CircleItem> circleItems = mAdapter.getDatas();
		for(int i=0; i<circleItems.size(); i++){
			if(circleId.equals(circleItems.get(i).getId())){
				circleItems.remove(i);
				mAdapter.notifyDataSetChanged();
				return;
			}
		}
	}

	@Override
	public void update2AddFavorite(int circlePosition, FavortItem addItem) {
		if(addItem != null){
			CircleItem item = (CircleItem) mAdapter.getDatas().get(circlePosition);
			item.setIs_praise("1");
			item.getFavorters().add(addItem);
			mAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void update2DeleteFavort(int circlePosition, String favortId) {
		CircleItem item = (CircleItem) mAdapter.getDatas().get(circlePosition);
		item.setIs_praise("0");
		List<FavortItem> items = item.getFavorters();
		for(int i=0; i<items.size(); i++){
			if(TCUserMgr.getInstance().getNickname().equals(items.get(i).getUser().getName())){
				items.remove(i);
				mAdapter.notifyDataSetChanged();
				return;
			}
		}
	}

	@Override
	public void update2AddComment(int circlePosition, CommentItem addItem) {
		if(addItem != null){
			CircleItem item = (CircleItem) mAdapter.getDatas().get(circlePosition);
			item.getComments().add(addItem);
			mAdapter.notifyDataSetChanged();
		}
		//清空评论文本
		mEditText.setText("");
	}

	@Override
	public void update2DeleteComment(int circlePosition, String commentId) {
		CircleItem item = (CircleItem) mAdapter.getDatas().get(circlePosition);
		List<CommentItem> items = item.getComments();
		for(int i=0; i<items.size(); i++){
			if(commentId.equals(items.get(i).getId())){
				items.remove(i);
				mAdapter.notifyDataSetChanged();
				return;
			}
		}
	}

	@Override
	public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig) {
		mCommentConfig = commentConfig;
		mEditTextBody.setVisibility(visibility);

		measureCircleItemHighAndCommentItemOffset(commentConfig);

		if(View.VISIBLE==visibility){
			mEditText.requestFocus();
			//弹出键盘
			CommonUtils.showSoftInput(mEditText.getContext(), mEditText);

		}else if(View.GONE==visibility){
			//隐藏键盘
			CommonUtils.hideSoftInput(mEditText.getContext(), mEditText);
		}
	}

	@Override
	public void update2loadData(int loadType, List<CircleItem> datas) {
		if (loadType == TYPE_PULLREFRESH){
			mAdapter.setDatas(datas);
		}else if(loadType == TYPE_UPLOADREFRESH){
			mAdapter.getDatas().addAll(datas);
		}
		mAdapter.notifyDataSetChanged();


	}


	/**
	 * 测量偏移量
	 * @param commentConfig
	 * @return
	 */
	private int getListviewOffset(CommentConfig commentConfig) {
		if(commentConfig == null)
			return 0;
		//这里如果你的listview上面还有其它占高度的控件，则需要减去该控件高度，listview的headview除外。
		//int listviewOffset = mScreenHeight - mSelectCircleItemH - mCurrentKeyboardH - mEditTextBodyHeight;
		int listviewOffset = mScreenHeight - mSelectCircleItemH - mCurrentKeyboardH - mEditTextBodyHeight - titleBar.getHeight();
		if(commentConfig.commentType == CommentConfig.Type.REPLY){
			//回复评论的情况
			listviewOffset = listviewOffset + mSelectCommentItemOffset;
		}
		Log.i(TAG, "listviewOffset : " + listviewOffset);
		return listviewOffset;
	}

	private void measureCircleItemHighAndCommentItemOffset(CommentConfig commentConfig){
		if(commentConfig == null)
			return;

		int firstPosition = layoutManager.findFirstVisibleItemPosition();
		//只能返回当前可见区域（列表可滚动）的子项
		View selectCircleItem = layoutManager.getChildAt(commentConfig.circlePosition + BFragment_HEADVIEW_SIZE - firstPosition);

		if(selectCircleItem != null){
			mSelectCircleItemH = selectCircleItem.getHeight();
		}

		if(commentConfig.commentType == CommentConfig.Type.REPLY){
			//回复评论的情况
			CommentListView commentLv = (CommentListView) selectCircleItem.findViewById(R.id.commentList);
			if(commentLv!=null){
				//找到要回复的评论view,计算出该view距离所属动态底部的距离
				View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
				if(selectCommentItem != null){
					//选择的commentItem距选择的CircleItem底部的距离
					mSelectCommentItemOffset = 0;
					View parentView = selectCommentItem;
					do {
						int subItemBottom = parentView.getBottom();
						parentView = (View) parentView.getParent();
						if(parentView != null){
							mSelectCommentItemOffset += (parentView.getHeight() - subItemBottom);
						}
					} while (parentView != null && parentView != selectCircleItem);
				}
			}
		}
	}



	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void showError(String errorMsg) {

	}

}

package com.yiw.circledemo.mvp.presenter;

import android.view.View;

import com.leichui.conghua.utils.UtKt;
import com.leichui.shortviedeo.bean.SomeBodyCircleBean;
import com.leichui.shortviedeo.http.OkGoStringCallBack;
import com.leichui.shortviedeo.mapper.ApiMapper;
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr;
import com.yiw.circledemo.bean.CircleItem;
import com.yiw.circledemo.bean.CommentConfig;
import com.yiw.circledemo.bean.CommentItem;
import com.yiw.circledemo.bean.FavortItem;
import com.yiw.circledemo.bean.MyCircleItem;
import com.yiw.circledemo.mvp.modle.CircleModel;
import com.yiw.circledemo.mvp.modle.IDataRequestListener;
import com.yiw.circledemo.mvp.view.ICircleView;
import com.yiw.circledemo.utils.DatasUtil;

import java.util.List;

/**
 * 
* @ClassName: CirclePresenter 
* @Description: 通知model请求服务器和通知view更新
* @author yiw
* @date 2015-12-28 下午4:06:03 
*
 */
public class CirclePresenter extends BasePresenter<ICircleView> {
	private CircleModel mCircleModel;

	public CirclePresenter(){
		mCircleModel = new CircleModel();
	}

	public void loadData(String userId,boolean isMyB,int loadType,int page){

		if(isMyB){
			if(userId == null){
				userId = "";
			}
			ApiMapper.INSTANCE.getMyList(TCUserMgr.getInstance().getUserToken(), String.valueOf(page), "1",userId, new OkGoStringCallBack<SomeBodyCircleBean>(getContext(),SomeBodyCircleBean.class,false,false,true) {
				@Override
				public void onSuccess2Bean(SomeBodyCircleBean bean) {
					if(bean.getData().getList().size() > 0){
						List<CircleItem> datas = DatasUtil.covert2CircleDatas(bean);
						getView().update2loadData(loadType, datas);
					}
				}
			});
		}else{
			ApiMapper.INSTANCE.getFriendVideoList(TCUserMgr.getInstance().getUserToken(), "0", String.valueOf(page), new OkGoStringCallBack<MyCircleItem>(getContext(),MyCircleItem.class,false,false,true) {
				@Override
				public void onSuccess2Bean(MyCircleItem bean) {
					if(bean.getData().size() > 0){
						List<CircleItem> datas = DatasUtil.covert2CircleDatas(bean);
						getView().update2loadData(loadType, datas);
					}
				}
			});
		}

	}


	/**
	 * 
	* @Title: deleteCircle 
	* @Description: 删除动态 
	* @param  circleId     
	* @return void    返回类型 
	* @throws
	 */
	public void deleteCircle(final String circleId){
		mCircleModel.deleteCircle(circleId,new IDataRequestListener() {

			@Override
			public void loadSuccess(Object object) {
				getView().update2DeleteCircle(circleId);
			}
		});
	}
	/**
	 * 
	* @Title: addFavort 
	* @Description: 点赞
	* @param  circlePosition     
	* @return void    返回类型 
	* @throws
	 */
	public void addFavort(final int circlePosition, final String favortId){
		mCircleModel.addFavort(favortId,new IDataRequestListener() {

			@Override
			public void loadSuccess(Object object) {
				FavortItem item = DatasUtil.createCurUserFavortItem();
				getView().update2AddFavorite(circlePosition, item);
			}
		});
	}
	/**
	 * 
	* @Title: deleteFavort 
	* @Description: 取消点赞 
	* @param @param circlePosition
	* @param @param favortId     
	* @return void    返回类型 
	* @throws
	 */
	public void deleteFavort(final int circlePosition, final String favortId){
		mCircleModel.deleteFavort(favortId,new IDataRequestListener() {

			@Override
			public void loadSuccess(Object object) {
				getView().update2DeleteFavort(circlePosition, favortId);
			}
		});
	}
	
	/**
	 * 
	* @Title: addComment 
	* @Description: 增加评论
	* @param  content
	* @param  config  CommentConfig
	* @return void    返回类型 
	* @throws
	 */
	public void addComment(final String content, final CommentConfig config){
		if(config == null){
			return;
		}
		mCircleModel.addComment(config.circleId,content,new IDataRequestListener() {

			@Override
			public void loadSuccess(Object object) {
				CommentItem newItem = null;
				if (config.commentType == CommentConfig.Type.PUBLIC) {
					newItem = DatasUtil.createPublicComment(content);
				} else if (config.commentType == CommentConfig.Type.REPLY) {
					newItem = DatasUtil.createReplyComment(config.replyUser, content);
				}

				getView().update2AddComment(config.circlePosition, newItem);
			}

		});
	}
	
	/**
	 * 
	* @Title: deleteComment 
	* @Description: 删除评论 
	* @param @param circlePosition
	* @param @param commentId     
	* @return void    返回类型 
	* @throws
	 */
	public void deleteComment(final int circlePosition, final String commentId){
		mCircleModel.deleteComment(commentId,new IDataRequestListener(){

			@Override
			public void loadSuccess(Object object) {
				getView().update2DeleteComment(circlePosition, commentId);
			}
			
		});
	}

	/**
	 *
	 * @param commentConfig
	 */
	public void showEditTextBody(CommentConfig commentConfig){
		getView().updateEditTextBodyVisible(View.VISIBLE, commentConfig);
	}

}

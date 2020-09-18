package com.yiw.circledemo.mvp.modle;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.leichui.conghua.utils.UtKt;
import com.leichui.shortviedeo.Base.BaseBean;
import com.leichui.shortviedeo.http.OkGoStringCallBack;
import com.leichui.shortviedeo.mapper.ApiMapper;
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr;
import com.tencent.qcloud.xiaoshipin.TCApplication;

/**
 * 
* @ClassName: CircleModel 
* @Description: 因为逻辑简单，这里我就不写model的接口了
* @author yiw
* @date 2015-12-28 下午3:54:55 
 */
public class CircleModel {
	
	
	public CircleModel(){
		//
	}

	public void loadData(final String id,final IDataRequestListener listener){
		requestServer(id,"loadData","",listener);
	}
	
	public void deleteCircle(final String id, final IDataRequestListener listener) {
		requestServer(id,"deleteCircle","",listener);
	}

	public void addFavort(final String id, final IDataRequestListener listener) {
		requestServer(id,"addFavort","",listener);
	}

	public void deleteFavort(final String id,final IDataRequestListener listener) {
		requestServer(id,"deleteFavort","",listener);
	}

	public void addComment(final String id,final String comment, final IDataRequestListener listener) {
		requestServer(id,"addComment",comment,listener);
	}

	public void deleteComment( final String id,final IDataRequestListener listener) {
		requestServer(id,"deleteComment","",listener);
	}
	
	/**
	 * 
	* @Title: requestServer 
	* @Description: 与后台交互, 因为demo是本地数据，不做处理
	* @param  listener    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@SuppressLint("StaticFieldLeak")
	private void requestServer(final String id,final String type,final String comment, final IDataRequestListener listener) {
		new AsyncTask<Object, Integer, Object>(){
			@Override
			protected Object doInBackground(Object... params) {

				UtKt.L("type:" + type,"asd");
				UtKt.L("id:" + id,"asd");
				UtKt.L("comment:" + comment,"asd");
				if("loadData".equals(type)){

				}else if("deleteCircle".equals(type)){

				}else if("addFavort".equals(type)){
					ApiMapper.INSTANCE.addVideoPraise(TCUserMgr.getInstance().getUserToken(), id, "1", new OkGoStringCallBack<BaseBean>(TCApplication.instance,BaseBean.class,false,false,false) {
						@Override
						public void onSuccess2Bean(BaseBean bean) {

						}
					});

				}else if("deleteFavort".equals(type)){
					ApiMapper.INSTANCE.addVideoPraise(TCUserMgr.getInstance().getUserToken(), id, "1", new OkGoStringCallBack<BaseBean>(TCApplication.instance,BaseBean.class,false,false,false) {
						@Override
						public void onSuccess2Bean(BaseBean bean) {

						}
					});
				}else if("addComment".equals(type)){
					ApiMapper.INSTANCE.addVideoDiscuss(TCUserMgr.getInstance().getUserToken(),comment, id, "1",new OkGoStringCallBack<BaseBean>(TCApplication.instance,BaseBean.class,false,false,false) {
						@Override
						public void onSuccess2Bean(BaseBean bean) {

						}
					});
				}else if("deleteComment".equals(type)){

				}
				//和后台交互
				return null;
			}
			
			protected void onPostExecute(Object result) {
				listener.loadSuccess(result);
			}
		}.execute();
	}
	
}

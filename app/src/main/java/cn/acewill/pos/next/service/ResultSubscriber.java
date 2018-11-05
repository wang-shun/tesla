package cn.acewill.pos.next.service;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import cn.acewill.pos.next.exception.ErrorCode;
import cn.acewill.pos.next.exception.PosServiceException;
import cn.acewill.pos.next.model.event.PosEvent;
import cn.acewill.pos.next.utils.Constant;
import cn.acewill.pos.next.utils.ToolsUtils;
import rx.Subscriber;

/**
 * Created by Acewill on 2016/6/14.
 */
public class ResultSubscriber<T> extends Subscriber<T> {
	private ResultCallback<T> resultCallback;

	public ResultSubscriber(ResultCallback<T> resultCallback) {
		this.resultCallback = resultCallback;
	}

	@Override
	public void onCompleted() {
		Log.i("ResultSubscriber", "----------------Completed!");
	}

	/**
	 * 这个函数不能抛出异常
	 *
	 * @param e
	 */
	@Override
	public void onError(Throwable e) {
		e.printStackTrace();
		if (e instanceof IOException) {
			PosServiceException exception = new PosServiceException(ErrorCode.NETWORK_ERROR, ToolsUtils
					.returnXMLStr("net_error"));
			//            EventBus.getDefault().post(new PosEvent(Constant.EventState.ERR_GET_PAY_STATE_FAILURE));
			resultCallback.onError(exception);
		} else if (e instanceof PosServiceException) {
			resultCallback.onError((PosServiceException) e);
		} else if (e.getCause() instanceof PosServiceException) {
			resultCallback.onError((PosServiceException) e.getCause());
		} else if (e instanceof ConnectException) {
			PosServiceException exception = new PosServiceException(ErrorCode.NETWORK_ERROR, ToolsUtils
					.returnXMLStr("net_error"));
			EventBus.getDefault().post(new PosEvent(Constant.EventState.ERR_GET_PAY_STATE_FAILURE));
			resultCallback.onError(exception);
		} else if (e instanceof SocketTimeoutException) {
			PosServiceException exception = new PosServiceException(ErrorCode.NETWORK_ERROR, ToolsUtils
					.returnXMLStr("timeout"));
			resultCallback.onError(exception);
		} else {
			PosServiceException exception = new PosServiceException(ErrorCode.UNKNOWN_ERROR, ToolsUtils
					.returnXMLStr("unknow_error"));
			resultCallback.onError(exception);
		}
	}

	@Override
	public void onNext(T o) {
		resultCallback.onResult(o);
	}
}

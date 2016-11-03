package com.aidebar.retrofitutils.Utils.RetrofitUtils;

import android.content.Context;
import android.widget.Toast;

import com.aidebar.retrofitutils.R;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.JsonBean.BaseJsonBean;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * @author xzj
 * @date 2016/8/25 11:07.
 */
public abstract class ResponseSubscriber<T> implements Subscriber<T> {
    private Context mContext;
    
    public ResponseSubscriber(Context context) {
        mContext = context;
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (!error(e)) {
            if (e instanceof ConnectException) {
                //网络异常
                Toast.makeText(mContext, R.string.network_error,Toast.LENGTH_SHORT).show();
            } else if (e instanceof HttpException) {
                //服务器异常
                Toast.makeText(mContext, R.string.network_servier_error,Toast.LENGTH_SHORT).show();
            } else if (e instanceof SocketTimeoutException) {
                //网络超时
                Toast.makeText(mContext, R.string.network_timeout,Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onNext(T t) {
        BaseJsonBean data;
        if (t instanceof BaseJsonBean) {
            data = (BaseJsonBean) t;
            if (data.success) {             //服务端返回的是true
                success(t);
            } else {                        //服务端返回false，就是操作异常
                if (!operationError(t, data.errorCode, data.msg)) {    //可以复写此方法，返回true，就用户自己处理，返回false，走下面的代码
                    Toast.makeText(mContext,data.msg,Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            success(t);
        }
    }

    /**
     * 请求成功同时业务成功的情况下会调用此函数
     */
    public abstract void success(T t);

    /**
     * 请求成功但业务失败的情况下会调用此函数.
     * @return 空实现，默认返回false，执行父类方法。 用户可以复写此方法，返回true来自己处理
     */
    public boolean operationError(T t, int errorCode, String message) {
        return  false;
    }

    /**
     * 请求失败的情况下会调用此函数
     * @return 空实现，默认返回false，执行父类方法。 用户可以复写此方法，返回true来自己处理
     */
    public boolean error(Throwable e) {
        return false;
    }
}

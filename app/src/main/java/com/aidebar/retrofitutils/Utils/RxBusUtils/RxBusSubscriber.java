package com.aidebar.retrofitutils.Utils.RxBusUtils;

/**
 * @author xzj
 * @date 2016/8/24 14:24.
 */

import rx.Subscriber;

/**
 * 请使用此类来subscribe RxBus返回的Observable以简化onError与onCompleted函数.
 */
public abstract class RxBusSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        completed();
    }

    @Override
    public void onError(Throwable e) {
        error(e);
    }

    @Override
    public void onNext(T t) {
        receive(t);
    }

    public abstract void receive(T data);
    public void error(Throwable e) {
        e.printStackTrace();
    }
    public void completed() {}

}

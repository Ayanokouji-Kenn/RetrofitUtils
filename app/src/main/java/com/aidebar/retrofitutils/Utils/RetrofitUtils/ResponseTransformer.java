package com.aidebar.retrofitutils.Utils.RetrofitUtils;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * 用于对网络请求的Observable做转换.
 * 配合{@link com.trello.rxlifecycle.ActivityLifecycleProvider#bindToLifecycle()}一起使用
 * 可以将原始Observable绑定至Activity/Fragment生命周期, 同时声明在IO线程运行, 在main线程接收.
 * 像这样用 
 * manager.getAds().compose(new ResponseTransformer<>(this.<BaseJsonBean> bindToLifeCycle()));
 */
public class ResponseTransformer<T> implements FlowableTransformer<T, T> {

    private FlowableTransformer<T, T> transformer;

    public ResponseTransformer() {}

    public ResponseTransformer(FlowableTransformer<T, T> t) {
        transformer = t;
    }


    @Override
    public Flowable<T> apply(Flowable<T> upstream) {
        if (transformer != null)
            return ((Flowable)transformer.apply(upstream)).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        else
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    }
}

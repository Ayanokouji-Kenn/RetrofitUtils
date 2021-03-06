package com.aidebar.retrofitutils.Utils.RetrofitUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 用于对网络请求的Observable做转换.
 * 配合{@link com.trello.rxlifecycle.ActivityLifecycleProvider#bindToLifecycle()}一起使用
 * 可以将原始Observable绑定至Activity/Fragment生命周期, 同时声明在IO线程运行, 在main线程接收.
 * 像这样用 
 * manager.getAds().compose(new ResponseTransformer<>(this.<BaseJsonBean> bindToLifeCycle()));
 */
public class ResponseTransformer<T> implements Observable.Transformer<T, T> {

    private Observable.Transformer<T, T> transformer;

    public ResponseTransformer() {}

    public ResponseTransformer(Observable.Transformer<T, T> t) {
        transformer = t;
        
    }

    @Override
    public Observable<T> call(Observable<T> source) {
        if (transformer != null)
            return transformer.call(source).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        else
            return source.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    }
}

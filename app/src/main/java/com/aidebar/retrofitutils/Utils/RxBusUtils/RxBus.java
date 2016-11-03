package com.aidebar.retrofitutils.Utils.RxBusUtils;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author xzj
 * @date 2016/8/24 14:00.
 */
public class RxBus {
    private static RxBus mRxBus = null;
    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     */

    private FlowableProcessor<Object> mRxBusFlowable = PublishProcessor.create().toSerialized();

    public static synchronized RxBus getInstance() {
        if (mRxBus == null) {
            mRxBus = new RxBus();
        }
        return mRxBus;
    }

    public void send(Object o, String tag) {
        mRxBusFlowable.onNext(new RxBusObject(tag, o));
    }

//    public Observable<Object> toObserverable() {
//        return mRxBusObserverable;
//    }

    public <T> Flowable<T> toObservable(final Class<T> eventType, final String tag) {
        return mRxBusFlowable.filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                if (!(o instanceof RxBusObject)) return false;
                RxBusObject ro = (RxBusObject) o;
                return eventType.isInstance(ro.getObj()) && tag != null
                        && tag.equals(ro.getTag());
            }

        }).map(new Function<Object, T>() {
            @Override
            public T apply(Object o) throws Exception {
                RxBusObject ro = (RxBusObject) o;
                return (T) ro.getObj();
            }
        });
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mRxBusFlowable.hasSubscribers();
    }
}

package com.aidebar.retrofitutils.NetApi;

import com.aidebar.retrofitutils.Utils.RetrofitUtils.JsonBean.ClockListJson;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author xzj
 * @date 2016/10/28 09:26.
 */

public interface ClockListApi {
    @GET("alarmclock/query.json")
    Flowable<ClockListJson> getRingList(@Query("creator_oid") String usercode);
}

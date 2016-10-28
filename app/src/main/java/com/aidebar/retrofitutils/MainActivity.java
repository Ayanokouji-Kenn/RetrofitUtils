package com.aidebar.retrofitutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aidebar.retrofitutils.NetApi.ClockListApi;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.ApiFactory;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.JsonBean.ClockListJson;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.ResponseSubscriber;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.ResponseTransformer;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.RetrofitFactory;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

public class MainActivity extends RxFragmentActivity {
    String URL = "http://testsvr.aidebar.com/";
    private MainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        //要先设置BaseUrl，如果项目中就一个，可以直接写死
        RetrofitFactory.setBaseUrl(URL);
    }

    public void getData(View v) {
        ApiFactory.getFactory().create(ClockListApi.class).getRingList("1085432")
                .compose(new ResponseTransformer<>(this.<ClockListJson>bindToLifecycle()))
                .subscribe(new ResponseSubscriber<ClockListJson>(instance) {
                    @Override
                    public void success(ClockListJson clockListJson) {
                        if (clockListJson.success) {
                            Toast.makeText(instance,clockListJson.data.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    //操作失败和网络失败可以重写operationError和error
                });
    }
}

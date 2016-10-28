package com.aidebar.retrofitutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aidebar.retrofitutils.NetApi.ClockListApi;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.ApiFactory;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.JsonBean.ClockListJson;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.ResponseSubscriber;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.ResponseTransformer;
import com.aidebar.retrofitutils.Utils.RetrofitUtils.RetrofitFactory;
import com.aidebar.retrofitutils.Utils.RxBusUtils.RxBus;
import com.aidebar.retrofitutils.Utils.RxBusUtils.RxBusSubscriber;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import java.util.Observable;

public class MainActivity extends RxFragmentActivity {
    String URL = "http://testsvr.aidebar.com/";
    private MainActivity instance;
    private Button btn;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        //要先设置BaseUrl，如果项目中就一个，可以直接写死
        RetrofitFactory.setBaseUrl(URL);
        RxBus.getInstance().toObservable(Integer.class, "点击次数")
                .subscribe(new RxBusSubscriber<Integer>() {
                    @Override
                    public void receive(Integer data) {
                        btn.setText(String.valueOf(data));
                    }
                });
    }

    public void getData(View v) {
        RxBus.getInstance().send(++count,"点击次数");
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

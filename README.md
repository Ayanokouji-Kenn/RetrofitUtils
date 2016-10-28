# RetrofitUtils
retrofit和rxbus的一些工具类
***

### RetrofitUtils的用法   
- 添加依赖
```java
    compile 'com.google.code.gson:gson:2.7'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    compile 'io.reactivex:rxjava:1.1.9'
    compile 'io.reactivex:rxandroid:1.2.1'
    compile 'com.trello:rxlifecycle-components:0.6.0'
    
```
- 设置baseUrl `RetrofitFactory.setBaseUrl(URL);`
  
- ClockListApi.class是retrofit的接口,getRingList是接口的方法
```
ApiFactory.getFactory().create(ClockListApi.class).getRingList("1085432")
                .compose(new ResponseTransformer<>(this.<ClockListJson>bindToLifecycle()))
                .subscribe(new ResponseSubscriber<ClockListJson>(instance) {
                    @Override
                    public void success(ClockListJson clockListJson) {
                        if (clockListJson.success) {
                            Toast.makeText(instance,clockListJson.data.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    //要自定义操作失败和网络失败可以重写operationError和error
                });
                
```
### RxBus的用法
- 发消息`RxBus.getInstance().send(++count,"点击次数");`
- 收消息
```java
RxBus.getInstance().toObservable(Integer.class, "点击次数")
                .subscribe(new RxBusSubscriber<Integer>() {
                    @Override
                    public void receive(Integer data) {
                        btn.setText(String.valueOf(data));
                    }
                });```

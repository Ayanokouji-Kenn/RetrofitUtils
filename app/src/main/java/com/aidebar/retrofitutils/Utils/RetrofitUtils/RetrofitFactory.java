package com.aidebar.retrofitutils.Utils.RetrofitUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xzj
 * @date 2016/8/25 09:37.
 * 用于获取配置好的retrofit对象
 * 需要先调用setBaseUrl，如果项目中BaseUrl不变，可以写死
 */
public class RetrofitFactory {
    private static Retrofit retrofit;
    private static String baseUrl;

    public static void setBaseUrl(String url) {
        baseUrl = url;
    }

    /**
     * 获取配置好的retrofit对象来生产Manager对象
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            if (baseUrl == null || baseUrl.length() <= 0)
                throw new IllegalStateException("请在调用getFactory之前先调用setBaseUrl");

            Retrofit.Builder builder = new Retrofit.Builder();

            builder.baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 参考RxJava
                    .addConverterFactory(GsonConverterFactory.create()); // 参考与GSON的结合

            // 参考自定义Log输出
            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .addInterceptor(new Interceptor() {     //这个拦截器是操作请求头的
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request request = chain.request().newBuilder()
//                                    .addHeader("version", "123411") //这里就是添加一个请求头
//                                    .build();
//
////                        Buffer buffer = new Buffer();       不依赖下面的Interceptor，用这三行也能打印出请求体
////                        request.body().writeTo(buffer);
////                        Log.d(getClass().getSimpleName(), "intercept: " + buffer.readUtf8());
//
//                            return chain.proceed(request);
//                        } 
//                    })
//                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))       //这个拦截器是用来打印日志的，不稳定
                    .build();


            builder.client(client);
            retrofit = builder.build();
        }
        return retrofit;
    }
}

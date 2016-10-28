package com.aidebar.retrofitutils.Utils.RetrofitUtils;

import java.util.HashMap;

/**
 * @author xzj
 * @date 2016/8/25 09:38.
 * 通过定义好的api接口以及Retrofit来生成具体的实例.
 */
public class ApiFactory {
    private static ApiFactory factory;
    private static HashMap<String, Object> serviceMap = new HashMap<>();

    public static ApiFactory getFactory() {
        if (factory == null) {
            synchronized (ApiFactory.class) {
                if (factory == null)
                    factory = new ApiFactory();
            }
        }
        return factory;
    }

    public <T> T create(Class<T> clz) {
        Object service = serviceMap.get(clz.getName());
        if (service == null) {
            service = RetrofitFactory.getRetrofit().create(clz);
            serviceMap.put(clz.getName(), service);
        }
        return (T) service;
    }
}

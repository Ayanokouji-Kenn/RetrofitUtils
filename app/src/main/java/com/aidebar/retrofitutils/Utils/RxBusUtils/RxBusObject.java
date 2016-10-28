package com.aidebar.retrofitutils.Utils.RxBusUtils;

/**
 * @author xzj
 * @date 2016/8/24 14:22.
 */
public class RxBusObject {
    private String tag;
    private Object obj;

    public RxBusObject(String tag, Object obj) {
        this.tag = tag;
        this.obj = obj;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static RxBusObject newInstance(String tag, Object obj) {
        return new RxBusObject(tag, obj);
    }
}

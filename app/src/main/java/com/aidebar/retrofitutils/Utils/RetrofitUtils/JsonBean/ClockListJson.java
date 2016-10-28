package com.aidebar.retrofitutils.Utils.RetrofitUtils.JsonBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author xzj
 * @date 2016/7/26 11:14.
 */
public class ClockListJson extends BaseJsonBean{


    /**
     * data : [{"oid":1762,"time":["2016-08-09 09:33:26"],"description":"闹钟","alarm_style":0,"isLunar":0,"remark":"","remind_early":"0","bgImg":"\"\"","repeat":"0000000","ring":{"name":"A Woodland Night","id":0,"tag":1},"switch":0,"creator_oid":2946831,"receiver_phone":"00000000000","receiver_oid":0,"is_notice":1,"type":0,"remindlater":0,"alarm_status":0,"latesttime":1470712145000,"creator_nickname":"包租婆","receiver_nickname":"00000000000"}]
     * errorCode : 895000
     * msg :
     * sessionId : 8CA58B688BD8485EFC9FF53C163A3A7F
     * success : true
     */

//    public int errorCode;
//    public String msg;
//    public String sessionId;
//    public boolean success;
    /**
     * oid : 1762
     * time : ["2016-08-09 09:33:26"]
     * description : 闹钟
     * alarm_style : 0
     * isLunar : 0
     * remark :
     * remind_early : 0
     * bgImg : ""
     * repeat : 0000000
     * ring : {"name":"A Woodland Night","id":0,"tag":1}
     * switch : 0
     * creator_oid : 2946831
     * receiver_phone : 00000000000
     * receiver_oid : 0
     * is_notice : 1
     * type : 0
     * remindlater : 0
     * alarm_status : 0
     * latesttime : 1470712145000
     * creator_nickname : 包租婆
     * receiver_nickname : 00000000000
     */

    public List<DataBean> data;

    public static class DataBean {
        public int oid;
        public String description;
        public int alarm_style;
        public int isLunar;
        public String remark;
        public String remind_early;
        public String bgImg;
        public String repeat;
        /**
         * name : A Woodland Night
         * id : 0
         * tag : 1
         */

        public RingBean ring;
        @SerializedName("switch")
        public int switchX;
        public int creator_oid;
        public String receiver_phone;
        public int receiver_oid;
        public int is_notice;
        public int type;
        public int remindlater;
        public int alarm_status;
        public long latesttime;
        public String creator_nickname;
        public String receiver_nickname;
        public List<String> time;

        public static class RingBean {
            public String name;
            public int id;
            public int tag;
        }
    }
}

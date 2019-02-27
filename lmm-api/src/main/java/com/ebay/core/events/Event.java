package com.ebay.core.events;


/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/30
 */
public class Event {

    private Integer uid;
    private long timestamp;
    private String target;
    private String name;

    public Event(){
        super();
    }

    public Event(Integer uid, String target, String name) {
        super();
        this.uid = uid;
        this.target = target;
        this.name = name;
        this.timestamp = System.currentTimeMillis();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

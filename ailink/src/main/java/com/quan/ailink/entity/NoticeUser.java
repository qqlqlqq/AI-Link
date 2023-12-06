package com.quan.ailink.entity;

public class NoticeUser {
    private Long nuid;

    private Long uid;

    private Long nid;

    private Byte isRead;

    private Byte state;

    public Long getNuid() {
        return nuid;
    }

    public void setNuid(Long nuid) {
        this.nuid = nuid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
package com.quan.ailink.entity;

import java.util.Date;

public class Project {
    private Long pid;

    private String pname;

    private Date beginTime;

    private Date expectTime;

    private Date finishTime;

    private Long createUser;

    private Byte isCompany;

    private Byte isIdh;

    private String customerContact;

    private String remarks;

    private Byte state;

    private Byte status;

    private Byte isDelay;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Byte getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(Byte isCompany) {
        this.isCompany = isCompany;
    }

    public Byte getIsIdh() {
        return isIdh;
    }

    public void setIsIdh(Byte isIdh) {
        this.isIdh = isIdh;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact == null ? null : customerContact.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsDelay() {
        return isDelay;
    }

    public void setIsDelay(Byte isDelay) {
        this.isDelay = isDelay;
    }
}
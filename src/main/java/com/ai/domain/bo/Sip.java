package com.ai.domain.bo;

import java.math.BigDecimal;
import java.util.Date;

public class Sip {
    private Long id;

    private String name;

    private String username;

    private String password;

    private String host;

    private BigDecimal charged;

    private Date createat;

    private String firms;

    private Integer maxthread;

    private Integer currentthread;

    private String remark;

    private String appid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public BigDecimal getCharged() {
        return charged;
    }

    public void setCharged(BigDecimal charged) {
        this.charged = charged;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public String getFirms() {
        return firms;
    }

    public void setFirms(String firms) {
        this.firms = firms == null ? null : firms.trim();
    }

    public Integer getMaxthread() {
        return maxthread;
    }

    public void setMaxthread(Integer maxthread) {
        this.maxthread = maxthread;
    }

    public Integer getCurrentthread() {
        return currentthread;
    }

    public void setCurrentthread(Integer currentthread) {
        this.currentthread = currentthread;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }
}
package com.ai.domain.bo;

import java.math.BigDecimal;

public class AuthUserInfo {
    private String userId;

    private Integer threadNum;

    private BigDecimal charged;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    public BigDecimal getCharged() {
        return charged;
    }

    public void setCharged(BigDecimal charged) {
        this.charged = charged;
    }
}
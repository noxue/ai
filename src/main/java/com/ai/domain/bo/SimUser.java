package com.ai.domain.bo;

public class SimUser {
    private String userId;

    private Long simId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getSimId() {
        return simId;
    }

    public void setSimId(Long simId) {
        this.simId = simId;
    }
}
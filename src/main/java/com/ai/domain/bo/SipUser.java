package com.ai.domain.bo;

public class SipUser {
    private Long id;

    private String userId;

    private Long sipId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getSipId() {
        return sipId;
    }

    public void setSipId(Long sipId) {
        this.sipId = sipId;
    }
}
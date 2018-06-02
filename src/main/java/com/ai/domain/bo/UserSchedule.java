package com.ai.domain.bo;

import java.util.Date;

public class UserSchedule {
    private String userId;

    private Date startAt;

    private Date endAt;

    private Byte repeat;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public Byte getRepeat() {
        return repeat;
    }

    public void setRepeat(Byte repeat) {
        this.repeat = repeat;
    }
}
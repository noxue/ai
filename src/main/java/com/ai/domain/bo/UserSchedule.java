package com.ai.domain.bo;

import java.util.Date;

public class UserSchedule {
    private Long userId;

    private Date startAt;

    private Date endAt;

    private Byte repeat;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
package com.ai.controller.robot.v1.bean;

public class BaseAction {
    private String action;

    public BaseAction() {
    }

    public BaseAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "BaseAction{" +
                "action='" + action + '\'' +
                '}';
    }
}


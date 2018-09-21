package com.ai.controller.robot.v1.bean;

public class Auth extends BaseAction {
    private String id;
    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

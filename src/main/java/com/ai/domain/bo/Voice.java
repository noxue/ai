package com.ai.domain.bo;

public class Voice {
    private Long id;

    private String path;

    private String pcm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getPcm() {
        return pcm;
    }

    public void setPcm(String pcm) {
        this.pcm = pcm == null ? null : pcm.trim();
    }
}
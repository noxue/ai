package com.ai.domain.bo;

public class Voice {
    private Long id;

    private String path;

    private String pcm;

    private String pcmHash;

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

    public String getPcmHash() {
        return pcmHash;
    }

    public void setPcmHash(String pcmHash) {
        this.pcmHash = pcmHash == null ? null : pcmHash.trim();
    }
}
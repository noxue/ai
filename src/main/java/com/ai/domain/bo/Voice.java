package com.ai.domain.bo;

import java.util.Date;

public class Voice {
    private String hash;

    private String path;

    private String pcm;

    private String filename;

    private Date visitedAt;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Date getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Date visitedAt) {
        this.visitedAt = visitedAt;
    }
}
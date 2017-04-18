package com.example.blue.gasbuy;

/**
 * Created by youolo on 14/04/2017.
 */

public class PhanHoi {
    private String content;
    private boolean ckGH;
    private boolean ckTD;
    private boolean ckCL;

    public PhanHoi(String content, boolean ckGH, boolean ckTD, boolean ckCL) {
        this.content = content;
        this.ckGH = ckGH;
        this.ckTD = ckTD;
        this.ckCL = ckCL;
    }

    public PhanHoi() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCkGH() {
        return ckGH;
    }

    public void setCkGH(boolean ckGH) {
        this.ckGH = ckGH;
    }

    public boolean isCkTD() {
        return ckTD;
    }

    public void setCkTD(boolean ckTD) {
        this.ckTD = ckTD;
    }

    public boolean isCkCL() {
        return ckCL;
    }

    public void setCkCL(boolean ckCL) {
        this.ckCL = ckCL;
    }
}

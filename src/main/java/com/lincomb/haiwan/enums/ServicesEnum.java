package com.lincomb.haiwan.enums;

import com.lincomb.haiwan.util.FastDFSUtil;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 15:30
 */
public enum ServicesEnum {

    WIFI("宽带", "group1/M00/00/4B/rBBr1Vnv73qAZ0R9AAAHLF-KMiI035.png"),
    BREAKFAST("早餐", "group1/M00/00/4B/rBBr1lppqkuAab6uAAAIOQdubxI143.png"),
    BATHROOM("卫浴", "group1/M00/00/4B/rBBr1Vnv7_SAA4epAAAGLfb2bNw237.png"),
    YARD("庭院", "group1/M00/00/4B/rBBr1lppqfWAUMSnAAAGAHBvy0s082.png"),
    TV("电视", "group1/M00/00/4B/rBBr1lppqU-ALOM8AAAGEMxzh14322.png"),
    FRIG("冰箱", "group1/M00/00/4B/rBBr1Vnv7oeAG7XgAAAFOBfdQes402.png"),
    PC("电脑", "group1/M00/00/4B/rBBr1lppqSGAQW7DAAAFBVkp6PM734.png"),
    BBQ("烧烤", "group1/M00/00/4B/rBBr1lppqcyAa7l0AAAHBlNDLHs137.png"),
    AIRCONDITIONING("空调", "group1/M00/00/4B/rBBr1Vnv71KACcLrAAAGEp2G8xQ365.png");

    private String text;

    private String src;

    ServicesEnum(String text, String src) {
        this.text = text;
        this.src = FastDFSUtil.DOWNLOAD_PATH + src;
    }

    public String getText() {
        return text;
    }

    public String getSrc() {
        return src;
    }
}

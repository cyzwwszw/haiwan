package com.lincomb.haiwan.enums;

import com.lincomb.haiwan.util.FastDFSUtil;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 15:30
 */
public enum ServicesEnum {

    WIFI("宽带", "group1/M00/05/29/wKgthln5ykKAJgpBAAAHLF-KMiI862.png"),
    BREAKFAST("早餐", "group1/M00/05/29/wKgthln5yqOAEsFFAAAIOQdubxI076.png"),
    BATHROOM("卫浴", "group1/M00/05/69/wKgthVn5yo-ARKWmAAAGLfb2bNw057.png"),
    YARD("庭院", "group1/M00/05/69/wKgthVn5ynCANoszAAAGAHBvy0s376.png"),
    TV("电视", "group1/M00/05/29/wKgthln5yhCAX0A9AAAGEMxzh14347.png"),
    FRIG("冰箱", "group1/M00/05/29/wKgthln5ybqAeQEYAAAFOBfdQes845.png"),
    PC("电脑", "group1/M00/05/69/wKgthVn5yfCAZ4jGAAAFBVkp6PM575.png"),
    BBQ("烧烤", "group1/M00/05/69/wKgthVn5ylqAN3cnAAAHBlNDLHs940.png"),
    AIRCONDITIONING("空调", "group1/M00/05/69/wKgthVn5yiyAcAxlAAAGEp2G8xQ003.png");

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

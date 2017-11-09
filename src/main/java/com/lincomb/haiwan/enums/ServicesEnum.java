package com.lincomb.haiwan.enums;

import com.lincomb.haiwan.util.BasePathUtil;
/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 15:30
 */
public enum ServicesEnum {

    WIFI("宽带", "static/images/icon_kuandai.png"),
    BREAKFAST("早餐", "static/images/icon_zaocan.png"),
    BATHROOM("卫浴", "static/images/icon_weiyu.png"),
    YARD("庭院", "static/images/icon_tingyuan.png"),
    TV("电视", "static/images/icon_dianshi.png"),
    FRIG("冰箱", "static/images/icon_bingxiang.png"),
    PC("电脑", "static/images/icon_diannao.png"),
    BBQ("烧烤", "static/images/icon_shaokao.png"),
    AIRCONDITIONING("空调", "static/images/icon_kongtiao.png");

    private String text;

    private String src;

    ServicesEnum(String text, String src) {
        this.text = text;
        this.src = BasePathUtil.getBasePath() + src;
    }

    public String getText() {
        return text;
    }

    public String getSrc() {
        return src;
    }
}

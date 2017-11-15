package com.lincomb.haiwan.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by QianYunlong on 15
 */
@Data
public class BarVO {
    private String name;
    private String type="bar";
    private List<Long> data;
}

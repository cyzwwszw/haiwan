package com.lincomb.haiwan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by QianYunlong on 26
 */
@Entity
@Data
public class Transaction {

    @Id
    private String transactionId;

    private String orderId;

    private String payTime;

    private String payAmount;

    private String refundTime;

    private String refundAmount;

    private Date createTime;

    private Date updateTime;

    public Transaction() {
    }
}

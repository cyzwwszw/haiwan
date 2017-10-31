package com.lincomb.haiwan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
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

    private Date payTime;

    private BigDecimal payAmount;

    private Date refundTime;

    private BigDecimal refundAmount;

    private Date createTime;

    private Date updateTime;

    public Transaction() {
    }
}

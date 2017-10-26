package com.lincomb.haiwan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by QianYunlong on 26
 */
@Entity
@Data
public class Transaction {

    @Id
    private String transactionId;

    private String transactionType;

    private String transactionTime;

    private String orderId;

    public Transaction() {
    }
}

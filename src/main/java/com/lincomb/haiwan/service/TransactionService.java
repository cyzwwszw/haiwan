package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Transaction;

/**
 * Created by QianYunlong on 26
 */
public interface TransactionService {

    Transaction findOne(String transactionId);

    Transaction save(Transaction transaction);
}

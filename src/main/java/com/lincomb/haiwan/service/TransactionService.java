package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by QianYunlong on 26
 */
public interface TransactionService {

    Transaction findOne(String orderId);

    Transaction save(Transaction transaction);

    Page<Transaction> findAll(Pageable pageable);
}

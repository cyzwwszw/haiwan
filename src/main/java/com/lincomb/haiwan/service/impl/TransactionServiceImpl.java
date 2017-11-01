package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.domain.Transaction;
import com.lincomb.haiwan.enums.ProductStatusEnum;
import com.lincomb.haiwan.repository.TransactionRepository;
import com.lincomb.haiwan.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by QianYunlong on 26
 */
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction findOne(String orderId) {

        return transactionRepository.findOne(orderId);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }


    @Override
    public Page<Transaction> findAll(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }
}

package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Transaction;
import com.lincomb.haiwan.repository.TransactionRepository;
import com.lincomb.haiwan.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by QianYunlong on 26
 */
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction findOne(String transactionId) {
        return transactionRepository.findOne(transactionId);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}

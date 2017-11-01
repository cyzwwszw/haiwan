package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.domain.Transaction;
import com.lincomb.haiwan.enums.ProductStatusEnum;
import com.lincomb.haiwan.repository.TransactionRepository;
import com.lincomb.haiwan.service.TransactionService;
import com.lincomb.haiwan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public Page<Transaction> findAll(Pageable pageable, String orderId) {
        Transaction transaction = new Transaction();
        if(!StringUtil.isEmpty(orderId)){
            transaction.setOrderId(orderId);
        }
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("orderId", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        Example<Transaction> example = Example.of(transaction, matcher);

        return transactionRepository.findAll(example, pageable);
    }
}

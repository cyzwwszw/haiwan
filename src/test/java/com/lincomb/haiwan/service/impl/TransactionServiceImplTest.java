package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午7:00 17/10/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceImplTest {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void save() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1");
        transactionService.save(transaction);

    }

}
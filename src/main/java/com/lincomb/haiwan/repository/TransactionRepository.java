package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by QianYunlong on 26
 */
public interface TransactionRepository extends JpaRepository<Transaction, String>{
}

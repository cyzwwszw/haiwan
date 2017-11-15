package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/23 16:44
 */
public interface BuyerRepository extends JpaRepository<Buyer,String> {

    Buyer findTopByBuyerMobile(String buyerMobile);


}

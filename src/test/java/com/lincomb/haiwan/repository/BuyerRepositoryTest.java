package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.enums.BuyerStatusEnum;
import com.lincomb.haiwan.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午7:07 17/10/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerRepositoryTest {

    @Autowired
    BuyerRepository buyerRepository;

    @Test
    public void save(){
        Buyer buyer = new Buyer();
        buyer.setBuyerId(KeyUtil.genUniqueKey());
        buyer.setBuyerMobile("15921613518");
        buyer.setBuyerWechatname("QYL");
        buyer.setBuyerWechatpic("http://img4.imgtn.bdimg.com/it/u=1016482165,112380195&fm=27&gp=0.jpg");
        buyer.setBuyerName("钱云龙");
        buyer.setIsIdentity(1);
        buyer.setIdentityNo("320522198806116114");
        Buyer result = buyerRepository.save(buyer);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateOne(){
        Buyer buyer = buyerRepository.findOne("1508757366552317340");

    }
}
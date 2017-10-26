package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yongsheng.he
 * @date 2017/10/26 0:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryProductRepositoryTest {

    @Autowired
    private QueryProductRepository queryProductRepository;

    @Test
    public void test(){
        Map<String,String> map=new HashMap<>();
        map.put("orderDateIn","2017-10-30");
        map.put("orderDateOut","2017-10-31");
        map.put("productId","1508680395661809415");
        map.put("categoryId","1");
        map.put("productType","0");
        Page page= queryProductRepository.findByTimeOrCategoryTypeOrproductType(map,1,3);
        System.out.println(JsonUtil.toJSonString(page));

    }
}
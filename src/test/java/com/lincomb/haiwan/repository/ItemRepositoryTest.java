package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Item;
import com.lincomb.haiwan.util.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author yongsheng.he
 * @date 2017/10/25 14:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void save(){
        Item item=new Item();
        item.setItemId(KeyUtil.genUniqueKey());
        item.setItemName("333");
       item.setItemDescription("所有套票之票据皆在使用期限内使用，过期将视为作废；");
       item.setProductId("1508680395661809415");

        itemRepository.save(item);
    }
}
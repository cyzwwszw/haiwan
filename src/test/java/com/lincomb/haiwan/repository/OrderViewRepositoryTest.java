package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Order_view;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yongsheng.he
 * @date 2017/11/14 20:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderViewRepositoryTest {

    @Autowired
    private OrderViewRepository orderViewRepository;

    @Test
    public void test() throws Exception {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(3);
        list.add(4);
        list.add(7);
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<Order_view> page = orderViewRepository.findAllByBuyerIdAndOrderStatusInOrderByCreateTimeDesc("1510641357544545277", list, pageRequest);
        System.out.println("是否是首页：" + page.isFirst());
        System.out.println("是否是尾页：" + page.isLast());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("当前显示条数：" + page.getSize());
        System.out.println("页码：" + page.getNumber());
        System.out.println("总条数：" + page.getTotalElements());
        page.getContent().forEach(order_view ->
                System.out.println(order_view.getOrderId() + "---" + order_view.getOrderStatusEnum().getMessage() + "---" + order_view.getPayStatus()));
    }

}
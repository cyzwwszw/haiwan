package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.repository.OrderRepository;
import com.lincomb.haiwan.service.StatisticService;
import com.lincomb.haiwan.vo.BarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by QianYunlong on 15
 */
@Service
public class StatisticServiceImpl implements StatisticService{


    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Map<String, Object> analysisOrder() {
        Map<String, Object> resultMap = new HashMap<>();
        List<Order_t> orderTList = orderRepository.findAll();
        SimpleDateFormat yyyymmdd=new SimpleDateFormat("yyyy-MM-dd");
        List<String> result = orderTList.stream().map((one)->(yyyymmdd.format(one.getCreateTime()))).distinct().collect(Collectors.toList());
        String[] legend = {"新订单笔数","已取消笔数","待使用笔数","已完成笔数","已过期笔数","已退款笔数"};
        List<BarVO> barVOList = new ArrayList<>();

        List<Long>newOrderCountList =new ArrayList<>();
        List<Long>cancelOrderCountList =new ArrayList<>();
        List<Long>waitOrderCountList =new ArrayList<>();
        List<Long>finishOrderCountList =new ArrayList<>();
        List<Long>overtimeOrderCountList =new ArrayList<>();
        List<Long>refundOrderCountList =new ArrayList<>();

        for (String date: result){
            Predicate<Date> equalDate = (n) -> yyyymmdd.format(n).equals(date);//等于该日期
            Predicate<Integer> newOrder = (n) -> n.intValue() == OrderStatusEnum.NEW.getCode();//新订单0
            Predicate<Integer> cancelOrder = (n) -> n.intValue() == OrderStatusEnum.CANCEL.getCode();//新订单1
            Predicate<Integer> waitOrder = (n) -> n.equals(OrderStatusEnum.WAIT.getCode());//待使用3
            Predicate<Integer> finishOrder = (n) -> n.equals(OrderStatusEnum.FINISH.getCode());//已过期5
            Predicate<Integer> overtimeOrder = (n) -> n.equals(OrderStatusEnum.OVERTIME.getCode());//已过期5
            Predicate<Integer> refundOrder = (n) -> n.equals(OrderStatusEnum.REFUND.getCode());//已退款7
            List<Order_t> orderTList1 = orderTList.stream().filter((one) -> (equalDate.test(one.getCreateTime()))).collect(Collectors.toList());

            long newOrderCount = orderTList1.stream().filter((one) -> (newOrder.test(one.getOrderStatus()))).count();
            long cancelOrderCount = orderTList1.stream().filter((one) -> (cancelOrder.test(one.getOrderStatus()))).count();
            long waitOrderCount = orderTList1.stream().filter((one) -> (waitOrder.test(one.getOrderStatus()))).count();
            long finishOrderCount = orderTList1.stream().filter((one) -> (finishOrder.test(one.getOrderStatus()))).count();
            long overtimeOrderCount = orderTList1.stream().filter((one) -> (overtimeOrder.test(one.getOrderStatus()))).count();
            long refundOrderCount = orderTList1.stream().filter((one) -> (refundOrder.test(one.getOrderStatus()))).count();
            newOrderCountList.add(newOrderCount);
            cancelOrderCountList.add(cancelOrderCount);
            waitOrderCountList.add(waitOrderCount);
            finishOrderCountList.add(finishOrderCount);
            overtimeOrderCountList.add(overtimeOrderCount);
            refundOrderCountList.add(refundOrderCount);
        }

        Map<String,List<Long>> term = new HashMap<>();
        term.put(legend[0],newOrderCountList);
        term.put(legend[1],cancelOrderCountList);
        term.put(legend[2],waitOrderCountList);
        term.put(legend[3],finishOrderCountList);
        term.put(legend[4],overtimeOrderCountList);
        term.put(legend[5],refundOrderCountList);

        for (String l:legend){
            BarVO barVO = new BarVO();
            barVO.setName(l);
            barVO.setData(term.get(l));
            barVOList.add(barVO);
        }
        resultMap.put("series", barVOList);
        resultMap.put("x_data",result);
        resultMap.put("legend_data", legend);
        return resultMap;
    }
}

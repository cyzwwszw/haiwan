package com.lincomb.haiwan;

import com.lincomb.haiwan.util.DateUtil;
import sun.rmi.runtime.Log;

import java.util.*;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/28 16:28
 */
public class Test {

    public static void main(String[] args) {
//        List<Integer> numbers = new ArrayList<>();    //Arrays.asList(7, 4, 1, 2, 9, 3, 5, 6, 8, 0);
//        System.out.println(numbers);
//        numbers.sort(Comparator.reverseOrder());
//        System.out.println(numbers);
//
//        if (numbers == null || numbers.isEmpty()) {
//            System.out.println(1);
//    }

        Date date = DateUtil.stringToUtilDate("2017-10-29 11:28:00", DateUtil.SIMPLE_TIME_FORMAT_H);

//        if (date.before(new Date()))
//            System.out.println(1);

        System.out.println(date);
        System.out.println(date.getTime());
        Date date1 =new Date();
        System.out.println(date1);
        System.out.println(date1.getTime());
        long diff = date1.getTime() - date.getTime();
        System.out.println(diff);
        long min = diff / 1000;
        System.out.println(min);
    }
}

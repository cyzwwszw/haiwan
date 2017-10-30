package com.lincomb.haiwan;

import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.vo.ResultVO;
import sun.rmi.runtime.Log;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String IDCARD="((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
                "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
                "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
        Pattern p1 = Pattern.compile(IDCARD);
        Matcher m1 = p1.matcher("740326199505126715");
        if (!m1.matches()) {
            System.out.println(1);
        }else {
            System.out.println(2);
        }
    }
}

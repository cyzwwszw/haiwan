package com.lincomb.haiwan;

import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.FastDFSUtil;
import com.lincomb.haiwan.util.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HaiwanApplicationTests {

    @Test
    public void contextLoads() {

//        try {
//            FastDFSUtil.upload("E:\\111\\295772.jpg","295772.jpg");
//        }catch (Exception e){
//         e.printStackTrace();
//        }

        Date date1=DateUtil.stringToUtilDate("2017-10-27",DateUtil.SIMPLE_DATE_FORMAT);
        Date date2=DateUtil.stringToUtilDate("2017-10-28",DateUtil.SIMPLE_DATE_FORMAT);
        System.out.println(DateUtil.getDays(date1,date2));


    }

}

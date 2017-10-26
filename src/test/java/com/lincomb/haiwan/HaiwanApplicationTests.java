package com.lincomb.haiwan;

import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.FastDFSUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HaiwanApplicationTests {

    @Test
    public void contextLoads() {

        try {
            FastDFSUtil.upload("E:\\111\\295772.jpg","295772.jpg");
        }catch (Exception e){
         e.printStackTrace();
        }
    }

}

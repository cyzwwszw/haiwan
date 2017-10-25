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
            FastDFSUtil.upload("E:\\111\\icon_zaocan.png", "icon_zaocan.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

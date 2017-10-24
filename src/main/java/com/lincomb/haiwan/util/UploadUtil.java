package com.lincomb.haiwan.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午5:20 17/10/23
 */
public class UploadUtil {

    public static String upload(HttpServletRequest request){
        StringBuffer pictureUrl = new StringBuffer(); // 图片地址
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());

            // 判断是否有文件上传
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = mRequest.getFileMap();
                System.out.println("图片集合的长度：" + fileMap.size());
                Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
                int i = 0;
                while (it.hasNext()) {
                    Map.Entry<String, MultipartFile> entry = it.next();
                    MultipartFile mFile = entry.getValue();
                    System.out.println("第" + (i + 1) + "次进入上传图片方法");

                    if (!mFile.isEmpty()) {
                        if (i != 0) {
                            pictureUrl.append(",");
                        }
                        // 返回文件保存路径
                        String path = FastDFSUtil.upload(mFile);
                        if (!StringUtil.isEmpty(path)) {
                            pictureUrl.append(path);
                        } else {
                            System.out.println("图片上传失败");
                        }
                    }
                    i++;
                }
            } else {
                System.out.println("没有文件上传");
            }
        } catch (Exception e) {
            System.out.println("register Exception:[" + e.getMessage() + "]");
        }
        return pictureUrl.toString();
    }
}

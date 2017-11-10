package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Photo;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.repository.PhotoRepository;
import com.lincomb.haiwan.service.PhotoService;
import com.lincomb.haiwan.util.FastDFSUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/30 18:32
 */
@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public ResultVO<Object> queryPictures(String productId, Integer page, Integer size) {
        List<String> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            Photo photo = new Photo();
            photo.setProductId(productId);
            Example<Photo> ex = Example.of(photo);
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            PageRequest request = new PageRequest(page - 1, size, sort);
            Page<Photo> photoPage = photoRepository.findAll(ex, request);
            photoPage.getContent().forEach(
                    p -> list.add(FastDFSUtil.DOWNLOAD_PATH + p.getPhotoUrl()));
            map.put("photoUrlList", list);
            map.put("isLast", photoPage.isLast());
            map.put("isFirst", photoPage.isFirst());
        } catch (Exception e) {
            log.error("queryPictures() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map);
    }

    /**
     * 添加/修改产品图片
     *
     * @param photo
     * @return
     */
    @Override
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public List<Photo> findByProductId(String productId) {
        return photoRepository.findByProductId(productId);
    }

    @Override
    public void delete(String photoId) {
        photoRepository.delete(photoId);
    }
}

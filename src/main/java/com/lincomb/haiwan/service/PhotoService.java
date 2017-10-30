package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Photo;
import com.lincomb.haiwan.vo.ResultVO;

import java.util.List;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/30 18:30
 */
public interface PhotoService {

    /**
     * 查询图片
     *
     * @param productId
     * @param page
     * @param size
     * @return
     */
    ResultVO<Object> queryPictures(String productId, Integer page, Integer size);


    /**
     * 添加/修改产品图片
     *
     * @param photo
     * @return
     */
    Photo savePhoto(Photo photo);

    /**
     * 根据产品ID查询产品图片
     *
     * @param productId
     * @return
     */
    List<Photo> findByProductId(String productId);

    /**
     * 删除
     * @param photoId
     */
    void delete(String photoId);
}

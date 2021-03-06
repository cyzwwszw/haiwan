package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Item;
import com.lincomb.haiwan.domain.Photo;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午10:37 17/10/22
 */
public interface ProductService {

    Product findOne(String productId);

    Page<Product> findAll(Pageable pageable);

    //新增 保存
    Product save(Product product);

    //上架
    Product onSale(String productId);

    //下架
    Product offSale(String productId);

    //删除
    Product delete(String productId);

    /**
     * 查看产品详情
     *
     * @param productId
     * @return
     */
    ResultVO<Object> queryProductDetails(String productId);

    /**
     * 根据入住时间，结束时间，类目，类型，服务查询
     *
     * @param map
     * @param page
     * @param size
     * @return
     */
    ResultVO<Object> findByStartDateOrEndDateOrCategoryIdOrTypeIdOrServiceId(Map<String, String> map, Integer page, Integer size);

    /**
     * 根据入住时间，结束时间,产品ID查询当前产品的所剩数量
     *
     * @param orderDateIn
     * @param orderDateOut
     * @param productId
     * @return
     */
    ResultVO<Object> findByStartDateAndEndDateAndProductId(String orderDateIn, String orderDateOut, String productId,String orderId);

    /**
     * 添加/修改产品须知
     *
     * @param photo
     * @return
     */
    Item saveItem(Item item);

    /**
     * 根据产品ID查询产品须知
     *
     * @param productId
     * @return
     */
    List<Item> findByProductId(String productId);

    /**
     * 根据itemId查询
     *
     * @param itemId
     * @return
     */
    Item findOneItem(String itemId);

    /**
     * 删除产品须知
     */
    void deleteItem(String itemId);
}

package com.lincomb.haiwan.repository;


import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/25 22:54
 */
public interface QueryProductRepository<T, ID extends Serializable> {

    /**
     * 根据入住时间，结束时间，类目，类型查询
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<T> findByTimeOrCategoryTypeOrproductType(Map<String, String> map, int pageNo, int pageSize);

    /**
     * 根据入住时间，结束时间,产品ID查询当前产品的所剩数量
     *
     * @param orderDateIn
     * @param orderDateOut
     * @param productId
     * @return
     */
    BigDecimal findByTimeAndproductId(String orderDateIn, String orderDateOut, String productId);
}

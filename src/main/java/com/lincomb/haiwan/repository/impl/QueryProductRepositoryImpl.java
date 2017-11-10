package com.lincomb.haiwan.repository.impl;

import com.lincomb.haiwan.repository.QueryProductRepository;
import com.lincomb.haiwan.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/25 22:57
 */
@Repository
public class QueryProductRepositoryImpl<T, ID extends Serializable> implements QueryProductRepository<T, ID> {

    @PersistenceContext
    protected EntityManager em;

    /**
     * 根据入住时间，结束时间，类目，类型，服务查询
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<T> findByStartDateOrEndDateOrCategoryIdOrTypeIdOrServiceId(Map<String, String> map, int pageNo, int pageSize) {

        StringBuffer querySql = new StringBuffer("SELECT a.product_id,a.product_name,a.product_address,a.product_price,a.product_pic,a.type, " +
                " a.is_have_wifi,a.is_have_breakfast,a.is_have_bathroom,a.is_have_yard, " +
                " IF(ISNULL(a.r),a.pq,a.r) AS residualQuantity " +
                " FROM " +
                " ( SELECT p.product_id,p.product_name,p.product_address,p.product_price,p.product_pic, " +
                "   (SELECT c.category_name FROM category c WHERE c.category_id=p.product_type) as type, " +
                "   p.is_have_wifi,p.is_have_breakfast,p.is_have_bathroom,p.is_have_yard,p.product_quantity AS pq, " +
                "   p.product_quantity-( " +
                "     SELECT SUM(o.order_count) FROM order_t o " +
                "     WHERE o.order_status IN (0, 2, 3) " +
                "     AND o.product_id=p.product_id " +
                "     AND ((STR_TO_DATE('" + map.get("orderDateIn") + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) ");


        StringBuffer countSql = new StringBuffer("SELECT COUNT(b.product_id) AS count FROM " +
                " (SELECT a.product_id,IF(ISNULL(a.r),a.pq,a.r) AS residualQuantity " +
                " FROM " +
                " ( SELECT p.product_id,p.product_quantity AS pq, " +
                "   p.product_quantity-( " +
                "     SELECT SUM(o.order_count) FROM order_t o " +
                "     WHERE o.order_status IN (0, 2, 3) " +
                "     AND o.product_id=p.product_id " +
                "     AND ((STR_TO_DATE('" + map.get("orderDateIn") + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) ");

        if (!StringUtil.isEmpty(map.get("orderDateOut"))) {
            querySql.append(" OR (STR_TO_DATE('" + map.get("orderDateOut") + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) ");
            countSql.append(" OR (STR_TO_DATE('" + map.get("orderDateOut") + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) ");
        }

        querySql.append(" ) GROUP BY o.product_id) as r FROM product p WHERE p.product_status =0 ");
        countSql.append(" ) GROUP BY o.product_id) as r FROM product p WHERE p.product_status =0 ");

        if (!StringUtil.isEmpty(map.get("categoryId"))) {
            querySql.append(" AND p.category_id =" + map.get("categoryId"));
            countSql.append(" AND p.category_id =" + map.get("categoryId"));
        }
        if (!StringUtil.isEmpty(map.get("typeId"))) {
            querySql.append(" AND p.product_type=" + map.get("typeId"));
            countSql.append(" AND p.product_type=" + map.get("typeId"));
        }
        if (!StringUtil.isEmpty(map.get("serviceId"))) {
            if (map.get("serviceId").equals("1")) {
                querySql.append(" AND p.is_have_breakfast=0 ");
                countSql.append(" AND p.is_have_breakfast=0 ");
            }
            if (map.get("serviceId").equals("2")) {
                querySql.append(" AND p.is_have_wifi=0 ");
                countSql.append(" AND p.is_have_wifi=0 ");
            }
            if (map.get("serviceId").equals("3")) {
                querySql.append(" AND p.is_have_bathroom=0 ");
                countSql.append(" AND p.is_have_bathroom=0 ");
            }
            if (map.get("serviceId").equals("4")) {
                querySql.append(" AND p.is_have_yard=0 ");
                countSql.append(" AND p.is_have_yard=0 ");
            }
        }

        querySql.append(" ORDER BY p.create_time DESC,p.product_name ASC ) AS a HAVING residualQuantity <> 0 ");

        countSql.append(" ORDER BY p.create_time DESC,p.product_name ASC ) AS a HAVING residualQuantity <> 0 ) AS b");

        Query query = em.createNativeQuery(querySql.toString());
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Object[]> list = query.getResultList();

        List<BigInteger> totals = em.createNativeQuery(countSql.toString()).getResultList();
        BigInteger total = new BigInteger("0");
        for (BigInteger integer : totals) {
            total = total.add(integer);
        }

        Pageable pageable = new PageRequest((pageNo - 1), pageSize);

        PageImpl page = new PageImpl(list, pageable, total.longValue());
        return page;

    }

    @Override
    public BigDecimal findByStartDateAndEndDateAndProductId(String orderDateIn, String orderDateOut, String productId) {

        StringBuffer sql = new StringBuffer("SELECT IF(ISNULL(b.r),b.pq,b.r) AS residualQuantity FROM " +
                "( SELECT p.product_quantity AS pq,p.product_quantity-( " +
                "      SELECT SUM(o.order_count) FROM order_t o " +
                "      WHERE o.order_status IN (0, 2, 3) " +
                "      AND o.product_id=p.product_id " +
                "      AND ((STR_TO_DATE('" + orderDateIn + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) " +
                "             OR (STR_TO_DATE('" + orderDateOut + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out)) " +
                "      GROUP BY o.product_id " +
                "      ) as r " +
                " FROM product p " +
                " WHERE p.product_status=0 " +
                " AND p.product_id ='" + productId + "'" +
                ") AS b ");

        List<BigDecimal> residualQuantitys = em.createNativeQuery(sql.toString()).getResultList();

        if (residualQuantitys != null && !residualQuantitys.isEmpty()) {
            if (residualQuantitys.size() == 1) {
                return residualQuantitys.get(0);
            }
            return new BigDecimal(0);
        } else {
            return new BigDecimal(0);
        }
    }
}

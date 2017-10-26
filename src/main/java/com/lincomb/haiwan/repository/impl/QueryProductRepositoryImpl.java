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
     * 根据入住时间，结束时间，类目，类型查询
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<T> findByTimeOrCategoryTypeOrproductType(Map<String, String> map, int pageNo, int pageSize) {
        String sql = "SELECT v.product_id,v.product_name,v.product_address," +
                "v.product_price,v.product_pic,v.product_type,v.is_have_wifi,v.is_have_breakfast," +
                "v.is_have_bathroom,is_have_yard,v.product_quantity,(v.product_quantity - SUM(v.order_count)) aa " +
                " FROM order_view v WHERE 1=1 ";

        String sql1 = "SELECT COUNT(*) FROM( " +
                "SELECT v.product_id,v.product_quantity,(v.product_quantity - SUM(v.order_count)) aa " +
                " FROM order_view v WHERE 1=1  ";

        if (!StringUtil.isEmpty(map.get("orderDateIn")) &&
                !StringUtil.isEmpty(map.get("orderDateOut"))) {
            sql += " AND v.product_id NOT IN (SELECT o.product_id FROM order_view o WHERE o.order_status IN (0, 1) " +
                    " AND (o.order_date_in BETWEEN STR_TO_DATE('" + map.get("orderDateIn") + "', '%Y-%m-%d') " +
                    " AND STR_TO_DATE('" + map.get("orderDateOut") + "', '%Y-%m-%d'))" +
                    " OR " +
                    "(o.order_date_out BETWEEN STR_TO_DATE('" + map.get("orderDateIn") + "', '%Y-%m-%d') " +
                    " AND STR_TO_DATE('" + map.get("orderDateOut") + "', '%Y-%m-%d')) " +
                    " GROUP BY o.product_id HAVING SUM(o.order_count)=(SELECT p.product_quantity FROM product p WHERE p.product_id = o.product_id)) ";

            sql1 += " AND v.product_id NOT IN (SELECT o.product_id FROM order_view o WHERE o.order_status IN (0, 1) " +
                    " AND (o.order_date_in BETWEEN STR_TO_DATE('" + map.get("orderDateIn") + "', '%Y-%m-%d') " +
                    " AND STR_TO_DATE('" + map.get("orderDateOut") + "', '%Y-%m-%d'))" +
                    " OR " +
                    "(o.order_date_out BETWEEN STR_TO_DATE('" + map.get("orderDateIn") + "', '%Y-%m-%d') " +
                    " AND STR_TO_DATE('" + map.get("orderDateOut") + "', '%Y-%m-%d')) " +
                    " GROUP BY o.product_id HAVING SUM(o.order_count)=(SELECT p.product_quantity FROM product p WHERE p.product_id = o.product_id)) ";
        }
        if (!StringUtil.isEmpty(map.get("categvryId"))) {
            sql += " AND v.category_id =" + map.get("categoryId");
            sql1 += " AND v.category_id =" + map.get("categvryId");
        }
        if (!StringUtil.isEmpty(map.get("productType"))) {
            sql += " AND v.product_type=" + map.get("productType");
            sql1 += " AND v.product_type=" + map.get("productType");
        }

        sql += " GROUP BY v.product_id HAVING SUM(v.order_count) <> v.product_quantity ";

        sql1 += " GROUP BY v.product_id HAVING SUM(v.order_count) <> v.product_quantity ) AS b";

        Query query = em.createNativeQuery(sql);

        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<Object[]> list = query.getResultList();

        List<BigInteger> totals = em.createNativeQuery(sql1).getResultList();

        BigInteger total = new BigInteger("0");

        if (totals != null && !totals.isEmpty()) {
            if (totals.size() == 1) {
                total.add(totals.get(0));
            }
        }

        Pageable pageable = new PageRequest((pageNo - 1), pageSize);

        PageImpl page = new PageImpl(list, pageable, total.longValue());
        return page;

    }

    @Override
    public BigDecimal findByTimeAndproductId(String orderDateIn, String orderDateOut, String productId) {

        String sql = "SELECT (o.product_quantity - SUM(o.order_count)) aa FROM order_view o WHERE o.order_status IN (0, 1) ";

        if (!StringUtil.isEmpty(productId)) {
            sql += " AND o.product_id = '" + productId + "' ";
        }
        if (!StringUtil.isEmpty(orderDateIn) &&
                !StringUtil.isEmpty(orderDateOut)) {
            sql += " AND (o.order_date_in BETWEEN STR_TO_DATE('" + orderDateIn + "', '%Y-%m-%d') " +
                    " AND STR_TO_DATE('" + orderDateOut + "', '%Y-%m-%d'))" +
                    " OR " +
                    "(o.order_date_out BETWEEN STR_TO_DATE('" + orderDateIn + "', '%Y-%m-%d') " +
                    " AND STR_TO_DATE('" + orderDateOut + "', '%Y-%m-%d')) ";
        }
        sql += " GROUP BY o.product_id";

        List<BigDecimal> residualQuantitys = em.createNativeQuery(sql).getResultList();

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

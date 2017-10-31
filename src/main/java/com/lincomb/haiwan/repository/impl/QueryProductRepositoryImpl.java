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

        StringBuffer sql = new StringBuffer("SELECT p.product_id,p.product_name,p.product_address,p.product_price," +
                "p.product_pic,p.product_type,p.is_have_wifi,p.is_have_breakfast,p.is_have_bathroom,p.is_have_yard," +
                "p.product_quantity,p.product_quantity-( SELECT SUM(o.order_count) FROM order_t o " +
                " WHERE o.product_id=p.product_id AND o.order_status IN (0, 2, 3) " +
                " AND ((STR_TO_DATE('" + map.get("orderDateIn") + "','%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) ");

        StringBuffer sql1 = new StringBuffer("SELECT COUNT(*) FROM( " +
                "SELECT p.product_id,p.product_name,p.product_address,p.product_price," +
                "p.product_pic,p.product_type,p.is_have_wifi,p.is_have_breakfast,p.is_have_bathroom,p.is_have_yard," +
                "p.product_quantity,p.product_quantity-( SELECT SUM(o.order_count) FROM order_t o " +
                " WHERE o.product_id=p.product_id AND o.order_status IN (0, 2, 3) " +
                " AND ((STR_TO_DATE('" + map.get("orderDateIn") + "','%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) ");

        if (!StringUtil.isEmpty(map.get("orderDateOut"))) {
            sql.append(" OR (STR_TO_DATE('" + map.get("orderDateOut") + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) ");
            sql1.append(" OR (STR_TO_DATE('" + map.get("orderDateOut") + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) ");
        }

        sql.append(" ) GROUP BY o.product_id) as residualQuantity " +
                " FROM product p LEFT JOIN category c ON p.category_id=c.category_id WHERE p.product_status=0 ");
        sql1.append(" ) GROUP BY o.product_id) as residualQuantity " +
                " FROM product p LEFT JOIN category c ON p.category_id=c.category_id WHERE p.product_status=0 ");

        if (!StringUtil.isEmpty(map.get("categoryType"))) {
            sql.append(" AND c.category_type =" + map.get("categoryType"));
            sql1.append(" AND c.category_type =" + map.get("categoryType"));
        }
        if (!StringUtil.isEmpty(map.get("productType"))) {
            sql.append(" AND p.product_type=" + map.get("productType"));
            sql1.append(" AND p.product_type=" + map.get("productType"));
        }

        sql.append(" ORDER BY p.create_time DESC,p.product_name ASC ");

        sql1.append(" ) AS b");

        Query query = em.createNativeQuery(sql.toString());
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Object[]> list = query.getResultList();

        List<BigInteger> totals = em.createNativeQuery(sql1.toString()).getResultList();
        BigInteger total = new BigInteger("0");
        for (BigInteger integer : totals) {
            total = total.add(integer);
        }

        Pageable pageable = new PageRequest((pageNo - 1), pageSize);

        PageImpl page = new PageImpl(list, pageable, total.longValue());
        return page;

    }

    @Override
    public BigDecimal findByTimeAndproductId(String orderDateIn, String orderDateOut, String productId) {

        String sql = "SELECT IF (isnull(p.product_quantity - (SELECT SUM(o.order_count) FROM order_t o WHERE o.product_id = p.product_id AND o.order_status IN (0, 2, 3) " +
                " AND ((STR_TO_DATE('" + orderDateIn + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) " +
                "  OR (STR_TO_DATE('" + orderDateOut + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out)) GROUP BY o.product_id) " +
                " ),p.product_quantity,p.product_quantity - (SELECT SUM(o.order_count) FROM order_t o WHERE o.product_id = p.product_id AND o.order_status IN (0, 2, 3) " +
                " AND ((STR_TO_DATE('" + orderDateIn + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out) " +
                "  OR (STR_TO_DATE('" + orderDateOut + "', '%Y-%m-%d') BETWEEN o.order_date_in AND o.order_date_out)) GROUP BY o.product_id) " +
                " )AS residualQuantity FROM product p LEFT JOIN category c ON p.category_id = c.category_id " +
                "WHERE p.product_id = '" + productId + "' ";
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

package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.*;
import com.lincomb.haiwan.enums.*;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.repository.*;
import com.lincomb.haiwan.service.ProductService;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.EnumUtil;
import com.lincomb.haiwan.util.FastDFSUtil;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ProductDetailsVO;
import com.lincomb.haiwan.vo.ProductVO;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import sun.print.resources.serviceui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午10:50 17/10/22
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RefundRuleRepository refundRuleRepository;
    @Autowired
    private QueryProductRepository queryProductRepository;

    @Override
    public Product findOne(String productId) {
        return productRepository.findOne(productId);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByProductStatusNot(ProductStatusEnum.DELETE.getCode(), pageable);
    }

    @Override
    public Product save(Product product) {
        Category category1 = categoryRepository.findOne(product.getCategoryId());
        if (category1 == null) {
            throw new HaiwanException(ResultEnum.CATEGORY_NOT_EXIST);
        }
        return productRepository.save(product);
    }

    @Override
    public Product onSale(String productId) {
        Product productInfo = productRepository.findOne(productId);
        if (productInfo == null) {
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new HaiwanException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productRepository.save(productInfo);
    }

    @Override
    public Product offSale(String productId) {
        Product productInfo = productRepository.findOne(productId);
        if (productInfo == null) {
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new HaiwanException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productRepository.save(productInfo);
    }

    @Override
    public Product delete(String productId) {

        Product productInfo = productRepository.findOne(productId);
        if (productInfo == null) {
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DELETE) {
            throw new HaiwanException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DELETE.getCode());
        return productRepository.save(productInfo);
    }

    @Override
    public ResultVO<Object> queryProductDetails(String productId) {
        ProductDetailsVO detailsVO = new ProductDetailsVO();
        try {
            Product product = productRepository.findOne(productId);

            detailsVO.setProductId(product.getProductId());
            detailsVO.setProductName(product.getProductName());
            detailsVO.setProductDescription(product.getProductDescription());
            detailsVO.setProductAddress(product.getProductAddress());
            detailsVO.setProductPrice(product.getProductPrice());
            detailsVO.setProductPic(product.getProductPic());
            List<Map<String, String>> maps = new ArrayList<>();

            if (product.getIsHaveYard() == 0) {
                Map<String, String> map = new HashMap<>();
                map.put("text", ServicesEnum.YARD.getText());
                map.put("src", ServicesEnum.YARD.getSrc());
                maps.add(map);
            }
            if (product.getIsHaveBreakfast() == 0) {
                Map<String, String> map = new HashMap<>();
                map.put("text", ServicesEnum.BREAKFAST.getText());
                map.put("src", ServicesEnum.BREAKFAST.getSrc());
                maps.add(map);
            }
            if (product.getIsHaveBathroom() == 0) {
                Map<String, String> map = new HashMap<>();
                map.put("text", ServicesEnum.BATHROOM.getText());
                map.put("src", ServicesEnum.BATHROOM.getSrc());
                maps.add(map);
            }
            if (product.getIsHaveWifi() == 0) {
                Map<String, String> map = new HashMap<>();
                map.put("text", ServicesEnum.WIFI.getText());
                map.put("src", ServicesEnum.WIFI.getSrc());
                maps.add(map);
            }
            maps.addAll(disposeStr(product.getEquipment()));
            detailsVO.setServicesList(maps);

            List<Map<String, String>> strings = new ArrayList<>();

            List<Item> items = itemRepository.findByProductId(productId);
            items.forEach(
                    item -> {
                        Map<String, String> map1 = new HashMap<>();
                        map1.put("itemTitle", item.getItemName());
                        map1.put("itemDescription", item.getItemDescription());
                        strings.add(map1);
                    });
            detailsVO.setItemDescriptionList(strings);

            RefundRule refundRule = refundRuleRepository.findTopByRuleNo(product.getRuleNo());
            detailsVO.setRuleDescription(refundRule.getRuleDescription());

        } catch (Exception e) {
            log.error("queryProductDetails() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, detailsVO);
    }

    /**
     * 根据入住时间，结束时间，类目，类型查询
     *
     * @param map
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResultVO<Object> findByTimeOrCategoryTypeOrproductType(Map<String, String> map, Integer page, Integer size) {

        Map<String, Object> map1 = new HashMap<>();
        try {
            List<ProductVO> productVOList = new ArrayList<>();
            Page<Object[]> page1 = queryProductRepository.findByTimeOrCategoryTypeOrproductType(map, page, size);
            for (Object[] o : page1.getContent()) {
                ProductVO vo = new ProductVO();
                if (o[11] != null) {
                    if (new BigDecimal(StringUtil.null2String(o[11])).intValue() == 0) {
                        continue;
                    }
                    vo.setResidualQuantity(new BigDecimal(StringUtil.null2String(o[11])));
                } else {
                    vo.setResidualQuantity(new BigDecimal(StringUtil.null2String(o[10])));
                }
                vo.setProductId(StringUtil.null2String(o[0]));
                vo.setProductName(StringUtil.null2String(o[1]));
                vo.setProductAddress(StringUtil.null2String(o[2]));
                vo.setProductPrice(new BigDecimal(StringUtil.null2String(o[3])));
                vo.setProductPic(StringUtil.null2String(o[4]) == "" ? "" : FastDFSUtil.DOWNLOAD_PATH + o[4].toString());
                vo.setProductType(StringUtil.null2String(o[5]) == "" ? "" : EnumUtil.getByCode(
                        Integer.valueOf(StringUtil.null2String(o[5])), ProductTypeEnum.class).getMessage());

                List<String> list = new ArrayList<>();
                if (StringUtil.null2String(o[6]).equals("0")) {
                    list.add(ServicesEnum.WIFI.getText());
                }
                if (StringUtil.null2String(o[7]).equals("0")) {
                    list.add(ServicesEnum.BREAKFAST.getText());
                }
                if (StringUtil.null2String(o[8]).equals("0")) {
                    list.add(ServicesEnum.BATHROOM.getText());
                }
                if (StringUtil.null2String(o[9]).equals("0")) {
                    list.add(ServicesEnum.YARD.getText());
                }
                vo.setServicesList(list);

                if (!StringUtil.isEmpty(map.get("orderDateIn"))) {
                    Date date2 = DateUtil.stringToUtilDate(map.get("orderDateIn"), DateUtil.SIMPLE_DATE_FORMAT);
                    vo.setOrderDateIn(DateUtil.toDateTimeString(date2, DateUtil.SIMPLE_DATE_FORMAT_CN));
                } else {
                    vo.setOrderDateIn("");
                }
                if (!StringUtil.isEmpty(map.get("orderDateOut"))) {
                    Date date1 = DateUtil.stringToUtilDate(map.get("orderDateOut"), DateUtil.SIMPLE_DATE_FORMAT);
                    vo.setOrderDateOut(DateUtil.toDateTimeString(date1, DateUtil.SIMPLE_DATE_FORMAT_CN));
                } else {
                    vo.setOrderDateOut("");
                }
                productVOList.add(vo);
            }
            map1.put("productVOList", productVOList);
            map1.put("isLast", page1.isLast());
            map1.put("isFirst", page1.isFirst());
        } catch (Exception e) {
            log.error("queryPictures() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map1);
    }

    /**
     * 根据入住时间，结束时间,产品ID查询当前产品的所剩数量
     *
     * @param orderDateIn
     * @param orderDateOut
     * @param productId
     * @return
     */
    @Override
    public ResultVO<Object> findByTimeAndproductId(String orderDateIn, String orderDateOut, String productId) {
        Map<String, Object> map = new HashMap<>();
        try {
            BigDecimal residualQuantity = queryProductRepository.findByTimeAndproductId(orderDateIn, orderDateOut, productId);
            map.put("residualQuantity", residualQuantity);
        } catch (Exception e) {
            log.error("queryPictures() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map);
    }


    /**
     * 添加产品须知
     *
     * @param item
     * @return
     */
    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findByProductId(String productId) {
        return itemRepository.findByProductId(productId);
    }

    @Override
    public Item findOneItem(String itemId) {
        return itemRepository.findOne(itemId);
    }


    /**
     * 私有方法
     */

    private List<Map<String, String>> disposeStr(String str) {
        List<Map<String, String>> maps = new ArrayList<>();
        String[] strs = str.split(",");
        for (int i = 0; i < strs.length; i++) {
            String str1 = strs[i];
            //电视，冰箱，电脑，烧烤，空调
            switch (str1) {
                case "1":
                    Map<String, String> map1 = new HashMap<>();
                    map1.put("text", ServicesEnum.TV.getText());
                    map1.put("src", ServicesEnum.TV.getSrc());
                    maps.add(map1);
                    break;
                case "2":
                    Map<String, String> map2 = new HashMap<>();
                    map2.put("text", ServicesEnum.FRIG.getText());
                    map2.put("src", ServicesEnum.FRIG.getSrc());
                    maps.add(map2);
                    break;
                case "3":
                    Map<String, String> map3 = new HashMap<>();
                    map3.put("text", ServicesEnum.PC.getText());
                    map3.put("src", ServicesEnum.PC.getSrc());
                    maps.add(map3);
                    break;
                case "4":
                    Map<String, String> map4 = new HashMap<>();
                    map4.put("text", ServicesEnum.BBQ.getText());
                    map4.put("src", ServicesEnum.BBQ.getSrc());
                    maps.add(map4);
                    break;
                case "5":
                    Map<String, String> map5 = new HashMap<>();
                    map5.put("text", ServicesEnum.AIRCONDITIONING.getText());
                    map5.put("src", ServicesEnum.AIRCONDITIONING.getSrc());
                    maps.add(map5);
                    break;
            }
        }
        return maps;
    }

}

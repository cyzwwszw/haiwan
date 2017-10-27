package com.lincomb.haiwan.controller.client;

import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.service.CategoryService;
import com.lincomb.haiwan.service.ProductService;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe 产品
 * @date 2017/10/24 11:03
 */
@RestController
@RequestMapping("/client/product")
@Slf4j
public class ClientProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据入住时间，结束时间,产品ID查询当前产品的所剩数量
     *
     * @return
     */
    @PostMapping("/findByTimeAndproductId")
    public ResultVO<Object> findByTimeAndproductId(
            @RequestParam String orderDateIn,
            @RequestParam String orderDateOut,
            @RequestParam String productId
    ) {
        if (StringUtil.isEmpty(orderDateIn) || StringUtil.isEmpty(orderDateOut) || StringUtil.isEmpty(productId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }

        ResultVO<Object> resultVO = productService.findByTimeAndproductId(orderDateIn, orderDateOut, productId);
        return resultVO;
    }

    /**
     * 根据入住时间，结束时间，类目，类型查询
     *
     * @return
     */
    @PostMapping("/findByTimeOrCategoryTypeOrproductType")
    public ResultVO<Object> findByTimeOrCategoryTypeOrproductType(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderDateOut", required = false) String orderDateOut,
            @RequestParam(value = "productType", required = false) String productType,
            @RequestParam(value = "categoryType", required = false) String categoryType,
            @RequestParam() String orderDateIn
    ) {
        Map<String, String> map = new HashMap<>();
        map.put("orderDateIn", orderDateIn);
        map.put("orderDateOut", orderDateOut);
        map.put("productType", productType);
        map.put("categoryType", categoryType);

        ResultVO<Object> resultVO = productService.findByTimeOrCategoryTypeOrproductType(map, page, size);
        return resultVO;
    }

    /**
     * 根据产品ID查询产品详情
     *
     * @return
     */
    @PostMapping("/queryProductDetails")
    public ResultVO<Object> queryProductDetails(@RequestParam String productId) {

        if (StringUtil.isEmpty(productId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }
        ResultVO<Object> result = productService.queryProductDetails(productId);
        return result;
    }

    /**
     * 根据产品ID查询图片
     *
     * @return
     */
    @PostMapping("/queryPictures")
    public ResultVO<Object> queryPictures(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam String productId) {
        if (StringUtil.isEmpty(productId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }
        ResultVO<Object> result = productService.queryPictures(productId, page, size);
        return result;
    }

    /**
     * 查询类目
     *
     * @return
     */
    @PostMapping("/queryCategory")
    public ResultVO<Object> queryCategory() {

        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Category> list = categoryService.findAll();
            list.forEach(category -> {
                Map<String, Object> map = new HashMap<>();
                map.put("categoryName", category.getCategoryName());
                map.put("categoryType", category.getCategoryType());
                mapList.add(map);
            });
        } catch (Exception e) {
            log.error("queryCategory() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, mapList);
    }

}

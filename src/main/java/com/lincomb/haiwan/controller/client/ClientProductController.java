package com.lincomb.haiwan.controller.client;

import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.service.CategoryService;
import com.lincomb.haiwan.service.PhotoService;
import com.lincomb.haiwan.service.ProductService;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.Value;
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
    @Autowired
    private PhotoService photoService;

    /**
     * 根据入住时间，结束时间,产品ID查询当前产品的所剩数量
     *
     * @return
     */
    @PostMapping("/findByStartDateAndEndDateAndProductId")
    public ResultVO<Object> findByStartDateAndEndDateAndProductId(
            @RequestParam String orderDateIn,
            @RequestParam String orderDateOut,
            @RequestParam String productId,
            @RequestParam(value = "orderId", required = false) String orderId
    ) {
        if (StringUtil.isEmpty(orderDateIn) || StringUtil.isEmpty(orderDateOut) || StringUtil.isEmpty(productId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }

        ResultVO<Object> resultVO = productService.findByStartDateAndEndDateAndProductId(orderDateIn, orderDateOut, productId, orderId);
        return resultVO;
    }

    /**
     * 根据入住时间，结束时间，类目，类型，服务查询
     *
     * @return
     */
    @PostMapping("/findByStartDateOrEndDateOrCategoryIdOrTypeIdOrServiceId")
    public ResultVO<Object> findByStartDateOrEndDateOrCategoryIdOrTypeIdOrServiceId(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam() String orderDateIn,
            @RequestParam(value = "orderDateOut", required = false) String orderDateOut,
            @RequestParam(value = "categoryId", required = false) String categoryId,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "serviceId", required = false) String serviceId
    ) {
        Map<String, String> map = new HashMap<>();
        map.put("orderDateIn", orderDateIn);
        map.put("orderDateOut", orderDateOut);
        map.put("categoryId", categoryId);
        map.put("typeId", typeId);
        map.put("serviceId", serviceId);

        ResultVO<Object> resultVO = productService.findByStartDateOrEndDateOrCategoryIdOrTypeIdOrServiceId(map, page, size);
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
        ResultVO<Object> result = photoService.queryPictures(productId, page, size);
        return result;
    }

    /**
     * 查询类别
     *
     * @return
     */
    @PostMapping("/queryCategory")
    public ResultVO<Object> queryCategory() {

        ResultVO<Object> result = categoryService.queryCategory();
        return result;
    }

    /**
     * 查询类型
     *
     * @param categoryId
     * @return
     */
    @PostMapping("/queryType")
    public ResultVO<Object> queryType(@RequestParam(value = "categoryId", required = false) String categoryId) {

        ResultVO<Object> result = categoryService.queryType(categoryId);
        return result;
    }

}

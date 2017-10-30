package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.converter.Product2ProductDTOConverter;
import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.domain.RefundRule;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.form.ProductForm;
import com.lincomb.haiwan.service.CategoryService;
import com.lincomb.haiwan.service.ProductService;
import com.lincomb.haiwan.service.RefundRuleService;
import com.lincomb.haiwan.util.FastDFSUtil;
import com.lincomb.haiwan.util.KeyUtil;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.util.UploadUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午10:54 17/10/22
 */
@Controller
@RequestMapping("/backend/product/")
public class ProductController {

    @Autowired
    ProductService productService;
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    RefundRuleService refundRuleService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map){
        Sort sort =new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size,sort);
        Page<Product> productPage = productService.findAll(request);
        map.put("productPage", productPage);
        map.put("productList", Product2ProductDTOConverter.convert(productPage.getContent(), categoryService.findAll()));
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map){
        if(!StringUtils.isEmpty(productId)){
            Product product = productService.findOne(productId);
            map.put("product", product);
        }

        //查询所有的类目
        List<Category> categoryList = categoryService.findAll();
        List<RefundRule> refundRuleList = refundRuleService.findAll();
        map.put("categoryList", categoryList);
        map.put("refundRuleList",refundRuleList);
        return new ModelAndView("product/index", map);
    }

    /**
     * 保存更新
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,Object> map,
                             HttpServletRequest httpServletRequest){
        String url = UploadUtil.upload(httpServletRequest);
        if(!StringUtil.isEmpty(url)){
            productForm.setProductPic(FastDFSUtil.DOWNLOAD_PATH + url);
        }
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/haiwan/backend/product/index");
            return new ModelAndView("common/error", map);
        }
        Product product = new Product();
        try{
            if(!StringUtils.isEmpty(productForm.getProductId())){
                 product= productService.findOne(productForm.getProductId());
            }else{
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm, product);
            productService.save(product);
        }catch(HaiwanException e){
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/backend/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/haiwan/backend/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId, Map<String, Object> map){
        try{
            productService.onSale(productId);
        }catch (HaiwanException e){
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/backend/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url","/haiwan/backend/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId, Map<String, Object> map){
        try{
            productService.offSale(productId);
        }catch (HaiwanException e){
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/backend/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url","/haiwan/backend/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("productId") String productId, Map<String, Object> map){
        try{
            productService.delete(productId);
        }catch (HaiwanException e){
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/backend/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url","/haiwan/backend/product/list");
        return new ModelAndView("common/success", map);
    }
}

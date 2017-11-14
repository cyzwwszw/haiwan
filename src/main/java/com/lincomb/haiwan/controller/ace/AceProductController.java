package com.lincomb.haiwan.controller.ace;


import com.lincomb.haiwan.converter.Product2ProductDTOConverter;
import com.lincomb.haiwan.domain.*;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.form.ItemForm;
import com.lincomb.haiwan.form.ProductForm;
import com.lincomb.haiwan.service.CategoryService;
import com.lincomb.haiwan.service.PhotoService;
import com.lincomb.haiwan.service.ProductService;
import com.lincomb.haiwan.service.RefundRuleService;
import com.lincomb.haiwan.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
@RequestMapping("/ace/product/")
@Slf4j
public class AceProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    RefundRuleService refundRuleService;
    @Autowired
    private PhotoService photoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size, sort);
        Page<Product> productPage = productService.findAll(request);
        map.put("productList", Product2ProductDTOConverter.convert(productPage.getContent(), categoryService.findAll()));
        map.put("productPage", productPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("ace/product/list", map);
    }


    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {
        if (!StringUtil.isEmpty(productId)) {
            Product product = productService.findOne(productId);
            map.put("product", product);
        }

        //查询所有的类目
        List<Category> categoryList = categoryService.findByParentId(null);
        List<RefundRule> refundRuleList = refundRuleService.findAll();
        map.put("path", FastDFSUtil.DOWNLOAD_PATH);
        map.put("categoryList", categoryList);
        map.put("refundRuleList", refundRuleList);
        return new ModelAndView("ace/product/index", map);
    }

    /**
     * 保存更新
     *
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map,
                             HttpServletRequest httpServletRequest) {
        String url = UploadUtil.upload(httpServletRequest);
        if (!StringUtil.isEmpty(url)) {
            productForm.setProductPic(url);
        }
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/haiwan/ace/product/index");
            return new ModelAndView("common/error", map);
        }
        Product product = new Product();
        try {
            if (!StringUtil.isEmpty(productForm.getProductId())) {
                product = productService.findOne(productForm.getProductId());
            } else {
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm, product);
            productService.save(product);
        } catch (HaiwanException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/ace/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/haiwan/ace/product/index?productId=" + product.getProductId());
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (HaiwanException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/ace/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/haiwan/ace/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (HaiwanException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/ace/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/haiwan/ace/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            productService.delete(productId);
        } catch (HaiwanException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/ace/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/haiwan/ace/product/list");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/saveItem")
    public ModelAndView saveItem(ItemForm itemForm, Map<String, Object> map) {
        try {
            Item item = new Item();
            if (!StringUtil.isEmpty(itemForm.getItemId())) {
                item = productService.findOneItem(itemForm.getItemId());
            } else {
                itemForm.setItemId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(itemForm, item);
            productService.saveItem(item);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        map.put("url", "/haiwan/ace/product/toItemList?productId=" + itemForm.getProductId());
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/toItem")
    public ModelAndView toItem(@RequestParam(value = "itemId", required = false) String itemId,
                               @RequestParam(value = "productId", required = false) String productId,
                               Map<String, Object> map) {

        Item item = new Item();
        if (!StringUtil.isEmpty(itemId)) {
            item = productService.findOneItem(itemId);
        }
        ItemForm itemForm = new ItemForm();
        BeanUtils.copyProperties(item, itemForm);
        itemForm.setProductId(productId);
        map.put("itemForm", itemForm);
        return new ModelAndView("ace/product/item", map);
    }

    @RequestMapping("/toItemList")
    public ModelAndView toItemList(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {

        if (!StringUtil.isEmpty(productId)) {
            List<Item> items = productService.findByProductId(productId);
            map.put("items", items);
            map.put("itemsSize", items.size());
        }
        map.put("productId", productId);
        return new ModelAndView("ace/product/itemList", map);
    }

    @RequestMapping("/deleteItem")
    public ModelAndView deleteItem(@RequestParam String itemId, @RequestParam String productId, Map<String, Object> map) {
        try {
            productService.deleteItem(itemId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        map.put("url", "/haiwan/ace/product/toItemList?productId=" + productId);
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/toPeictures")
    public ModelAndView toPeictures(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {

        if (!StringUtil.isEmpty(productId)) {
            map.put("productId", productId);
            List<Photo> photos = photoService.findByProductId(productId);
            map.put("photos", photos);
            map.put("path", FastDFSUtil.DOWNLOAD_PATH);
        }
        return new ModelAndView("ace/product/pictures", map);
    }

    @RequestMapping("/savePeictures")
    public ModelAndView savePeictures(@RequestParam MultipartFile[] files, @RequestParam String productId, Map<String, Object> map) {
        try {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                if (!file.isEmpty()) {
                    // 返回文件保存路径
                    String path = FastDFSUtil.upload(file);
                    if (StringUtil.isEmpty(path)) {
                        log.info("图片上传失败");
                    } else {
                        Photo photo = new Photo();
                        photo.setPhotoId(KeyUtil.genUniqueKey());
                        photo.setProductId(productId);
                        photo.setPhotoUrl(path);
                        photoService.savePhoto(photo);
                    }
                }
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/ace/product/toPeictures?productId=" + productId);
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/haiwan/ace/product/toPeictures?productId=" + productId);
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/deletePeictures")
    @ResponseBody
    public String deletePeictures(@RequestParam String id, Map<String, Object> map) {
        try {
            photoService.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        map.put("success", "{}");
        return JsonUtil.toJSonString(map);
    }
}

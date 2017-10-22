package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.enums.CategoryStatusEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.form.CategoryForm;
import com.lincomb.haiwan.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by QianYunlong on 19
 */
@Controller
@RequestMapping("/backend/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map){
        List<Category> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map){
        if (categoryId != null){
            Category productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/haiwan/backend/category/index");
            return new ModelAndView("common/error", map);
        }

        Category category = new Category();
        try{
            if(form.getCategoryId()!=null){
                category = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, category);
            categoryService.save(category);
        }catch (HaiwanException e){
            map.put("msg", e.getMessage());
            map.put("url","/haiwan/backend/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/haiwan/backend/category/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "categoryId") Integer categoryId,
                              Map<String, Object> map){
        try{
            Category category = categoryService.findOne(categoryId);
            category.setCategoryStatus(CategoryStatusEnum.DELETE.getCode());
            categoryService.save(category);
        }catch (HaiwanException e){
            map.put("msg", e.getMessage());
            map.put("url","/haiwan/backend/category/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/haiwan/backend/category/list");
        return new ModelAndView("common/success", map);
    }

}

package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.domain.RoomUser;
import com.lincomb.haiwan.enums.CategoryStatusEnum;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.repository.CategoryRepository;
import com.lincomb.haiwan.service.CategoryService;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<Category> findAll() {
        Category category = new Category();
        category.setCategoryStatus(CategoryStatusEnum.NOMARL.getCode());
        Example<Category> ex = Example.of(category);
        Sort sort = new Sort(Sort.Direction.ASC, "categoryType");
        return categoryRepository.findAll(ex, sort);

    }

    @Override
    public List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public Category save(Category Category) {
        return categoryRepository.save(Category);
    }


    @Override
    public List<Category> findByParentId(Integer categoryId) {
        return categoryRepository.findByParentIdOrderByCategoryType(categoryId);
    }

    @Override
    public ResultVO<Object> queryCategory() {

        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            List<Category> list = categoryRepository.queryAllCategory();
            list.forEach(category -> {
                Map<String, Object> map = new HashMap<>();
                map.put("categoryId", category.getCategoryId());
                map.put("categoryName", category.getCategoryName());
                mapList.add(map);
            });
        } catch (Exception e) {
            log.error("queryRoomUser() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, mapList);
    }

    @Override
    public ResultVO<Object> queryType(String categoryId) {

        Map<String,Object> map=new HashMap<>();
        try {
            List<Category> list = new ArrayList<>();
            if (StringUtil.isNull(categoryId)) {
                list = categoryRepository.queryAllType();
            } else {
                list = categoryRepository.queryTypeBYCategoryId(categoryId);
            }
            List<Map<String, Object>> typeList = new ArrayList<>();
            list.forEach(category -> {
                Map<String, Object> typeMap = new HashMap<>();
                typeMap.put("typeId", category.getCategoryId());
                typeMap.put("typeName", category.getCategoryName());
                typeList.add(typeMap);
            });

            List<Map<String, Object>> serviceList = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("serviceId", 1);
            map1.put("serviceName", "早餐");
            serviceList.add(map1);
            Map<String, Object> map2 = new HashMap<>();
            map2.put("serviceId", 2);
            map2.put("serviceName", "宽带");
            serviceList.add(map2);
            Map<String, Object> map3 = new HashMap<>();
            map3.put("serviceId", 3);
            map3.put("serviceName", "卫浴");
            serviceList.add(map3);
            Map<String, Object> map4 = new HashMap<>();
            map4.put("serviceId", 4);
            map4.put("serviceName", "庭院");
            serviceList.add(map4);

            map.put("type", typeList);
            map.put("service", serviceList);

        } catch (Exception e) {
            log.error("queryRoomUser() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map);
    }
}

package com.lincomb.haiwan.converter;

import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.dto.ProductDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午4:59 17/10/30
 */
public class Product2ProductDTOConverter {

    public static ProductDTO convert(Product product, List<Category> categoryList){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        List<Category> filter = categoryList.stream().filter(category -> category.getCategoryId()==product.getCategoryId()).collect(Collectors.toList());
        List<Category> filter1 = categoryList.stream().filter(category -> category.getCategoryId()==product.getProductType()).collect(Collectors.toList());
        productDTO.setCategoryName(filter.get(0).getCategoryName());
       // productDTO.setProductTypeName(filter1.get(0).getCategoryName());
        return productDTO;
    }

    public static List<ProductDTO> convert(List<Product> productList, List<Category> categoryList){
        return productList.stream().map
                (e->convert(e,categoryList)).collect(Collectors.toList());
    }


}

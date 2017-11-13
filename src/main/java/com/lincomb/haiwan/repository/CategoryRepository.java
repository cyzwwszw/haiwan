package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午3:17 17/10/21
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList);

    List<Category> findByParentIdOrderByCategoryType(Integer parentId);

    @Query(value = "select * from category c where c.category_status=0 and c.parent_id is null order by c.category_type", nativeQuery = true)
    List<Category> queryAllCategory();

    @Query(value = "select * from category c where c.category_status=0 and c.parent_id is not null order by c.category_type", nativeQuery = true)
    List<Category> queryAllType();

    @Query(value = "select * from category c where c.category_status=0 and c.parent_id =:categoryId order by c.category_type", nativeQuery = true)
    List<Category> queryTypeBYCategoryId(@Param("categoryId") String categoryId);
}

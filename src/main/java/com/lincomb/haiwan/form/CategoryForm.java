package com.lincomb.haiwan.form;

import com.lincomb.haiwan.enums.CategoryStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by QianYunlong on 19
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;

    @NotNull(message = "类目编号不能为空")
    private Integer categoryType;

    private Integer categoryStatus = CategoryStatusEnum.NOMARL.getCode();
}

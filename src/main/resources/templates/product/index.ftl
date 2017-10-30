<html>
<#include "../common/header.ftl">
<script>
    $().ready(function() {
        $("#commentForm").validate();
    });
</script>
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h3 class="page-header">产品发布</h3>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" class="form-horizontal" method="post" enctype="multipart/form-data" action="/haiwan/backend/product/save">
                        <div class="col-md-12 column">
                            <h4 class="page-header">基本信息</h4>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-4">
                                <input name="productName" type="text" class="form-control"
                                       value="${(product.productName )!''}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">图片展现</label>
                            <div class="col-sm-6">
                            <#if (product.productPic)??&&(product.productPic) != ''>
                                <img src="${(product.productPic)!''}" height="200">
                            </#if>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-4">
                                <input name="fileUpload" type="file">
                            </div>
                        </div>

                        <#--<div class="form-group">-->
                            <#--<label class="col-sm-2 control-label">图片路径</label>-->
                            <#--<div class="col-sm-10">-->
                                <#--<input name="productPic" type="text" class="form-control"-->
                                       <#--value="${(product.productPic)!''}"/>-->
                            <#--</div>-->
                        <#--</div>-->


                        <div class="form-group">
                            <label class="col-sm-2 control-label">描述说明</label>
                            <div class="col-sm-10">
                                <textarea name="productDescription" class="form-control"
                                          rows="3">${(product.productDescription)!''}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-4">
                                <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                        <#if (product.categoryType)?? && product.categoryType == category.categoryType>
                                            selected
                                        </#if>
                                    >
                                    ${category.categoryName}
                                    </option>
                                </#list>
                                </select>
                            </div>
                            <label class="col-sm-2 control-label">面积</label>
                            <div class="col-sm-4">
                                <input name="productArea" type="text" class="form-control"
                                       value="${(product.productArea )!''}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">位置</label>
                            <div class="col-sm-4">
                                <input name="productAddress" type="text" class="form-control"
                                       value="${(product.productAddress )!''}"/>
                            </div>
                            <label class="col-sm-2 control-label">规格</label>
                            <div class="col-sm-4">
                                <select name="productType" class="form-control">
                                    <option value="0">单人间</option>
                                    <option value="1">标准间</option>
                                    <option value="2">家庭间</option>
                                    <option value="3">VIP间</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">退房时间</label>
                            <div class="col-sm-4">
                                <input name="productCheckoutTime" type="text" class="form-control"
                                       value="${(product.productCheckoutTime )!''}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">支付方式</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" name="productPayType" value="0"
                                    <#if (product.productPayType)??&&product.productPayType == 0>
                                           checked
                                    </#if>
                                    >都支持
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="productPayType" value="1"
                                    <#if (product.productPayType)??&&product.productPayType == 1>
                                           checked
                                    </#if>
                                    >仅线上支付
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="productPayType" value="2"
                                    <#if (product.productPayType)??&&product.productPayType == 2>
                                           checked
                                    </#if>
                                    >仅线下支付
                                </label>
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">标准价格</label>
                            <div class="col-sm-4">
                                <input name="productPrice" type="text" class="form-control"
                                       value="${(product.productPrice?replace(',',''))!''}">
                            </div>
                            <label class="col-sm-1 control-label">元</label>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">数量</label>
                            <div class="col-sm-4">
                                <input name="productQuantity" type="text" class="form-control"
                                       value="${(product.productQuantity )!''}"/>
                            </div>
                            <label class="col-sm-1 control-label">间</label>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">发布上架</label>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="productStatus" value="0"
                                    <#if (product.isHaveYard)??&&product.productStatus == 0>
                                           checked
                                    </#if>>是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="productStatus" value="1"
                                    <#if (product.isHaveYard)??&&product.productStatus == 1>
                                           checked
                                    </#if>>否
                                </label>
                            </div>
                        </div>

                        <div class="col-md-12 column">
                            <h4 class="page-header">提供服务</h4>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">早餐</label>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveBreakfast" value="0"
                                    <#if (product.isHaveBreakfast)??&&product.isHaveBreakfast == 0>
                                           checked
                                    </#if>
                                    >有
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveBreakfast" value="1"
                                    <#if (product.isHaveBreakfast)??&&product.isHaveBreakfast == 1>
                                           checked
                                    </#if>>无
                                </label>
                            </div>

                            <label class="col-sm-2 control-label">卫浴</label>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveBathroom" value="0"
                                    <#if (product.isHaveBathroom)??&&product.isHaveBathroom == 0>
                                           checked
                                    </#if>>有
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveBathroom" value="1"
                                    <#if (product.isHaveBathroom)??&&product.isHaveBathroom == 1>
                                           checked
                                    </#if>>无
                                </label>
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">宽带</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveWifi" value="0"
                                    <#if (product.isHaveWifi)??&&product.isHaveWifi == 0>
                                           checked
                                    </#if>>无
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveWifi" value="1"
                                    <#if (product.isHaveWifi)??&&product.isHaveWifi == 1>
                                           checked
                                    </#if>>免费wifi
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveWifi" value="2"
                                    <#if (product.isHaveWifi)??&&product.isHaveWifi == 2>
                                           checked
                                    </#if>>免费有线
                                </label>

                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">庭院</label>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveYard" value="0"
                                    <#if (product.isHaveYard)??&&product.isHaveYard == 0>
                                           checked
                                    </#if>>有
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="isHaveYard" value="1"
                                    <#if (product.isHaveYard)??&&product.isHaveYard == 1>
                                           checked
                                    </#if>>无
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">电器</label>
                            <div class="col-sm-10">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="equipment" value="1"
                                    <#if (product.equipment)??&&product.equipment?contains('1')>
                                           checked
                                    </#if>>电视
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="equipment" value="2"
                                    <#if (product.equipment)??&&product.equipment?contains('2')>
                                           checked
                                    </#if>>冰箱
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="equipment" value="3"
                                    <#if (product.equipment)??&&product.equipment?contains('3')>
                                           checked
                                    </#if>>电脑
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="equipment" value="4"
                                    <#if (product.equipment)??&&product.equipment?contains('4')>
                                           checked
                                    </#if>>烧烤机
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="equipment" value="5"
                                    <#if (product.equipment)??&&product.equipment?contains('6')>
                                           checked
                                    </#if>>空调
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-2">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="equipment" value="6"
                                    <#if (product.equipment)??&& product.equipment?contains('6')>
                                           checked
                                    </#if>>其他
                                </label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" name="others" value="${(product.others )!''}">
                            </div>
                        </div>

                        <div class="col-md-12 column">
                            <h4 class="page-header">退票配置</h4>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">退票规则</label>
                            <div class="col-sm-4">
                                <select name="ruleNo" class="form-control">
                                <#list refundRuleList as rule>
                                    <option value="${rule.ruleNo}"
                                        <#if (product.ruleNo)?? && product.ruleNo == rule.ruleNo>
                                            selected
                                        </#if>
                                    >
                                    ${rule.ruleName}
                                    </option>
                                </#list>
                                </select>
                            </div>
                        </div>

                        <input hidden type="text" name="productId" value="${(product.productId)!''}">
                        <div class="col-sm-offset-2 col-sm-4">
                            <button type="submit" class="btn btn-default">保存，下一步</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

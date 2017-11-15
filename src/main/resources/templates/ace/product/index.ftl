<html>
<meta charset="UTF-8"/>
<#include "../common/header.ftl">
<link href="/haiwan/static/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>

<script>
    function clearNoNum(obj) {
        obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
    }
</script>
<body>
<#include "../common/top.ftl">
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
    <#include "../common/menu.ftl">


        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="#">首页</a>
                    </li>
                    <li>
                        <a href="#">产品</a>
                    </li>
                    <li class="active">产品发布</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            产品发布
                            <small>
                                <i class="icon-double-angle-right"></i>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->

                            <div class="alert alert-info">
                                <i class="icon-hand-right"></i>
                                请添加您要发布的产品
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>

                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <form id="productForm" role="form" class="form-horizontal" method="post" enctype="multipart/form-data" action="/haiwan/ace/product/save">
                                            <div class="row clearfix">
                                                <div class="col-md-12 column">
                                                    <h4 class="page-header">基本信息</h4>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">名称</label>
                                                <div class="col-sm-4">
                                                    <input name="productName" type="text" class="form-control"
                                                           value="${(product.productName )!''}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">首页图片</label>
                                                <div class="col-sm-4">
                                                    <input id="fileUpload" name="fileUpload" type="file">
                                                    <input hidden type="text" name="productPic"
                                                           value="${(product.productPic)!''}"
                                                           id="productPic">
                                                    <input hidden type="text" name="path" value="${(path)!''}"
                                                           id="path">
                                                </div>
                                            </div>

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
                                                    <select id="categoryId" name="categoryId" class="form-control">
                                                    <#list categoryList as category>
                                                        <option value="${category.categoryId}"
                                                            <#if (product.categoryId)?? && product.categoryId == category.categoryId>
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
                                                    <input name="productArea" type="number" class="form-control"
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
                                                    <select name="productType" class="form-control" id="productType">

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
                                                           value="${(product.productPrice?replace(',',''))!''}"
                                                           onkeyup="clearNoNum(this)">
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
                                            <div class="row clearfix">
                                                <div class="col-md-12 column">
                                                    <h4 class="page-header">提供服务</h4>
                                                </div>
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
                                            </div>
                                            <div class="form-group">
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
                                                        </#if>>有
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="isHaveWifi" value="1"
                                                        <#if (product.isHaveWifi)??&&product.isHaveWifi == 1>
                                                               checked
                                                        </#if>>无
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

                                            <#--<div class="form-group">-->
                                                <#--<label class="col-sm-2 control-label"></label>-->
                                                <#--<div class="col-sm-2">-->
                                                    <#--<label class="checkbox-inline">-->
                                                        <#--<input type="checkbox" name="equipment" value="6"-->
                                                        <#--<#if (product.equipment)??&& product.equipment?contains('6')>-->
                                                               <#--checked-->
                                                        <#--</#if>>其他-->
                                                    <#--</label>-->
                                                <#--</div>-->
                                                <#--<div class="col-sm-4">-->
                                                    <#--<input type="text" name="others" value="${(product.others )!''}">-->
                                                <#--</div>-->
                                            <#--</div>-->
                                            <div class="row clearfix">
                                                <div class="col-md-12 column">
                                                    <h4 class="page-header">退票配置</h4>
                                                </div>
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
                                            <div class="hr-line-dashed"></div>
                                            <div class="form-group">
                                                <input hidden type="text" name="productId"
                                                       value="${(product.productId)!''}">
                                                <div class="col-sm-offset-2 col-sm-1">
                                                    <button type="submit" class="btn btn-primary">保存 </button>
                                                </div>
                                                <div class="col-sm-1">
                                                    <button type="button" class="btn btn-primary"
                                                            <#if (product.productId)??>

                                                            <#else>
                                                                disabled="disabled"
                                                            </#if>
                                                            onclick='location.href="/haiwan/ace/product/toPeictures?productId=${(product.productId)!''}"'>
                                                        下一步
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- PAGE CONTENT ENDS -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.page-content -->
            </div>
        </div>

    <#include "../common/design.ftl">
    </div>

    <!--快速回到顶部控件-->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<#include "../common/footer.ftl">
<script src="/haiwan/static/fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="/haiwan/static/fileinput/js/locales/zh.js" type="text/javascript"></script>
<script src="/haiwan/static/js/productForm_validate.js"></script>
<script>
    $(document).ready(function () {
        var url1 = ""
        if ($("#productPic").val() !== "") {
            url1 = $("#path").val() + $("#productPic").val();
        }

        $('#fileUpload').fileinput({
            initialPreview: url1,
            initialPreviewAsData: true,
            language: 'zh',
            allowedFileExtensions: ['jpg', 'png'],
            maxFileSize: 1000,
            textEncoding: 'UTF-8',
            showUpload: false
        });

        var type = "${(product.productType)!''}";
        typeBind(type);

        $("#categoryId").change(function () {
            typeBind(null);
        })

    });

    function typeBind(type) {
        var category = $("#categoryId option:selected").val();
        //判断省份这个下拉框选中的值是否为空
        if (category == "") {
            return;
        }
        $("#productType").html("");
        var str = "";
        $.ajax({
            type: "post",
            url: "/haiwan/ace/product/findByParentId",
            data: {
                "parentId": category
            },
            dataType: "JSON",
            async: false,
            success: function (data) {
                //从服务器获取数据进行绑定
                $.each(data.categoryList,
                        function (i, item) {
                            if (type == item.categoryId) {
                                str += "<option selected='selected' value=" + item.categoryId + ">"
                                        + item.categoryName
                                        + "</option>";
                            } else {
                                str += "<option value=" + item.categoryId + ">"
                                        + item.categoryName
                                        + "</option>";
                            }
                        });
                //将数据添加到省份这个下拉框里面
                $("#productType").append(str);
            },
            error: function () {
            }
        });
    }
</script>
</body>
</html>


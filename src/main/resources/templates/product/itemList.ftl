<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h1 class="page-header">产品发布</h1>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <h3 class="page-header">查看要求须知</h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <button type="button" class="btn btn-primary"
                            onclick="tianjia()">
                        添加
                    </button>
                    <table class="table table-bordered table-condensed table-hover">
                        <colgroup>
                            <col style="">
                            <col style="">
                            <col style="width: 50%">
                            <col style="">
                            <col style="">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>描述说明</th>
                            <th>发布时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list items as item>
                        <tr>
                            <td>${item.itemId}</td>
                            <td>${item.itemName}</td>
                            <td>${item.itemDescription}</td>
                            <td>${item.createTime}</td>
                            <td>
                                <a href="/haiwan/backend/product/toItem?itemId=${item.itemId}&productId=${item.productId}">修改</a>
                            </td>
                            <td>
                                <a href="/haiwan/backend/product/deleteItem?itemId=${item.itemId}&productId=${item.productId}">删除</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="col-sm-offset-1 col-sm-1">
                        <button type="button" class="btn btn-primary"
                                onclick='location.href="/haiwan/backend/product/toPeictures?productId=${(productId)!''}"'>
                            上一步
                        </button>
                    </div>
                    <div class="col-sm-offset-1 col-sm-1">
                        <button type="submit" class="btn btn-primary"
                                onclick='location.href="/haiwan/backend/product/list"'>
                            发布
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    function tianjia() {
        var itemsSize;
    <#if itemsSize??>
        itemsSize = "${itemsSize}";
    </#if>
        if (itemsSize < 5) {
            location.href = "/haiwan/backend/product/toItem?productId=${(productId)!''}"
        } else {
            alert("要求须知只能添加五条！");
        }
    }
</script>
</body>
</html>

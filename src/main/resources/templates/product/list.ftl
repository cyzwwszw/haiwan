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
                        <h3 class="page-header">产品查询</h3>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>产品编号</th>
                                <th>产品名称</th>
                                <th>类型</th>
                                <th>规格</th>
                                <th>发布状态</th>
                                <th>发布时间</th>
                                <th>数量</th>
                                <th>价格</th>
                                <th colspan="3">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list productList as product>
                            <tr>
                                <td>${product.productId}</td>
                                <td>${product.productName}</td>
                                <td>${product.categoryName}</td>
                                <td>${product.productTypeName}</td>
                                <td>${product.getProductStatusEnum().message}</td>
                                <td>${product.createTime}</td>
                                <td>${product.productQuantity}</td>
                                <td>${product.productPrice}</td>
                                <td>
                                    <a href="/haiwan/backend/product/index?productId=${product.productId}">修改</a>
                                </td>
                                <td>
                                    <#if product.getProductStatusEnum().message == "上架">
                                        <a href="/haiwan/backend/product/off_sale?productId=${product.productId}">下架</a>
                                    </#if>
                                    <#if product.getProductStatusEnum().message == "下架">
                                        <a href="/haiwan/backend/product/on_sale?productId=${product.productId}">上架</a>
                                    </#if>
                                </td>
                                <td>
                                    <a href="/haiwan/backend/product/delete?productId=${product.productId}">删除</a>
                                </td>
                            </tr>
                            </#list>

                            </tbody>
                        </table>

                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                            <#if currentPage lte 1>
                                <li class="disabled"><a href="#">上一页</a></li>
                            <#else>
                                <li><a href="/haiwan/backend/product/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                            </#if>
                            <#list 1..productPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="active"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/haiwan/backend/product/list?page=${index}&size=${size}">${index}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte productPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/haiwan/backend/product/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                            </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </body>
</html>

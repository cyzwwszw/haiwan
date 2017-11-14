<html>
<meta charset="UTF-8"/>
<#include "../common/header.ftl">
<body>
<#include "../common/top.ftl">
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
    <#include "../common/menu.ftl">


        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="#">首页</a>
                    </li>
                    <li>
                        <a href="#">产品</a>
                    </li>
                    <li class="active">产品列表</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            产品列表
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
                                请查询维护您的产品
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>

                            <!-- PAGE CONTENT ENDS -->
                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <div class="table-header">
                                            查询结果
                                        </div>
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
                                                <td>''</td>
                                                <td>${product.getProductStatusEnum().message}</td>
                                                <td>${product.createTime}</td>
                                                <td>${product.productQuantity}</td>
                                                <td>${product.productPrice}</td>
                                                <td>
                                                    <a href="/haiwan/ace/product/index?productId=${product.productId}">修改</a>
                                                </td>
                                                <td>
                                                    <#if product.getProductStatusEnum().message == "上架">
                                                        <a href="/haiwan/ace/product/off_sale?productId=${product.productId}">下架</a>
                                                    </#if>
                                                    <#if product.getProductStatusEnum().message == "下架">
                                                        <a href="/haiwan/ace/product/on_sale?productId=${product.productId}">上架</a>
                                                    </#if>
                                                </td>
                                                <td>
                                                    <a href="/haiwan/ace/product/delete?productId=${product.productId}">删除</a>
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
                                                <li><a href="/haiwan/ace/product/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                                            </#if>
                                            <#list 1..productPage.getTotalPages() as index>
                                                <#if currentPage == index>
                                                    <li class="active"><a href="#">${index}</a></li>
                                                <#else>
                                                    <li><a href="/haiwan/ace/product/list?page=${index}&size=${size}">${index}</a></li>
                                                </#if>
                                            </#list>
                                            <#if currentPage gte productPage.getTotalPages()>
                                                <li class="disabled"><a href="#">下一页</a></li>
                                            <#else>
                                                <li><a href="/haiwan/ace/product/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                                            </#if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
</body>
</html>


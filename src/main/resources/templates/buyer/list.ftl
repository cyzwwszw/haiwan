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
                                <th>用户Id</th>
                                <th>注册日期</th>
                                <th>联系电话</th>
                                <th>上次登录时间</th>
                                <th colspan="3">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list buyerPage.content as buyer>
                            <tr>
                                <td>${buyer.buyerId}</td>
                                <td>${buyer.createTime}</td>
                                <td>${buyer.buyerMobile}</td>
                                <td>${buyer.updateTime}</td>
                                <td>
                                    <a href="/haiwan/backend/buyer/index?buyerId=${buyer.buyerId}">查看详情</a>
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
                                <li><a href="/haiwan/backend/buyer/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                            </#if>
                            <#list 1..buyerPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="active"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/haiwan/backend/buyer/list?page=${index}&size=${size}">${index}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte buyerPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/haiwan/backend/buyer/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
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

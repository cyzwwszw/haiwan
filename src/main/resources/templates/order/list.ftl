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
                        <h3 class="page-header">订单列表</h3>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-6 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th>订单号</th>
                                <th>产品名称</th>
                                <th>类型</th>
                                <th>规格</th>
                                <th>用户手机</th>
                                <th>数量</th>
                                <th>支付金额（元）</th>
                                <th>下单时间</th>
                                <th>产品状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list orderPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.productName}</td>
                                <td>${orderDTO.categoryName}</td>
                                <td>${orderDTO.getProductTypeEnum().message}</td>
                                <td>${orderDTO.buyerMobile}</td>
                                <td>${orderDTO.orderCount}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.createTime}</td>
                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                <td>
                                    <a href="/haiwan/backend/order/index?orderId=${orderDTO.orderId}">详情</a>
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
                                <li><a href="/haiwan/backend/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                            </#if>
                            <#list 1..orderPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/haiwan/backend/order/list?page=${index}&size=${size}">${index}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte orderPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/haiwan/backend/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
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

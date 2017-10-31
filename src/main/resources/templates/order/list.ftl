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
                <div class="col-md-12 column">
                    <h4 class="page-header">查询选项</h4>
                </div>
                <div class="col-md-12 column">
                    <form id="orderQueryForm" role="form" class="form-horizontal" method="get"
                          action="/haiwan/backend/order/list?page=0&size=10">

                        <div class="form-group">
                            <label class="col-sm-1 control-label">手机号</label>
                            <div class="col-sm-2">
                                <input name="buyerPhone" type="text" class="form-control"
                                       value="${(buyerPhone )!''}"/>
                            </div>
                            <#--NEW(0, "新订单"),-->
                            <#--CANCEL(1, "已取消"),-->
                            <#--WAIT(3, "待使用"),-->
                            <#--FINISH(4, "已完成"),-->
                            <#--OVERTIME(5, "已过期"),-->
                            <#--REFUND_ING(6, "退款中"),-->
                            <#--REFUND(7, "已退款");-->
                            <label class="col-sm-1 control-label">订单状态</label>
                            <div class="col-sm-2">
                                <select name="orderStatus" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="0"
                                    <#if (orderStatus)?? && orderStatus == 0>
                                            selected
                                    </#if>
                                    >新订单</option>
                                    <option value="1"
                                    <#if (orderStatus)?? && orderStatus == 1>
                                            selected
                                    </#if>
                                    >已取消</option>
                                    <option value="3"
                                    <#if (orderStatus)?? && orderStatus == 3>
                                            selected
                                    </#if>
                                    >待使用</option>
                                    <option value="4"
                                    <#if (orderStatus)?? && orderStatus == 4>
                                            selected
                                    </#if>
                                    >已完成</option>
                                    <option value="5"
                                    <#if (orderStatus)?? && orderStatus == 5>
                                            selected
                                    </#if>
                                    >已过期</option>
                                    <option value="7"
                                    <#if (orderStatus)?? && orderStatus == 7>
                                            selected
                                    </#if>
                                    >已退款</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <button type="submit" class="btn btn-primary">查询</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h4 class="page-header">查询结果</h4>
                </div>
                <div class="col-md-12 column">
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
                            <th colspan="2">操作</th>
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
                            <td>
                                <#if orderDTO.getOrderStatusEnum().message == '待使用'>
                                    <a href="/haiwan/backend/order/finish?orderId=${orderDTO.orderId}">使用</a>
                                </#if>
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
                            <li><a href="/haiwan/backend/order/list?page=${currentPage - 1}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">上一页</a></li>
                        </#if>
                        <#list 1..orderPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <#if index &gt; 6 && index &lt;= orderPage.getTotalPages() -4 && orderPage.getTotalPages() &gt; 10>
                                    <li><a>...</a></li>
                                </#if>
                                <li class="active"><a href="#">${index}</a></li>
                                <#if index &gt;= 5 && index &lt;= orderPage.getTotalPages() -6 && orderPage.getTotalPages() &gt; 10>
                                    <li><a>...</a></li>
                                </#if>
                            <#else>
                                <#if index &lt; 6 || index &gt;= orderPage.getTotalPages() -4 >
                                    <#if index ==5 && currentPage &lt; 6 && orderPage.getTotalPages() &gt; 10>
                                        <li><a href="/haiwan/backend/order/list?page=${index}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">${index}</a>
                                        </li>
                                        <li><a>...</a></li>
                                    <#elseif index == orderPage.getTotalPages()-4 && currentPage &gt; orderPage.getTotalPages()-5&& orderPage.getTotalPages() &gt; 10>
                                        <li><a>...</a></li>
                                        <li><a href="/haiwan/backend/order/list?page=${index}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">${index}</a>
                                        </li>
                                    <#else>
                                        <li><a href="/haiwan/backend/order/list?page=${index}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">${index}</a>
                                        </li>
                                    </#if>
                                </#if>
                            </#if>

                        </#list>

                        <#if currentPage gte orderPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/haiwan/backend/order/list?page=${currentPage + 1}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">下一页</a></li>
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

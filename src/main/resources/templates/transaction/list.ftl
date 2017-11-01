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
                        <h3 class="page-header">对账查询</h3>
                    </div>
                </div>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <h4 class="page-header">查询选项</h4>
                    </div>
                    <div class="col-md-12 column">
                        <form id="orderQueryForm" role="form" class="form-horizontal" method="get"
                              action="/haiwan/backend/transaction/list?page=0&size=10">

                            <div class="form-group">
                                <label class="col-sm-1 control-label">订单号</label>
                                <div class="col-sm-2">
                                    <input name="orderId" type="text" class="form-control"
                                           value="${(orderId )!''}"/>
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
                                <th>支付交易订单</th>
                                <th>交易订单</th>
                                <th>支付金额</th>
                                <th>支付时间</th>
                                <th>退款金额</th>
                                <th>退款时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list transactionPage.getContent() as transaction>
                            <tr>
                                <td>${(transaction.transactionId)!''}</td>
                                <td>${(transaction.orderId)!''}</td>
                                <td>${(transaction.payAmount)!''}</td>
                                <td>${(transaction.payTime)!''}</td>
                                <td>${(transaction.refundAmount)!''}</td>
                                <td>${(transaction.refundTime)!''}</td>
                                <td>
                                    <a href="/haiwan/backend/transaction/index?transactionId=${transaction.transactionId}">详情</a>
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
                                <li><a href="/haiwan/backend/transaction/list?page=${currentPage - 1}&size=${size}&orderId=${(orderId)!''}">上一页</a></li>
                            </#if>
                            <#list 1..transactionPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="active"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/haiwan/backend/transaction/list?page=${index}&size=${size}&orderId=${(orderId)!''}">${index}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte transactionPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/haiwan/backend/transaction/list?page=${currentPage + 1}&size=${size}&orderId=${(orderId)!''}">下一页</a></li>
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

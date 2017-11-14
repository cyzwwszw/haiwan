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
                        <a href="#">对账</a>
                    </li>
                    <li class="active">对账列表</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            账务列表
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
                                请核对您的账目
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>

                            <!-- PAGE CONTENT ENDS -->
                            <div class="container-fluid">

                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <form id="orderQueryForm" role="form" class="form-horizontal" method="get"
                                              action="/haiwan/ace/transaction/list?page=0&size=10">

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
                                        <div class="table-header">
                                            查询结果
                                        </div>
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
                                                    <a href="/haiwan/ace/transaction/index?transactionId=${transaction.transactionId}">详情</a>
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
                                                <li><a href="/haiwan/ace/transaction/list?page=${currentPage - 1}&size=${size}&orderId=${(orderId)!''}">上一页</a></li>
                                            </#if>
                                            <#list 1..transactionPage.getTotalPages() as index>
                                                <#if currentPage == index>
                                                    <li class="active"><a href="#">${index}</a></li>
                                                <#else>
                                                    <li><a href="/haiwan/ace/transaction/list?page=${index}&size=${size}&orderId=${(orderId)!''}">${index}</a></li>
                                                </#if>
                                            </#list>
                                            <#if currentPage gte transactionPage.getTotalPages()>
                                                <li class="disabled"><a href="#">下一页</a></li>
                                            <#else>
                                                <li><a href="/haiwan/ace/transaction/list?page=${currentPage + 1}&size=${size}&orderId=${(orderId)!''}">下一页</a></li>
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


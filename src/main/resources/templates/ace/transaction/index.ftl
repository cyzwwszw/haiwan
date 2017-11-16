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
                    <li>
                        <a href="/haiwan/ace/transaction/list">对账查询</a>
                    </li>
                    <li class="active">对账明细</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            交易明细
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
                                请查看具体的交易信息
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>

                            <!-- PAGE CONTENT ENDS -->
                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <h4 class="page-header">订单信息</h4>
                                    </div>
                                    <div class="col-md-4 column">
                                        <table class="table table-bordered table-condensed">
                                            <tr>
                                                <td class="info col-md-6">订单号</td>
                                                <td class="col-md-6">${(transaction.orderId )!''}</td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-6">下单时间</td>
                                                <td class="col-md-6">${(order.createTime )!''}</td>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-6">支付平台</td>
                                                <td class="col-md-6">微信</td>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-6">支付流水号</td>
                                                <td class="col-md-6">${(transaction.transactionId )!''}</td>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>

                                    <div class="col-md-12 column">
                                        <h4 class="page-header">交易信息</h4>
                                    </div>
                                    <div class="col-md-12 column">
                                        <table class="table table-bordered table-condensed">
                                            <tr>
                                                <td class="info col-md-2">用户</td>
                                                <td class="col-md-2">${(buyer.buyerId )!''}</td>
                                                <td class="info col-md-2">用户电话</td>
                                                <td class="col-md-2">${(buyer.buyerMobile )!''}</td>
                                                <td class="info col-md-2">订单数量</td>
                                                <td class="col-md-2">${(order.orderCount )!''}</td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-2">票据单价</td>
                                                <td class="col-md-2">${(product.productPrice )!''}</td>
                                                <td class="info col-md-2">票据状态</td>
                                                <td class="col-md-2">${(order.getOrderStatusEnum().message )!''}</td>
                                                <td class="info col-md-2">退款金额</td>
                                                <td class="col-md-2">${(transaction.refundAmount)!''}</td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-2">退票时间</td>
                                                <td class="col-md-2">${(transaction.refundTime )!''}</td>
                                                <td class="info col-md-2">实付金额</td>
                                                <td class="col-md-2">${(transaction.payAmount )!''}</td>
                                            </tr>
                                        </table>
                                    </div>

                                    <div class="col-md-12 column">
                                        <h4 class="page-header">产品信息</h4>
                                    </div>
                                    <div class="col-md-12 column">
                                        <table class="table table-bordered table-condensed">
                                            <tr>
                                                <td class="info col-md-2">名称</td>
                                                <td class="col-md-2">${(product.productName )!''}</td>
                                                <td class="info col-md-2">编号</td>
                                                <td class="col-md-2">${(product.productId )!''}</td>
                                                <td class="info col-md-2">类型</td>
                                                <td class="col-md-2">${(category.categoryName )!''}</td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-2">位置</td>
                                                <td class="col-md-2">${(product.productAddress )!''}</td>
                                                <td class="info col-md-2">面积</td>
                                                <td class="col-md-2">${(product.productQuantity )!''}</td>
                                            </tr>
                                        </table>
                                    </div>

                                    <div class="col-md-12 column">
                                        <h4 class="page-header">游客信息</h4>
                                    </div>
                                    <div class="col-md-4 column">
                                        <table class="table table-bordered table-condensed">
                                            <tr>
                                                <td class="info col-md-6">用户名</td>
                                                <td class="col-md-6">${(roomUser.userName )!''}</td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-6">手机</td>
                                                <td class="col-md-6">${(roomUser.userMobile )!''}</td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-6">身份证号</td>
                                                <td class="col-md-6">${(roomUser.userIdentityNo )!''}</td>
                                            </tr>
                                        </table>
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


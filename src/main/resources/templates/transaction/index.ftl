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
                    <h3 class="page-header">订单详情</h3>
                </div>
            </div>
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
                            <td class="col-md-2">${(order.orderAmount)!''}</td>
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
    </div>
</div>

</body>
</html>

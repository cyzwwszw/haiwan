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
                           <td class="info col-md-2">订单号</td>
                            <td class="col-md-2">${(transaction.orderId )!''}</td>
                        </tr>
                        <tr>
                           <td class="info col-md-2">下单时间</td>
                            <td class="col-md-2">${(order.createTime )!''}</td>
                            </td>
                        </tr>
                        <tr>
                            <td class="info col-md-2">支付平台</td>
                            <td class="col-md-2">微信</td>
                            </td>
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
                            <td class="col-md-2">${(product.categoryId )!''}</td>
                        </tr>
                        <tr>
                           <td class="info col-md-2">位置</td>
                            <td class="col-md-2">${(product.productAddress )!''}</td>
                           <td class="info col-md-2">面积</td>
                            <td class="col-md-2">${(product.productArea )!''}</td>
                           <td class="info col-md-2">规格</td>
                            <td class="col-md-2">${(product.productType )!''}</td>
                        </tr>
                        <tr>
                            <td class="info col-md-2">退房时间</td>
                            <td class="col-md-2">${(product.productCheckoutTime )!''}</td>
                            <td class="info col-md-2">数量</td>
                            <td class="col-md-2">${(product.productQuantity )!''}</td>
                            <td class="info col-md-2">标准价格</td>
                            <td class="col-md-2">${(product.productPrice )!''}</td>
                        </tr>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <h4 class="page-header">游客信息</h4>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <tr>
                            <td class="info col-md-2">用户名</td>
                            <td class="col-md-2">${(roomUser.userName )!''}</td>
                            <td class="info col-md-2">手机</td>
                            <td class="col-md-2">${(roomUser.userMobile )!''}</td>
                            <td class="info col-md-2">身份证号</td>
                            <td class="col-md-2">${(roomUser.userIdentityNo )!''}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

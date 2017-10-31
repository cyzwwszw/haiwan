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
                    <h3 class="page-header">订单详情</h3>
                </div>
            </div>


            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h4 class="page-header">订单信息</h4>
                </div>
                <div class="col-md-6 column">
                    <table class="table table-bordered table-condensed">
                        <tr>
                            <td class="info">订单号</td>
                            <td>${(buyer.buyerWechatname )!''}</td>
                            <td class="info">订单状态</td>
                            <td>${(buyer.buyerMobile )!''}</td>
                            <td class="info">订单入口</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="info">下单时间</td>
                            <td>${(buyer.buyerName )!''}</td>
                            <td class="info">支付金额</td>
                            <td>${(buyer.buyerAddress )!''}</td>
                            <td class="info">预约时间</td>
                            <td>${(buyer.openId )!''}</td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <h4 class="page-header">产品信息</h4>
                </div>
                <div class="col-md-6 column">
                    <table class="table table-bordered table-condensed">
                        <tr>
                            <td class="info">名称</td>
                            <td>${(product.productName )!''}</td>
                            <td class="info">编号</td>
                            <td>${(product.productId )!''}</td>
                            <td class="info">类型</td>
                            <td>${(product.identityNo )!''}</td>
                        </tr>
                        <tr>
                            <td class="info">位置</td>
                            <td>${(product.productAddress )!''}</td>
                            <td class="info">面积</td>
                            <td>${(product.productArea )!''}</td>
                            <td class="info">规格</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="info">退房时间</td>
                            <td>${(product.productCheckoutTime )!''}</td>
                            <td class="info">早餐</td>
                            <td></td>
                            <td class="info">卫浴</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="info">宽带</td>
                            <td></td>
                            <td class="info">庭院</td>
                            <td></td>
                            <td class="info">电器</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="info">数量</td>
                            <td>${(product.productCount )!''}</td>
                            <td class="info">标准价格</td>
                            <td>${(product.productPrice )!''}</td>
                        </tr>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <h4 class="page-header">用户信息</h4>
                </div>
                <div class="col-md-6 column">
                    <table class="table table-bordered table-condensed">
                        <tr>
                            <td class="info">用户名</td>
                            <td>${(buyer.buyerId )!''}</td>
                            <td class="info">手机</td>
                            <td>${(buyer.buyerMobile )!''}</td>
                            <td class="info">身份证号</td>
                            <td>${(buyer.identityNo )!''}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

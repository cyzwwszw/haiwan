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
                        <a href="#">类目</a>
                    </li>
                    <li>
                        <a href="/haiwan/ace/order/list">订单查询</a>
                    </li>
                    <li class="active">订单明细</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            订单明细
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
                                请查看订单明细
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>



                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <h4 class="page-header">订单信息</h4>
                                    </div>
                                    <div class="col-md-12 column">
                                        <table class="table table-bordered table-condensed">
                                            <tr>
                                                <td class="info col-md-2">订单号</td>
                                                <td class="col-md-2">${(order.orderId )!''}</td>
                                                <td class="info col-md-2">订单状态</td>
                                                <td class="col-md-2">${(order.getOrderStatusEnum().message )!''}</td>
                                                <td class="info col-md-2">订单入口</td>
                                                <td class="col-md-2">微信</td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-2">下单时间</td>
                                                <td class="col-md-2">${(order.createTime )!''}</td>
                                                <td class="info col-md-2">支付金额</td>
                                                <td class="col-md-2">${(order.orderAmount )!''}</td>
                                                <td class="info col-md-2">预约时间</td>
                                                <td class="col-md-2">${(order.orderDateIn?substring(0,10))!''}
                                                    ~${(order.orderDateOut?substring(0,10) )!''}
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
                                                <td class="col-md-2">${(categoryName )!''}</td>
                                            </tr>
                                            <tr>
                                                <td class="info col-md-2">位置</td>
                                                <td class="col-md-2">${(product.productAddress )!''}</td>
                                                <td class="info col-md-2">面积</td>
                                                <td class="col-md-2">${(product.productArea )!''}</td>
                                                <td class="info col-md-2">规格</td>
                                                <td class="col-md-2">${(ProductTypeName )!''}</td>
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
                                        <h4 class="page-header">用户信息</h4>
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
                            <!-- PAGE CONTENT ENDS -->
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


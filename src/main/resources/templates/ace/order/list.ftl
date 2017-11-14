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
                        <a href="#">订单</a>
                    </li>
                    <li class="active">订单查询</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            订单查询
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
                                查询近期的订单情况
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>

                            <!-- PAGE CONTENT ENDS -->
                            <div class="container-fluid">


                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <form id="orderQueryForm" role="form" class="form-horizontal" method="get"
                                              action="/haiwan/ace/order/list?page=0&size=10">

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">手机号</label>
                                                <div class="col-sm-2">
                                                    <input name="buyerPhone" type="text" class="form-control"
                                                           value="${(buyerPhone )!''}"/>
                                                </div>
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
                                        <div class="table-header">
                                            查询结果
                                        </div>
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
                                                <td>${orderDTO.productTypeName}</td>
                                                <td>${orderDTO.buyerMobile}</td>
                                                <td>${orderDTO.orderCount}</td>
                                                <td>${orderDTO.orderAmount}</td>
                                                <td>${orderDTO.createTime}</td>
                                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                                <td>
                                                    <a href="/haiwan/ace/order/index?orderId=${orderDTO.orderId}">详情</a>
                                                </td>
                                                <td>
                                                    <#if orderDTO.getOrderStatusEnum().message == '待使用'>
                                                        <a href="/haiwan/ace/order/finish?orderId=${orderDTO.orderId}">使用</a>
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
                                                <li><a href="/haiwan/ace/order/list?page=${currentPage - 1}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">上一页</a></li>
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
                                                            <li><a href="/haiwan/ace/order/list?page=${index}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">${index}</a>
                                                            </li>
                                                            <li><a>...</a></li>
                                                        <#elseif index == orderPage.getTotalPages()-4 && currentPage &gt; orderPage.getTotalPages()-5&& orderPage.getTotalPages() &gt; 10>
                                                            <li><a>...</a></li>
                                                            <li><a href="/haiwan/ace/order/list?page=${index}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">${index}</a>
                                                            </li>
                                                        <#else>
                                                            <li><a href="/haiwan/ace/order/list?page=${index}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">${index}</a>
                                                            </li>
                                                        </#if>
                                                    </#if>
                                                </#if>

                                            </#list>

                                            <#if currentPage gte orderPage.getTotalPages()>
                                                <li class="disabled"><a href="#">下一页</a></li>
                                            <#else>
                                                <li><a href="/haiwan/ace/order/list?page=${currentPage + 1}&size=${size}&buyerPhone=${(buyerPhone)!''}&orderStatus=${(orderStatus)!''}">下一页</a></li>
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


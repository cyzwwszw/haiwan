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
                        <a href="#">用户</a>
                    </li>
                    <li class="active">用户列表</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            用户
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
                                请添加您要发布的类目
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>


                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <form id="orderQueryForm" role="form" class="form-horizontal" method="get"
                                              action="/haiwan/ace/buyer/list?page=0&size=10">

                                            <div class="form-group">
                                                <label class="col-sm-1 control-label">手机号</label>
                                                <div class="col-sm-2">
                                                    <input name="buyerMobile" type="text" class="form-control"
                                                           value="${(buyerMobile )!''}"/>
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
                                                <th>用户Id</th>
                                                <th>注册日期</th>
                                                <th>联系电话</th>
                                                <th>上次登录时间</th>
                                                <th colspan="3">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <#list buyerPage.content as buyer>
                                            <tr>
                                                <td>${buyer.buyerId}</td>
                                                <td>${buyer.createTime}</td>
                                                <td>${buyer.buyerMobile}</td>
                                                <td>${buyer.updateTime}</td>
                                                <td>
                                                    <a href="/haiwan/ace/buyer/index?buyerId=${buyer.buyerId}">查看详情</a>
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
                                                <li><a href="/haiwan/ace/buyer/list?page=${currentPage - 1}&size=${size}&buyerMobile=${(buyerMobile)!''}">上一页</a></li>
                                            </#if>
                                            <#list 1..buyerPage.getTotalPages() as index>
                                                <#if currentPage == index>
                                                    <li class="active"><a href="#">${index}</a></li>
                                                <#else>
                                                    <li><a href="/haiwan/ace/buyer/list?page=${index}&size=${size}&buyerMobile=${(buyerMobile)!''}">${index}</a></li>
                                                </#if>
                                            </#list>
                                            <#if currentPage gte buyerPage.getTotalPages()>
                                                <li class="disabled"><a href="#">下一页</a></li>
                                            <#else>
                                                <li><a href="/haiwan/ace/buyer/list?page=${currentPage + 1}&size=${size}&buyerMobile=${(buyerMobile)!''}">下一页</a></li>
                                            </#if>
                                            </ul>
                                        </div>
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


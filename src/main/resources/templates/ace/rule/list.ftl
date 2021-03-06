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
                        <a href="#">退票</a>
                    </li>
                    <li class="active">退票查询</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            退票查询
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
                                请维护对应的退票规则，注意不要轻易修改
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>

                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <div class="table-header">
                                            查询结果
                                        </div>
                                        <table class="table table-bordered table-condensed">
                                            <thead>
                                            <tr>
                                                <th>规则ID</th>
                                                <th>规则名称</th>
                                                <th>规则代号</th>
                                                <th>创建时间</th>
                                                <th>修改时间</th>
                                                <th colspan="2">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <#list ruleList as rule>
                                            <tr>
                                                <td>${rule.ruleId}</td>
                                                <td>${rule.ruleName}</td>
                                                <td>${rule.ruleNo}</td>
                                                <td>${rule.createTime}</td>
                                                <td>${rule.updateTime}</td>
                                                <td>
                                                    <a href="/haiwan/ace/rule/index?ruleId=${rule.ruleId}">修改</a>
                                                </td>
                                                <td>
                                                    <a href="/haiwan/ace/rule/delete?ruleId=${rule.ruleId}">删除</a>
                                                </td>
                                            </tr>
                                            </#list>

                                            </tbody>
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


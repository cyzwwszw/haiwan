<html>
<meta charset="UTF-8"/>
<#include "../common/header.ftl">
<body>
<#include "../common/top.ftl">
<div class="main-container" id="main-container">
    <script src="/haiwan/static/js/ruleForm_validate.js"></script>
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
                    <li class="active">退票规则发布</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            退票规则发布
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
                                请添加您要发布的退票规则
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>

                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <form id="ruleForm" role="form" class="form-horizontal" method="post" action="/haiwan/ace/rule/save">
                                            <div class="form-group">
                                                <div class="row clearfix">
                                                <div class="col-md-12 column">
                                                    <h4 class="page-header">基本信息</h4>
                                                </div>
                                                </div>
                                                <label class="col-sm-2 control-label">规则名称</label>
                                                <div class="col-sm-4">
                                                    <input name="ruleName" type="text" class="form-control" value="${(rule.ruleName )!''}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">规则代号</label>
                                                <div class="col-sm-4">
                                                    <input name="ruleNo" type="text" class="form-control" value="${(rule.ruleNo)!''}"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">规则描述</label>
                                                <div class="col-sm-10">
                                                    <textarea name="ruleDescription" class="form-control" rows="3">${(rule.ruleDescription)!''}</textarea>
                                                </div>
                                            </div>
                                            <div class="row clearfix">
                                            <div class="col-md-12 column">
                                                <h4 class="page-header">配置参数</h4>
                                            </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">有效时间</label>
                                                <div class="col-sm-10">

                                                    <label class="radio">
                                                        <input type="radio" name="ruleType"  value="1"
                                                        <#if (rule.ruleType)?? && rule.ruleType == 1>
                                                               checked
                                                        </#if>
                                                        >
                                                        入住前
                                                        <input type="number" name="ruleDay" value="${(rule.ruleDay)!'1'}">天
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" name="ruleType"  value="0"
                                                        <#if (rule.ruleType)?? && rule.ruleType == 0>
                                                               checked
                                                        </#if>
                                                        >
                                                        入住前无限制
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">折扣率</label>
                                                <div class="col-sm-4">
                                                    <input name="ruleDiscount" type="number" class="form-control"
                                                           value="${(rule.ruleDiscount)!'100'}"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"></label>
                                                <div class="col-sm-10">
                                                    <label>"70"表示扣除费用的30%作为服务费，包含转款收费，只返还用户70%</label>
                                                </div>
                                            </div>

                                            <input hidden type="text" name="ruleId" value="${(rule.ruleId)!''}">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button type="submit" class="btn btn-default">提交</button>
                                            </div>
                                        </form>
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


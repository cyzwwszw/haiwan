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
                    <h3 class="page-header">退票查询</h3>
                </div>
                <div class="col-md-12 column">
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
                                <a href="/haiwan/backend/rule/index?ruleId=${rule.ruleId}">修改</a>
                            </td>
                            <td>
                                <a href="/haiwan/backend/rule/delete?ruleId=${rule.ruleId}">删除</a>
                            </td>
                        </tr>
                        </#list>

                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

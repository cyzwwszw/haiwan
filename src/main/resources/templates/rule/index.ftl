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
                    <h3 class="page-header">退票维护</h3>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-md-6 column">
                    <form role="form" class="form-horizontal" method="post" action="/haiwan/backend/rule/save">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">规则名称</label>
                            <div class="col-sm-10">
                                <input name="ruleName" type="text" class="form-control" value="${(rule.ruleName )!''}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">规则代号</label>
                            <div class="col-sm-10">
                                <input name="ruleNo" type="text" class="form-control" value="${(rule.ruleNo)!''}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">规则描述</label>
                            <div class="col-sm-10">
                                <textarea name="ruleDescription" class="form-control" rows="3">${(rule.ruleDescription)!''}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">规则描述</label>
                            <div class="col-sm-10">

                                <label class="radio">
                                    <input type="radio" name="ruleType"  value="1"
                                    <#if (rule.ruleType)?? && rule.ruleType == 1>
                                           checked
                                    </#if>
                                    >
                                    入住前
                                    <input type="number" name="ruleDay" value="${(rule.ruleDay)!''}">天
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
                            <div class="col-sm-10">
                                <input name="ruleDiscount" type="number" class="form-control"
                                       value="${(rule.ruleDiscount)!''}"/>
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
    </div>
</div>

</body>
</html>

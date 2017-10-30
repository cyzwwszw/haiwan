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
                    <h3 class="page-header">用户详情</h3>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h4 class="page-header">基本信息</h4>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <tr>
                            <td class="info">昵称</td>
                            <td>${(buyer.buyerWechatname )!''}</td>
                            <td class="info">联系电话</td>
                            <td>${(buyer.buyerMobile )!''}</td>
                            <td class="info">头像</td>
                            <td><img height="80" src="${(buyer.buyerWechatpic )!''}"></td>
                        </tr>
                        <tr>
                            <td class="info">实名</td>
                            <td>${(buyer.buyerName )!''}</td>
                            <td class="info">居住地址</td>
                            <td>${(buyer.buyerAddress )!''}</td>
                            <td class="info">微信号</td>
                            <td>${(buyer.openId )!''}</td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <h4 class="page-header">账户信息</h4>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <tr>
                            <td class="info">账户号</td>
                            <td>${(buyer.buyerId )!''}</td>
                            <td class="info">身份证绑定</td>
                            <td>${(buyer.isIdentity )!''}</td>
                            <td class="info">身份证号</td>
                            <td>${(buyer.identityNo )!''}</td>
                        </tr>
                        <tr>
                            <td class="info">注册日期</td>
                            <td>${(buyer.createTime )!''}</td>
                            <td class="info">注册渠道</td>
                            <td>微信</td>
                            <td class="info">注册方式</td>
                            <td>微信</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

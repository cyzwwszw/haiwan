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
                            <td class="info col-md-2">昵称</td>
                            <td class="col-md-2">${(wechatInfo.nickName )!''}</td>
                            <td class="info col-md-2">联系电话</td>
                            <td class="col-md-2">${(buyer.buyerMobile )!''}</td>
                            <td class="info col-md-2">头像</td>
                            <td class="col-md-2"><img height="80" src="${(wechatInfo.headimgUrl )!''}"></td>
                        </tr>
                        <tr>
                            <td class="info col-md-2">实名</td>
                            <td class="col-md-2">${(buyer.buyerName )!''}</td>
                            <td class="info col-md-2">居住地址</td>
                            <td class="col-md-2">${(buyer.buyerAddress )!''}</td>
                            <td class="info col-md-2">微信号</td>
                            <td class="col-md-2">${(wechatInfo.openId )!''}</td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <h4 class="page-header">账户信息</h4>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <tr>
                            <td class="info col-md-2">账户号</td>
                            <td class="col-md-2">${(buyer.buyerId )!''}</td>
                            <td class="info col-md-2">身份证绑定</td>
                            <td class="col-md-2">${(buyer.isIdentity )!''}</td>
                            <td class="info col-md-2">身份证号</td>
                            <td class="col-md-2">${(buyer.identityNo )!''}</td>
                        </tr>
                        <tr>
                            <td class="info col-md-2">注册日期</td>
                            <td class="col-md-2">${(buyer.createTime )!''}</td>
                            <td class="info col-md-2">注册渠道</td>
                            <td class="col-md-2">微信</td>
                            <td class="info col-md-2">注册方式</td>
                            <td class="col-md-2">微信</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

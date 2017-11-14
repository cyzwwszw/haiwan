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
                    <li>
                        <a href="/haiwan/ace/buyer/list">用户列表</a>
                    </li>
                    <li class="active">用户明细</li>
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
                                用户明细相关信息
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
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


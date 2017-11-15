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
                        <li class="active">首页</li>
                    </li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            欢迎使用海湾系统
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
                                海湾运微信公众号营情况
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>


                            <div class="row">
                                <div class="space-6"></div>

                                <div class="col-sm-7 infobox-container">
                                    <div class="infobox infobox-green  ">
                                        <div class="infobox-icon">
                                            <i class="icon-user"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${(userCount )!''}人</span>
                                            <div class="infobox-content">用户数</div>
                                        </div>
                                        <div class="stat stat-success"></div>
                                    </div>

                                    <div class="infobox infobox-blue  ">
                                        <div class="infobox-icon">
                                            <i class="icon-shopping-cart"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${(orderCount )!''}单</span>
                                            <div class="infobox-content">已完成订单数</div>
                                        </div>

                                        <div class="stat stat-success"></div>
                                    </div>

                                    <div class="infobox infobox-pink  ">
                                        <div class="infobox-icon">
                                            <i class="icon-money"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${(orderMoney )!''}元</span>
                                            <div class="infobox-content">交易总额</div>
                                        </div>
                                        <div class="stat stat-success"></div>
                                    </div>
                                </div>

                                <div class="vspace-sm"></div>
                                <!--
                                                                <div class="col-sm-5">
                                                                    <div class="widget-box">
                                                                        <div class="widget-header widget-header-flat widget-header-small">
                                                                            <h5>
                                                                                <i class="icon-signal"></i>
                                                                                增量
                                                                            </h5>

                                                                            <div class="widget-toolbar no-border">
                                                                                <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown">
                                                                                    本周
                                                                                    <i class="icon-angle-down icon-on-right bigger-110"></i>
                                                                                </button>

                                                                                <ul class="dropdown-menu pull-right dropdown-125 dropdown-lighter dropdown-caret">
                                                                                    <li class="active">
                                                                                        <a href="#" class="blue">
                                                                                            <i class="icon-caret-right bigger-110">&nbsp;</i>
                                                                                            本周
                                                                                        </a>
                                                                                    </li>

                                                                                    <li>
                                                                                        <a href="#">
                                                                                            <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                                                                            上周
                                                                                        </a>
                                                                                    </li>

                                                                                    <li>
                                                                                        <a href="#">
                                                                                            <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                                                                            本月
                                                                                        </a>
                                                                                    </li>

                                                                                    <li>
                                                                                        <a href="#">
                                                                                            <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                                                                            上月
                                                                                        </a>
                                                                                    </li>
                                                                                </ul>
                                                                            </div>
                                                                        </div>

                                                                        <div class="widget-body">
                                                                            <div class="widget-main">
                                                                                <div id="piechart-placeholder"></div>

                                                                                <div class="hr hr8 hr-double"></div>

                                                                                <div class="clearfix">
                                                                                    <div class="grid3">
                                                                                            <span class="grey">
                                                                                                <i class="icon-user icon-2x blue"></i>
                                                                                            </span>
                                                                                        <h4 class="bigger pull-right">1,255</h4>
                                                                                    </div>

                                                                                    <div class="grid3">
                                                                                            <span class="grey">
                                                                                                <i class="icon-shopping-cart icon-2x purple"></i>
                                                                                            </span>
                                                                                        <h4 class="bigger pull-right">941</h4>
                                                                                    </div>

                                                                                    <div class="grid3">
                                                                                            <span class="grey">
                                                                                                <i class="icon-money icon-2x red"></i>
                                                                                            </span>
                                                                                        <h4 class="bigger pull-right">1,050</h4>
                                                                                    </div>
                                                                                </div>
                                                                            </div><!-- /widget-main -->
                                        </div><!-- /widget-body -->
                                    </div><!-- /widget-box -->
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

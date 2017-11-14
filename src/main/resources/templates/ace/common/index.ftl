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

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
                        <a href="#">产品</a>
                    </li>
                    <li>
                        <a href="#">产品发布</a>
                    </li>
                    <li>
                        <a href="#">产品图片</a>
                    </li>
                    <li class="active">产品项目</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            类目发布
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
                                        <div class="row clearfix">
                                            <div class="col-md-12 column">
                                                <h3 class="page-header">添加/修改 要求须知</h3>
                                            </div>
                                        </div>
                                        <form id="itemForm" role="form" class="form-horizontal" method="post"
                                              action="/haiwan/ace/product/saveItem">
                                            <div class="row clearfix">
                                                <div class="col-md-7 column">
                                                    <div id="panel" class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <label style="font-size: 16px">要求须知</label>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div class="form-group">
                                                                <label class="col-sm-2 control-label">名称</label>
                                                                <div class="col-sm-4">
                                                                    <input hidden type="text" name="itemId"
                                                                           value="${(itemForm.itemId)!''}"/>
                                                                    <input name="itemName" type="text" class="form-control limited-input"
                                                                           maxlength="10"
                                                                           value="${(itemForm.itemName)!''}"/>
                                                                </div>
                                                            </div>
                                                            <div class="hr-line-dashed"></div>
                                                            <div class="form-group">
                                                                <label class="col-sm-2 control-label">描述说明</label>
                                                                <div class="col-sm-9">
                                            <textarea class="autosize-transition limited-textarea form-control"
                                                      maxlength="300"
                                                      name="itemDescription"
                                                      style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;">${(itemForm.itemDescription)!''}</textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-md-12 column">
                                                        <input hidden type="text" name="productId" value="${(itemForm.productId)!''}">
                                                        <div class="col-sm-offset-1 col-sm-1">
                                                            <button type="submit" class="btn btn-primary">保存</button>
                                                        </div>
                                                        <div class="col-sm-offset-1 col-sm-1">
                                                            <button type="button" class="btn btn-primary"
                                                                    onclick='location.href="/haiwan/ace/product/toItemList?productId=${(itemForm.productId)!''}"'>
                                                                返回
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
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
<script src="/haiwan/static/js/jquery.autosize.min.js" type="text/javascript"></script>
<script src="/haiwan/static/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script>
    $(function () {
        $("#itemForm").validate({
            errorElement: "em",
            errorPlacement: function (error, element) {
                if (element.is(':radio') || element.is(':checkbox')) {
                    error.appendTo(element.closest("div"));
                } else {
                    error.appendTo(element.closest("div"));
                }
            },
            //设置验证触发事件
            focusInvalid: true,
            rules: {
                itemName: {required: true},
                itemDescription: {required: true},
            },
            messages: {
                itemName: {required: "请输入名称！"},
                itemDescription: {required: "请输入描述说明！"},
            }
        });

        $('textarea.autosize-transition').autosize({
            append: "\n"
        });
        $('textarea.limited-textarea').inputlimiter({
            remText: '剩余字符数为：%n...',
            limitText: '最大允许字符数为：%n.'
        });
        $('input.limited-input').inputlimiter({
            remText: '剩余字符数为：%n...',
            limitText: '最大允许字符数为：%n.'
        });
    });
</script>
</body>
</html>


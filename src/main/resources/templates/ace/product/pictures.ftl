<html>
<meta charset="UTF-8"/>
<#include "../common/header.ftl">
<link href="/haiwan/static/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
<body>
<#include "../common/top.ftl">
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
    <#include "../common/menu.ftl">


        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
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
                        <a href="/haiwan/ace/product/index?productId=${(productId)!''}">产品发布</a>
                    </li>
                    <li class="active">产品图片</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            产品图片
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
                                请添加您的产品图片
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>

                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <div class="row clearfix">
                                            <div class="col-md-12 column">
                                                <form role="form" class="form-horizontal" method="post"
                                                      action="/haiwan/ace/product/savePeictures"
                                                      enctype="multipart/form-data">
                                                    <div class="form-group">
                                                        <div class="col-md-12 column">
                                                            <input id="file-zh" name="files" type="file" multiple>
                                                        </div>
                                                    </div>
                                                    <div class="hr-line-dashed"></div>
                                                    <div class="form-group">
                                                        <div class="col-md-12 column">
                                                            <input hidden type="text" id="productId" name="productId"
                                                                   value="${(productId)!''}">
                                                        <#--<div class="col-sm-offset-1 col-sm-1">-->
                                                        <#--<button type="button" class="btn btn-primary"-->
                                                        <#--onclick='location.href="/haiwan/ace/product/index?productId=${(productId)!''}"'>-->
                                                        <#--上一步-->
                                                        <#--</button>-->
                                                        <#--</div>-->
                                                            <div class="col-sm-1">
                                                                <button type="submit" class="btn btn-primary">保存
                                                                </button>
                                                            </div>
                                                            <div class="col-sm-1">
                                                                <button type="button" class="btn btn-primary"
                                                                        onclick='location.href="/haiwan/ace/product/toItemList?productId=${(productId)!''}"'>
                                                                    下一步
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
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
<script src="/haiwan/static/fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="/haiwan/static/fileinput/js/locales/zh.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        Array previewPath = new Array();
        Array previewConfig = new Array();
        var path = "${(path)!''}";
        var photos = ${(photos)!''};

        $.each(photos, function (i, photo) {
            previewPath.push(path + photo.photoUrl);
            var tjson = {
                caption: "Picture-" + (i+1) + ".jpg", // 展示的文件名
                width: '120px',
                url: '/haiwan/ace/product/deletePeictures', // 删除url
                key: photo.photoId,  //删除是Ajax向后台传递的参数
                extra: {id: photo.photoId}
            };
            previewConfig.push(tjson);
        });

        $('#file-zh').fileinput({
            language: 'zh',
            uploadAsync: false,  //同步上传
            allowedFileExtensions: ['jpg', 'png'],
            maxFileCount: 50,
            maxFileSize: 1000,
            textEncoding: 'UTF-8',
            showUpload: false, //是否显示上传按钮
            validateInitialCount: true, //验证初始计数
            overwriteInitial: false,  //不覆盖已存在的图片
            initialPreviewAsData: true,
            initialPreview: previewPath,
            initialPreviewConfig: previewConfig
        });

    });
</script>
</body>
</html>


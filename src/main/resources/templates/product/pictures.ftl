<html>
<meta charset="UTF-8"/>
<#include "../common/header.ftl">
<link href="/haiwan/static/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h1 class="page-header">产品发布</h1>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <h3 class="page-header">图片上传</h3>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <form role="form" class="form-horizontal" method="post"
                                  action="/haiwan/backend/product/savePeictures" enctype="multipart/form-data">
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
                                        <div class="col-sm-offset-1 col-sm-1">
                                            <button type="button" class="btn btn-primary"
                                                    onclick='location.href="/haiwan/backend/product/index?productId=${(productId)!''}"'>
                                                上一步
                                            </button>
                                        </div>
                                        <div class="col-sm-offset-1 col-sm-1">
                                            <button type="submit" class="btn btn-primary">保存</button>
                                        </div>
                                        <div class="col-sm-offset-1 col-sm-1">
                                            <button type="button" class="btn btn-primary"
                                                    onclick='location.href="/haiwan/backend/product/toItemList?productId=${(productId)!''}"'>
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
    </div>
</div>

<script src="/haiwan/static/fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="/haiwan/static/fileinput/js/locales/zh.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        file_input();
    });

    function file_input() {
        Array
        path = new Array();
        Array
        con = new Array();

        //初始化将测试集包含的用例存在数组里面
    <#if photos??>
        <#list photos as photo>
            path.push("${path}${photo.photoUrl}");
            var tjson = {
                caption: "Picture-${photo_index + 1}.jpg", // 展示的文件名
                width: '120px',
                url: '/haiwan/backend/product/deletePeictures', // 删除url
                key: "${photo.photoId}",  //删除是Ajax向后台传递的参数
                extra: {id: "${photo.photoId}"}
            };
            con.push(tjson);
        </#list>
    </#if>

        $('#file-zh').fileinput({
            language: 'zh',
            uploadAsync: false,  //同步上传
            allowedFileExtensions: ['jpg', 'png'],
            maxFileCount: 50,
            maxFileSize: 1000,
            textEncoding: 'UTF-8',
            showRemove: true,  //是否显示删除按钮
            showUpload: false, //是否显示上传按钮
            overwriteInitial: false,  //不覆盖已存在的图片
            initialPreviewAsData: true,
            initialPreview: path,
            initialPreviewConfig: con
        });
    }
</script>
</body>
</html>

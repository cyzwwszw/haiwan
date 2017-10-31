<html>
<meta charset="UTF-8"/>
<#include "../common/header.ftl">
<link rel="stylesheet" type="text/css" href="/haiwan/fileinput/css/default.css">
<link href="/haiwan/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
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
                    <div class="col-md-12 column">
                        <h3 class="page-header">图片上传</h3>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12 column">
                            <form enctype="multipart/form-data">
                                <input id="file-zh" name="files" type="file" multiple>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-12 column">
                        <form role="form" class="form-horizontal" method="post"
                              action="/haiwan/backend/product/savePictures">
                            <input hidden type="text" id="productId" name="productId" value="${(productId)!''}">
                            <input hidden type="text" id="fileStr" name="fileStr" value="">
                            <button type="submit" class="btn btn-primary" id="save" disabled>保存，下一步
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/haiwan/fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="/haiwan/fileinput/js/locales/zh.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        $('#file-zh').fileinput({
            language: 'zh',
            uploadUrl: '/haiwan/backend/product/upload',
            allowedFileExtensions: ['jpg', 'png'],
            maxFileCount: 50,
            textEncoding: 'UTF-8'
        }).on("fileuploaded", function (event, data, previewId, index) {
            var data = data.response;
            if (data.path !== null || data.path !== undefined || data.path !== '') {
                if ($("#fileStr").val() !== null || $("#fileStr").val() !== undefined || $("#fileStr").val() !== '') {
                    $("#fileStr").attr('value', $("#fileStr").val() + "," + data.path);
                } else {
                    $("#fileStr").attr('value', data.path);
                }
                console.info($("#fileStr").val());
                $("#save").removeAttr("disabled");
            }
        });
    });

</script>
</body>
</html>

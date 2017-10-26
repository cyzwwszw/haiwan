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
                    <h3 class="page-header">产品发布</h3>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <form role="form" class="form-horizontal" method="post" enctype="multipart/form-data" action="/haiwan/backend/product/save">
                        <div class="col-md-12 column">
                            <h4 class="page-header">图片上传</h4>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">图片展现</label>
                            <div class="col-sm-6">
                                <img src="" height="200">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">上传图片</label>
                            <div class="col-sm-4">
                                <input name="fileUpload" type="file">
                            </div>
                        </div>

                        <input hidden type="text" name="productId" value="${(product.productId)!''}">
                        <div class="col-sm-offset-2 col-sm-4">
                            <button type="submit" class="btn btn-default">保存，下一步</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

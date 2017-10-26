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
                            <h4 class="page-header">要求须知</h4>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-4">
                                <input name="productName" type="text" class="form-control"
                                       value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">描述说明</label>
                            <div class="col-sm-10">
                                <textarea name="productDescription" class="form-control"
                                          rows="3"></textarea>
                            </div>
                        </div>

                        <input hidden type="text" name="productId" value="">
                        <div class="col-sm-offset-2 col-sm-4">
                            <button type="submit" class="btn btn-default">发布</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

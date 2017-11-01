<html>
    <#include "../common/header.ftl">
    <#--<script src="/haiwan/static/js/categoryForm_validate.js"></script>-->
    <body>
    <div id="wrapper" class="toggled">

        <#--边栏sidebar-->
        <#include "../common/nav.ftl">
        <#--主要内容content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <h3 class="page-header">类目维护</h3>
                    </div>
                </div>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form id="categoryForm" role="form" class="form-horizontal" method="post" action="/haiwan/backend/category/save">
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-4">
                                    <input name="categoryName" type="text" class="form-control" value="${(category.categoryName )!''}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">编号</label>
                                <div class="col-sm-4">
                                    <input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}"/>
                                </div>
                            </div>
                            <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">

                            <div class="col-sm-offset-2 col-sm-4">
                             <button type="submit" class="btn btn-default">提交</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </body>
</html>

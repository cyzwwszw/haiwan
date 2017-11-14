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
                        <a href="#">类目</a>
                    </li>
                    <li class="active">类目发布</li>
                </ul>

                <div class="page-content">

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
                                        <h3 class="page-header">类目发布</h3>
                                    </div>
                                </div>

                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <form id="categoryForm" role="form" class="form-horizontal" method="post" action="/haiwan/ace/category/save">
                                            <div class="form-group">
                                                <label  class="col-sm-2 control-label">名称</label>
                                                <div class="col-sm-4">
                                                    <input name="categoryName" type="text" class="form-control" value="${(category.categoryName )!''}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label  class="col-sm-2 control-label">排序号</label>
                                                <div class="col-sm-4">
                                                    <input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">隶属于</label>
                                                <div class="col-sm-4">
                                                    <select name="parentId" class="form-control">
                                                        <option value="">无</option>
                                                    <#list categoryList as categoryOne>
                                                        <option value="${categoryOne.categoryId}"
                                                            <#if (category.parentId)?? && category.parentId == categoryOne.categoryId>
                                                                selected
                                                            </#if>
                                                        >
                                                        ${categoryOne.categoryName}
                                                        </option>
                                                    </#list>
                                                    </select>
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


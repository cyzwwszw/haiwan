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
                        <a href="/haiwan/ace/product/toPeictures?productId=${(productId)!''}">产品图片</a>
                    </li>
                    <li class="active">产品项目</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            产品项目
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
                                请添加您要产品的须知内容
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>


                            <div class="container-fluid">
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <button type="button" class="btn btn-primary"
                                                onclick="tianjia()">
                                            添加
                                        </button>
                                        <table class="table table-bordered table-condensed table-hover">
                                            <colgroup>
                                                <col style="">
                                                <col style="">
                                                <col style="width: 50%">
                                                <col style="">
                                                <col style="">
                                            </colgroup>
                                            <thead>
                                            <tr>
                                                <th>编号</th>
                                                <th>名称</th>
                                                <th>描述说明</th>
                                                <th>发布时间</th>
                                                <th colspan="2">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <#list items as item>
                                            <tr>
                                                <td>${item.itemId}</td>
                                                <td>${item.itemName}</td>
                                                <td>${item.itemDescription}</td>
                                                <td>${item.createTime}</td>
                                                <td>
                                                    <a href="/haiwan/ace/product/toItem?itemId=${item.itemId}&productId=${item.productId}">修改</a>
                                                </td>
                                                <td>
                                                    <a href="/haiwan/ace/product/deleteItem?itemId=${item.itemId}&productId=${item.productId}">删除</a>
                                                </td>
                                            </tr>
                                            </#list>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-md-12 column">
                                        <div class="col-sm-1">
                                            <button type="submit" class="btn btn-primary"
                                                    onclick='location.href="/haiwan/ace/product/list"'>
                                                发布
                                            </button>
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
<script>
    function tianjia() {
        var itemsSize;
    <#if itemsSize??>
        itemsSize = "${itemsSize}";
    </#if>
        if (itemsSize < 5) {
            location.href = "/haiwan/ace/product/toItem?productId=${(productId)!''}"
        } else {
            alert("要求须知只能添加五条！");
        }
    }
</script>
</body>
</html>


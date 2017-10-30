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
                    <h1 class="page-header">产品发布</h1>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <h1 class="page-header">要求须知</h1>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <form class="form-horizontal">
                            <div id="quanbu" class="col-md-6">
                                <div id="panel" class="panel panel-default">
                                    <div class="panel-heading">
                                        <label style="font-size: 16px">项目项</label>
                                        <label style="float: right">
                                            <button class="btn btn-primary btn-xs">添加</button>
                                            <button class="btn btn-danger btn-xs" style="display: none;">删除</button>
                                        </label>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">名称</label>
                                            <div class="col-sm-4">
                                                <input name="productName" type="text" class="form-control"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">描述说明</label>
                                            <div class="col-sm-10">
                                            <textarea id="form-field-11"
                                                      class="autosize-transition limited form-control"
                                                      maxlength="50"
                                                      style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                    <div class="col-md-12 column">
                                        <input hidden type="text" name="productId" value="${(product.productId)!''}">
                                        <button type="submit" class="btn btn-primary">保存，下一步</button>
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
<script src="/haiwan/js/jquery.autosize.min.js" type="text/javascript"></script>
<script src="/haiwan/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script>
    $('textarea[class*=autosize]').autosize({append: "\n"});
    $('textarea.limited').inputlimiter({
        remText: '剩余字符数为：%n...',
        limitText: '最大允许字符数为：%n.'
    });

    function tianjian() {
        var panel = $(".panel");
        
        $("#quanbu").append();
    }

    function shenchu() {

    }
</script>
</body>
</html>

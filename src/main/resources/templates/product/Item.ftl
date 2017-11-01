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
                            <h3 class="page-header">要求须知</h3>
                        </div>
                    </div>
                    <form role="form" class="form-horizontal" method="post"
                          action="/haiwan/backend/product/saveItem">
                        <div class="row clearfix">
                            <div class="col-md-6 column">
                                <div id="panel" class="panel panel-default">
                                    <div class="panel-heading">
                                        <label style="font-size: 16px">要求须知一</label>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">名称</label>
                                            <div class="col-sm-4">
                                                <input hidden type="text" name="itemId_1"
                                                       value="${(itemForm.itemId_1)!''}"/>
                                                <input name="itemName_1" type="text" class="form-control"
                                                       maxlength="10"
                                                       value="${(itemForm.itemName_1)!''}"/>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">描述说明</label>
                                            <div class="col-sm-10">
                                            <textarea id="form-field-11"
                                                      class="autosize-transition limited-1 form-control"
                                                      maxlength="300"
                                                      name="itemDescription_1"
                                                      style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;">${(itemForm.itemDescription_1)!''}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 column">
                                <div id="panel" class="panel panel-default">
                                    <div class="panel-heading">
                                        <label style="font-size: 16px">要求须知二</label>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">名称</label>
                                            <div class="col-sm-4">
                                                <input hidden type="text" name="itemId_2" value="${(item.itemId_2)!''}">
                                                <input name="itemName_2" type="text" class="form-control"
                                                       maxlength="10"
                                                       value="${(itemForm.itemName_2)!''}"/>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">描述说明</label>
                                            <div class="col-sm-10">
                                            <textarea id="form-field-11"
                                                      class="autosize-transition limited-2 form-control"
                                                      maxlength="300"
                                                      name="itemDescription_2"
                                                      style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;">${(itemForm.itemDescription_2)!''}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 column">
                                <div id="panel" class="panel panel-default">
                                    <div class="panel-heading">
                                        <label style="font-size: 16px">要求须知三</label>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">名称</label>
                                            <div class="col-sm-4">
                                                <input hidden type="text" name="itemId_3" value="${(item.itemId_3)!''}">
                                                <input name="itemName_3" type="text" class="form-control"
                                                       maxlength="10"
                                                       value="${(itemForm.itemName_3)!''}"/>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">描述说明</label>
                                            <div class="col-sm-10">
                                            <textarea id="form-field-11"
                                                      class="autosize-transition limited-3 form-control"
                                                      maxlength="300"
                                                      name="itemDescription_3"
                                                      style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;">${(itemForm.itemDescription_3)!''}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 column">
                                <div id="panel" class="panel panel-default">
                                    <div class="panel-heading">
                                        <label style="font-size: 16px">要求须知四</label>
                                    </div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">名称</label>
                                            <div class="col-sm-4">
                                                <input hidden type="text" name="itemId_4" value="${(item.itemId_4)!''}">
                                                <input name="itemName_4" type="text" class="form-control"
                                                       maxlength="10"
                                                       value="${(item.itemName_4)!''}"/>
                                            </div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">描述说明</label>
                                            <div class="col-sm-10">
                                            <textarea id="form-field-11"
                                                      class="autosize-transition limited-4 form-control"
                                                      maxlength="300"
                                                      name="itemDescription_4"
                                                      style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 52px;">${(itemForm.itemDescription_4)!''}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 column" style="text-align: center">
                                    <input hidden type="text" name="productId" value="${(itemForm.productId)!''}">
                                    <button type="submit" class="btn btn-primary">发布</button>
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
<script src="/haiwan/static/js/jquery.autosize.min.js" type="text/javascript"></script>
<script src="/haiwan/static/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script>
    $('textarea[class*=autosize]').autosize({append: "\n"});
    $('textarea.limited-1').inputlimiter({
        remText: '剩余字符数为：%n...',
        limitText: '最大允许字符数为：%n.'
    });
    $('textarea.limited-2').inputlimiter({
        remText: '剩余字符数为：%n...',
        limitText: '最大允许字符数为：%n.'
    });
    $('textarea.limited-3').inputlimiter({
        remText: '剩余字符数为：%n...',
        limitText: '最大允许字符数为：%n.'
    });
    $('textarea.limited-4').inputlimiter({
        remText: '剩余字符数为：%n...',
        limitText: '最大允许字符数为：%n.'
    });

    $('input[name^="itemName_1"]').inputlimiter({
        remText: '',
        limitText: '最大允许字符数为：%n.'
    });
    $('input[name^="itemName_2"]').inputlimiter({
        remText: '',
        limitText: '最大允许字符数为：%n.'
    });
    $('input[name^="itemName_3"]').inputlimiter({
        remText: '',
        limitText: '最大允许字符数为：%n.'
    });
    $('input[name^="itemName_4"]').inputlimiter({
        remText: '',
        limitText: '最大允许字符数为：%n.'
    });
</script>
</body>
</html>

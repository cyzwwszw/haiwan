<html>
<meta charset="UTF-8"/>
<#include "../common/header.ftl">
<style>
    body {
        background-color: #f0f0f0;
    }

    .center {
        font-size: 36px;
        height: 100px;
        text-align: center;
        margin-left: auto;
        margin-right: auto;
        margin-top: 200px;
    }
</style>
<body>
<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="row">
                        <div class="aler center">
                            欢迎使用 <strong class="green">海湾国家公园后台管理系统
                            <small>(v1.0)</small>
                        </strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

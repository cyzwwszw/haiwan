<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/haiwan/static/css/login.css">

    <script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="/haiwan/fileinput/js/jquery-1.11.0.min.js"><\/script>')</script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <!--[if IE]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="htmleaf-container">
    <header class="htmleaf-header">
        <h1>海湾国家公园<span>后台登录</span></h1>
    </header>
    <div class="demo form-bg" style="padding: 20px 0;">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form role="form" class="form-horizontal" method="post"
                          action="/haiwan/ace/adminUser/doLogin" onsubmit="return check()">
                        <span class="heading"></span>
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="用户名" title="请输入用户名" value="">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group help">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="密　码" title="请输入密码" value="">
                            <i class="fa fa-lock"></i>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default">登录</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#name").focus(function () {
            hide('name');
        });

        $("#name").blur(function () {
            show('name');
        });

        $("#password").focus(function () {
            hide('password');
        });

        $("#password").blur(function () {
            show('password');
        });
    });


    function show(id) {
        var text = $("#" + id).val();
        if (text.length < 1) {
            $("#" + id).tooltip('show');
            $("#" + id).css("border", "1.5px solid red");
        }
    }

    function hide(id) {
        $("#" + id).tooltip('hide');
        $("#" + id).css("border", "1px solid #ccc");
    }

    function check() {
        var falg = true;
        if ($("#name").val() == null || $("#name").val() == undefined || $("#name").val() == "") {
            show('name');
            falg = false;
        }
        if ($("#password").val() == null || $("#password").val() == undefined || $("#password").val() == "") {
            show('password');
            falg = false;
        }
        console.info(falg);
        return falg;
    }
</script>
</html>
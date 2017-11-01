<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="/haiwan/backend/adminUser/toIndex">后台管理系统</a>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i>欢迎&nbsp;&nbsp;<#if Session.admin?exists>${Session.admin.name}</#if>
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">--------------------</li>
                <li><a href="/haiwan/backend/adminUser/logout"><i class="fa fa-fw fa-list-alt"></i>登出</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 产品 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">--------------------</li>
                <li><a href="/haiwan/backend/product/index">产品发布</a></li>
                <li><a href="/haiwan/backend/product/list">产品管理</a></li>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 订单 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">--------------------</li>
                <li><a href="/haiwan/backend/order/list">订单查询</a></li>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 用户 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">--------------------</li>
                <li><a href="/haiwan/backend/buyer/list">用户管理</a></li>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 退票 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">--------------------</li>
                <li><a href="/haiwan/backend/rule/index">退票维护</a></li>
                <li><a href="/haiwan/backend/rule/list">退票查询</a></li>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">--------------------</li>
                <li><a href="/haiwan/backend/category/index">类目维护</a></li>
                <li><a href="/haiwan/backend/category/list">类目查询</a></li>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                <i class="fa fa-fw fa-plus"></i> 对账 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">--------------------</li>
                <li><a href="/haiwan/backend/transaction/list">对账查询</a></li>
            </ul>
        </li>
    </ul>
</nav>
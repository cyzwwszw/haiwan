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
                    <li class="active">类目查询</li>
                </ul>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            类目查询&amp; 维护
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
                                删除类目，必须保证所有该类目的产品订单都完结
                                <button class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                            </div>
                            <div class="jqGrid_wrapper">
                                <table id="grid-table"></table>
                                <div id="grid-pager"></div>
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

    jQuery("#grid-table").jqGrid({
        url:'/haiwan/ace/category/listQuery',
        datatype: "json",
        width:"100%",
        autowidth:true,
        height:"100%",
        colNames:['类目id','名字', '类目编号', '创建时间','修改时间',''],
        colModel:[
            {name:'categoryId',index:'categoryId', width:55},
            {name:'categoryName',index:'categoryName', width:90},
            {name:'categoryType',index:'categoryType', width:100},
            {name:'createTime',index:'createTime', width:80, align:"right", formatter: formatDate},
            {name:'updateTime',index:'updateTime', width:80, align:"right", formatter: formatDate},
            {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                formatter:'actions',
                formatoptions:{
                    keys:true,
                    delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
                    editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback}
                }
            }
        ],
        sortname: 'categoryType',
        viewrecords: true,
        sortorder: "desc",
        multiselect: false,
        subGrid : true,
        subGridRowExpanded: showChildGrid,
        caption: "类目查询"
    });
    jQuery("#grid-table").jqGrid('navGrid','#grid-pager',{add:false,edit:false,del:false});


    function showChildGrid(parentRowID, parentRowKey) {
        var childGridID = parentRowID + "_table";
        var childGridPagerID = parentRowID + "_pager";

        var rowData = $("#grid-table").jqGrid('getRowData',parentRowKey);
        console.log(rowData);
        $('#' + parentRowID).append(' <div class="jqGrid_wrapper"><table class="child" id=' + childGridID + '></table><div id=' + childGridPagerID + ' class=scroll></div></div>');
        $("#" + childGridID).jqGrid({
            url: '/haiwan/ace/category/listQuery?categoryId=' +rowData.categoryId,
            mtype: "GET",
            datatype: "json",
            page: 1,
            colNames:['类目id','名字', '类目编号', '创建时间','修改时间',''],
            colModel:[
                {name:'categoryId',index:'categoryId', width:55},
                {name:'categoryName',index:'categoryName', width:90},
                {name:'categoryType',index:'categoryType', width:100},
                {name:'createTime',index:'createTime', width:80, align:"right", formatter: formatDate},
                {name:'updateTime',index:'updateTime', width:80, align:"right", formatter: formatDate},
                {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                    formatter:'actions',
                    formatoptions:{
                        keys:true,
                        delOptions:{
                            recreateForm: true,
                            beforeShowForm:beforeDeleteCallback,
                            url:"/haiwan/ace/category/listQuery"}
                       // editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback}
                    }
                }
            ],
            loadonce: true,
            width: '100%',
            height: '100%',
            autowidth:true
        });

    }


    function beforeDeleteCallback(e) {
        var form = $(e[0]);
        if(form.data('styled')) return false;

        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_delete_form(form);

        form.data('styled', true);
    }

    function style_delete_form(form) {
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
        buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
        buttons.eq(1).prepend('<i class="icon-remove"></i>')
    }

    function formatDate(cellvalue, options, rowObject) {
        return (new Date(cellvalue)).format("yyyy-MM-dd hh:mm:ss");
    }


    $(window).bind("resize", function() {
        var width = $(".jqGrid_wrapper").width();
        $("#grid-table").setGridWidth(width);
        $(".child").setGridWidth(width)
    });
</script>

<script>
    Date.prototype.format = function(fmt)
    { //author: meizz
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
</script>
</body>
</html>

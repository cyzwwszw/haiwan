$().ready(function() {
    $("#categoryForm").validate({
        errorElement: "em",
        errorPlacement: function (error, element) {
            if (element.is(':radio') || element.is(':checkbox')) {
                var eid = element.attr('name');
                error.appendTo(element.parent());
            } else {
                //error.appendTo(element.closest("p"));
                 error.appendTo(element.closest("div"));
            }
        },
        //设置验证触发事件
        focusInvalid: true,
        rules: {
            categoryName:{required:true,maxlength:10},
            categoryType:{required:true,min:1}
        },
        messages: {
            categoryName:{required:"请输入类目名称",maxlength:"最大长度为10个汉字"},
            categoryType:{required:"请输入类目编号",min:"必须从1开始"}
        }
    });
});
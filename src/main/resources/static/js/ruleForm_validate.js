$().ready(function() {
    $("#ruleForm").validate({
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
            ruleName:{required:true,maxlength:30},
            ruleNo:{required:true,maxlength:15},
            ruleDescription:{maxlength:100},
            ruleType:{required:true},
            ruleDiscount:{required:true,max:100,min:1},
            ruleDay:{min:1}
        },
        messages: {
            ruleName:{required:"请输入规则名称",maxlength:"最大长度为30个汉字"},
            ruleNo:{required:"请输入规则代号",maxlength:"最大长度为15个汉字"},
            ruleDescription:{maxlength:"最大长度为100个汉字"},
            ruleType:{required:"请选择规则类型"},
            ruleDiscount:{required:"请输入折扣率",max:"请输入正确范围1-100",min:"请输入正确范围1-100"},
            ruleDay:{min:"日期必须大于1天"}
        }
    });
});
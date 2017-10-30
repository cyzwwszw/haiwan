$().ready(function() {
    $("#productForm").validate({
        errorElement: "em",
        errorPlacement: function (error, element) {
            if (element.is(':radio') || element.is(':checkbox')) {
                // var eid = element.attr('name');
                // error.appendTo(element.parent());
                error.appendTo(element.closest("div"));
            } else {
                 //error.appendTo(element.closest("p"));
                 error.appendTo(element.closest("div"));
            }
        },
        //设置验证触发事件
        focusInvalid: true,
        rules: {
            productName:{required:true,maxlength:30},
            productDescription:{required:true,maxlength:100},
            categoryType:{required:true},
            productArea:{min:0.01},
            productAddress:{required:true, maxlength:40},
            productType:{required:true},
            productPayType:{required:true},
            productPrice:{required:true,min:0.01},
            productQuantity:{required:true},
            productStatus:{required:true},
            isHaveBreakfast:{required:true},
            isHaveBathroom:{required:true},
            isHaveWifi:{required:true},
            isHaveYard:{required:true},
            ruleNo:{required:true}
        },
        messages: {
            productName:{required:"请输入产品名称",maxlength:"最大长度30"},
            productDescription:{required:"请输入产品描述",maxlength:"最大长度100"},
            categoryType:{required:"请选择类目"},
            productArea:{min:"请输入面积"},
            productAddress:{required:"请输入地址", maxlength:"最大长度40"},
            productType:{required:"请选择产品类型"},
            productPayType:{required:"请选择支付方式"},
            productPrice:{required:"请输入价格",min:"需要大于0.01"},
            productQuantity:{required:"请输入数量"},
            productStatus:{required:"请选择默认状态"},
            isHaveBreakfast:{required:"请选择是否有早餐"},
            isHaveBathroom:{required:"请选择是否有浴室"},
            isHaveWifi:{required:"请选择是否有宽带"},
            isHaveYard:{required:"请选择是否有庭院"},
            ruleNo:{required:"请选择退款规则"}
        }
    });
});
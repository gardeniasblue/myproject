$('#commentSubmit').click(function () {

    var leaveMessageator = $('#leaveMessageator').val();
    var email = $('#email').val();
    var websiteUrl = $('#websiteUrl').val();
    var leaveMessageBody = $('#leaveMessageBody').val();
    if (isNull(leaveMessageator)) {
        swal("请输入你的称呼", {
            icon: "warning",
        });
        return;
    }
    if (isNull(email)) {
        swal("请输入你的邮箱", {
            icon: "warning",
        });
        return;
    }
    if (!validCN_ENString2_100(leaveMessageator)) {
        swal("请输入符合规范的名称(不要输入特殊字符)", {
            icon: "warning",
        });
        return;
    }
    if (!validCN_ENString2_100(leaveMessageBody)) {
        swal("请输入符合规范的评论内容(不要输入特殊字符)", {
            icon: "warning",
        });
        return;
    }
    var data = {
        "leaveMessageator": leaveMessageator,
        "email": email, "websiteUrl": websiteUrl, "leaveMessageBody": leaveMessageBody
    };
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: '/view/v10/savemessage',
        data: data,
        success: function (result) {
            console.log("ajax success");
            if (result.resultCode == 200) {
                swal("评论提交成功请等待博主审核", {
                    icon: "success",
                });
                $('#leaveMessageBody').val('');
            }
            else {
                swal("不可知错误", {
                    icon: "error",
                });
            }
        },
        error: function () {
            console.log("ajax error")
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});
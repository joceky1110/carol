layui.use(['laydate', 'layedit', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'form'], function () {
    var form = layui.form, $ = layui.jquery, layedit = layui.layedit, laydate = layui.laydate;

    //标签复选框渲染
    $.ajax({
        url: UrlConfig.labelList,
        dataType: "json",
        success: function (resp) {
            if ("0" == resp.code) {
                layui.laytpl($("#label-template").html()).render(resp.data, function (html) {
                    $("#labelDiv").html(html);

                    $("input:checkbox[name='label']").each(function () { // 遍历多选框,设置选中
                        var data = $(this).attr('data');
                        var code = $(this).attr('code');
                        if (data.indexOf(code) != -1) {
                            $(this).attr("checked", "true");
                        }
                    });
                    form.render();//刷新select选择框渲染
                });
            }
        }
    });

    //图片渲染
    var imgArr = $("#img").val();
    // console.log(imgArr.split(","));
    layui.laytpl($("#img-template").html()).render(imgArr.split(","), function (html) {
        $("#layer-photos-demo").html(html);
    });


    function getFormJson(frm) {
        var o = {};
        var a = $(frm).serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });

        var labelCode = "";
        $("input:checkbox[name='label']:checked").each(function () { // 遍历多选框,设置选中
            var code = $(this).attr('code');
            labelCode += code + ",";
        });
        if (labelCode != "") {
            labelCode = labelCode.substr(0, labelCode.length - 1);
            o["labelCode"] = labelCode;
        }
        return o;
    }

    form.on("submit(add)", function (data) {
        var data = getFormJson($('#editForm'));

        if(!data["labelCode"] || data["labelCode"]==''){
            layer.msg("请选择标签");
            return false;
        }
        // console.log(data);
        $.ajax({
            cache: true,
            url: "/data/readRecord/edit",
            type: "post",
            data: data,
            async: false,
            success: function (resp) {
                if (resp.success) {
                    location.href = "/page/readRecord";
                } else {
                    layer.open({
                        type: 1,
                        area: ['300px', '120px'],
                        shadeClose: true,
                        content: '\<\div style="padding:20px;">' + resp.msg + '<\/div>'
                    });
                }
            }
        });
        return false;
    })

    layer.photos({
        photos: '#layer-photos-demo'
        , anim: 5
    });

    $(document).ready(function () {
        //alert($(".comment_list").find(".comment")[0] == undefined);
        // if($(".comment_list").find(".comment")[0] == undefined) {
        //     $(".comment_add_or_last").html("暂无人评论");
        // }
        // $(document).on('click','.show_reply_list', function(){
        //     if($(this).closest('.comment').find(".reply_list").css("display") != "none") {
        //         $(this).closest('.comment').find(".show_remain_reply").css("display","none");
        //         $(this).html("查看回复");
        //
        //     }else {
        //         $(this).closest('.comment').find(".show_remain_reply").css("display","block");
        //         $(this).html("收起回复");
        //
        //     }
        //     $(this).closest('.comment').find(".reply_list").toggle(500);
        //
        //
        // });

        id = $("input[name=id]").val();

        $.ajax({
            url: UrlConfig.listRecordComm,
            data: {
                recordId: id
            },
            type: "post",
            dataType: "json",
            success: function (resp) {
                if ("0" == resp.code) {
                    layui.laytpl($("#comment-template").html()).render(resp.data, function (html) {
                        // layer.open({
                        //     type: 1,
                        //     area: ['1020px', '860px'], //宽高
                        //     content: html
                        // });
                        $("#comment-content").html(html);
                    });
                }
            }
        });


        $(document).on('click', '.show_remain_reply', function () {
            $(this).html("已显示全部回复");
            //TODO发送数据

        });
        $(document).on('click', '.comment_add_or_last', function () {
            var getMoreComment = $(this);
            //TODO如果获取的数据为零
            getMoreComment.html("没有更多评论了");
        });

    });

    $(".NavLinks").mouseenter(function () {
        $(this).find(".del_comment").css("display","inline");
        // $(this).find(".del_comment").show();
    });


    $(".NavLinks").mouseleave(function () {
        $(this).find(".del_comment").css("display","none");
    });

    //删除图片
    $(".NavLinks a").click(function () {
        var delIndex =  $(this).attr("data-id");
        var imgArr = $("#img").val().split(",");
        imgArr.splice(delIndex, 1);
        $("#img").val(imgArr.join(","))
        var data = getFormJson($('#editForm'));

        if(!data["labelCode"] || data["labelCode"]==''){
            layer.msg("请选择标签");
        }
        // console.log(data);
        $.ajax({
            cache: true,
            url: "/data/readRecord/edit",
            type: "post",
            data: data,
            async: false,
            success: function (resp) {
                if (resp.success) {
                    layer.msg("删除成功")
                } else {
                    layer.open({
                        type: 1,
                        area: ['300px', '120px'],
                        shadeClose: true,
                        content: '\<\div style="padding:20px;">' + resp.msg + '<\/div>'
                    });
                }
            }
        });
        $(this).closest('.NavLinks').remove()
        return false;
    });
});
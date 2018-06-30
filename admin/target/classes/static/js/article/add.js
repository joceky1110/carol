layui.use(['laydate', 'layedit', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'form'], function () {
    var form = layui.form, $ = layui.jquery, layedit = layui.layedit, laydate = layui.laydate;
    KindEditor.ready(function (K) {
        window.editor = K.create('#editor_id', {
            width: '1000px',
            height: '600px',
            themeType: 'default',
            allowPreviewEmoticons: false,
            allowImageUpload: true,
            allowFileManager: true,//true时显示浏览远程服务器按钮
            allowImageRemote: false,//网络图片
            items: [
                'source', '|', 'undo', 'redo', '|', 'print', 'code', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
                'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                'anchor', 'link', 'unlink', '|', 'preview'
            ]
        });
    });
    $(".date").each(function () {
        var id = $(this).attr("id");
        laydate.render({
            elem: "#" + id,
            format: 'yyyy-MM-dd'
        });
    });
    $(".datetime").each(function () {
        var id = $(this).attr("id");
        laydate.render({
            elem: "#" + id,
            type: 'datatime',
            format: 'yyyy-MM-dd HH:mm:ss'
        });
    });

    //标签复选框渲染
    $.ajax({
        url: UrlConfig.labelList,
        dataType: "json",
        success: function (resp) {
            if ("0" == resp.code) {
                layui.laytpl($("#label-template").html()).render(resp.data, function (html) {
                    $("#labelDiv").html(html);

                    // $("input:checkbox[name='label']").each(function () { // 遍历多选框,设置选中
                    //     var data = $(this).attr('data');
                    //     var code = $(this).attr('code');
                    //     if (data.indexOf(code) != -1) {
                    //         $(this).attr("checked", "true");
                    //     }
                    // });
                    form.render();//刷新select选择框渲染
                });
            }
        }
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
        var htmlContent = editor.html();;
        if(htmlContent != ""){
            o["content"] = htmlContent;
        }
        o["summary"] = $("#summary").val();
        return o;
    }

    form.on("submit(add)", function (data) {
        var data = getFormJson($('#addForm'));
        if(!data["labelCode"] || data["labelCode"]==''){
            layer.msg("请选择标签");
            return false;
        }
        $.ajax({
            cache: true,
            url: "/data/article/add",
            type: "post",
            data: data,
            async: false,
            success: function (resp) {
                if (resp.success) {
                    location.href = "/page/article";
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
    // getQToken();
})
// $('#coverImg').click(function() {
//     alert(1);
//     editor.loadPlugin('image', function() {
//         editor.plugin.imageDialog({
//             showRemote : false,
//             // imageUrl : K('#url3').val(),
//             clickFn : function(url, title, width, height, border, align) {
//                 alert(url);
//                 editor.hideDialog();
//             }
//         });
//     });
// });


var upload = layui.upload;

//普通图片上传
var uploadInst = upload.render({
    elem: '#coverImg'
    , url: UrlConfig.uploadImg
    , before: function (obj) {
        //预读本地文件示例，不支持ie8
        obj.preview(function (index, file, result) {
            $('#demo1').attr('src', result); //图片链接（base64）
        });
    }
    , done: function (res) {
        //如果上传失败
        if (res.code > 0) {
            return layer.msg('上传失败');
        }
        //上传成功
    }
    , error: function () {
        //演示失败状态，并实现重传
        var demoText = $('#demoText');
        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
        demoText.find('.demo-reload').on('click', function () {
            uploadInst.upload();
        });
    }
});


//客户端上传到七牛没解决跨越问题，暂用服务器上传
// function getQToken() {
//     $.ajax({
//         cache: true,
//         url: UrlConfig.getQiniuToken,
//         type: "get",
//         async:true,
//         success: function (data) {
//             if (data) {
//                 $("#qiniuToken").val(data);
//
//
//             } else {
//                 layer.msg("获取七牛token失败，图片无法上传")
//             }
//         }
//     });
// }
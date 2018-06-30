layui.use(['laydate','layedit', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element','form'], function(){
	var form = layui.form ,$ = layui.jquery ,   layedit = layui.layedit,laydate=layui.laydate;
	$("textarea").each(function(){
	    var id = $(this).attr("id");
        layedit.build(id,{height:'150px' });
     });
     $(".date").each(function(){
     	    var id = $(this).attr("id");
            laydate.render({
             elem:"#"+id,
             format: 'yyyy-M-d'
             });
      });
      $(".datetime").each(function(){
        var id = $(this).attr("id");
              laydate.render({
               elem:"#"+id,
               type:'datatime',
               format: 'yyyy-M-d H:m:s'
              });
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
              return o;
          }
    form.on("submit(add)",function(data){
        var data=getFormJson($('#editForm'));
        $.ajax({
            cache : true,
            url : "/data/article/edit",
            type : "post",
            data : data,
            async : false,
            success : function(resp){
                if(resp.success){
                      location.href="/page/article";
                }else{
                        layer.open({
                                 type: 1,
                                 area: ['300px', '120px'],
                                 shadeClose: true,
                                 content: '\<\div style="padding:20px;">'+resp.msg+'<\/div>'
                          });
                }
            }
        });
        return false;
 	})
});
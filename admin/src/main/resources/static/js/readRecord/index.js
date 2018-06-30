layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
  var table = layui.table;
  table.render({
    elem: '#dataTable'
    ,url:'/data/readRecord/list/'
    ,cellMinWidth: 80
    ,cols: [[
      {field:'id',   title: 'ID'},
  {field:'userName',   title: '用户名'},{field:'title',   title: '标题'},{field:'content',   title: '内容'},{field:'bookName',   title: '书名'},{field:'labelName',   title: '标签名字'},{field:'remark',   title: '备注'},
      {title:'操作',fixed: 'right',fixed: 'right', width:220, align:'center', toolbar: '#bar'}
    ]],
     id: 'reload',
     page: {
              layout: ['limit', 'count', 'prev', 'page', 'next']
              ,groups: 5
              ,first: '首页'
              ,last: '尾页'
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
          return o;
      }
  var $ = layui.$, active = {
      reload: function(){
        var searchForm = $("#searchForm");
        table.reload('reload', {
          page: {
            curr: 1
          }
          ,where:  getFormJson(searchForm)
        });
      }
    };
$('#searchForm .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
 //监听工具条
   table.on('tool(table)', function(obj){
     var data = obj.data //获得当前行数据
     ,layEvent = obj.event; //获得 lay-event 对应的值
     if(layEvent === 'detail'){
       location.href="/page/readRecord/info?id="+data.id;
     } else if(layEvent === 'del'){
       layer.confirm('确定删除？', function(index){
            layer.close(index);
            $.ajax({
                url:'/data/readRecord/delete',
                data:{id:data.id},
                method:"post",
                dataType:"json",
                success:function(resp){
                   if(resp.success){
                         layer.open({
                                 type: 1,
                                 area: ['300px', '120px'],
                                 shadeClose: true, //点击遮罩关闭
                                 content: '\<\div style="padding:20px;">删除成功！\<\/div>'
                               });
                               obj.del();
                   }else{
                            layer.open({
                                 type: 1,
                                 area: ['300px', '120px'],
                                 shadeClose: true, //点击遮罩关闭
                                 content: '\<\div style="padding:20px;">"+resp.msg+"\<\/div>'
                               });
                   }
                }
            });
       });
     } else if(layEvent === 'edit'){
        location.href="/page/readRecord/edit?id="+data.id;
     }
   });
});
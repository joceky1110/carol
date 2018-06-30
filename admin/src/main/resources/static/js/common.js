  //初始化字典表
$("select[dict]").each(function(){
    $(this).empty();
    var list = map[$(this).attr("dict")];
      $(this).append("<option value=''>----请选择----</option>");
    for(var key in list){
     $(this).append("<option value='"+key+"'>"+list[key]+"</option>");
    }
});
//賦值
$("select[initValue]").each(function(){
    $(this).find("option[value='"+$(this).attr("initValue")+"']").attr("selected",true);
});
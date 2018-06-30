BaseUrl = "http://localhost:8080/";
ctx = "/";
UrlConfig ={
	dict:BaseUrl+'static/js/dict',
	listRecordComm:BaseUrl + '/data/listRecordComm',
    labelList : BaseUrl + 'data/readLabel/list',
    uploadImg: BaseUrl + 'qiniu/uploadImage.do',

};

Constant = {
		// hospitalNo:$.cookie("hospitalNo"),
		// token:$.cookie("token"),
		// deptId:$.cookie("deptId"),
		// doctorNo:$.cookie("doctorNo"),
		// username:$.cookie("username")
};

// var map = undefined;
// $.ajax({
//     url:UrlConfig.dict,
//     data:{},
//     type:"get",
//     dataType:"json",
//     async:true,
//     success:function(resp){
//     	alert(resp);
//         map = resp;
//     }
// });

function logout(){
	// if(!$.cookie('token')){
	// 	location.reload();
	// }
	// $.ajax({
	// 	url:UrlConfig.logout,
	// 	data:{token: $.cookie('token')},
	// 	type:"post",
	// 	dataType:"json",
	// 	success:function(resp){
	// 		$.cookie('token', null);
	// 		$.cookie('deptId', null);
	// 		$.cookie('hospitalNo', null);
	// 		$.cookie("doctorNo",null),
	// 		Constant={};
	// 		location.reload();
	// 	}
	// });
}
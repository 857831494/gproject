

//var BASE_URL="http://127.0.0.1:9090/";
var BASE_URL="http://192.168.16.28:9099/";
//http://127.0.0.1:9090/html/index/Login.html
var AUTH="auth/";
var APIManager={
		post:function(url,parame,callBack){
			jQuery.ajax({url:BASE_URL+url,
				 type: "post",
				 contentType: 'application/json',
				 //dataType:"json",
					data:parame,
					success:function(ret,textStatus,jq){
				if (ret.code>0) {
					$.ligerDialog.error('错误码:'+ret.code);
				}else{
					callBack(ret.data);
				}
			}});
		}
}
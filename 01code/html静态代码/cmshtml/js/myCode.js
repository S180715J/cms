/**
 *  定义角色权限的jS
 */
  function checkControl(url,conId) {
	
	  //发送ajax请求得到 登录的用户信息 ，并用一个对象接收
	
	 
	  var obj='';
	  $.ajax({
		   method:'get',
		   url:'http://localhost:8080/findTokenByUser',
		   headers:createAuthorizationTokenHeader(),
		   dataType:'json',
		   async:false,
		  
		   success: function (data) {
			  
			if(data!=null){
				obj=data;
			}
		}
		,error:function(){
			layui.msg('错误，请联系超级管理员',{
				icon:1,
				time:2000
			})
		}
	   }).responseText;
	  
	   //再次发送异步请求，获取当前用户的权限
	  var uri='';
	  if(obj!=null||obj!=''||obj!=undefined||obj.role!=null||obj.role!=''||obj.role!=undefined){
		  $.ajax({
			   method:'get',
			   url:'http://localhost:8080/control/'+obj.role.rid,
			   headers:createAuthorizationTokenHeader(),
			   async:false,
			   dataType:'json',
			   success:function(data){
				   //获取得到的权限id判断是否存在
				  
				   if(data!=null){
					   $.each(data,function(index,val){
						   
						   
						   if(val.controlId == conId){
							   	
							   uri=url;
						   }
						   
					   });
				   }
					   },
					   error:function(){
						   layer.msg('错误，请联系超级管理员',{
							   icon:2,
							   time:2000
						   });
					   }
		   
		   });
		 
		   return uri;
	  }else {
		uri='#';
		
		return uri;
	}
	  
	
	  
}
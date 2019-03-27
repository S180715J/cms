/**
 * 机构联动的js
 */
layui.define(["form","jquery"],function(exports){  
    var form = layui.form,  
    $ = layui.jquery,  
    Address = function(){};  
  
    Address.prototype.institution = function() {  
        //加载机构数据  
        var proHtml = '',that = this; 
            $.ajax({
            	method:'get',
            	url:'http://localhost:8080/findInstitution',
            	data:{
            		"code":0,
            		"type":0
            	},
            	success:function(pro){
            		 for (var i = 0; i < pro.length; i++) {
                         proHtml += '<option value="' + pro[i].id + '">' + pro[i].iname + '</option>';
                     }  
                     //初始化机构数据  
                     $("select[name=institution]").append(proHtml);  
                     form.render();  
                     form.on('select(institution)', function (proData) {
                          
          
                         $("select[name=department]").html('<option value="">请选择县/区</option>');
                         var value = proData.value;
         
          
          
          
                         if (value > 0) {
                        	 $.ajax({
                        		 method:'get',
                        		 url:'http://localhost:8080/findInstitution',
                        		 data:{
                        			 "code":value,
                        			 "type":1
                        		 },
                        		 success: function(val) {
                        			
                        			 mechanism(val);
                        			 
								}
                        	 });
                        	 
                             //that.citys(pro[$(this).index() - 1].childs);
          
          
                         } else {
                             $("select[name=mechanism]").attr("disabled", "disabled");
                         }
                     });
            	}
            }); 
    }
  
    //加载子机构数据  
   function mechanism(val) {
    	    
    	     var mechanismHtml = '<option value="">请选择子机构</option>',that = this;
           $.each(val,function(index,obj){
        	   
        	
        	    mechanismHtml += '<option value="' + obj.id + '">' + obj.iname + '</option>';  
           });
 
         
                
        $("select[name=mechanism]").html(mechanismHtml).removeAttr("disabled");  
       form.render(); 
        form.on('select(mechanism)', function (mechanismData) {  
            var value = mechanismData.value;  
          
            if (value > 0) {
            	
            	$.ajax({
            		method:'get',
            		url:'http://localhost:8080/findInstitution',
            		data:{
            			"code":value,
            			"type":2
            		},success: function(data) {
            			
						department(data);
					}
            	});
               
                //that.areas(citys[$(this).index() - 1].childs);
            } else {  
                $("select[name=department]").attr("disabled", "disabled");  
            }  
        });  
    }  
  
    //加载县/区数据  
    function department(val) {
    	      
        var departmentHtml = '<option value="">请选择bumen</option>';  
        for (var i = 0; i < val.length; i++) {  
        	departmentHtml += '<option value="' + val[i].id + '">' + val[i].iname + '</option>';  
        }  
        $("select[name=department]").html(departmentHtml).removeAttr("disabled");  
        form.render();  
    }  
  
    var address = new Address();  
    exports("address",function(){  
        address.institution();  
      
    });  
});  

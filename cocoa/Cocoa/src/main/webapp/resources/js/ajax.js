/**
 * 
 */
	$(document).ready(function() {
		alert('회원가입 내용을 작성하세요')
		$('#validate').click(function() {
			
			var flag = false;
			var user_id = $('#id').val();
			var user_pwd = $('#pwd1').val();
			var user_name = $('#name').val();
			var user_phone = $('#phone').val();
			
			//Ajax연결해서 
			$.ajax({
				data :  {id : user_id, pwd : user_pwd , name : user_name , phone : user_phone },
				type : "post",
				dataType: "json",
				async: false,
				url : '/cocoa/join',
				success : function(data){
					if (data == 1) {
						alert("회원가입에 성공했습니다");
						location.href='/cocoa/';
					} else {
						alert("회원가입에 실패했습니다");
						location.href='/cocoa/view_join';
					}
				},
				error : function(data, textStatus){
					alert('실패');
					location.href='/cocoa/view_join';
				},
				complete : function(data,textStatus){
					
				}
			});
			
			return flag;
		});
		
	
		$('.form-control').focusout(function () {
	        var pwd1 = $("#pwd1").val();
	        var pwd2 = $("#pwd2").val();
	  
	        if ( pwd1 != '' && pwd2 == '' ) {
	            null;
	        } else if (pwd1 != "" || pwd2 != "") {
	            if (pwd1 == pwd2) {
	                $("#alert-success").css('display', 'inline-block');
	                $("#alert-danger").css('display', 'none');
	            } else {
	              
	                $("#alert-success").css('display', 'none');
	                $("#alert-danger").css('display', 'inline-block');
	            }
	        }
	    });
		
		$('#idCheck').click(function(){
			var _id = $("#id").val();
			if (_id == null) {
				alert("ID를 입력하세요");
				return;
			}
			$.ajax({
				type : "post",
				async : true,
				url : "/cocoa/idChk",
				dataType : "json",
				data : {"id" : _id},
				
				success : function(data, textStatus) {
					

					if (data == 1) {
						alert("사용할 수 없는 ID입니다.");
					} else {
						alert("사용할 수 있는 ID입니다.");
					}
				},
				error : function(data) {
					alert("에러가! 발생했습니다.");
					
				},
				complete : function(data) {
					//alert("작업을 완료 했습니다");
				}
			}); //end ajax	 
		});
	});
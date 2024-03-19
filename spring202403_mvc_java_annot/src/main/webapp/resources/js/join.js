$(function(){

		$("#checkId").on("click", function(){
			const member_id = $("#member_id").val();

			$.ajax({
				type: "post",
				url: "checkIdProcess.do",
				data: {"member_id": member_id},
				success: function(resData){
					//응답내용을 자바크립트 객체로 바꿈
					if(resData == "PASS"){//PASS:기존의 회원과 같은 아이디가 없는 경우
						$("#result_checkId").html("사용 가능합니다").css("color", "green");
					
					}else{
						$("#result_checkId").html("사용할 수 없습니다").css("color", "red");
						$("#member_id").val("").trigger("focus"); //focus이벤트를 강제로 발생시킴
					}
				},
				error: function(error){
					console.log("아이디 확인시 에러 발생");
				}
			});//end of ajax
			
		});//end of click
		
		
		////////// Email 인증 관련 추가 부분 //////////
		
		let code; //서버로부터 받은 인증번호 저장
		const checkInput = $("#auth_num_input"); //인증번호 입력하는 곳
		
		$("#email_auth_btn").on("click", function(){
			
			const email = $("#email").val(); //이메일 주소 
			
			//이메일 유효성 검사
			let regExp_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
			
			if(regExp_email.test(email)){//유효성 검사를 통과하면
				
				$.ajax({
					type: "get",
					url: "emailCheck.do?email="+email,
					success: function(data){
						checkInput.attr("disabled", false); //인증번호 입력란을 활성화시킴
						code = data; //서버에서 받은 인증번호를 변수 code에 저장
						alert("인증번호가 전송되었습니다.");
					}
				});//end of ajax
				
			}else{
				alert("입력하신 내용이 이메일 형식에 맞지 않습니다.");
				frm_join.email.focus();
			}
			
		});//end of email

		//사용자가 자신의 메일에서 인증번호를 확인한 후 인증번호 입력란에 인증번호를 입력하고 난 뒤 
		//인증확인 버튼을 클릭한 경우
		$("#confirm_email_btn").click(function(){
			
			const inputCode = checkInput.val(); //인증번호 입력란에 입력된 값
			const resultMsg = $("#mail-check-result"); //결과값을 보여주는 span태그 부분
			const resultEmailAuth = $("#result_confirm"); //인증결과를 넘겨줄 input hidden 태그
			
			if(inputCode == code){//인증번호 입력란에 입력된 값과 서버에서 받은 값이 일치하면
				resultMsg.html("정상적으로 인증되었습니다");
				resultMsg.css("color", "green");
				resultEmailAuth.val("PASS");//메일인증 성공
			}else{
				resultMsg.html("인증번호가 불일치합니다. 다시 확인해주세요");
				resultMsg.css("color", "red");
				resultEmailAuth.val("FAIL");//메일인증 실패
			}
			
		});//end of confirm_email

 
        frm_join.addEventListener("submit", function(e){
            //입력값의 유효성검사 통과여부:valid
            let valid = false;
            
            //아이디: 아이디는 4글자로 합니다.
            const regExp_id = /^[A-Za-z0-9~!@#$%^()+|=]{4}$/;
            //비밀번호: 비밀번호는 8글자 이상 16글자 이하로 영문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.
            const regExp_pw = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[~!@#$%^()+|=])[A-Za-z0-9~!@#$%^()+|=]{8,16}$/;
            //전화번호: 전화번호는 010-숫자4자리-숫자4자리 로 입력해야 합니다
            const regExp_phone =/^010-\d{4}-\d{4}$/;

            if(frm_join.member_id.value.length == 0){
                alert("아이디가 입력되지 않았습니다");
                frm_join.member_id.focus();

            }else if(!regExp_id.test(frm_join.member_id.value)){
                alert("아이디는 4글자로 합니다");
                frm_join.member_id.focus();

            }else if(frm_join.member_pw.value.length == 0){
                alert("비밀번호가 입력되지 않았습니다");
                frm_join.member_pw.focus();

            }else if(!regExp_pw.test(frm_join.member_pw.value)){
                alert("비밀번호는 8글자 이상 16글자 이하로 영문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.");
                frm_join.member_pw.focus();

            }else if(frm_join.member_name.value.length == 0){
                alert("이름이 입력되지 않았습니다");
                frm_join.member_name.focus();

            }else if(frm_join.handphone.value.length == 0){
                alert("전화번호가 입력되지 않았습니다");
                frm_join.handphone.focus();

            }else if(!regExp_phone.test(frm_join.handphone.value)){
                alert("전화번호는 010-숫자4자리-숫자4자리 로 입력해야 합니다");
                frm_join.handphone.focus();

            }else if(frm_join.email.value.length == 0){
                alert("이메일이 입력되지 않았습니다");
                frm_join.email.focus();

            }else if(frm_join.result_confirm.value == 'FAIL'){
                alert("이메일 인증이 필요합니다");
                frm_join.email.focus();

            }else{//입력값이 모두 정상적으로 입력되어진 경우
                valid = true;
            }
            
            if(!valid){
                e.preventDefault();
                e.stopPropagation();
            }
        });


 });//end of jQuery



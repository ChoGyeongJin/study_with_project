$(function(){
	
	const s_btn = $("#login_btn");//로그인 버튼
	s_btn.on("click", checkInput);
	
});

function checkInput(){
    if(frm_login.member_id.value.length==0){
        alert("아이디가 입력되지 않았습니다.");
        frm_login.member_id.focus();
        return false;

    }else if(frm_login.member_pw.value.length==0){
        alert("비밀번호가 입력되지 않았습니다.");
        frm_login.member_pw.focus();
        return false;

    }else{
       document.frm_login.submit();
    }
    
    return true;
}
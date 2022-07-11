/**
 * 
 */

function frmCheck(){
	var rf = document.registerForm;
	
	if(rf.clientId.value == "" || rf.clientName.value == "" || rf.clientEmail.value == ""|| rf.clientPassword.value == ""){
		alert("필요한 정보를 입력해주세요!");
		return false;		
	}
}
function convertMessage(temp_MSG, tgtVal){
	var result = temp_MSG;
	
	if(result.includes("$$target$$")){
		result = temp_MSG.replace("$$target$$",tgtVal);
	}else{
		alert("err");
	}
		
	return result;
}

function sendMessage(temp_MSG, tgtVal){
	var message = "";

	if(temp_MSG=="KS_IMS_ERRMSG_002_JS"){
		message = convertMessage(KS_IMS_ERRMSG_002_JS,tgtVal);
		alert(message);
		return false;
	}else if (temp_MSG=="KS_IMS_ERRMSG_003_JS"){
		message = convertMessage(KS_IMS_ERRMSG_003_JS,tgtVal);
		alert(message);
		return false;
	}else if (temp_MSG=="KS_IMS_ERRMSG_004_JS"){
		message = convertMessage(KS_IMS_ERRMSG_004_JS,tgtVal);
		alert(message);
		return false;
	}
	
	
	
}

function inputYearCheck(p_id){
	var checkTarget  = document.getElementById(p_id).value;

	if(isNullCheck(checkTarget)){
		alert(KS_IMS_ERRMSG_001_JS);
		return false;
	}else if(isDateCheck(checkTarget)){
		sendMessage("KS_IMS_ERRMSG_002_JS",checkTarget);
		return false;
	}else if(isNumber(checkTarget)){
		sendMessage("KS_IMS_ERRMSG_003_JS",checkTarget);
		return false;
	}else if(isFoundationCheck(checkTarget)){
		sendMessage("KS_IMS_ERRMSG_004_JS",checkTarget);
		return false;
	}else{
		return true;
	}
	
}

/**入力値が空白かをチェック、NULL・""・スペースの変換・undefinedのケースをチェックする*/

function isNullCheck(checkTarget){
	if(checkTarget===null || checkTarget.value=="" 
		|| checkTarget.value == "undefined" || checkTarget.replace(/ /gi, "") == ""){
		return true;
	} else {
		return false;
	}
	
}
 

function isDateCheck(checkTarget){
	if(checkTarget.length!=4){
		return true;
	} else {
		return false;
	}
	
}

function isNumber(p_id) {
	var input = document.getElementById(p_id);
	var chars = "0123456789";

	return containsCharsOnly(input, chars);
}

function containsCharsOnly(input, chars) {
	for (var i = 0; i < input.value.length; i++) {
		if (chars.indexOf(input.value.charAt(i)) == -1) {
			return false;
		}
	}
	return true;
}
/*
function isNumber(checkTarget){
	var patt1 = /[a-z]/g;
	var result = checkTarget.match(patt1);
	
	if(result){
		if(result.length>0){
			return true;
		} else {
			return false;
		}
	}
	
}
*/

function isFoundationCheck(checkTarget){

	if(checkTarget<2014){
		return true;
	} else {
		return false;
	}
	
}

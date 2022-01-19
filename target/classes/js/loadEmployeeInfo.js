function loadEmployeeInfo() {
	$.ajax({
		url:`/ksims/LoadEmployeeApiController`,
		dataType:"json"
	}).done(res=>{
		console.log("성공",res);
		console.log("section",res.emp_section_name);
		$('input[name=payLevel]').attr('value',1);
		$('input[name=empSectionName]').attr('value',res.emp_section_name);
		$('input[name=companyMail]').attr('value',res.emp_comp_mail);
		$('input[name=bankName]').attr('value',res.emp_bank_name);
		$('input[name=bankBran]').attr('value',res.emp_bank_bran);
		$('input[name=bankNumGubun]').attr('value',res.emp_bank_num_gubun);
		$('input[name=nationality]').attr('value',res.emp_nationality);
		
		
		$("#dependentCountDd").val(0).prop("selected", true);
		$('input[name=dependentCount]').attr('value',0);
		
		$("#empGenderDd").val(res.emp_gender).prop("selected", true);
		$('input[name=empGender]').attr('value',res.emp_gender);
		
		$("#pst_select").val(res.position_code).prop("selected", true);
		$('input[name=positionCode]').attr('value',res.position_code);
		
		$("#departmentCodeDd").val(res.department_code).prop("selected", true);
		$('input[name=departmentCode]').attr('value',res.department_code);
		
		
		//$('#test').val('원하는 값');
	}).fail(error=>{
		console.log("실패",error);
	});
	
}
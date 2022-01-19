<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="leftListAjax">
	<c:forEach items="${list_l}" var="list">
		<label style="border: 1px; border-radius: 0.5em; padding: 5px; width: 100%; background-color: white; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
			<input type="checkbox" name="left_check" value="${list.emp_id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span>${list.position_name }</span>&nbsp;&nbsp;<span>${list.emp_name }</span>
			<input type="hidden" id="${list.emp_id}" value="${list.leader_check}">
			<c:if test="${list.leader_check eq 'Y'}"><b><img src="images/leader.png" style="width: 25px; margin-top: -7px;"></b></c:if>
		</label>
	</c:forEach>
</div>



<div id="RightListAjax">
	<c:forEach items="${list_r}" var="list">
		<label style="border: 1px; border-radius: 0.5em; padding: 5px; width: 100%; background-color: white; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
			<input type="checkbox" name="right_check" value=${list.emp_id }>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span>${list.position_name}</span>&nbsp;&nbsp;<span>${list.emp_name}</span>&emsp;
			<span>
				<c:if test="${list.field_code ne '0000'}">(${list.field_name})</c:if> <!-- 本社がない場合、現場名出力  -->
			</span>
		</label>
	</c:forEach>
</div>
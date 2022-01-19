<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="charset=UTF-8">
	<style>
h1 {
  font-family: "Arial", "メイリオ";
  position: relative;
  color: #6495ed;
  line-height: 1.4;
  -webkit-box-reflect: below -20px -webkit-linear-gradient(top,rgba(0,0,0,0),rgba(0,0,0,0) 10%,rgba(0, 0, 0, 0.6));
}

		.table thead th{vertical-align:middle; font-weight: normal;}
		.table tbody td{text-align: center;}
		.hoverTd:hover{background:#f0f9f7;}
	</style>
	
	<script>
	  function ld_score(num){
		  readAppr.emp_id.value=$('#emp_id_'+num).text();
		  readAppr.appraisal_start_date.value=$('#conduct_start_month_'+num).text();
		  readAppr.appraisal_end_date.value=$('#conduct_end_month_'+num).text();
		  readAppr.field_code.value=$('#field_code_'+num).val();
		  readAppr.action='${pageContext.request.contextPath}/AppraisalLeaderController';
		  readAppr.submit();
	  }
	  
	  function ad_score(num){
		  readAppr.emp_id.value=$('#emp_id_'+num).text();
		  readAppr.appraisal_start_date.value=$('#conduct_start_month_'+num).text();
		  readAppr.appraisal_end_date.value=$('#conduct_end_month_'+num).text();
		  readAppr.field_code.value=$('#field_code_'+num).val();
		  readAppr.action='${pageContext.request.contextPath}/AppraisalPerformanceController';
		  readAppr.submit();
	  }
	 </script>
</head>
<body>
	<div class="content_margin">
		<form class="ui form" name="readAppr" method="POST">
			<h1><i class="caret square left icon"></i>評価管理一覧</h1>
			
			<div class="ui grid">
				<div class="column">
					<input name="emp_id" type="hidden" value="">
					<input name="field_code" type="hidden" value="">
					<input name="appraisal_start_date" type="hidden" value="">
					<input name="appraisal_end_date" type="hidden" value="">
		
					<table class="table table-striped table-bordered table-sm">
						<thead align="center">
							<tr bgcolor="#e9ecef">
								<th rowspan="2" style="width:5%">社番</th>
								<th rowspan="2" style="width:8%">氏名</th>
								<th rowspan="2" style="width:20%">プロジェクト名 </th>
								<th rowspan="2" style="width:5%">職位</th>
								<th colspan="2" style="width:20%">評価期間</th>
								<th rowspan="2" class="table-danger" style="width:3%">LD評価</th>
								<th colspan="3" class="table-success" style="width:18%">顧客評価</th>
								<th colspan="3" class="table-warning" style="width:18%">実績評価</th>
								<th rowspan="2"  class="table-primary" style="width:3%">総点</th>
								<th rowspan="2" style="width:3%">承認状態</th>
							</tr>
							<tr>
								<th bgcolor="#e9ecef">開始年月日</th>
								<th bgcolor="#e9ecef">終了年月日</th>
								<th class="table-success">業務<br>能力</th>
								<th class="table-success">次PJ<br>継続<br>有無</th>
								<th class="table-success" style="width:3%;">成績</th>
								<th class="table-warning">売上及び<br>利益<br>貢献度</th>
								<th class="table-warning">社員<br>育成<br>貢献度</th>
								<th class="table-warning" style="width:3%;">成績</th>
							</tr>
						</thead>
						<tbody>
							<c:set value="0" var="i"/>
							<c:forEach items="${dtoList}" var="list">
							<tr>
								<td id="emp_id_${i}">${list.emp_id}</td>
								<td style="text-align: left;">${list.emp_name}</td>
								<td style="text-align: left;">${list.field_name}</td>
								<td style="text-align: left;">${list.field_position_name}</td>
								<td id="conduct_start_month_${i}">${list.conduct_start_month}</td>
								<td id="conduct_end_month_${i}">${list.conduct_end_month}</td>
								<td class="table-primary hoverTd" align="center" onclick="ld_score('${i}')" style="cursor: pointer;">${list.ld_sum}</td>
								<td align="center">${list.cust_ability}</td>
								<td align="center">${list.cust_keepwork}</td>
								<td class="table-primary hoverTd" align="center" onclick="ad_score('${i}')" style="cursor: pointer;">${list.cust_sum}</td>
								<td align="center">${list.ld_sales}</td>
								<td align="center">${list.ld_promote}</td>
								<td class="table-primary hoverTd" align="center" onclick="ad_score('${i}')" style="cursor: pointer;">${list.sales_sum}</td>
								<td align="center">${list.app_score}</td>
								<td><c:if test="${list.accept_flg}">
										<font color="green">完</font>
									</c:if>
									<c:if test="${!list.accept_flg}">
										<font color="red">待</font>
									</c:if>
								</td>
							</tr>
							
							<input name="field_code_${i}" id="field_code_${i}" type="hidden" value="${list.field_code}">
							<c:set value="${i+1}" var="i"/>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div align="center">
				<div class="ui pagination menu">	
					<c:if test="${page.curBlock > 1}">
						<a class="item" href="AppraisalScoreController?curPage=1&param=1"><i class="angle double left icon"></i></a>
					</c:if>
					<c:if test="${page.curBlock > 1}">
						<a class="item" href="AppraisalScoreController?curPage=${page.prevPage}&param=1"><i class="angle left icon"></i></a>
					</c:if>
					<c:forEach var="pageNum" begin="${page.blockBegin}" end="${page.blockEnd}">
						<c:choose>
							<c:when test="${pageNum == page.curPage}">
								<div class="active item">${pageNum}</div>
							</c:when>
							<c:otherwise>
								<a class="item" href="AppraisalScoreController?curPage=${pageNum}&param=1">
									${pageNum}
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						
					<c:if test="${page.curBlock < page.totBlock}">
						<a class="item" href="AppraisalScoreController?curPage=${page.nextPage}&param=1"><i class="angle right icon"></i></a>
					</c:if>
					<c:if test="${page.curBlock < page.totBlock}">
						<a class="item" href="AppraisalScoreController?curPage=${page.totPage}&param=1"><i class="angle double right icon"></i></a>
					</c:if>
				</div>
			</div>
			
			<div class="ui">
				<div class="ui button left floated"  onclick="javascript:location.href='${pageContext.request.contextPath}/AppraisalMainController'">
					<i class="left chevron icon"></i>戻る
				</div>
			</div>
		</form>
	</div>	
</body>
</html>
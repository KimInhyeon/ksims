<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>自己評価とリーダー評価</title>
    <script>//버튼에 대한 함수
           
            function formBack(){ //뒤로가기
                history.back();
            }
            function checkForNumber(){//문자방지, 숫자 0~10
            	var activeID=document.activeElement.id;
            	var activeVal=document.getElementById(activeID).value;
            	if(!/^[0-9]{1,4}$/.test(activeVal)){
            		document.getElementById(activeID).value=0;
            	}else if(Number(activeVal)>10){
            		document.getElementById(activeID).value=10;
            	}
            }
        
        	$(document).ready(function(){
        		if('${page_type=="self_appraisal"}'=='true'){
        			$('.selfAppr').css({'background':'#afffb9','border':'3px solid #afffb9','border-top':'none'});//VS #e9ecef
        			for(var k=1;k<12;k++){
        				$('#leader'+k).attr('disabled','disabled');
        				$('#me'+k).removeAttr('disabled');
	      				$('#me'+k).closest("td").css('border','3px solid #afffb9');
        				$('#leader'+k).closest("input").css('background','none');
        			}
        		}else if('${page_type=="leader_appraisal"}'=='true'){
        			$('.leaderAppr').css({'background':'#afffb9','border':'3px solid #afffb9','border-top':'none'});
        			var tst='${dto.appriaiser_id}';
        			if(tst==' '){
        				document.getElementById('appriaiser_id').value='${sessionScope.sid}';
        				document.getElementById('appriaiser_name').value='${sessionScope.sname}';
        			}
        			for(var k=1;k<12;k++){
        				$('#me'+k).attr('disabled','disabled');
        				$('#leader'+k).removeAttr('disabled');
        				$('#leader'+k).closest("td").css('border','3px solid #afffb9');
        				$('#me'+k).closest("input").css('background','none');
        			}
        		}else{
        			location.href="${pageContext.request.contextPath}/main";
        			  		}
        	});     
               
         function beforesubmit(){
        	 if('${page_type=="self_appraisal"}'=='true'){
        		 self_n_leader.action="${pageContext.request.contextPath}/Appraisal_self";
        		 for(var t=1;t<=8;t++){
        			 if(Number(document.getElementById('me'+t).value)==0){
        				 if(!confirm('0点があります。　続きますか？')){
        					 return;
        				 }else{
        					 break;
        				 }
        			 }
        		 }
        		 for(var u=9;u<=11;u++){
        			 if(document.getElementById('me'+u).value==''){
        				 alert('入力が必要な項目があります。');
        				 return;
        			 }
        		 }
        	 }else if('${page_type=="leader_appraisal"}'=='true'){
        		 self_n_leader.action="${pageContext.request.contextPath}/Appraisal_leader";
        		 for(var t=1;t<=8;t++){
        			 if(Number(document.getElementById('leader'+t).value)=='0'){
        				 if(!confirm('0点があります。　続きますか？')){
        					 return;
        				 }else{
        					 break;
        				 }
        			 }
        		 }
        		 for(var u=9;u<=11;u++){
        			 if(document.getElementById('leader'+u).value==''){
        				 alert('入力が必要な項目があります。');
        				 return;
        			 }
        		 }  
        	 }
        	 $('#sb_btn').css('pointer-events','none');
        	 $('.disis').removeAttr('disabled');
        	 self_n_leader.submit();
         }   
    </script>
<script>

    //숫자 이외 입력 방지
   
   function typing(){//그래프 및 입력 받은 값 화면에 출력
    
      var me1 = document.getElementById('me1').value;
      var me2 = document.getElementById('me2').value;
      var me3 = document.getElementById('me3').value;
      var me4 = document.getElementById('me4').value;
      var me5 = document.getElementById('me5').value;
      var me6 = document.getElementById('me6').value;
      var me7 = document.getElementById('me7').value*2;
      var me8 = document.getElementById('me8').value*2;
      var meTotals = document.getElementById('meTotals').value;
      //사원 합계 출력
      meTotals = Number(me1)+Number(me2)+Number(me3)+
                 Number(me4)+Number(me5)+Number(me6)+
                 Number(me7)+Number(me8);
      document.getElementById("meTotals").value=meTotals;
       //리더 합계 출력
      var leader1 = document.getElementById('leader1').value;
      var leader2 = document.getElementById('leader2').value;
      var leader3 = document.getElementById('leader3').value;
      var leader4 = document.getElementById('leader4').value;
      var leader5 = document.getElementById('leader5').value;
      var leader6 = document.getElementById('leader6').value;
      var leader7 = document.getElementById('leader7').value*2;
      var leader8 = document.getElementById('leader8').value*2;          
      var leaderTotals = document.getElementById('leaderTotals').value;
      leaderTotals = Number(leader1)+Number(leader2)+Number(leader3)+
                 Number(leader4)+Number(leader5)+Number(leader6)+
                 Number(leader7)+Number(leader8);
      document.getElementById("leaderTotals").value=leaderTotals;

      var chart = c3.generate({//차트
                            size :{
                                height: 500,
                                width: 280
                            },
                            color: {
                                    pattern: ['#1f77b4', '#aec7e8', 
                                              '#ff7f0e', '#ffbb78', 
                                              '#2ca02c', '#98df8a',
                                              '#d62728', '#ff9896'] 
                            },
                            bar: {
                                width: {
                                    ratio: 0.2
                                }
                            },
                            data: {
                                x: "x-axis",
                                json:[
                                    { "x-axis": "自己評価",//사원아이디
                                        "勤務誠実度": me1,
                                        "仕事熱意度": me2,
                                        "責任感": me3,
                                        "業務理解力": me4,
                                        "技術習得力": me5,
                                        "応用力/冷静度": me6,
                                        "コミュニケーション能力": me7,
                                        "チーム貢献度": me8},

                                    { "x-axis": "リーダー評価",//책임자아이디
                                        "勤務誠実度" :leader1,
                                        "仕事熱意度": leader2,
                                        "責任感": leader3,
                                        "業務理解力": leader4,
                                        "技術習得力": leader5,
                                        "応用力/冷静度": leader6,
                                        "コミュニケーション能力": leader7,
                                        "チーム貢献度": leader8}
                                    ],
                                    keys: {
                                        x: "x-axis",
                                        value: ["勤務誠実度", "仕事熱意度", "責任感", 
                                                "業務理解力", "技術習得力", "応用力/冷静度",
                                                "コミュニケーション能力","チーム貢献度"]
                                    },
                                    groups: [
                                            ["勤務誠実度", "仕事熱意度", "責任感", 
                                            "業務理解力", "技術習得力", "応用力/冷静度",
                                            "コミュニケーション能力","チーム貢献度"]
                                            ],
                                         
                                type: 'bar'
                                },
                                axis: {
                                    x: {
                                         type: 'category',
                                          tick: {
                                             rotate: 0,
                                              multiline: false
                                             },
                                             height: 50
                                    }
                                 },
                                tooltip: {
                                    grouped: false
                                }
                            });
                        }
</script> 
<style>
h1 {
  font-family: "Arial", "メイリオ";
  position: relative;
  color: #6495ed;
  line-height: 1.4;
  -webkit-box-reflect: below -20px -webkit-linear-gradient(top,rgba(0,0,0,0),rgba(0,0,0,0) 10%,rgba(0, 0, 0, 0.6));
}

	.table tr td{padding:0; text-align:left; padding-left:10px; vertical-align: middle;}
	.profiletable th {width:20%; padding:1px auto; }
	.table-secondary{background:#e9ecef;}
	.profiletable tr td{padding:1px 0px 1px 10px; text-align: left;}
	.table input{padding:0; margin:0; vertical-align: middle;}
	.table tr td textarea{padding:4px 7px;}
	.content{text-align: center; border:0;}
	.sort{text-align: left!important; padding-inline-start:10px!important;}
</style>   
</head>
<body onload ="typing()">
	<div style="margin-bottom:7px; margin-left:15%;">
		<h1><i class="caret square left icon"></i>
			<c:if test="${page_type=='self_appraisal'}">
				自己評価
			</c:if>
			<c:if test="${page_type=='leader_appraisal'}">
				リーダー評価
			</c:if>
		</h1>
	</div>
<form name="self_n_leader" method="post">
	<table class="table table-bordered profiletable" style="width:70%; margin:0 auto;">
            <tr>
            	<th class="table-info" style="width:20%;">
            		プロジェクト名
            	</th>
            	<td>
            		<input type = "text" class="form-control-plaintext disis" name = "field_name" value="${dto.field_name}" disabled size="40"/>
            		<input type = "hidden"  name = "field_code" value="${dto.field_code}"/>
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		評価期間
            	</th>
         		 <td>
         		   <input type = "text" class="form-control-plaintext disis" name = "conduct_start_month" value="${dto.conduct_start_month}" style="width:90px; float:left;" disabled/>
         		   <span style="line-height: 1.5; float:left; text-align:center; vertical-align: middle;">~&nbsp;</span>
         		   <input type = "text" class="form-control-plaintext disis" name = "conduct_end_month" value="${dto.conduct_end_month}" style="width:90px; float:left;" disabled/>
				</td>
			</tr>        
            <tr>
            	<th class="table-info" style="border-bottom:none;">
            		作業場所
            	</th>
           	<td>
           		 <input type = "text" class="form-control-plaintext disis" name = "field_addr" value="${dto.field_addr}" disabled/>
           	</td>
      		</tr>
            <tr>
	            <th class="table-info" style="background:#afffb9; border:3px solid #afffb9;">
	            	作業役割
	            </th>
            	<td style="padding:0 7px; border:3px solid #afffb9;">
            		<input type = "text" class="form-control-plaintext disis" name = "field_role" value="${dto.field_role}" autofocus="autofocus"/>
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		開発環境
            	</th>
            	<td>
            		<input type = "text" class="form-control-plaintext disis" name = "field_env" value="${dto.field_env}" disabled/>
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		開発ツール
            	</th>
            	<td>
            		<input type = "text" class="form-control-plaintext disis" name ="field_tool" value="${dto.field_tool}" disabled/>
            	</td>
            </tr>
              </table>
             <table class="table table-bordered profiletable" style="width:70%; height:10%; margin:3px auto;">
           		 <tr>
             	   <th class="table-info" style="width:10%;">
             	   		氏名/ 社番
             	   	</th>
                	<td>
                  		<input type = "text" class="form-control-plaintext" name = "emp_name" value="${dto.emp_name} / ${dto.emp_id}" disabled>
                  		<input type = "hidden" name = "emp_id" value="${dto.emp_id}">
                	</td>
                	<th class="table-info" style="width:10%;">
                		職位
                	</th>
                	<td style="width:15%;">
                		<input type = "text" class="form-control-plaintext disis"  class="form-control" name = "field_position_name" value="${dto.field_position_name}" disabled/>
                		<input type = "hidden" name = "field_position_code" value="${dto.field_position_code}"/>
                	</td>
                	<th class="table-info" style="width:10%; padding:10px;">
                		評価者
                	</th>
                	<td>
                		<input type = "text" class="form-control-plaintext disis" id="appriaiser_name" name = "appriaiser_name" value="${dto.appriaiser_name}" disabled>
                		<input type="hidden" id="appriaiser_id" name="appriaiser_id" value="${dto.appriaiser_id}"/>
                		<input type="hidden" name="appraisal_idx" value="${dto.appraisal_idx}">
                	</td>
         	   </tr>
         	 </table> 
            <table class="table table-bordered" style="width:70%; margin:3px auto;" >
                <thead class="table-info" style="text-align: center;">
                        <tr>
                            	<th colspan="2">評価項目(0~10)</th>
                                <th style="width:8%" class="selfAppr">自己評価</th>
                                <th style="width:8%" class="leaderAppr">リーダー<br>評価</th>
                                <th>評価内容</th>
                                <td style="border-bottom: none; background-color:#FFF;"></td>
                        </tr>
                </thead>
                <tbody>
                        <tr>    
                        	<th class="table-info table-bordered" rowspan="6">業務<br/>能力</th>
                            <td class="table-info table-bordered" style="text-align:left">勤務誠実度</td>
                            <td><input type="number" id ="me1" class="form-control content" name="self_diligence" value ="${dto.self_diligence}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10" >
                            </td>
                            <td><input type="number" id ="leader1" class="form-control content" name="ld_diligence" value="${dto.ld_diligence}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10" ></td>
                            <td class="sort">遅刻、怠慢など、仕事に対する心構え</td>
                            <td rowspan="9" style="width: 300px; border-top: none;">
                            		<div id="chart" style="float: bottom;"></div>
                            </td>
                        </tr>
                        <tr>
                            <td class="table-info" style="text-align:left">仕事熱意度</td>
                            <td><input type="number" id = "me2" class="form-control content" name="self_passion" value="${dto.self_passion}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td><input type="number" id = "leader2" class="form-control content" name="ld_passion" value="${dto.ld_passion}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td class="sort">仕事に対する熱意。肯定・否定的の程度</td>
                        </tr>
                        <tr>
                            <td class="table-info" style="text-align:left">責任感</td>
                            <td><input type="number" id = "me3" class="form-control content" name="self_responsibility" value="${dto.self_responsibility}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td><input type="number" id = "leader3" class="form-control content" name="ld_responsibility" value="${dto.ld_responsibility}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td class="sort">与えれた任務に対する責任感</td>
                        </tr>
                        <tr>    
                            <td class="table-info" style="text-align:left">業務理解力</td>
                            <td><input type="number" id = "me4" class="form-control content" name="self_understand" value="${dto.self_understand}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td><input type="number" id = "leader4" class="form-control content" name="ld_understand" value="${dto.ld_understand}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td class="sort">任務と関連する業務に関する理解の程度</td>
                        </tr>
                        <tr>    
                            <td class="table-info" style="text-align:left">技術習得力</td>
                            <td><input type="number" id = "me5" class="form-control content" name="self_learning" value="${dto.self_learning}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td><input type="number" id = "leader5" class="form-control content" name="ld_learning" value="${dto.ld_learning}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td class="sort">新たな技術に対する理解、熟練などの習得力</td>
                        </tr>
                        <tr>    
                            <td class="table-info" style="height:10%;text-align:left">応用力/冷静度</td>
                            <td><input type="number" id = "me6" class="form-control content" name="self_application" value="${dto.self_application}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td><input type="number" id = "leader6" class="form-control content" name="ld_application" value="${dto.ld_application}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td class="sort">習得した技術の応用力<br>
                                軽率せず、冷静に考えて行動する程度</td>
                        </tr>
                        <tr>    
                            <td class="table-info" colspan="2" style="text-align:left">コミュニケーション能力</td>
                            <td><input type="number" id = "me7" class="form-control content" name="self_communication" value="${dto.self_communication}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td><input type="number" id = "leader7" class="form-control content" name="ld_communication" value="${dto.ld_communication}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td class="sort">日本人担当者との積極的な業務会話能力</td>
                        </tr>
                        <tr>    
                            <td class="table-info" colspan="2" style="text-align:left">チーム貢献度</td>
                            <td><input type="number" id = "me8" class="form-control content" name="self_contribution" value="${dto.self_contribution}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td><input type="number" id = "leader8" class="form-control content" name="ld_contribution" value="${dto.ld_contribution}" onkeyup="checkForNumber(); typing();" onchange="checkForNumber(); typing();" maxlength="2" placeholder="0~10"/></td>
                            <td class="sort">チームに貢献した程度</td>
                        </tr>
                        <tr>    
                            <td class="table-info" colspan="2">合計</td>
                            <td style="background:#f2f7eb;" ><input type="number" id = "meTotals" class="form-control content disis" name="self_sum" disabled style="background:#f2f7eb;"/></td>
                            <td style="background:#f2f7eb;" ><input type="number" id = "leaderTotals" class="form-control content disis" name="ld_sum" disabled style="background:#f2f7eb;"/></td>
                            <td></td>
                        </tr>
                </tbody>
            </table>
             <table class="table table-bordered" style="width:70%; margin:3px auto;"> 
                <thead class="table-info">
                         <tr>
                			<th colspan="2" style="border-bottom: none; text-align:center;" align="center">長所</th>
                		</tr>
                        <tr>
                             <th class="selfAppr">自己評価</th>
                             <th class="leaderAppr">リーダー評価</th>
                        </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><textarea class = "form-control-plaintext" id = "me9" name="self_advantage" style="resize: none; width: 100%; height: 100%;"  maxlength="500" rows="5" placeholder="500桁以内">${dto.self_advantage}</textarea></td>
                        <td><textarea class = "form-control-plaintext" id = "leader9" name="ld_advantage" style="resize: none; width: 100%; height: 100%;" maxlength="500" rows="5" placeholder="500桁以内">${dto.ld_advantage}</textarea></td>
                    </tr>
                </tbody>
            </table>
            <table class="table table-bordered" style="width:70%; margin:3px auto;"> 
                <thead class="table-info">
                		<tr>
                			<th colspan="2" style="border-bottom: none; text-align:center;">短所</th>
                		</tr>
                        <tr>
                             <th class="selfAppr">自己評価</th>
                             <th class="leaderAppr">リーダー評価</th>
                        </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><textarea class = "form-control-plaintext" id = "me10" name="self_weak" style="resize: none; width: 100%; height: 100%;" maxlength="500" rows="5" placeholder="500桁以内">${dto.self_weak}</textarea></td>
                        <td><textarea class = "form-control-plaintext" id = "leader10" name="ld_weak" style="resize: none; width: 100%; height: 100%;" maxlength="500" rows="5" placeholder="500桁以内">${dto.ld_weak}</textarea></td>
                    </tr>
                </tbody>
            </table>
           <table class="table table-bordered" style="width:70%; margin:3px auto;"> 
                <thead  class="table-info">
               			 <tr>
                			<th colspan="2" style="border-bottom: none; text-align:center;">総合平価</th>
                		</tr>
                        <tr>
                             <th class="selfAppr">自己評価</th>
                             <th class="leaderAppr">リーダー評価</th>
                        </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><textarea class = "form-control-plaintext" id = "me11" name="self_eval" style="resize: none; width: 100%; height: 100%;" maxlength="500" rows="5" placeholder="500桁以内">${dto.self_eval}</textarea></td>
                        <td><textarea class = "form-control-plaintext" id = "leader11" name="ld_eval" style="resize: none; width: 100%; height: 100%;" maxlength="500" rows="5" placeholder="500桁以内">${dto.ld_eval}</textarea></td>
                    </tr>
                    
                </tbody>
            </table>
                 <div align="center" style="margin:7px auto;">
                 	<button id="sb_btn" class="ui primary button" type="button" onclick="beforesubmit()" >登録</button>
                 	<button class="ui button" type="button"onclick="formBack()">戻る</button>
                </div>
                <div class="ui">
					<div class="ui button left floated" onclick="locationURL()">
						<i class="left chevron icon"></i>戻る
					</div>
				</div>		
                <br/>
</form>
</body>
</html>
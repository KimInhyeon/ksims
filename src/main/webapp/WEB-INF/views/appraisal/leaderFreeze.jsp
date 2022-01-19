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
                history.back();//메인으로가기
            }
    </script>
<script>

    //숫자 이외 입력 방지
   
   function typing(){//그래프 및 입력 받은 값 화면에 출력
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
                                        "勤務誠実度": '${dto.self_diligence}',
                                        "仕事熱意度": '${dto.self_passion}',
                                        "責任感":	 '${dto.self_responsibility}',
                                        "業務理解力": '${dto.self_understand}',
                                        "技術習得力": '${dto.self_learning}',
                                        "応用力/冷静度":'${dto.self_application}',
                                        "コミュニケーション能力":'${dto.self_communication}',
                                        "チーム貢献度": '${dto.self_contribution}'},

                                    { "x-axis": "リーダー評価",//책임자아이디
                                        	"勤務誠実度": '${dto.ld_diligence}',
                                            "仕事熱意度": '${dto.ld_passion}',
                                            "責任感": '${dto.ld_responsibility}',
                                            "業務理解力": '${dto.ld_understand}',
                                            "技術習得力": '${dto.ld_learning}',
                                            "応用力/冷静度":  '${dto.ld_application}',
                                            "コミュニケーション能力":  '${dto.ld_communication}',
                                            "チーム貢献度": '${dto.ld_contribution}'},
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

	.table tr td{padding:0; text-align:center;}
	.table-secondary{background:#e9ecef;}
	.profiletable th {width:20%; padding:1px auto;}
	.profiletable tr td{padding:0 0 0 8px; text-align: justify;vertical-align: middle;}
	._cmt{white-space: pre-line; word-break: break-all; padding: 1px; text-align: justify; vertical-align: text-top; overflow-y:scroll; height:140px;}
	.chart_table tbody tr td{vertical-align: middle; padding:0;}
	.sort{text-align: left!important; padding-inline-start:10px!important;}
	.sort_r{text-align: end!important; padding-inline-end:10px!important;}
</style>   
</head>
<body onload ="typing()">
	<div style="margin-left:15%;">
		<h1><i class="caret square left icon"></i>
			<c:if test="${page_type=='self_appraisal'}">
				自己評価
			</c:if>
			<c:if test="${page_type=='leader_appraisal'}">
				リーダー評価
			</c:if>
		</h1>
	</div>
	<table class="table table-bordered profiletable" style="width:70%; margin:3px auto;  ">
            <tr>
            	<th class="table-info" style="width:20%;">
            		プロジェクト名
            	</th>
            	<td>
            		${dto.field_name}
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		評価期間
            	</th>
         		 <td>
         		   ${dto.conduct_start_month}
         		   ~
         		   ${dto.conduct_end_month}
				</td>
			</tr>        
            <tr>
            	<th class="table-info">
            		作業場所
            	</th>
           	<td>
           		${dto.field_addr}
           	</td>
      		</tr>
            <tr>
	            <th class="table-info">
	            	作業役割
	            </th>
            	<td>
            		${dto.field_role}
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		開発環境
            	</th>
            	<td>
            		${dto.field_env}
            	</td>
            </tr>
            <tr>
            	<th class="table-info">
            		開発ツール
            	</th>
            	<td>
            		${dto.field_tool}
            	</td>
            </tr>
              </table>
             <table class="table table-bordered profiletable" style="width:70%; height:10%; margin:3px auto;">
           		 <tr>
             	   <th class="table-info" style="width:10%;">
             	   		氏名/ 社番
             	   	</th>
                	<td>
                  		${dto.emp_name} / ${dto.emp_id}
                	</td>
                	<th class="table-info" style="width:10%;">
                		職位
                	</th>
                	<td style="width:15%;">
                		${dto.field_position_name}
                	</td>
                	<th class="table-info" style="width:10%;">
                		評価者
                	</th>
                	<td style="width:27.5%;">
                		${dto.appriaiser_name}
                	</td>
         	   </tr>
         	 </table> 
            <table class="table table-bordered chart_table" style="width:70%; margin:3px auto; text-align: center;" >
                <thead class="table-info  table-hover">
                        <tr>
                           		<th colspan="2">評価項目(0~10)</th>
                                <th style="width:8%">自己評価</th>
                                <th style="width:8%">リーダー<br>評価</th>
                                <th>評価内容</th>
                                <td style="border-bottom: none; background-color:#FFF;"></td>
                        </tr>
                </thead>
                <tbody align="char">
                        <tr>    
                        	<th class="table-info table-hover" rowspan="6">業務<br/>能力</th>
                            <td class="table-info table-hover" style="text-align:left">勤務誠実度</td>
                            <td class="sort_r">${dto.self_diligence}</td>
                            <td class="sort_r">${dto.ld_diligence}</td>
                            <td class="sort">遅刻、怠慢など、仕事に対する心構え</td>
                            <td rowspan="9" style="width: 300px; border-top: none;">
                            		<div id="chart" style="float: bottom;"></div>
                            </td>
                        </tr>
                        <tr>
                            <td class="table-info"  style="text-align:left">仕事熱意度</td>
                            <td class="sort_r">${dto.self_passion}</td>
                            <td class="sort_r">${dto.ld_passion}</td>
                            <td class="sort">仕事に対する熱意。肯定・否定的の程度</td>
                        </tr>
                        <tr>
                            <td class="table-info"  style="text-align:left">責任感</td>
                            <td class="sort_r">${dto.self_responsibility}</td>
                            <td class="sort_r">${dto.ld_responsibility}</td>
                            <td class="sort">与えれた任務に対する責任感</td>
                        </tr>
                        <tr>    
                            <td class="table-info"  style="text-align:left">業務理解力</td>
                            <td class="sort_r">${dto.self_understand}</td>
                            <td class="sort_r">${dto.ld_understand}</td>
                            <td class="sort">任務と関連する業務に関する理解の程度</td>
                        </tr>
                        <tr>    
                            <td class="table-info"  style="text-align:left">技術習得力</td>
                            <td class="sort_r">${dto.self_learning}</td>
                            <td class="sort_r">${dto.ld_learning}</td>
                            <td class="sort">新たな技術に対する理解、熟練などの習得力</td>
                        </tr>
                        <tr>    
                            <td class="table-info" style="height:10%;text-align:left">応用力/冷静度</td>
                            <td class="sort_r">${dto.self_application}</td>
                            <td class="sort_r">${dto.ld_application}</td>
                            <td class="sort">習得した技術の応用力<br>
                          		      軽率せず、冷静に考えて行動する程度</td>
                        </tr>
                        <tr>    
                            <td class="table-info" colspan="2" style="text-align:left">コミュニケーション能力</td>
                            <td class="sort_r">${dto.self_communication}</td>
                            <td class="sort_r">${dto.ld_communication}</td>
                            <td class="sort">日本人担当者との積極的な業務会話能力</td>
                        </tr>
                        <tr>    
                            <td class="table-info" colspan="2" style="text-align:left">チーム貢献度</td>
                            <td class="sort_r">${dto.self_contribution}</td>
                            <td class="sort_r">${dto.ld_contribution}</td>
                            <td class="sort">チームに貢献した程度</td>
                        </tr>
                        <tr>    
                            <td class="table-info" colspan="2">合計</td>
                            <td class="sort_r">${dto.self_sum}</td>
                            <td class="sort_r">${dto.ld_sum}</td>
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
                             <th style="width:50%;">自己評価</th>
                             <th>リーダー評価</th>
                        </tr>
                </thead>
                <tbody>
                    <tr style="height:100px;">
                        <td><p class="_cmt">${dto.self_advantage}</p></td>
                        <td><p class="_cmt">${dto.ld_advantage}</p></td>
                    </tr>
                </tbody>
            </table>
            <table class="table table-bordered" style="width:70%; margin:3px auto;"> 
                <thead class="table-info">
                		<tr>
                			<th colspan="2" style="border-bottom: none; text-align:center;">短所</th>
                		</tr>
                        <tr>
                             <th style="width:50%;">自己評価</th>
                             <th>リーダー評価</th>
                        </tr>
                </thead>
                <tbody>
                    <tr style="height:100px;">
                        <td><p class="_cmt">${dto.self_weak}</p></td>
                        <td><p class="_cmt">${dto.ld_weak}</p></td>
                    </tr>
                </tbody>
            </table>
           <table class="table table-bordered" style="width:70%; margin:3px auto;"> 
                <thead  class="table-info">
               			 <tr>
                			<th colspan="2" style="border-bottom: none; text-align:center;">総合平価</th>
                		</tr>
                        <tr>
                             <th style="width:50%;">自己評価</th>
                             <th>リーダー評価</th>
                        </tr>
                </thead>
                <tbody>
                    <tr style="height:200px;">
                        <td><p class="_cmt">${dto.self_eval}</p></td>
                        <td><p class="_cmt">${dto.ld_eval}</p></td>
                    </tr>
                    
                </tbody>
            </table><br/>
				<div class="ui" style="margin-left:15%;">
					<div class="ui button left floated" onclick="formBack()">
						<i class="left chevron icon"></i>戻る
					</div>
				</div>
						                
</body>
</html>
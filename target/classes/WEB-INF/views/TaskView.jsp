<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
			// inline JavaScript here
		</script>
		
		<style>
table {
	
	text-align: left;
}

#task{
text-align: left;
}
</style>
  	</jsp:attribute>
	<jsp:body>
	
	
	<form:form method="post" action="addEmployee"
			modelAttribute="assignTaskEntity">
		
				<h3> View Tasks</h3>
				
			<p>	Filter By Project <form:select path="projectName" id="projectId"
				onchange="getDetails(this.value)">
					<option value="0">ALL</option>
					<option th:each="dropd : ${drpdwn}" th:value="${dropd.id}"
					th:text="${dropd.tagName}"></option>
				</form:select>
				</p>
				<br>
				
				
				
				<!-- For Updating the record -->
					<div id="list"></div>
				</form:form>
	
	
	
		
		<script type="text/javascript">
		var finalIndex = 0;
		var employeeData = '';
			$(document).ready(
							function() { // for client name 

								// country = define value of selected option
								$.getJSON('http://localhost:8081/TaskMng/api/assign/getProjects',
												{},function(data) {
													var select = $('#projectId');
													select.find('option').remove();
													$('<option>').val(0).text("ALL").appendTo(select);
													$.each(data,function(key,value) {
														$('<option>').val(key + 1).text(value).appendTo(select);
														finalIndex = key + 1
												});
								});
							
								getDetails(0);
							});
			
			function getTasks(elementIdIndex) {
				return new Promise((resolve, reject) => {
				$.ajax({
							type : "GET",
							url : 'http://localhost:8081/TaskMng/project?projId='
									+ elementIdIndex,
							data : ({}),
							dataType : 'json',
							success : function(items) {
								var data='';
							if(items['task_list'].length!=0){

							
								if($(".details").length > 0) {
									  while($(".details").length > 0)
								 			$('.details').remove();
								}
								var projectName='';
								var finalData='';
								/* var temp = document.createElement("div");
								temp.className = "details"; */
								var d = 0;
								for(var k =0 ; k<items['task_list'].length ;k++){
									data= '';
									 projectName ='<strong><spring:message code="tsk.project" />: '+items['projects'][k]+' </strong><br>'; 
								for (var i = 0; i < items['task_list'][k].length; i++) {
								 	
									var tasks ='';
									tasks = tasks+ '<tr>'+
									'							<td> <spring:message code="tsk.description" />: '+
									'								'+ items['task_list'][k][i].description+'<td>'+
									'								'+
									'								</tr>	<tr>'+
									'						<td><spring:message code="tsk.startDate" />: '+ items['task_list'][k][i].startDate+'</td>'+
									'								'+
									'									</tr>	<tr>'+
									'						<td><spring:message code="tsk.endDate" />: '+ items['task_list'][k][i].endDate+'</td> </tr>';
								
								
								
								 var employees = '';
								 for(var j = 0; j < items['employee_list'][d].length; j++){

									 employees = employees +'						<tr>'+
									 '									 <td>'+items['employee_list'][d][j].emp_id+' </td>'+
									 '									 <td>'+items['employee_list'][d][j].emp_name+' </td>'+
									 '									 </tr>';
									 
									 	

								 }
								 
								 var taskTable ='<br><table id="task">'+
															
								 '							<tbody>'+ tasks +
								 '							</tbody>'+
								 '							</table>';
								 
								 
								 var table='<table border="1" >'+
								 '							<thead>'+
								 '								<tr>'+
								 '									<th><spring:message code="tsk.mid" /></th>'+
								 '								    <th><spring:message code="tsk.employeeName" /></th>'+
								 '								</tr> '+
								 '								</thead>'+
								 '							<tbody>'+ employees +
								 '							</tbody>'+
								 '							</table>';
								 
								 
								 
								 data = data+ taskTable +table + '<br>';
								 d++;
								}
								
								/* temp.innerHTML = data ;
							        document.getElementById("list").appendChild(temp); */
							        if(data!='')
								finalData= finalData + projectName + data +'<br> <br>';
								
							}
								data = finalData;
							}
							else{
								if($(".details").length > 0) {
									  while($(".details").length > 0)
								 			$('.details').remove();
								}
							}
								
							resolve(data);
							},
							error : function(e) {
								alert(e);
								alert(JSON.stringify(e));
								 reject(e);
							},
						});

			})
			}
			
			function getDetails(elementIndex) {
				var employeeDetails= '';
				employeeData ='';
					getTasks(elementIndex).then(data => {
						var temp = document.createElement("div");
						temp.className = "details";
						temp.innerHTML = data ;
				        document.getElementById("list").appendChild(temp);
					});
					
				}
				
		
			
			
		</script>
		
	</jsp:body>
</tags:template>
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
.error {
	color: red;
}
</style>
  	</jsp:attribute>
	<jsp:body>
		
		<form:form method="post" action="addEmployee"
			modelAttribute="assignTaskEntity">
		<table>
			<tbody>
			
				<tr>
				<td>
				<spring:message code="lbl.project" />
				</td>
				
				<td>
				<form:select path="TaskEntity.proj_id" id="projectId"
								onchange="getListOfEmployees(this.value)">
					<option value="NONE">Select</option>
					<option th:each="dropd : ${drpdwn}" th:value="${dropd.id}"
									th:text="${dropd.tagName}"></option>
				</form:select>
				</td>
					
				</tr>
				
					<tr>
				<td>
				<spring:message code="lbl.description" />
				</td>
				
				<td>
				<form:input path="TaskEntity.description" />
				<form:errors path="taskEntity.description" cssClass="error" />
				</td>
					
				</tr>
				
					<tr>
				<td>
				
				<spring:message code="lbl.startDate" />
				</td>
				
				<td>
				<form:input path="TaskEntity.startDate" />
				<form:errors path="taskEntity.startDate" cssClass="error" />
				</td>
					
				</tr>
				
				
				<tr>
				<td>
					<spring:message code="lbl.endDate" />
				</td>
				
				<td>
				<form:input path="TaskEntity.endDate" />
				<form:errors path="taskEntity.endDate" cssClass="error" />
				</td>
					
				</tr>
				
					<tr>
				<td>
				<spring:message code="lbl.whoCan" />
				</td>
				
				<td>
				<form:select path="employees" id="employees" multiple="true">
 					<option value="NONE">Select</option>
					<option th:each="dropd : ${drpdwn}" th:value="${dropd.id}"
									th:text="${dropd.tagName}"></option>
				</form:select>
				</td>
					
				</tr>
				<tr>
				
				<td>
				<button type="submit">	<spring:message code="lbl.addTask" />
							</button>
				</td>
				
				<td>
				<button type="reset">
								<spring:message code="lbl.cancel" />
							</button>
				</td>
					
				</tr>
				
				
			
			</tbody>
		
		</table>
		
		</form:form>
		
		<script>
			$(document)
					.ready(
							function() { // for client name 

								// country = define value of selected option
								$
										.getJSON(
												'http://localhost:8081/TaskMng/api/assign/getProjects',
												{},
												function(data) {
													var select = $('#projectId');
													select.find('option')
															.remove();
													$
															.each(
																	data,
																	function(
																			key,
																			value) {
																		$(
																				'<option>')
																				.val(
																						key + 1)
																				.text(
																						value)
																				.appendTo(
																						select);
																	});
												});
								getListOfEmployees(1);
							});

			function getListOfEmployees(elementIdIndex) {
				$
						.ajax({
							type : "GET",
							url : 'http://localhost:8081/TaskMng/api/assign/getEmployee?projectID='
									+ elementIdIndex,
							data : ({}),
							dataType : 'json',
							success : function(items) {
								/* alert("Sucess: " + JSON.stringify(items)); */

								var select = $('#employees');
								select.find('option').remove();
								$.each(items, function(key, value) {
									$('<option>').val(value).text(value)
											.appendTo(select);
								});

							},
							error : function(e) {
								alert(e);
								alert(JSON.stringify(e));
							},
						});

			}
		</script>
		
	</jsp:body>
</tags:template>
<#macro exam_quicklink title verb exams>
	<#list exams>
		<h2>${title}</h2>
		<table class="table table-striped table-hover">
			<#items as exam>
				<tr>
					<td><a href="/exams/${exam.id}">${exam.module.name}</a><td>
					<td><a href="/exams/${exam.id}/query-by-exercises/">Aufgabenweise ${verb}</a><td>
					<td><a href="/exams/${exam.id}/query-by-participations/">Prüfungsweise ${verb}</a><td>
				</tr>
			</#items>
		</table>
	</#list>
</#macro>
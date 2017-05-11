<#macro exam_quicklink title exams>
	<#list exams>
	<h2>${title}</h2>
	<table class="table table-striped table-hover">
	<#items as exam>
		<tr>
			<td><a href="/exams/${exam.id}">${exam.module.name}</a><td>
			<td><a href="#">Aufgabenweise korrigieren</a><td>
			<td><a href="#">Prüfungsweise korrigieren</a><td>
		</tr>
	</#items>
	</table>
	</#list>
</#macro>
<#include "bodyTemplate.ftl">

<#macro body_main>
	<header>
		<h1>Dashboard</h1>
	</header>
	
	<h2>Meine Prüfungen</h2>
	<#list exams>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>						
					<th>Modul</th>
					<th>Dozent</th>
					<th>Status</th>
					<th>Hilfsmittel</th>
				</tr>
			</thead>
			<tbody>
				<#items as exam>
				<tr>
					<td><a href="/exams/${exam.module.id}">${exam.module.name}</a></td>
					<td>${exam.author.firstName} ${exam.author.lastName}</td>
					<td>${exam.state}</td>
					<td>
						<#list exam.allowedUtilities as allowedUtility>
							${allowedUtility}
						</#list>
					</td>
				</tr>
				</tr>
				</#items>
			</tbody>
		</table>
	<#else>
		<p>keine Prüfungen gefunden</p>
	</#list>
</#macro>

<@display_page/>
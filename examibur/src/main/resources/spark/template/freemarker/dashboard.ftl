<#include "bodyTemplate.ftl">

<#macro body_main>
	<header>
	<h1>Dashboard</h1>
	</header>

	<p>Keine offenen Aufgaben gefunden</p>
	<a href="/exams/1/participants/1">Abschlussprüfung SE2 FS2017</a>
	
	<h2>Prüfungen</h2>
	<#list exams as exam>
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
				<tr>
					<td>${exam.module.name}</td>
					<td>${exam.author.firstName} ${exam.author.lastName}</td>
					<td>${exam.state}</td>
					<td>
						<#list exam.allowedUtilities as allowedUtility>
							${allowedUtility}
						</#list>
					</td>
				</tr>
				</tr>
			</tbody>
		</table>
	<#else>
		<p>keine Prüfungen gefunden</p>
	</#list>
</#macro>

<@display_page/>
<#include "../templates/bodyTemplate.ftl">

<#macro body_main>
	<header>
	<h1>${participation.pseudonym}</h1>
	</header>

	<div class="top-navigation pull-right">
	<button class="btn btn-default" type="submit">Teilnahme korrigieren</button>
	</div>

	<ul class="nav nav-tabs">
	<li role="presentation"><a href="..">Informationen</a></li>
	<li role="presentation" class="active"><a href="#">Aufgaben</a></li>
	<li role="presentation"><a href="#">Auswertung</a></li>
	</ul>	

	<div class="tab-body">
	<#list exerciseSolutions>			
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>						
					<th>Nr</th>
					<th>Titel</th>
					<th>Max. Punkte</th>
					<th>Erreicht</th>
					<th>Status</th>
					<th>Erledigt</th>
				</tr>
			</thead>
			<tbody>
				<#items as es>
				<tr>
					<th scope="row"><a href="${es.id}">${es.exercise.orderInExam}</th>
					<td><a href="#"><a href="${es.id}">${es.exercise.title}</a></td>
					<td>${es.exercise.maxPoints}</td>
					<td>8</td>
					<td>${es.participation.exam.state}</td>
					<td>
						<#if es.isDone()>
							Ja
						<#else>
							Nein
						</#if>
					</td>
				</tr>
				</#items>
			</tbody>
		</table>
	<#else>
		<p>Keine Aufgaben gefunden</p>
	</#list>
	</div>
</#macro>

<@display_page/>
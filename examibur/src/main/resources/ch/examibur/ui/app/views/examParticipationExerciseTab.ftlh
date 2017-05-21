<#include "../templates/bodyTemplate.ftlh">

<#macro body_main>
	<#local examState = participation.exam.state>
	<header>
		<h1>${participation.pseudonym}</h1>
	</header>

	<div class="top-navigation pull-right">
		<#if examState == "CORRECTION">
			<a class="btn btn-default" href="../query-by-participation/" role="button" id="query-by-participation">Teilnahme korrigieren</a>
		<#elseif examState == "REVIEW" || examState == "APPROVAL">
			<a class="btn btn-default" href="../query-by-participation/" role="button" id="query-by-participation">Teilnahme reviewen</a>
		</#if>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation"><a href="..">Informationen</a></li>
		<li role="presentation" class="active"><a href="#">Aufgaben</a></li>
	</ul>	

	<div class="tab-body">
	<#list exerciseSolutionOverviews>			
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
				<#items as overview>
				<tr>
					<th scope="row"><a href="${overview.exerciseSolution.id}">${overview.exerciseSolution.exercise.orderInExam}</a></th>
					<td><a href="${overview.exerciseSolution.id}">${overview.exerciseSolution.exercise.title}</a></td>
					<td>${overview.exerciseSolution.exercise.maxPoints}</td>
					<td>
						<#if overview.points.isPresent()>
							${overview.points.get()}
						<#else>
							-
						</#if>
					</td>
					<td>${overview.exerciseSolution.participation.exam.state}</td>
					<td>
						<#if overview.exerciseSolution.isDone()>
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
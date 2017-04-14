<#include "bodyTemplate.ftl">

<#macro body_main>
	<header>
		<h1>Prüfung ${exam.module.name}</h1>
	</header>

	<div class="top-navigation pull-right">
		<button class="btn btn-default" type="submit">Prüfung korrigieren</button>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation"><a href="../">Informationen</a></li>
		<li role="presentation" class="active"><a href="#">Teilnahmen</a></li>
		<li role="presentation"><a href="../exercises">Aufgaben</a></li>
		<li role="presentation"><a href="#">Auswertung</a></li>
	</ul>	

	<div class="tab-body">
		<div class="row">
			<div class="col-md-12">
					<#list participantsOverview>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>						
								<th>Prüfling</th>
								<th>Punkte</th>
								<th>Note</th>
								<th>Bestanden</th>
								<th>Status</th>
								<th>Fortschritt</th>
							</tr>
						</thead>
						<tbody>
							<#items as participantOverview>
							<tr>
								<td><a href="/exams/${exam.id}/participants/${participantOverview.examParticipation.participant.id}">${participantOverview.examParticipation.pseudonym}</a></td>
								<td>${participantOverview.totalPoints}</td>
								<td>${participantOverview.grading}</td>
								<td>
								<#if participantOverview.grading gte 3.75 >
								  Ja
								<#else>
								  Nein
								</#if>
								</td>
								<td></td>
								<td></td>
							</tr>
							</#items>
						</tbody>
					</table>
				<#else>
					<p>keine Teilnahmen gefunden</p>
				</#list>
			</div>
		</div>					
	</div>
</#macro>

<@display_page/>
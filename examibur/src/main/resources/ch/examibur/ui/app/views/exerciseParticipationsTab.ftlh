<#include "../templates/bodyTemplate.ftlh">

<#macro body_main>
	<header>
		<h1>Prüfung ${exam.module.name}</h1>
	</header>

	<div class="top-navigation pull-right">
		<form method="get">
			<input type="hidden" name="browse" value="participations">
			<input type="hidden" name="querynext">
			<button type="submit" class="btn btn-default">Prüfungsweise korrigieren</button>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation"><a href="../">Aufgabenstellung</a></li>
		<li role="presentation" class="active"><a href="#">Teilnahmen</a></li>
	</ul>	

	<div class="tab-body">
		<div class="row">
			<div class="col-md-12">
				<#list exerciseParticipantsOverview>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>						
								<th>Prüfling</th>
								<th>Punkte</th>
								<th>Note</th>
								<th>Erledigt</th>
							</tr>
						</thead>
						<tbody>
							<#items as exerciseParticipantOverview>
							<tr>
								<td><a href="/exams/${exam.id}/participants/${exerciseParticipantOverview.exerciseSolution.participation.id}">${exerciseParticipantOverview.exerciseSolution.participation.pseudonym}</a></td>
								<td>${exerciseParticipantOverview.totalPoints}</td>
								<#if exerciseParticipantOverview.grading.isPresent()>
									<td>
										${exerciseParticipantOverview.grading.get()}
									</td>
								<#else>
									<td>-</td>
								</#if>	
								<td>
									${exerciseParticipantOverview.exerciseSolution.isDone()?string('Ja', 'Nein')}								
								</td>
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
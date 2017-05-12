<#include "../templates/bodyTemplate.ftl">

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
		<li role="presentation"><a href="../">Informationen</a></li>
		<li role="presentation" class="active"><a href="#">Teilnahmen</a></li>
		<li role="presentation"><a href="../exercises">Aufgaben</a></li>
		<li role="presentation"><a href="../reports">Auswertung</a></li>
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
								<th>Fortschritt</th>
							</tr>
						</thead>
						<tbody>
							<#items as participantOverview>
							<tr>
								<td><a href="/exams/${exam.id}/participants/${participantOverview.examParticipation.id}">${participantOverview.examParticipation.pseudonym}</a></td>
								<td>${participantOverview.totalPoints}</td>
								<#if participantOverview.grading.isPresent()>
									<td>
										${participantOverview.grading.get()}
									</td>
									<td>
										<#if participantOverview.grading.get() gte 3.75 >
										  Ja
										<#else>
										  Nein
										</#if>
									</td>
								<#else>
									<td>-</td>
									<td>-</td>
								</#if>	
								<td>
									${participantOverview.formattedProgress}								
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
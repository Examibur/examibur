<#include "../templates/bodyTemplate.ftlh">
<#include "../includes/startGradingDropDown.ftlh">

<#macro body_main>
	<header>
		<h1>Prüfung ${exam.module.name}</h1>
	</header>

	<div class="top-navigation pull-right">
		<@start_grading_dropdown examState=exam.state relativePath="../"/>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation"><a href="../">Informationen</a></li>
		<li role="presentation"><a href="../participants">Teilnahmen</a></li>
		<li role="presentation" class="active"><a href="#">Aufgaben</a></li>
		<li role="presentation"><a href="../reports">Auswertung</a></li>
	</ul>	

	<div class="tab-body">
		<div class="row">
			<div class="col-md-12">
					<#list exercises>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>						
								<th>ID</th>
								<th>Titel</th>
								<th>Punkte</th>
								<th>Aufgabenstellung</th>
							</tr>
						</thead>
						<tbody>
							<#items as exercise>
							<tr>
								<td><a href="/exams/${exam.id}/exercises/${exercise.id}">${exercise.orderInExam}</a></td>
								<td><a href="/exams/${exam.id}/exercises/${exercise.id}">${exercise.title}</a></td>
								<td>${exercise.maxPoints}</td>
								<td class="td-ellipsis">${exercise.taskDescription}</td>
							</tr>
							</#items>
						</tbody>
					</table>
				<#else>
					<p>keine Aufgaben gefunden</p>
				</#list>
			</div>
		</div>					
	</div>
</#macro>

<@display_page/>
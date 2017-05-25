<#include "../templates/bodyTemplate.ftlh">
<#include "../includes/startGradingDropDown.ftlh">
<#include "../includes/participantComponent.ftlh">

<#macro body_main>
	<header>
		<h1>Prüfung ${exam.module.name}</h1>
	</header>

	<div class="top-navigation pull-right">
		<@start_grading_dropdown examState=exam.state relativePath="../"/>
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
				<@participant_list/>
			</div>
		</div>					
	</div>
</#macro>

<@display_page/>
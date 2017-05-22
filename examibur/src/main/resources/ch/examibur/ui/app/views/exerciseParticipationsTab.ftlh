<#include "../templates/bodyTemplate.ftlh">
<#include "../includes/participantComponent.ftlh">

<#macro body_main>
	<#local examState = exam.state>
	<header>
		<h1>Prüfung ${exam.module.name}</h1>
	</header>

	<div class="top-navigation pull-right">
		<#if examState == "CORRECTION">
			<a class="btn btn-default" href="../query-by-exercise/" role="button" id="query-by-exercise">Aufgabenweise korrigieren</a>
		<#elseif examState == "REVIEW">
			<a class="btn btn-default" href="../query-by-exercise/" role="button" id="query-by-exercise">Aufgabenweise reviewen</a>
		<#elseif examState == "APPROVAL">
			<a class="btn btn-default" href="../query-by-exercise/" role="button" id="query-by-exercise">Aufgabenweise approven</a>
		</#if>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation"><a href="../">Aufgabenstellung</a></li>
		<li role="presentation" class="active"><a href="#">Teilnahmen</a></li>
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
<#include "../templates/bodyTemplate.ftlh">

<#macro body_main>
	<#local examState = participation.exam.state>
	<header>
		<h1>${participation.pseudonym}</h1>
	</header>

	<div class="top-navigation pull-right">
		<#if examState == "CORRECTION">
			<a class="btn btn-default" href="query-by-participation/" role="button" id="query-by-participation">Teilnahme korrigieren</a>
		<#elseif examState == "REVIEW" || examState == "APPROVAL">
			<a class="btn btn-default" href="query-by-participation/" role="button" id="query-by-participation">Teilnahme reviewen</a>
		</#if>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#">Informationen</a></li>
		<li role="presentation"><a href="solutions">Aufgaben</a></li>
	</ul>	

	<div class="tab-body">
	</div>
</#macro>

<@display_page/>
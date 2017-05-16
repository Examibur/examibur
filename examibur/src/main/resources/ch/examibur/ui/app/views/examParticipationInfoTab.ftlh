<#include "../templates/bodyTemplate.ftlh">

<#macro body_main>
	<header>
	<h1>${participation.pseudonym}</h1>
	</header>

	<div class="top-navigation pull-right">
	<button class="btn btn-default" type="submit">Teilnahme korrigieren</button>
	</div>

	<ul class="nav nav-tabs">
	<li role="presentation" class="active"><a href="#">Informationen</a></li>
	<li role="presentation"><a href="solutions">Aufgaben</a></li>
	<li role="presentation"><a href="#">Auswertung</a></li>
	</ul>	

	<div class="tab-body">
	</div>
</#macro>

<@display_page/>
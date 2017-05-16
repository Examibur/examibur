<#include "../templates/bodyTemplate.ftlh">

<#macro body_main>
	<header>
		<h1>${participation.pseudonym}</h1>
	</header>

	<div class="top-navigation pull-right">
		<a class="btn btn-default" href="solutions/?browse=participation&querynext=" role="button" id="querynext">Teilnahme korrigieren</a>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#">Informationen</a></li>
		<li role="presentation"><a href="solutions">Aufgaben</a></li>
	</ul>	

	<div class="tab-body">
	</div>
</#macro>

<@display_page/>
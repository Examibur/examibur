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
		<li role="presentation"><a href="../participants">Teilnahmen</a></li>
		<li role="presentation"><a href="../exercises">Aufgaben</a></li>
		<li role="presentation" class="active"><a href="#">Auswertung</a></li>
	</ul>	

	<div class="tab-body">
		<div class="row">
			<div class="col-md-12">
					
			</div>
		</div>					
	</div>
</#macro>
<#macro page_scripts>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
</#macro>

<@display_page/>
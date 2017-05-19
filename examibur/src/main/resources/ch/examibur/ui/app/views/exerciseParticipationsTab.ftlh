<#include "../templates/bodyTemplate.ftlh">
<#include "../includes/participantComponent.ftlh">

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
				<@participant_list/>
			</div>
		</div>	
	</div>
</#macro>

<@display_page/>
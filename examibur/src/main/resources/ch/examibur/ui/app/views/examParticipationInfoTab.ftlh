<#include "../templates/bodyTemplate.ftlh">

<#macro body_main>
	<#local examState = participantOverview.examState>
	<header>
		<h1>${participantOverview.pseudonym}</h1>
	</header>

	<div class="top-navigation pull-right">
		<#if examState == "CORRECTION">
			<a class="btn btn-default" href="query-by-participation/" role="button" id="query-by-participation">Teilnahme korrigieren</a>
		<#elseif examState == "REVIEW">
			<a class="btn btn-default" href="query-by-participation/" role="button" id="query-by-participation">Teilnahme reviewen</a>
		<#elseif examState == "APPROVAL">
			<a class="btn btn-default" href="query-by-participation/" role="button" id="query-by-participation">Teilnahme approven</a>
		</#if>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#">Informationen</a></li>
		<li role="presentation"><a href="solutions">Aufgaben</a></li>
	</ul>	

	<div class="tab-body">
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Teilnahme</div>
					<div class="panel-body">
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Prüfling</label>
			    			</div>
				    		<div class="col-md-4">
				    			${participantOverview.pseudonym}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Erreichte Punktzahl</label>
				    		</div>
				    		<div class="col-md-4">
				    			${participantOverview.totalPoints}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Note</label>
				    		</div>
				    		<div class="col-md-4">
				    			<#if participantOverview.grading.isPresent()>
									<td>
										${participantOverview.grading.get()}
									</td>
								<#else>
									<td>-</td>
								</#if>	
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Status</label>
				    		</div>
				    		<div class="col-md-4">
				    			${participantOverview.examState}
				    		</div>
						</div>
				  	</div>
				</div>
			</div>
		<div>
	</div>
</#macro>

<@display_page/>
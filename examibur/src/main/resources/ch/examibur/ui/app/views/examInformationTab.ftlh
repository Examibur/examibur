<#include "../templates/bodyTemplate.ftlh">
<#include "../includes/startGradingDropDown.ftlh">

<#macro body_main>
	<header>
		<h1>Prüfung ${exam.module.name}</h1>
	</header>

	<div class="top-navigation pull-right">
		<@start_grading_dropdown examState=exam.state relativePath=""/>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#">Informationen</a></li>
		<li role="presentation"><a href="participants">Teilnahmen</a></li>
		<li role="presentation"><a href="exercises">Aufgaben</a></li>
		<li role="presentation"><a href="reports">Auswertung</a></li>
	</ul>	

	<div class="tab-body">
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Metadaten</div>
					<div class="panel-body">
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Ersteller</label>
				    		</div>
							<div class="col-md-8">
				    			${exam.author.firstName} ${exam.author.lastName}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Letze Änderung</label>
				    		</div>
							<div class="col-md-8">
				    			${exam.lastModified}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
								<label>Status</label>
							</div>
							<div class="col-md-3" id="exam-state">
								${exam.state.displayName}
							</div>
							<#if (exam.state == "CORRECTION" || exam.state == "REVIEW" || exam.state == "APPROVAL")>
								<div class="col-md-5">
									<div class="pull-right">
										<form method="POST">
											<button class="btn btn-primary" type="submit" id="start-transition">${exam.state.displayName} abschliessen</button>
										</form>
									</div>
								</div>
							</#if>
						</div>
				  	</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Prüfungsinformationen</div>
					<div class="panel-body">
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Bezeichnung</label>
				    		</div>
							<div class="col-md-8">
				    			Prüfung ${exam.module.name}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Modul</label>
				    		</div>
							<div class="col-md-8">
				    			${exam.module.name}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Durchführung</label>
				    		</div>
							<div class="col-md-8">
				    		</div>
						</div>
				  	</div>
				</div>
			</div>
		</div>			
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">Rahmenbedingungen</div>
					<div class="panel-body">
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Maximale Punktzahl</label>
				    		</div>
							<div class="col-md-8">
				    			${maxPoints}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Zeit (min)</label>
				    		</div>
							<div class="col-md-8">
				    			${exam.allowedTimeInMin}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Erlaubte Hilfsmittel</label>
				    		</div>
							<div class="col-md-8">
				    			<#list exam.allowedUtilities as allowedUtility>
									${allowedUtility}<#sep>,
								<#else>
									keine Hilfsmittel
								</#list>
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Bemerkung</label>
				    		</div>
							<div class="col-md-8">
				    		</div>
						</div>
				  	</div>
				</div>
			</div>
		</div>			
	</div>
</#macro>

<@display_page/>
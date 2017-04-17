<#include "bodyTemplate.ftl">

<#macro body_main>
	<header>
		<h1>Prüfung ${exam.module.name}</h1>
	</header>

	<div class="top-navigation pull-right">
		<button class="btn btn-default" type="submit">Prüfung korrigieren</button>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#">Informationen</a></li>
		<li role="presentation"><a href="#">Teilnahmen</a></li>
		<li role="presentation"><a href="#">Aufgaben</a></li>
		<li role="presentation"><a href="#">Auswertung</a></li>
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
				    		<div class="col-md-4">
				    			${exam.author.firstName} ${exam.author.lastName}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Letze Änderung</label>
				    		</div>
				    		<div class="col-md-4">
				    			${exam.lastModified}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Status</label>
				    		</div>
				    		<div class="col-md-4">
				    			${exam.state}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Prüfungseinsicht</label>
				    		</div>
				    		<div class="col-md-4">
				    		</div>
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
				    		<div class="col-md-4">
				    			Prüfung ${exam.module.name}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Modul</label>
				    		</div>
				    		<div class="col-md-4">
				    			${exam.module.name}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Durchführung</label>
				    		</div>
				    		<div class="col-md-4">
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
				    		<div class="col-md-4">
				    			${maxPoints}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Zeit (min)</label>
				    		</div>
				    		<div class="col-md-4">
				    			${exam.allowedTimeInMin}
				    		</div>
						</div>
				    	<div class="row">
				    		<div class="col-md-4">
				    			<label>Erlaubte Hilfsmittel</label>
				    		</div>
				    		<div class="col-md-4">
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
				    		<div class="col-md-4">
				    		</div>
						</div>
				  	</div>
				</div>
			</div>
		</div>			
	</div>
</#macro>

<@display_page/>
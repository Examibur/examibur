<#include "../templates/bodyTemplate.ftl">

<#macro body_main>
	<header>
		<h1>Prüfung ${exam.module.name}</h1>
	</header>

	<div class="top-navigation pull-right">
		<button class="btn btn-default" type="submit">Prüfung korrigieren</button>
	</div>

	<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="#">Aufgabenstellung</a></li>
		<li role="presentation"><a href="participants">Teilnahmen</a></li>
	</ul>	

	<div class="tab-body">
		<div class="row">
			<div class="col-md-12">
		    	<div class="row">
		    		<div class="col-md-2">
		    			<label>ID</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exercise.id}
		    		</div>
				</div>
		    	<div class="row">
		    		<div class="col-md-2">
		    			<label>Titel</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exercise.title}
		    		</div>
				</div>
		    	<div class="row">
		    		<div class="col-md-2">
		    			<label>Maximale Punktzahl</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exercise.maxPoints}
		    		</div>
				</div>
		    	<div class="row">
		    		<div class="col-md-2">
		    			<label>Aufgabenstellung</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exercise.taskDescription}
		    		</div>
				</div>				    	
				<div class="row">
		    		<div class="col-md-2">
		    			<label>Musterlösung</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exercise.exampleSolution.solutionText}
		    		</div>
				</div>
			</div>
		</div>	
	</div>
</#macro>

<@display_page/>
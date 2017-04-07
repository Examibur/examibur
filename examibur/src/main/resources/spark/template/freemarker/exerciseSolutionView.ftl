<#include "bodyTemplate.ftl">

<#macro body_main>
	<header>
		<h2>Prüfung ${exerciseSolution.exercise.exam.id} - Aufgabe ${exerciseSolution.exercise.id}</h2>
	</header>
	
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Aufgabenstellung</div>
			<div class="panel-body">
				<div class="row">
		    		<div class="col-md-2">
		    			<label>Titel</label>
		    		</div>
		    		<div class="col-md-10">
		    			TODO
		    		</div>
		    	</div>
		    	<div class="row">
		    		<div class="col-md-2">
		    			<label>Maximale Punktzahl</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exerciseSolution.exercise.maxPoints}
		    		</div>
		    	</div>
		    	<div class="row">
		    		<div class="col-md-2">
		    			<label>Aufgabenstellung</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exerciseSolution.exercise.taskDescription}
		    		</div>
				</div>
				<div class="row">
		    		<div class="col-md-2">
		    			<label>Musterlösung</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exerciseSolution.exercise.exampleSolution.solutionText}
		    		</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Teilnahme</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-2">
		    			<label>Prüfling</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exerciseSolution.participation.pseudonym}
		    		</div>
				</div>
				<div class="row">
					<div class="col-md-2">
		    			<label>Antwort</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exerciseSolution.participantSolution.solutionText}
		    		</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-8">
			<div class="panel panel-default">
				<div class="panel-heading">Korrektur</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2">
		    				<label>Bewertete Punktzahl</label>
		    			</div>
		    			<div class="col-md-10">
		    				TODO
		    			</div>
		    		</div>	
					<div class="row">
						<div class="col-md-2">
		    				<label>Kommentar</label>
		    			</div>
		    			<div class="col-md-10">
		    				TODO
		    			</div>
		    		</div>	
		    		<div class="row">
						<div class="col-md-2">
		    				<label>Begründung</label>
		    			</div>
		    			<div class="col-md-10">
		    				TODO
		    			</div>
		    		</div>
		    		<div class="row">
						<div class="col-md-2">
		    				<label>Korrektor</label>
		    			</div>
		    			<div class="col-md-10">
		    				TODO
		    			</div>
		    		</div>
				</div>
			</div>
		</div>
	</div>
</#macro>

<@display_page/>
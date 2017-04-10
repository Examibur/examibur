<#include "bodyTemplate.ftl">

<#macro body_main>
	<#local examState = exerciseSolution.exercise.exam.state>
	
	<header>
		<h2>Prüfung ${exerciseSolution.exercise.exam.id} - Aufgabe ${exerciseSolution.exercise.id}</h2>
	</header>
	
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Aufgabenstellung</div>
			<div class="panel-body">
			<div class="row">
		    		<div class="col-md-2">
		    			<label>ID</label>
		    		</div>
		    		<div class="col-md-10">
		    			TODO
		    		</div>
		    	</div>
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
	    <#if grading??>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<#if examState == "CORRECTION">
						<div class="pull-right">
							<a href="#edit">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>
						</div>
						</#if>
						<strong>${grading.gradingAuthor.firstName} ${grading.gradingAuthor.lastName}</strong> bewertete am ${grading.creationDate}
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2">
				    			<label>Bewertete Punktzahl</label>
				    		</div>
				    		<div class="col-md-4">
				    			${grading.points}
				    		</div>
						</div>
						<div class="row">
							<div class="col-md-2">
				    			<label>Kommentar</label>
				    		</div>
				    		<div class="col-md-4">
				    			${grading.comment}
				    		</div>
						</div>
						<div class="row">
							<div class="col-md-2">
				    			<label>Begründung</label>
				    		</div>
				    		<div class="col-md-4">
				    			${grading.reasoning}
				    		</div>
						</div>
					</div>
				</div>
			</div>
        </#if>
    
	
    	<#if review??>
    		<div class="col-md-6">
    			<div class="panel panel-default">
    				<div class="panel-heading">
    					<strong>${review.gradingAuthor.firstName} ${review.gradingAuthor.lastName}</strong> reviewte am ${review.creationDate}
    				</div>
    				<div class="panel-body">
    					<div class="row">
    						<div class="col-md-2">
    			    			<label>Bewertete Punktzahl</label>
    			    		</div>
    			    		<div class="col-md-4">
    			    			${review.points}
    			    		</div>
    					</div>
    					<div class="row">
    						<div class="col-md-2">
    			    			<label>Kommentar</label>
    			    		</div>
    			    		<div class="col-md-4">
    			    			${review.comment}
    			    		</div>
    					</div>
    					<div class="row">
    						<div class="col-md-2">
    			    			<label>Begründung</label>
    			    		</div>
    			    		<div class="col-md-4">
    			    			${review.reasoning}
    			    		</div>
    					</div>
    				</div>
    			</div>
            </div>
    	</#if>
    </div>
	
	<#if (examState == "CORRECTION" || examState == "REVIEW") && !(grading??)>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<#if examState == "CORRECTION">
							Korrektur
						<#else>
							Review
						</#if>
					</div>
					<div class="panel-body">
						<form action="#add-grading" method="POST"> 
							<div class="row">
								<div class="col-md-4">
				    				<label for="points">Bewertete Punktzahl</label>
				    			</div>
				    			<div class="col-md-4">
				    				<input class="form-control" id="points" type="number" min="0" max="${exerciseSolution.exercise.maxPoints}" />
				    			</div>
				    		</div>	
							<div class="row">
								<div class="col-md-4">
				    				<label for="comment">Kommentar</label>
				    			</div>
				    			<div class="col-md-8">
				    				<textarea class="form-control" id="comment" rows="3"></textarea>
				    			</div>
				    		</div>	
				    		<div class="row">
								<div class="col-md-4">
				    				<label for="reasoning">Begründung</label>
				    			</div>
				    			<div class="col-md-8">
				    				<textarea class="form-control" id="reasoning" rows="3"></textarea>
				    			</div>
				    		</div>
				    		<div class="row">
								<div class="col-md-4">
				    				<label>Korrektor</label>
				    			</div>
				    			<div class="col-md-4">
				    				TODO
				    			</div>
				    			<div class="pull-right">
				    				<input class="form-control" type="submit" value="Speichern" />
				    			</div>
				    		</div>
				    	</form>
					</div>
				</div>
			</div>
        </div>
    </#if>
</#macro>

<@display_page/>
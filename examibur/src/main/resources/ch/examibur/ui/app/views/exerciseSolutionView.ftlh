<#include "../templates/bodyTemplate.ftlh">
<#include "../includes/exerciseGradingComponent.ftlh">

<#macro body_main>
	<#local examState = exerciseSolution.exercise.exam.state>
	
	<header>
		<h2>Prüfung ${exerciseSolution.exercise.exam.id} - Aufgabe ${exerciseSolution.exercise.orderInExam}</h2>
	</header>
	
	<div class="row">
		<div class="top-navigation pull-right">
			<#if browse??>
				<a class="btn btn-primary" href="query-next/?browse=${browse}" role="button" id="query-next">Nächste Aufgabe</a>
			<#elseif examState == "CORRECTION">
				<a class="btn btn-default" href="?browse=exercise" role="button" id="query-by-exercise">Aufgabenweise korrigieren</a>
			<#elseif examState == "REVIEW">
				<a class="btn btn-default" href="?browse=exercise" role="button" id="query-by-exercise">Aufgabenweise reviewen</a>
			<#elseif examState == "APPROVAL">
				<a class="btn btn-default" href="?browse=exercise" role="button" id="query-by-exercise">Aufgabenweise approven</a>
			</#if>
		</div>
	</div>
	
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Aufgabenstellung</div>
			<div class="panel-body">
			<div class="row">
		    		<div class="col-md-2">
		    			<label>ID</label>
		    		</div>
		    		<div class="col-md-10">
		    			Aufgabe ${exerciseSolution.exercise.orderInExam}
		    		</div>
		    	</div>
				<div class="row">
		    		<div class="col-md-2">
		    			<label>Titel</label>
		    		</div>
		    		<div class="col-md-10">
		    			${exerciseSolution.exercise.title}
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
			<@exercise_grading grading=grading exerciseSolution=exerciseSolution examState=examState isReview=false/>
        </#if>
    	<#if review??>
    		<@exercise_grading grading=review exerciseSolution=exerciseSolution examState=examState isReview=true/>
    	</#if>
    </div>
	
	<#if (examState == "CORRECTION" || examState == "REVIEW") && !exerciseSolution.isDone()>
		<div class="row">
			<div class="col-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<#if examState == "CORRECTION">
							Korrektur
						<#else>
							Review
						</#if>
					</div>
					<div class="panel-body">
						<form action="gradings/" method="POST">
							<div class="row">
								<div class="col-md-4">
				    				<label for="points">Bewertete Punktzahl</label>
				    			</div>
				    			<div class="col-md-2">
									<input class="form-control" id="points-addgrading" name="points" type="number" min="0" max="${exerciseSolution.exercise.maxPoints}" required />
				    			</div>
				    		</div>	
							<div class="row">
								<div class="col-md-4">
				    				<label for="comment">Kommentar</label>
				    			</div>
				    			<div class="col-md-8">
									<textarea class="form-control" id="comment-addgrading" name="comment" rows="3"></textarea>
				    			</div>
				    		</div>	
				    		<div class="row">
								<div class="col-md-4">
				    				<label for="reasoning">Begründung</label>
				    			</div>
				    			<div class="col-md-8">
									<textarea class="form-control" id="reasoning-addgrading" name="reasoning" rows="3"></textarea>
				    			</div>
				    		</div>
				    		<div class="row">
								<div class="col-md-4">
				    				<label>Korrektor</label>
				    			</div>
				    			<div class="col-md-4">
				    				${user.username}
				    			</div>
							</div>
							<div class="row">
				    			<div class="pull-right">
									<button type="submit" class="btn btn-primary" id="submit-addgrading" name="action" value="save">Speichern</button>
									<#if browse??>
										<input type="hidden" name="browse" value="${browse}">
										<button type="submit" class="btn btn-primary" id="submit-addgrading-continue" name="action" value="saveandcontinue">Speichern und weiter</button>
									</#if>
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
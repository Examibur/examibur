<#include "../templates/bodyTemplate.ftl">
<#include "../includes/exerciseGradingComponent.ftl">

<#macro body_main>
	<#local examState = exerciseSolution.exercise.exam.state>
	
	<header>
		<h2>Prüfung ${exerciseSolution.exercise.exam.id} - Aufgabe ${exerciseSolution.exercise.orderInExam}</h2>
	</header>
	
	
	<div class="row">
		<div class="pull-right">
			<form method="get">
				<#if browse??>
					<input type="hidden" name="browse" value="${browse}">
					<input type="hidden" name="querynext">
					<button type="submit" class="btn btn-primary" id="querynext">Nächste Aufgabe</button>
				<#else>
					<input type="hidden" name="browse" value="exercise">
					<button type="submit" class="btn btn-default" id="browse-solutions">Aufgabenweise korrigieren</button>
				</#if>
			</form>
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
				    			<div class="pull-right">
									<#if browse??>
										<input type="hidden" name="browse" value="${browse}">
									</#if>
									<input class="form-control" type="submit" id="submit-addgrading" value="Speichern" />
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
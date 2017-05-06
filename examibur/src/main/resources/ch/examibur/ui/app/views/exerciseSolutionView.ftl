<#include "../templates/bodyTemplate.ftl">

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
					<button type="submit" class="btn btn-primary">Nächste Aufgabe</button>
				<#else>
					<input type="hidden" name="browse" value="participations">
					<button type="submit" class="btn btn-default">Aufgabenweise korrigieren</button>
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
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong>${grading.gradingAuthor.firstName} ${grading.gradingAuthor.lastName}</strong> bewertete am ${grading.creationDate}
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4">
				    			<label>Bewertete Punktzahl</label>
				    		</div>
				    		<div class="col-md-8">
				    			${grading.points}
				    		</div>
						</div>
						<div class="row">
							<div class="col-md-4">
				    			<label>Kommentar</label>
				    		</div>
				    		<div class="col-md-8">
				    			${grading.comment}
				    		</div>
						</div>
						<div class="row">
							<div class="col-md-4">
				    			<label>Begründung</label>
				    		</div>
				    		<div class="col-md-8">
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
    						<div class="col-md-4">
    			    			<label>Bewertete Punktzahl</label>
    			    		</div>
    			    		<div class="col-md-8">
    			    			${review.points}
    			    		</div>
    					</div>
    					<div class="row">
    						<div class="col-md-4">
    			    			<label>Kommentar</label>
    			    		</div>
    			    		<div class="col-md-8">
    			    			${review.comment}
    			    		</div>
    					</div>
    					<div class="row">
    						<div class="col-md-4">
    			    			<label>Begründung</label>
    			    		</div>
    			    		<div class="col-md-8">
    			    			${review.reasoning}
    			    		</div>
    					</div>
    					<#if examState == "APPROVAL" && !exerciseSolution.isDone()>
	    					<div class="row">
	    						<div class="col-md-3">
		    						<form action="gradings" method="POST">
		    							<input type="hidden" id="accept-review" value="true" />
		    							<input type="submit" class="form-control" value="Akzeptieren" />
		    						</form>
	    						</div>
	    						<div class="col-md-3">
		    						<form action="#reject-review" method="POST">
		    							<input type="submit" class="form-control" value="Ablehnen" />
		    						</form>	
	    						</div>
	    					</div>
    					</#if>
    				</div>
    			</div>
            </div>
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
				    				<input class="form-control" name="points" type="number" min="0" max="${exerciseSolution.exercise.maxPoints}" required />
				    			</div>
				    		</div>	
							<div class="row">
								<div class="col-md-4">
				    				<label for="comment">Kommentar</label>
				    			</div>
				    			<div class="col-md-8">
				    				<textarea class="form-control" name="comment" rows="3"></textarea>
				    			</div>
				    		</div>	
				    		<div class="row">
								<div class="col-md-4">
				    				<label for="reasoning">Begründung</label>
				    			</div>
				    			<div class="col-md-8">
				    				<textarea class="form-control" name="reasoning" rows="3"></textarea>
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
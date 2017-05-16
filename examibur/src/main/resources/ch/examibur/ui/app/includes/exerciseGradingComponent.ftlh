<#macro exercise_grading grading exerciseSolution examState isReview>
	<div class="col-md-6">
		<div class="panel panel-default" id="grading-panel">
			<div class="panel-heading">
				<strong>${grading.gradingAuthor.firstName} ${grading.gradingAuthor.lastName}</strong>
				<#if isReview == true>reviewte<#else>bewertete</#if> am ${grading.creationDate}
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
				<#if isReview == true>
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
				</#if>
			</div>
		</div>
	</div>
</#macro>
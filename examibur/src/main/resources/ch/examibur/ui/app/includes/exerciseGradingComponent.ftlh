<#macro exercise_grading grading exerciseSolution examState isReview>
	<div class="col-md-6">
		<div class="panel panel-default" id="<#if isReview == true>review<#else>grading</#if>-panel">
			<div class="panel-heading">
				<strong>${grading.gradingAuthor.firstName} ${grading.gradingAuthor.lastName}</strong>
				<#if isReview == true>reviewte<#else>bewertete</#if> am ${grading.creationDate}
				<#if isReview && examState != "REVIEW" && exerciseSolution.isDone()>
					<div id="review-result" class="pull-right">
						<#if grading.isFinalGrading()>
							<span class="label label-success">akzeptiert</span>
						<#else>
							<span class="label label-danger">abgelehnt</span>
						</#if>
					</div>
				</#if>
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
							<form action="gradings/${grading.id}/" method="POST">
								<#if browse??>
									<input type="hidden" name="browse" value="${browse}">
								</#if>
								<div class="col-md-3">
									<button class="form-control btn-success" type="submit" id="accept-review" name="approve-review" value="accept">Akzeptieren</button>
								</div>
								<div class="col-md-3">
									<button class="form-control btn-danger" type="submit" id="reject-review" name="approve-review" value="reject">Ablehnen</button>
								</div>
    						</form>	
						</div>
					</#if>
				</#if>
			</div>
		</div>
	</div>
</#macro>
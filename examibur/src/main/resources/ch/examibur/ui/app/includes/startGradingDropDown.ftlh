<#macro start_grading_dropdown examState relativePath>
	<#if exam.state == "CORRECTION" || exam.state == "REVIEW" || exam.state == "APPROVAL">
		<#if examState == "CORRECTION">
			<#local action = "Korrektur">
		<#else>
			<#local action = "Review">
		</#if>
		<div class="btn-group">
			<button type="button" id="query-next" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				${action} starten <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a class="btn btn-default" href="${relativePath}query-by-exercises/" role="button" id="query-by-exercises">Aufgabenweise</a></li>
				<li><a class="btn btn-default" href="${relativePath}query-by-participations/" role="button" id="query-by-participations">Prüfungsweise</a></li>
			</ul>
		</div>
	</#if>
</#macro>
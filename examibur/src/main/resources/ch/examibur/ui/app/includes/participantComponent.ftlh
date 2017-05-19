<#macro participant_list>
	<#list participantsOverview>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>						
					<#if visibleColumns?seq_contains("testee")><th>Prüfling</th></#if>
					<#if visibleColumns?seq_contains("points")><th>Punkte</th></#if>
					<#if visibleColumns?seq_contains("grade")><th>Note</th></#if>
					<#if visibleColumns?seq_contains("passed")><th>Bestanden</th></#if>
					<#if visibleColumns?seq_contains("progress")><th>Fortschritt</th></#if>
					<#if visibleColumns?seq_contains("done")><th>Erledigt</th></#if>
				</tr>
			</thead>
			<tbody>
				<#items as participantOverview>
				<tr>
					<#if visibleColumns?seq_contains("testee")>
						<td><a href="/exams/${exam.id}/participants/${participantOverview.id}">${participantOverview.pseudonym}</a></td>
					</#if>
					<#if visibleColumns?seq_contains("points")>
						<td>${participantOverview.totalPoints}</td>
					</#if>
					<#if visibleColumns?seq_contains("grade")>
						<#if participantOverview.grading.isPresent()>
							<td>
								${participantOverview.grading.get()}
							</td>
							<#if visibleColumns?seq_contains("passed")>
								<td>
									<#if participantOverview.grading.get() gte 3.75 >
									  Ja
									<#else>
									  Nein
									</#if>
								</td>
							</#if>	
						<#else>
							<td>-</td>
							<#if visibleColumns?seq_contains("passed")>
								<td>-</td>
							</#if>	
						</#if>	
					</#if>	
					<#if visibleColumns?seq_contains("progress")>
						<td>
							${participantOverview.formattedProgress}								
						</td>
					</#if>
					<#if visibleColumns?seq_contains("done")>
						<td>
							${participantOverview.isDone()?string('Ja', 'Nein')}								
						</td>
					</#if>
				</tr>
				</#items>
			</tbody>
		</table>
	<#else>
		<p>keine Teilnahmen gefunden</p>
	</#list>
</#macro>
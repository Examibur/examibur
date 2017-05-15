<#include "../templates/bodyTemplate.ftlh">
<#include "../includes/examListComponent.ftlh">

<#macro body_main>
	<header>
		<h1>Meine Prüfungen</h1>
	</header>
	<@exam_list/>
</#macro>

<@display_page/>
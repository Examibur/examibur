<#include "bodyTemplate.ftl">
<#include "examListComponent.ftl">

<#macro body_main>
	<header>
		<h1>Dashboard</h1>
	</header>
	
	<h2>Meine Prüfungen</h2>
	<@exam_list/>
</#macro>

<@display_page/>
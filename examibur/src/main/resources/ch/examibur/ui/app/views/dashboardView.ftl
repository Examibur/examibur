<#include "../templates/bodyTemplate.ftl">
<#include "../includes/examListComponent.ftl">
<#include "../includes/examQuickLinkComponent.ftl">

<#macro body_main>
	<header>
		<h1>Dashboard</h1>
	</header>

	<@exam_quicklink "Korrekturen" corrections/>
	<@exam_quicklink "Reviews" reviews/>
	
			
	<h2>Meine Prüfungen</h2>
	<@exam_list/>
</#macro>

<@display_page/>
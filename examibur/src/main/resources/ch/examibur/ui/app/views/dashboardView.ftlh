<#include "../templates/bodyTemplate.ftlh">
<#include "../includes/examListComponent.ftlh">
<#include "../includes/examQuickLinkComponent.ftlh">

<#macro body_main>
	<header>
		<h1>Dashboard</h1>
	</header>

	<@exam_quicklink "Korrekturen" "korrigieren" corrections/>
	<@exam_quicklink "Reviews" "reviewen" reviews/>
	
			
	<h2>Meine Prüfungen</h2>
	<@exam_list/>
</#macro>

<@display_page/>
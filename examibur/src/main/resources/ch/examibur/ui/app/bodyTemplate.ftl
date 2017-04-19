<#include "baseTemplate.ftl">

<#macro body_header_navigation>
	<ol class="breadcrumb">
		<li><a href="/">Dashboard</a></li>
	</ol>
	<#assign breadcrumb=url>
	<#if breadcrumb?matches("^\\/exams\\/", "r")>
		<li><a href="/exams/${exam.id}/">Prüfung ${exam.id}</a></li>
		
		${breadcrumb}
	</#if>

	${breadcrumb}
	<li class="active">Anonymes Känguru</li>
</#macro>

<#macro body_main>
	<p>nothing to display</p>
</#macro>

<#macro body_footer>
	<p>Copyright 2017 Examibur</p>
</#macro>

<#macro page_body>
	<div class="container">
	<header>
    	<@body_header_navigation/>
    </header>
		
		<main>
			<@body_main/>
		</main>
		
		<div class="navbar navbar-fixed-bottom">
			<div class="container">
				<footer>
					<@body_footer/>
				</footer>
			</div>
		</div>
	</div>
</#macro>
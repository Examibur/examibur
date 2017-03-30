<#include "baseTemplate.ftl">

<#macro body_header_navigation>
	<ol class="breadcrumb">
		<li><a href="/">Dashboard</a></li>
		<li><a href="/exams/1/participants/1">Abschlussprüfung SE2 FS2017</a></li>
		<li class="active">Anonymes Känguru</li>
	</ol>
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
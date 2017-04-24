<#include "baseTemplate.ftl">

<#macro body_header_navigation>
	<ol class="breadcrumb">
	<#list breadcrumb as crumb>
	<#if crumb?index == breadcrumb?size - 1>
	    <li class="active">${crumb.title}</li>
	<#else>
		<li><a href="${crumb.location}">${crumb.title}</a></li>
	</#if>
	</#list>
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
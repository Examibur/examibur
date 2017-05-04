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

<#macro header>
    <nav class="navbar navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="/">
            Examibur
          </a>
        </div>

	    <ul class="nav navbar-nav navbar-right">
	        <#if user??>
		    <li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		            <span class="glyphicon glyphicon-user"></span> 
		            <strong>${user.firstName} ${user.lastName}</strong>
		            <span class="glyphicon glyphicon-chevron-down"></span>
		        </a>
		        <ul class="dropdown-menu">
					<div class="navbar-login navbar-login-session">
			            <form action="/logout" method="POST">
							<input class="btn btn-block" type="submit" value="Logout">
			            </form>
					</div>

		        </ul>
		    </li>
		    <#else>
				<li><a href="/login/">login</a></li>
			</#if>
			</ul>
      </div>
    </nav>
</#macro>

<#macro body_main>
	<p>nothing to display</p>
</#macro>

<#macro body_footer>
	<p>Copyright 2017 Examibur</p>
</#macro>

<#macro page_body>
	<@header/>
	<div class="container">
	<header>
    	<@body_header_navigation/>
    </header>
		
		<main>
			<@body_main/>
		</main>
		
		<div class="navbar">
			<div class="container">
				<footer>
					<@body_footer/>
				</footer>
			</div>
		</div>
	</div>
</#macro>
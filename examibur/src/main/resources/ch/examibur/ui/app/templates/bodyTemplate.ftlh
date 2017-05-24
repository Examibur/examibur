<#include "baseTemplate.ftlh">

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

<#macro body_header_notification_bar>
	<#if notificationMessage??>
		<#switch notificationMessage.type>
		  <#case "SUCCESS">
		     <#local messageClass = "bg-success">
		     <#break>
		  <#case "INFO">
		     <#local messageClass = "bg-info">
		     <#break>
		  <#case "WARNING">
		     <#local messageClass = "bg-warning">
		     <#break>
		  <#default>
		     <#local messageClass = "bg-danger">
		</#switch>
		<div id="notification-message" class="alert ${messageClass} alert-dismissible fade in" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			${notificationMessage.messageText}
		</div>
    </#if>
</#macro>

<#macro header>
    <nav class="navbar navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="/">
			<img src="/images/logo-white-transparent-rotated.png">
            Examibur
          </a>
        </div>

	    <ul class="nav navbar-nav navbar-right">
	        <#if user??>
		    <li class="dropdown" id="user-details">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		            <span class="glyphicon glyphicon-user"></span> 
		            <strong>${user.firstName} ${user.lastName}</strong>
		            <span class="glyphicon glyphicon-chevron-down"></span>
		        </a>
		        <ul class="dropdown-menu">
					<div class="navbar-login navbar-login-session">
			            <form action="/logout" method="POST">
							<input class="btn btn-block" type="submit" value="Logout" id="logout">
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
		<@body_header_notification_bar/>
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
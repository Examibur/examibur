<#include "bodyTemplate.ftl">

<#macro body_main>
	<header>
		<h1>Login</h1>
	</header>
	
	<div class="row">
		<form method="POST">
		    <label for="username" class="sr-only">Username</label>
		    <input type="text" name="username" class="form-control" placeholder="Username address" required autofocus>
		    <label for="password" class="sr-only">Password</label>
		    <input type="password" name="password" class="form-control" placeholder="Password" required>
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		 </form>
	 </div>

</#macro>

<@display_page/>
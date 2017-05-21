<#include "../templates/bodyTemplate.ftlh">

<#macro body_main>
	<header>
		<h1>Login</h1>
	</header>
	
	<div class="row">
		<form method="POST">
		    <label for="username-login" class="sr-only">Username</label>
		    <input type="text" id="username-login" name="username" class="form-control" placeholder="Username" required autofocus>
		    <label for="password-login" class="sr-only">Password</label>
		    <input type="password" id="password-login" name="password" class="form-control" placeholder="Password" required>
		    <button class="btn btn-lg btn-primary btn-block" id="submit-login" type="submit">Sign in</button>
		 </form>
	 </div>

</#macro>

<@display_page/>
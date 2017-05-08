<#include "../templates/bodyTemplate.ftl">

<#macro body_main>
	<header>
	<h1>Abschlussprüfung SE2 FS2017</h1>
	</header>

	<div class="top-navigation pull-right">
	<button class="btn btn-default" type="submit">Prüfung korrigieren</button>
	</div>

	<ul class="nav nav-tabs">
	<li role="presentation"><a href="#">Informationen</a></li>
	<li role="presentation" class="active"><a href="#">Aufgaben</a></li>
	<li role="presentation"><a href="#">Auswertung</a></li>
	</ul>	

	<div class="tab-body">
	<div class="row">
		<div class="col-md-4">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Suchen...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
				</span>
			</div>
		</div>
	</div>			
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>						
				<th>Nr</th>
				<th>Titel</th>
				<th>Punkte</th>
				<th>Erreicht</th>
				<th>Status</th>
				<th>Review vorhanden</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th scope="row">1</th>
				<td><a href="#">Projektautomatisierung und Continuous Integration</a></td>
				<td>8</td>
				<td>8</td>
				<td>Korrektur</td>
				<td>Nein</td>
			</tr>
			<tr>
				<th scope="row">2</th>
				<td><a href="#">Software Engineering Practices</a></td>
				<td>10</td>
				<td>9</td>
				<td>Korrektur</td>
				<td>Nein</td>
			</tr>
			<tr>
				<th scope="row">3</th>
				<td><a href="#">Advanced Code Design</a></td>
				<td>8</td>
				<td>7</td>
				<td>Korrektur</td>
				<td>Nein</td>
			</tr>
			<tr>
				<th scope="row">3</th>
				<td><a href="#">Test Driven Development</a></td>
				<td>8</td>
				<td>8</td>
				<td>Korrektur</td>
				<td>Nein</td>
			</tr>
		</tbody>
	</table>
	</div>
</#macro>

<@display_page/>
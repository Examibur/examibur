package ch.examibur.ui.app.controller;

import spark.TemplateEngine;

public abstract class Controller {
	
	protected String controllerName = "Controller";
	
	public abstract void route(TemplateEngine engine);
	
}

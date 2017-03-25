package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

public class DashboardController extends Controller {
	
	public DashboardController() {
		controllerName = "DashboardController";
	}
	
	public ModelAndView show(Request request, Response response) {
		System.out.println("test");
		Map<String, Object> model = new HashMap<>();
        model.put("title", controllerName);
        return new ModelAndView(model, "dashboard.ftl");
    }

	@Override
	public void route(TemplateEngine engine) {
		ExamController examController = new ExamController();
		
		get("/", this::show, engine);
		
        path("/exams", () -> {
        	examController.route(engine);
        });
	}
	
}

package ch.examibur.ui.app.controller;

import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

public class ExerciseGradingController extends Controller {

	public ExerciseGradingController() {
		controllerName = "ExerciseGradingController";
	}
	
	public ModelAndView add(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
        return new ModelAndView(model, "404.ftl");
    }

	@Override
	public void route(TemplateEngine engine) {
		post("/", this::add, engine);
	}
    
}

package ch.examibur.ui.app.controller;

import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import ch.examibur.ui.app.util.TemplateRenderer;
import spark.Request;
import spark.Response;

public class ExerciseGradingController extends Controller {

	public ExerciseGradingController() {
		controllerName = "ExerciseGradingController";
	}
	
	public String add(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
		return new TemplateRenderer().render(model, "404.ftl");
    }

	@Override
	public void route() {
		post("/", this::add);
	}
    
}

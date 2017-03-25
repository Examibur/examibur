package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import java.util.HashMap;
import java.util.Map;

import ch.examibur.ui.app.util.TemplateRenderer;
import spark.Request;
import spark.Response;

public class ExerciseSolutionController extends Controller {

	public ExerciseSolutionController() {
		controllerName = "ExerciseSolutionController";
	}
	
	public String show(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
		return new TemplateRenderer().render(model, "404.ftl");
    }
    
    public String listAll(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
		return new TemplateRenderer().render(model, "404.ftl");
    }

	@Override
	public void route() {
		ExerciseGradingController exerciseGradingController = new ExerciseGradingController();
		
		get("/", this::listAll);
		
        path("/:exerciseId", () -> {
        	get("/", this::show);
        	
        	path("/gradings", () -> {
        		exerciseGradingController.route();
            });
        });
	}

}

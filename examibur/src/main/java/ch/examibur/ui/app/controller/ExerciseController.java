package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import java.util.HashMap;
import java.util.Map;

import ch.examibur.ui.app.util.TemplateRenderer;
import spark.Request;
import spark.Response;

public class ExerciseController extends Controller {
	
	public ExerciseController() {
		controllerName = "ExerciseController";
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
		get("/", this::listAll);
        
        path("/:exerciseId", () -> {
        	get("/", this::show);
        });
	}
    
}

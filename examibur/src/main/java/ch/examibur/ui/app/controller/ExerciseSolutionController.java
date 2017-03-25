package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

public class ExerciseSolutionController extends Controller {

	public ExerciseSolutionController() {
		controllerName = "ExerciseSolutionController";
	}
	
	public ModelAndView show(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
        return new ModelAndView(model, "404.ftl");
    }
    
    public ModelAndView listAll(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
        return new ModelAndView(model, "404.ftl");
    }

	@Override
	public void route(TemplateEngine engine) {
		ExerciseGradingController exerciseGradingController = new ExerciseGradingController();
		
		get("/", this::listAll, engine);
		
        path("/:exerciseId", () -> {
        	get("/", this::show, engine);
        	
        	path("/gradings", () -> {
        		exerciseGradingController.route(engine);
            });
        });
	}

}

package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

public class ExamController extends Controller {
	
	public ExamController() {
		controllerName = "ExamController";
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
    
    public ModelAndView update(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
        return new ModelAndView(model, "404.ftl");
    }

	@Override
	public void route(TemplateEngine engine) {
		ExerciseController exerciseController = new ExerciseController();
        ExamParticipationController examParticipationController = new ExamParticipationController();
		
		get("/", this::listAll, engine);
		
        path("/:examId", () -> {
        	get("/", this::show, engine);
        	post("/", this::update, engine);
        	
            path("/exercises", () -> {
            	exerciseController.route(engine);
            });
            
            path("/participants", () -> {
            	examParticipationController.route(engine);
            });
        });		
	}
	
}

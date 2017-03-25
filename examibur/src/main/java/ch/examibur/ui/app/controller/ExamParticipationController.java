package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

public class ExamParticipationController extends Controller {

	public ExamParticipationController() {
		controllerName = "ExamParticipationController";
	}
	
	public ModelAndView show(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
        return new ModelAndView(model, "examParticipationTabExerciseView.ftl");
    }
    
    public ModelAndView listAll(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
        return new ModelAndView(model, "examParticipationTabExerciseView.ftl");
    }

	@Override
	public void route(TemplateEngine engine) {
		ExerciseSolutionController exerciseSolutionController = new ExerciseSolutionController();
        
		get("/", this::listAll, engine);
        
        path("/:participantId", () -> {
        	get("/", this::show, engine);
        	
        	path("/exercises", () -> {
        		exerciseSolutionController.route(engine);
        	});
        });
	}
	
}

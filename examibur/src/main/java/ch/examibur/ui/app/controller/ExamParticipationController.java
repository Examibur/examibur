package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import java.util.HashMap;
import java.util.Map;

import ch.examibur.ui.app.util.TemplateRenderer;
import spark.Request;
import spark.Response;

public class ExamParticipationController extends Controller {

	public ExamParticipationController() {
		controllerName = "ExamParticipationController";
	}
	
	public String show(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
		return new TemplateRenderer().render(model, "examParticipationTabExerciseView.ftl");
    }
    
    public String listAll(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
		return new TemplateRenderer().render(model, "examParticipationTabExerciseView.ftl");
    }

	@Override
	public void route() {
		ExerciseSolutionController exerciseSolutionController = new ExerciseSolutionController();
        
		get("/", this::listAll);
        
        path("/:participantId", () -> {
        	get("/", this::show);
        	
        	path("/exercises", () -> {
        		exerciseSolutionController.route();
        	});
        });
	}
	
}

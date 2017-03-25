package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import ch.examibur.ui.app.util.TemplateRenderer;
import spark.Request;
import spark.Response;

public class ExamController extends Controller {
	
	public ExamController() {
		controllerName = "ExamController";
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
    
    public String update(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		model.put("title", controllerName);
		return new TemplateRenderer().render(model, "404.ftl");
    }

	@Override
	public void route() {
		ExerciseController exerciseController = new ExerciseController();
        ExamParticipationController examParticipationController = new ExamParticipationController();
		
		get("/", this::listAll);
		
        path("/:examId", () -> {
        	get("/", this::show);
        	post("/", this::update);
        	
            path("/exercises", () -> {
            	exerciseController.route();
            });
            
            path("/participants", () -> {
            	examParticipationController.route();
            });
        });		
	}
	
}

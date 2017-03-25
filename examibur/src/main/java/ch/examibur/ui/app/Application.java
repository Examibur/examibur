package ch.examibur.ui.app;

import static spark.Spark.*;

import ch.examibur.ui.app.filter.Filters;
import ch.examibur.ui.app.controller.DashboardController;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {
		
	@Override
	public void init() {
		
		staticFiles.location("/public");
		
        DashboardController dashboardController = new DashboardController();
        
        before("*", Filters.addTrailingSlashes);
        
        dashboardController.route();
        
        after("*", Filters.addGzipHeader);
		
	}
}

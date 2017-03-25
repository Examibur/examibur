package ch.examibur.ui.app;

import static spark.Spark.*;

import ch.examibur.ui.app.filter.Filters;
import ch.examibur.ui.app.controller.DashboardController;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

public class Application implements SparkApplication {
		
	@Override
	public void init() {
		
		// setup templating engine (Freemarker)
        FreeMarkerEngine engine = new FreeMarkerEngine();
        
        DashboardController dashboardController = new DashboardController();
        
        // routing and filters
        before("*", Filters.addTrailingSlashes);
        
        dashboardController.route(engine);
        
        after("*", Filters.addGzipHeader);
		
	}
}

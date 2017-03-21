package app;

import static spark.Spark.*;

import spark.servlet.SparkApplication;

public class Application implements SparkApplication {
	@Override
	public void init() {
		get("/hello", (req, res) -> "Hello World!");		
	}
}

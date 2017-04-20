package ch.examibur.ui.app.routing;

import spark.Request;

public final class RoutingHelpers {
	private RoutingHelpers() {
	}
	public static long getLongUrlParameter(Request request, UrlParameter urlParameter) {
		return Long.parseLong(request.params(urlParameter.toString()));
	}
}

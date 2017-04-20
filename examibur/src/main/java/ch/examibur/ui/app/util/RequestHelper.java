package ch.examibur.ui.app.util;

import ch.examibur.ui.app.routing.PartialUrl;
import ch.examibur.ui.app.routing.UrlParameter;
import java.util.List;
import java.util.Map;
import spark.Request;

public final class RequestHelper {

  private RequestHelper() {
  }

  /**
   * Adds a new BreadCrumb Entry to the current request model with the given title & url.
   */
  public static void pushBreadCrumb(Request request, String title, PartialUrl url) {
    pushBreadCrumb(request, title, url.url());
  }

  /**
   * Adds a new BreadCrumb Entry to the current request model with the given title & url.
   */
  @SuppressWarnings("unchecked")
  public static void pushBreadCrumb(Request request, String title, String url) {
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    List<BreadCrumbEntry> breadcrumb = (List<BreadCrumbEntry>) model
        .get(RequestAttributes.BREADCRUMB);
    breadcrumb.add(new BreadCrumbEntry(title, url));
  }

  public static long getLongUrlParameter(Request request, UrlParameter urlParameter) {
    return Long.parseLong(request.params(urlParameter.toString()));
  }

}

package ch.examibur.ui.app.util;

import java.util.List;
import java.util.Map;
import spark.Request;

public final class RequestHelper {

  private RequestHelper() {
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

}

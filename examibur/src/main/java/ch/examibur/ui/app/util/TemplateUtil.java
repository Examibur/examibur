package ch.examibur.ui.app.util;

import java.util.Map;

import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public final class TemplateUtil {
  
  private static TemplateEngine templateEngine = new FreeMarkerEngine();
  
  private TemplateUtil() {
  }

  public static String render(Map<String, Object> model, String templatePath) {
    model.put("title", "default");
    return templateEngine.render(new ModelAndView(model, templatePath));
  }

}

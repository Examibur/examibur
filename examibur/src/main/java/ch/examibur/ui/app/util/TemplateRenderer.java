package ch.examibur.ui.app.util;

import java.util.Map;

import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public class TemplateRenderer {

  private TemplateEngine templateEngine;

  public TemplateRenderer() {
    templateEngine = new FreeMarkerEngine();
  }

  public String render(Map<String, Object> model, String templatePath) {
    model.put("title", "default");
    return templateEngine.render(new ModelAndView(model, templatePath));
  }

}

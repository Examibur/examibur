package ch.examibur.ui.app.render;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Singleton
public class ThreadSafeFreeMarkerRenderer implements Renderer {

  private final Configuration configuration;

  @Inject
  public ThreadSafeFreeMarkerRenderer(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public String render(Map<String, Object> model, String viewName) {
    try {
      StringWriter stringWriter = new StringWriter();

      Template template = configuration.getTemplate(viewName);

      template.process(model, stringWriter);
      return stringWriter.toString();
    } catch (IOException | TemplateException e) {
      throw new IllegalArgumentException(e);
    }
  }

}

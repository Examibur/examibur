package ch.examibur.ui.app.module;

import ch.examibur.ui.app.util.Renderer;
import ch.examibur.ui.app.util.ThreadSafeFreeMarkerRenderer;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import freemarker.template.Configuration;
import freemarker.template.Version;

public class ControllerModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Renderer.class).to(ThreadSafeFreeMarkerRenderer.class);
  }

  @Provides
  @Singleton
  Configuration provideConfiguration() {
    Configuration configuration = new Configuration(new Version(2, 3, 23));
    configuration.setClassForTemplateLoading(getClass(), "/ch/examibur/ui/app");
    return configuration;
  }

}

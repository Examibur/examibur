package ch.examibur.ui.app.render;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import freemarker.template.Configuration;
import freemarker.template.Version;

public class RenderingModule extends AbstractModule {

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

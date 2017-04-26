package ch.examibur.ui.app;

import ch.examibur.business.BusinessEntrypoint;
import ch.examibur.business.Entrypoint;
import ch.examibur.ui.app.render.RenderingModule;
import ch.examibur.ui.app.routing.Router;
import com.google.inject.CreationException;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  @Override
  public void init() {

    try {
      Entrypoint businessEntry = new BusinessEntrypoint();
      Injector parentInjector = businessEntry.init();
      Injector injector = parentInjector.createChildInjector(new RenderingModule());

      final Router router = injector.getInstance(Router.class);
      router.route();
    } catch (CreationException | ProvisionException e) {
      LOGGER.error("Failed to intialize Guice", e);
      throw e;
    }
  }
}

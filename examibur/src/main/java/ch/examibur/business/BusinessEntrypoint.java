package ch.examibur.business;

import ch.examibur.business.service.ServiceModule;
import ch.examibur.integration.IntegrationEntrypoint;
import com.google.inject.Injector;

public class BusinessEntrypoint implements Entrypoint {

  @Override
  public Injector init() {
    Injector parentInjector = new IntegrationEntrypoint().init();
    return parentInjector.createChildInjector(new ServiceModule());
  }

}

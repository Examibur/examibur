package ch.examibur.business;

import ch.examibur.integration.IntegrationEntrypoint;

import com.google.inject.Injector;

public class BusinessEntrypoint implements Entrypoint {

  @Override
  public Injector init() {
    return new IntegrationEntrypoint().init();
  }

}

package ch.examibur.integration;

import com.google.inject.Injector;

@FunctionalInterface
public interface Entrypoint {
  Injector init();
}

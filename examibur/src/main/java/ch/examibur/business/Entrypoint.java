package ch.examibur.business;

import com.google.inject.Injector;

@FunctionalInterface
public interface Entrypoint {
  Injector init();
}

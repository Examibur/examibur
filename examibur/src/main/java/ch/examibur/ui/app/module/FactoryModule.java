package ch.examibur.ui.app.module;

import ch.examibur.ui.app.controller.Controller;
import ch.examibur.ui.app.controller.DashboardController;
import ch.examibur.ui.app.controller.ExamController;
import ch.examibur.ui.app.factory.DashboardControllerFactory;
import ch.examibur.ui.app.factory.ExamControllerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FactoryModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
        .implement(Controller.class, DashboardController.class)
        .build(DashboardControllerFactory.class));
    
    install(new FactoryModuleBuilder()
        .implement(Controller.class, ExamController.class)
        .build(ExamControllerFactory.class));
  }

}

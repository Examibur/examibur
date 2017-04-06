package ch.examibur.ui.app.factory;

import ch.examibur.ui.app.controller.Controller;
import ch.examibur.ui.app.controller.DashboardController;

public interface DashboardControllerFactory {
  
  public DashboardController create(Controller preController);
  
}

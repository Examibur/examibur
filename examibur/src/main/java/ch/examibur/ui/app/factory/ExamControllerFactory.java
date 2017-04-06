package ch.examibur.ui.app.factory;

import ch.examibur.ui.app.controller.Controller;
import ch.examibur.ui.app.controller.ExamController;

public interface ExamControllerFactory {
  
  public ExamController create(Controller preController);
  
}

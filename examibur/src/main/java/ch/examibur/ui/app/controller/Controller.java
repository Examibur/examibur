package ch.examibur.ui.app.controller;

public abstract class Controller {

  private final Controller preController;
  protected final String relativePath;

  public Controller(Controller preController, String relativePath) {
    this.preController = preController;
    this.relativePath = relativePath;
  }

  public abstract void route();

  /**
   * Returns the absolute actual routed path to this controller.
   * 
   * @return the path to this controller
   */
  public String getAbsolutePath() {
    if (preController != null) {
      return preController.getAbsolutePath() + relativePath;
    }
    return relativePath;
  }

}

package ch.examibur.ui.app.controller;

import java.util.HashMap;
import java.util.Map;

public abstract class Controller {

  private final Controller preController;
  protected final String relativePath;
  private Map<String, Object> model = new HashMap<>();

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

  /**
   * Returns a merged model from all controllers within the callers path. Values
   * from lower controller models with the same key will be overwritten by the
   * new values.
   * 
   * @return the merged model
   */
  public Map<String, Object> getFullPathModel() {
    Map<String, Object> result = new HashMap<>();
    if (preController != null) {
      result.putAll(preController.getModel());
    }
    result.putAll(model);
    return result;
  }

  public Map<String, Object> getModel() {
    return model;
  }

  public void setModel(Map<String, Object> model) {
    this.model = model;
  }

}

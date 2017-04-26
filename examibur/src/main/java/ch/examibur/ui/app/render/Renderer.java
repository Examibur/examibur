package ch.examibur.ui.app.render;

import java.util.Map;

@FunctionalInterface
public interface Renderer {

  String render(Map<String, Object> model, String viewName);

}

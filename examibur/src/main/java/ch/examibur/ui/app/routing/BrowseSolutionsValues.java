package ch.examibur.ui.app.routing;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BrowseSolutionsValues {
  BY_EXERCISE("exercise"),
  BY_PARTICIPATION("participation"),
  BY_EXERCISES("exercises"),
  BY_PARTICIPATIONS("participations");

  private final String name;
  private static final Map<String, BrowseSolutionsValues> nameToValueMap = new HashMap<>();

  static {
    for (BrowseSolutionsValues value : EnumSet.allOf(BrowseSolutionsValues.class)) {
      nameToValueMap.put(value.toString(), value);
    }
  }

  private BrowseSolutionsValues(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  public static BrowseSolutionsValues forName(String name) {
    return nameToValueMap.get(name);
  }
}

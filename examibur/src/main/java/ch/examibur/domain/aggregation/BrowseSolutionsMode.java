package ch.examibur.domain.aggregation;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BrowseSolutionsMode {
  BY_EXERCISE("exercise"),
  BY_PARTICIPATION("participation"),
  BY_EXERCISES("exercises"),
  BY_PARTICIPATIONS("participations");

  private final String name;
  private static final Map<String, BrowseSolutionsMode> nameToValueMap = new HashMap<>();

  static {
    for (BrowseSolutionsMode value : EnumSet.allOf(BrowseSolutionsMode.class)) {
      nameToValueMap.put(value.toString(), value);
    }
  }

  private BrowseSolutionsMode(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  /**
   * @param name
   *          the value of the enum property "name"
   * @return returns the enum object which belongs to the given property "name". Returns null if no
   *         matching enum is found.
   */
  public static BrowseSolutionsMode forName(String name) {
    if (name == null) {
      return null;
    }
    return nameToValueMap.get(name);
  }
}

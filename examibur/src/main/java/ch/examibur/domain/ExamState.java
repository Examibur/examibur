package ch.examibur.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ExamState {
  CORRECTION(0, "Correction"),
  REVIEW(1, "Review"),
  APPROVAL(2, "Approval"),
  APPEAL(3, "Appeal"),
  ARCHIVED(4, "Archived");

  private final int order;
  private final String displayName;

  private static final Map<Integer, ExamState> nameToValueMap = new HashMap<>();

  static {
    for (ExamState examState : EnumSet.allOf(ExamState.class)) {
      nameToValueMap.put(examState.getOrder(), examState);
    }
  }

  private ExamState(int order, String displayName) {
    this.order = order;
    this.displayName = displayName;
  }

  public int getOrder() {
    return order;
  }

  /**
   * @param order
   *          the value of the enum property "order"
   * @return returns the enum object which belongs to the given property "order". Returns null if no
   *         matching enum is found.
   */
  public static ExamState forName(int order) {
    return nameToValueMap.get(order);
  }

  public String getDisplayName() {
    return displayName;
  }

}

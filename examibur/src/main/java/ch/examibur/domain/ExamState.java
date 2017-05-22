package ch.examibur.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ExamState {
  CORRECTION(0),
  REVIEW(1),
  APPROVAL(2),
  APPEAL(3),
  ARCHIVED(4);

  private final int order;
  private static final Map<Integer, ExamState> nameToValueMap = new HashMap<>();

  static {
    for (ExamState examState : EnumSet.allOf(ExamState.class)) {
      nameToValueMap.put(examState.getOrder(), examState);
    }
  }

  private ExamState(int order) {
    this.order = order;
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
}

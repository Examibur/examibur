package ch.examibur.ui.app.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum MessageType {
  SUCCESS("success"),
  INFO("info"),
  WARNING("warning"),
  DANGER("danger");

  private final String type;

  private static final Map<String, MessageType> nameToValueMap = new HashMap<>();

  static {
    for (MessageType messageType : EnumSet.allOf(MessageType.class)) {
      nameToValueMap.put(messageType.getType(), messageType);
    }
  }

  private MessageType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  /**
   * @param type
   *          the value of the enum property "type"
   * @return returns the enum object which belongs to the given property "type". Returns null if no
   *         matching enum is found.
   */
  public static MessageType forName(String type) {
    return nameToValueMap.get(type);
  }
}

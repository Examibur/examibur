package ch.examibur.ui.app.model;

public class Message {

  private String messageText;
  private MessageType type;

  public Message(String message, MessageType type) {
    this.messageText = message;
    this.type = type;
  }

  public static Message success(String message) {
    return new Message(message, MessageType.SUCCESS);
  }

  public static Message info(String message) {
    return new Message(message, MessageType.INFO);
  }

  public static Message warning(String message) {
    return new Message(message, MessageType.WARNING);
  }

  public static Message danger(String message) {
    return new Message(message, MessageType.DANGER);
  }

  public String getMessageText() {
    return messageText;
  }

  public MessageType getType() {
    return type;
  }
}

package org.mpsjunio2023.messagehandler;

public class Message {
  private int identifier ;
  private String messageContents ;

  public Message(int identifier, String messageContents) {
    this.identifier = identifier ;
    this.messageContents = messageContents ;
  }

  public int getIdentifier() {
    return identifier;
  }

  public String getMessageContents() {
    return messageContents;
  }
}

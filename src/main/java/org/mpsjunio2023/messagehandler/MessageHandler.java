package org.mpsjunio2023.messagehandler;

public class MessageHandler {
  public boolean sendMessage(Message message, BoundedQueue<Message> queue) {
    boolean messageSent = false ;
    if (queue.getNumberOfItems() < queue.getCapacity()) {
      queue.put(message);
      messageSent = true ;
    }
    return messageSent ;
  }

  public Message receiveMessage(BoundedQueue<Message> queue) {
    if (queue.isEmpty()) {
      throw new EmptyQueueException() ;
    } else {
      return queue.get() ;
    }
  }
}

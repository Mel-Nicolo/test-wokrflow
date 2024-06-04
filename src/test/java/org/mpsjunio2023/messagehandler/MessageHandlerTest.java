package org.mpsjunio2023.messagehandler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageHandlerTest {

    @Test
    @DisplayName("Test send message")
    void testSendMessage() {
        // Arrange
        MessageHandler handler = new MessageHandler();
        Message message = new Message(1,"Test message");
        BoundedQueue<Message> queue = mock(BoundedQueue.class);
        when(queue.getNumberOfItems()).thenReturn(0);
        when(queue.getCapacity()).thenReturn(10);

        // Act
        boolean result = handler.sendMessage(message, queue);

        // Assert
        assertTrue(result);
        verify(queue).put(message);
    }

    @Test
    @DisplayName("Test send message to full queue")
    void testReceiveMessage() {
        // Arrange
        MessageHandler handler = new MessageHandler();
        Message message = new Message(1,"Test message");
        BoundedQueue<Message> queue = mock(BoundedQueue.class);
        when(queue.isEmpty()).thenReturn(false);
        when(queue.get()).thenReturn(message);

        // Act
        Message receivedMessage = handler.receiveMessage(queue);

        // Assert
        assertEquals(message, receivedMessage);
    }

    @Test
    @DisplayName("Test receive message from empty queue")
    void testReceiveMessageFromEmptyQueue() {
        // Arrange
        MessageHandler handler = new MessageHandler();
        BoundedQueue<Message> queue = mock(BoundedQueue.class);
        when(queue.isEmpty()).thenReturn(true);

        // Act & Assert
        assertThrows(EmptyQueueException.class, () -> handler.receiveMessage(queue));
    }

    @Test
    @DisplayName("Test send message to full queue")
    void testSendMessageToFullQueue() {
        // Arrange
        MessageHandler handler = new MessageHandler();
        Message message = new Message(1,"Test message");
        BoundedQueue<Message> queue = mock(BoundedQueue.class);
        when(queue.getNumberOfItems()).thenReturn(10);
        when(queue.getCapacity()).thenReturn(10);

        // Act
        boolean result = handler.sendMessage(message, queue);

        // Assert
        assertFalse(result);
        verify(queue, never()).put(message);
    }

    @Test
    @DisplayName("Test send message to full queue with Mockito")
    void testSendMessageToFullQueueWithMockito() {
        // Arrange
        MessageHandler handler = new MessageHandler();
        Message message = new Message(1,"Test message");
        BoundedQueue<Message> queue = mock(BoundedQueue.class);
        when(queue.getNumberOfItems()).thenReturn(10);
        when(queue.getCapacity()).thenReturn(10);

        // Act
        boolean result = handler.sendMessage(message, queue);

        // Assert
        assertFalse(result);
        verify(queue, never()).put(message);
    }
}
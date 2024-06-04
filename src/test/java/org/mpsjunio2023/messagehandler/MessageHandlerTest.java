package org.mpsjunio2023.messagehandler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageHandlerTest {

    @Test
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
    void testReceiveMessageFromEmptyQueue() {
        // Arrange
        MessageHandler handler = new MessageHandler();
        BoundedQueue<Message> queue = mock(BoundedQueue.class);
        when(queue.isEmpty()).thenReturn(true);

        // Act & Assert
        assertThrows(EmptyQueueException.class, () -> handler.receiveMessage(queue));
    }
}
package org.mpsjunio2023.messagehandler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
class MessageHandlerTest {

    private BoundedQueue<Message> cola;
    private MessageHandler mh = new MessageHandler();
    private Message mensaje;

    @Test
    @DisplayName("sendMessage devuelve false si la cola no tiene capaciedad")
    public void test_SendMessage_ColaSinCapacidadDevuelveFalse(){
        //Arrange
        cola = mock(BoundedQueue.class);
        mensaje = new Message(1, "Hola");
        when(cola.getNumberOfItems()).thenReturn(3);
        when(cola.getCapacity()).thenReturn(3);
    
        //Act
        boolean result = mh.sendMessage(mensaje, cola);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("sendMessage devuelve true si la cola tiene capaciedad")
    public void test_SendMessage_ColaConCapacidadDevuelveTrue(){
        //Arrange
        cola = mock(BoundedQueue.class);
        mensaje = new Message(1, "Hola");
        when(cola.getNumberOfItems()).thenReturn(2);
        when(cola.getCapacity()).thenReturn(3);
    
        //Act
        boolean result = mh.sendMessage(mensaje, cola);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("receiveMessage lanza EmptyQueueException si la cola esta vacia")
    public void test_ReceiveMessage_ColaVaciaLanzaEmptyQueueException(){
        //Arrange
        cola = mock(BoundedQueue.class);
        when(cola.isEmpty()).thenReturn(true);
    
        //Act & Assert
        assertThrows(EmptyQueueException.class, () -> mh.receiveMessage(cola));
    }

    @Test
    @DisplayName("receiveMessage devuelve el mensaje si la cola no esta vacia")
    public void test_ReceiveMessage_ColaNoVaciaDevuelveMensaje(){
        //Arrange
        cola = mock(BoundedQueue.class);
        mensaje = new Message(1, "Hola");
        when(cola.isEmpty()).thenReturn(false);
        when(cola.get()).thenReturn(mensaje);
    
        //Act
        Message result = mh.receiveMessage(cola);

        //Assert
        assertEquals(mensaje, result);
    }

    @Test
    @DisplayName("receiveMessage devuelve el mensaje si la cola no esta vacia")
    public void test_ReceiveMessage_ColaNoVaciaDevuelveMensaje2(){
        //Arrange
        cola = mock(BoundedQueue.class);
        mensaje = new Message(1, "Hola");
        when(cola.isEmpty()).thenReturn(false);
        when(cola.get()).thenReturn(mensaje);
    
        //Act
        Message result = mh.receiveMessage(cola);

        //Assert
        assertEquals(mensaje, result);
    }
}
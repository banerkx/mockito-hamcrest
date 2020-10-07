package com.oreilly.mockito;

import com.oreilly.Publisher;
import com.oreilly.Subscriber;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PublisherTest {

    private final Publisher pub = new Publisher();
    private final Subscriber sub1 = mock(Subscriber.class);
    private final Subscriber sub2 = mock(Subscriber.class);

    @Before
    public void setUp() {
        pub.addSubscriber(sub1);
        pub.addSubscriber(sub2);
    }

    @Test
    public void testSend() {
        pub.send("Hello");

        verify(sub1).receive("Hello");
        verify(sub2).receive("Hello");
    }

    @Test
    public void testSendWithBadSubscriber() {
        // sub1 throws an exception every time
        doThrow(RuntimeException.class).when(sub1).receive(anyString());

        pub.send("message 1");
        pub.send("message 2");

        // sub2 still receives the messages
        verify(sub2, times(2)).receive(argThat(s -> s.matches("message \\d")));
        verify(sub2, times(2)).receive(anyString());

        // sub1 receive method was called twice, even though it threw exception
        verify(sub1, times(2)).receive(anyString());
    }
}
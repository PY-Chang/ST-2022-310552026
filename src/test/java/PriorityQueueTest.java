import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class PriorityQueueTest {
    static Stream<Arguments> streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{3, 1, 2}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{-3, -1, -2, 5}, new int[]{-3, -2, -1, 5}),
                Arguments.of(new int[]{9, 12, -7, 3}, new int[]{-7, 3, 9, 12}),
                Arguments.of(new int[]{-3, 1, 11, 0, 9, 3}, new int[]{-3, 0, 1,3, 9, 11}),
                Arguments.of(new int[]{6, 2, 0, 3, 2}, new int[]{0, 2, 2, 3, 6})
        );

    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int [] random_array, int [] correct_array){
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
        int[] result =  new int[random_array.length];

        for (int i=0; i< random_array.length; i++){
            pQueue.offer(random_array[i]);
        }

        for (int i=0; i< random_array.length; i++){
            result[i] = pQueue.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void IllegalArgumentException (){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{test.offer(Integer.valueOf(""));
        });

        String expectedMessage = "For input string: \"\"";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void NullPointerException(){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        Exception exception = assertThrows(NullPointerException.class, ()->{test.offer(null);
        });
        assertEquals(null, exception.getMessage());
    }

    @Test
    public void ClassCastException(){
        PriorityQueue<String> test = new PriorityQueue<String>();
        test.offer("apple");
        Object para = 0;
        Exception exception = assertThrows(ClassCastException.class, ()->{test.offer((String)para );}
        );
        String expectedMessage = "class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
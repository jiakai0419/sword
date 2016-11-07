package sword.lib;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** 
* Filter Tester.
* 
* @author KaiJia
* @since 2016-11-07
* @version 1.0 
*/ 
public class FilterTest {
    /**
    *
    * Method: filter(List<T> list, Predicate<T> pred)
    *
    */
    @Test
    public void testFilter() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Assert.assertEquals(new LinkedList<Integer>() {{
            add(1);
            add(3);
            add(5);
            add(7);
            add(9);
        }}, Filter.filter(list, new Predicate<Integer>() {
            @Override
            public boolean predicate(Integer integer) {
                return integer % 2 == 1;
            }
        }));
        Assert.assertEquals(new LinkedList<Integer>() {{
            add(2);
            add(4);
            add(6);
            add(8);
            add(10);
        }}, Filter.filter(list, new Predicate<Integer>() {
            @Override
            public boolean predicate(Integer integer) {
                return integer % 2 == 0;
            }
        }));
    }

} 

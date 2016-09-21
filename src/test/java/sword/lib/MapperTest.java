package sword.lib;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/** 
* Mapper Tester. 
* 
* @author KaiJia
* @since 2016-09-21
* @version 1.0 
*/ 
public class MapperTest {
    /**
    *
    * Method: create(List<V> list, String name, K[] k)
    *
    */
    @Test
    public void testCreate() throws Exception {
        // case: normal
        List<Foo> foos = new LinkedList<Foo>() {{
            add(new Foo(5, 32L));
            add(new Foo(8, 256L));
            add(new Foo(12, 4096L));
        }};
        Map<Integer, Foo> actual = Mapper.create(foos, "x", new Integer[0]);
        Assert.assertEquals(new HashMap<Integer, Foo>() {{
            put(5, new Foo(5, 32L));
            put(8, new Foo(8, 256L));
            put(12, new Foo(12, 4096L));
        }}, actual);

        // case: repeated key
        foos.add(new Foo(8, 8L));
        try {
            actual = Mapper.create(foos, "x", new Integer[0]);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("repeated key", e.getMessage());
        }
    }
}

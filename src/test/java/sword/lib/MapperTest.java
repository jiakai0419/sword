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

    /**
     *
     * Method: group(List<V> list, String name, K[] k)
     *
     */
    @Test
    public void testGroup() throws Exception {
        // case: normal
        List<Foo> foos = new LinkedList<Foo>() {{
            add(new Foo(2, 2L));
            add(new Foo(2, 6L));
            add(new Foo(2, 8L));
            add(new Foo(2, 8L));
            add(new Foo(2, 8L));
            add(new Foo(3, 3L));
            add(new Foo(3, 9L));
            add(new Foo(2, 4L));
            add(new Foo(3, 6L));
            add(new Foo(3, 3L));
            add(new Foo(3, 3L));
            add(new Foo(5, 10L));
            add(new Foo(3, 6L));
        }};
        Map<Integer, List<Foo>> actual = Mapper.group(foos, "x", new Integer[0]);
        Assert.assertEquals(new HashMap<Integer, List<Foo>>() {{
            put(2, new LinkedList<Foo>() {{
                add(new Foo(2, 2L));
                add(new Foo(2, 6L));
                add(new Foo(2, 8L));
                add(new Foo(2, 8L));
                add(new Foo(2, 8L));
                add(new Foo(2, 4L));
            }});
            put(3, new LinkedList<Foo>() {{
                add(new Foo(3, 3L));
                add(new Foo(3, 9L));
                add(new Foo(3, 6L));
                add(new Foo(3, 3L));
                add(new Foo(3, 3L));
                add(new Foo(3, 6L));
            }});
            put(5, new LinkedList<Foo>() {{
                add(new Foo(5, 10L));
            }});
        }}, actual);
    }

    /**
     *
     * Method: group(List<V> list, FMapor<V, K> fMapor)
     *
     */
    @Test
    public void testGroup2() throws Exception {
        // case: normal
        List<Foo> foos = new LinkedList<Foo>() {{
            add(new Foo(1, 1L));
            add(new Foo(2, 4L));
            add(new Foo(3, 9L));
            add(new Foo(4, 16L));
            add(new Foo(5, 25L));
            add(new Foo(6, 36L));
            add(new Foo(7, 49L));
            add(new Foo(8, 64L));
            add(new Foo(9, 81L));
            add(new Foo(10, 100L));
        }};
        Map<Integer, List<Foo>> actual = Mapper.group(foos, new FMapor<Foo, Integer>() {
            @Override
            public Integer fmap(Foo foo) {
                return foo.getX() % 2;
            }
        });
        Assert.assertEquals(new HashMap<Integer, List<Foo>>() {{
            put(0, new LinkedList<Foo>() {{
                add(new Foo(2, 4L));
                add(new Foo(4, 16L));
                add(new Foo(6, 36L));
                add(new Foo(8, 64L));
                add(new Foo(10, 100L));
            }});
            put(1, new LinkedList<Foo>() {{
                add(new Foo(1, 1L));
                add(new Foo(3, 9L));
                add(new Foo(5, 25L));
                add(new Foo(7, 49L));
                add(new Foo(9, 81L));
            }});
        }}, actual);
    }
}

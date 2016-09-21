package sword.lib;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/** 
* Pagor Tester. 
* 
* @author KaiJia
* @since 2016-09-21
* @version 1.0 
*/ 
public class PagorTest {
    /**
    *
    * Method: page(List<T> list, int pageNo, int pageSize)
    *
    */
    @Test
    public void testPage() throws Exception {
        List<Foo> list = new LinkedList<Foo>() {{
            add(new Foo(1,1L));
            add(new Foo(2,1L));
            add(new Foo(3,1L));
            add(new Foo(4,1L));
            add(new Foo(5,1L));
            add(new Foo(6,1L));
            add(new Foo(7,1L));
            add(new Foo(8,1L));
            add(new Foo(9,1L));
            add(new Foo(10,1L));
        }};

        Assert.assertEquals(new LinkedList<Foo>() {{
            add(new Foo(1,1L));
            add(new Foo(2,1L));
            add(new Foo(3,1L));
            add(new Foo(4,1L));
        }}, Pagor.page(list, 1, 4));

        Assert.assertEquals(new LinkedList<Foo>() {{
            add(new Foo(5,1L));
            add(new Foo(6,1L));
            add(new Foo(7,1L));
            add(new Foo(8,1L));
        }}, Pagor.page(list, 2, 4));

        Assert.assertEquals(new LinkedList<Foo>() {{
            add(new Foo(9,1L));
            add(new Foo(10,1L));
        }}, Pagor.page(list, 3, 4));

        Assert.assertEquals(new LinkedList<Foo>(), Pagor.page(list, 4, 4));
    }
}

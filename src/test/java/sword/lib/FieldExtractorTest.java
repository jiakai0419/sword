package sword.lib;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/** 
* FieldExtractor Tester. 
* 
* @author KaiJia
* @since 2016-09-20
* @version 1.0 
*/ 
public class FieldExtractorTest {

    /**
    *
    * Method: extract(Object obj, String name, T[] a)
    *
    */
    @Test
    public void testExtractForObjNameA() throws Exception {
        Long y = FieldExtractor.extract(new Foo(1, 65535L), "y", new Long[0]);
        Assert.assertEquals(65535L, y.longValue());
    }

    /**
    *
    * Method: extract(List list, String name, T[] a)
    *
    */
    @Test
    public void testExtractForListNameA() throws Exception {
        List<Integer> xList = FieldExtractor.extract(
                Arrays.asList(new Foo(1024, 10L),
                        new Foo(2048, 11L)),
                "x",
                new Integer[0]);
        Assert.assertArrayEquals(new Object[]{1024, 2048}, xList.toArray(new Object[0]));
    }

} 

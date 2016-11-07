package sword.lib;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** 
* FMap Tester.
* 
* @author KaiJia
* @since 2016-11-07
* @version 1.0 
*/ 
public class FMapTest {
    /**
    *
    * Method: fmap(List<A> list, FMapor<A, B> fmapor)
    *
    */
    @Test
    public void testFMap() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Assert.assertEquals(new LinkedList<String>() {{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
        }}, FMap.fmap(list, new FMapor<Integer, String>() {
            @Override
            public String fmap(Integer integer) {
                return integer.toString();
            }
        }));
    }

}

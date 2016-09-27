package sword.lib;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/** 
* Compactor Tester. 
* 
* @author KaiJia
* @since 2016-09-28
* @version 1.0 
*/ 
public class CompactorTest {
    /**
    *
    * Method: compact(List<T> list)
    *
    */
    @Test
    public void testCompact() throws Exception {
        Assert.assertEquals(
                new LinkedList<Long>() {{
                    add(0L);
                    add(1L);
                    add(1L);
                    add(2L);
                    add(3L);
                    add(5L);
                    add(8L);
                }},
                Compactor.compact(new LinkedList<Long>() {{
                    add(0L);
                    add(1L);
                    add(null);
                    add(null);
                    add(1L);
                    add(2L);
                    add(null);
                    add(null);
                    add(null);
                    add(3L);
                    add(5L);
                    add(null);
                    add(8L);
                    add(null);
                }})
        );
    }

} 

package sword.lib;

import org.junit.Assert;
import org.junit.Test;

/** 
* Defaultor Tester.
* 
* @author KaiJia
* @since 2016-09-28
* @version 1.0 
*/ 
public class DefaultorTest {
    /**
    *
    * Method: get(T value, T dft)
    *
    */
    @Test
    public void testGet() throws Exception {
        Assert.assertEquals(new Integer(0), Defaultor.get(null, 0));
        Assert.assertEquals(new Integer(1024), Defaultor.get(1024, 0));
    }
} 

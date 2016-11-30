package sword.lib.dict;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/** 
* DyncDict Tester.
* 
* @author KaiJia
* @since 2016-11-30
* @version 1.0 
*/ 
public class DyncDictTest {
    /**
    *
    * Method: reload()
    *
    */
    @Test
    public void testReload() throws Exception {
        String path = this.getClass().getClassLoader().getResource("dync_text").getPath();
        DyncDict<Integer, List<String>> dyncDict = new DyncDict<>(
                path,
                line -> {
                    String[] kv = line.split("\t");
                    Integer k = Integer.valueOf(kv[0]);
                    String[] vItems = kv[1].split(",");
                    List<String> v = Arrays.asList(vItems);
                    return new ImmutablePair<>(k, v);
                },
                Integer.MAX_VALUE,
                "UTF-8");
        while (!dyncDict.isInitialized()) {
            Thread.sleep(1000);
        }
        Assert.assertEquals(new HashMap<Integer, List<String>>() {{
            put(1, Arrays.asList("abc", "777", "fet"));
            put(2, Arrays.asList("1024"));
            put(10, Arrays.asList("KaiJia", "18"));
        }}, dyncDict.snapshot());
    }

} 

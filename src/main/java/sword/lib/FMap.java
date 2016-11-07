package sword.lib;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KaiJia
 * @DATE 2016/11/7
 */
public class FMap {
    public static <A, B> List<B> fmap(List<A> list, FMapor<A, B> fmapor) {
        if (list == null) {
            return null;
        }
        List<B> result = new LinkedList<B>();
        for (A a : list) {
            result.add(fmapor.fmap(a));
        }
        return result;
    }
}

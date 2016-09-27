package sword.lib;

import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KaiJia
 * @DATE 2016-09-28
 */
public class Compactor {
    public static <T> List<T> compact(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<T> result = new LinkedList<T>();
        for (T t : list) {
            if (t == null) {
                continue;
            }
            result.add(t);
        }
        return result;
    }
}

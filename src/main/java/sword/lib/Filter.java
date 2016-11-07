package sword.lib;

import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KaiJia
 * @DATE 2016-11-07
 */
public class Filter {
    public static <T> List<T> filter(List<T> list, Predicate<T> pred) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<T> result = new LinkedList<T>();
        for (T t : list) {
            if (pred.predicate(t)) {
                result.add(t);
            }
        }
        return result;
    }
}

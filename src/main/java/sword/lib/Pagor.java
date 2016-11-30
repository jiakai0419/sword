package sword.lib;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KaiJia
 * @DATE 2016-09-21
 * @DESC page + or
 */
public class Pagor {
    public static <T> List<T> page(List<T> list, int pageNo, int pageSize) {
        if (list == null) {
            return null;
        }
        if (pageNo < 1 || pageSize < 1) {
            throw new IllegalArgumentException();
        }
        List<T> result = new LinkedList<>();
        int offset = (pageNo - 1) * pageSize;
        int end = offset + pageSize;
        for (int i = offset; i < end && i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }
}

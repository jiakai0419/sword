package sword.lib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author KaiJia
 * @DATE 2016-09-21
 */
public class Mapper {
    public static <K, V> Map<K, V> create(List<V> list, String name, K[] k) throws NoSuchFieldException, IllegalAccessException {
        if (list == null) {
            return null;
        }
        Map<K, V> result = new HashMap<K, V>();
        for (V obj : list) {
            K field = FieldExtractor.extract(obj, name, k);
            if (result.containsKey(field)) {
                throw new IllegalArgumentException("repeated key");
            }
            result.put(field, obj);
        }
        return result;
    }
}

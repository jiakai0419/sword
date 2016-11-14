package sword.lib;

import java.util.HashMap;
import java.util.LinkedList;
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

    public static <K, V> Map<K, List<V>> group(List<V> list, String name, K[] k) throws NoSuchFieldException, IllegalAccessException {
        if (list == null) {
            return null;
        }
        Map<K, List<V>> result = new HashMap<K, List<V>>();
        for (V obj : list) {
            K field = FieldExtractor.extract(obj, name, k);
            List<V> same = Defaultor.get(result.get(field), new LinkedList<V>());
            same.add(obj);
            result.put(field, same);
        }
        return result;
    }

    public static <K, V> Map<K, List<V>> group(List<V> list, FMapor<V, K> fMapor) {
        if (list == null) {
            return null;
        }
        Map<K, List<V>> result = new HashMap<K, List<V>>();
        for (V v : list) {
            K k = fMapor.fmap(v);
            List<V> same = Defaultor.get(result.get(k), new LinkedList<V>());
            same.add(v);
            result.put(k, same);
        }
        return result;
    }

}

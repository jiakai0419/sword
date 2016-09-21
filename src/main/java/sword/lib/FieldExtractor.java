package sword.lib;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author KaiJia
 * @DATE 2016-09-20
 */
public class FieldExtractor {
    public static <T> T extract(Object obj, String name, T[] a) throws NoSuchFieldException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    public static <T> List<T> extract(List list, String name, T[] a) throws NoSuchFieldException, IllegalAccessException {
        if (list == null) {
            return null;
        }
        List<T> result = new LinkedList<T>();
        for (Object obj : list) {
            result.add(extract(obj, name, a));
        }
        return result;
    }
}

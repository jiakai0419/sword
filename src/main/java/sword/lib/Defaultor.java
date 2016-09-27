package sword.lib;

/**
 * @Author KaiJia
 * @DATE 2016-09-28
 */
public class Defaultor {
    public static <T> T get(T value, T dft) {
        return value != null ? value : dft;
    }
}

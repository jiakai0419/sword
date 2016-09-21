package sword.lib;

/**
 * @Author KaiJia
 * @DATE 2016-09-20
 */
public class Foo {
    private Integer x;
    private Long y;

    public Foo() {}

    public Foo(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x.hashCode() + y.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Foo)) {
            return false;
        }
        Foo rhs = (Foo) o;
        if (x.equals(rhs.x) && y.equals(rhs.y)) {
            return true;
        } else {
            return false;
        }
    }
}

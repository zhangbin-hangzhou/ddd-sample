package shopping.infrastructure.common;

/**
 * 断言处理类，用于抛出各种API异常
 * Created by std on 2020/2/27.
 */
public class Asserts {
    public static void fail(String message) {
        throw new RuntimeException(message);
    }

    public static void isTrue(boolean flag, String message) {
        if (flag) {
            throw new RuntimeException(message);
        }
    }

}

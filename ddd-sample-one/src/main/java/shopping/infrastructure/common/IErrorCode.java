package shopping.infrastructure.common;

/**
 * 封装API的错误码
 * Created by std on 2019/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}

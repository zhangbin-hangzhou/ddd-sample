package shopping.infrastructure.common;

/**
 * 枚举了一些常用API操作码
 * Created by std on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "operation success"),
    FAILED(500, "operation fail"),
    BadRequest(400, "Bad Request"),
    UNAUTHORIZED(401, "not logged in or token expired"),
    FORBIDDEN(403, "No relevant permission"),
    MethodNotAllowed(405, "The request method is not supported"),
    BIZ_DEFAULT(500000, "通用业务处理异常");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package shopping.domain.messagehelper.entity.valueobject.enums;


import cn.hutool.core.util.StrUtil;

public enum EMhTag {

    NEW("New", "4日≤有效期"),
    EXPIRING_SONN("Expiring Soon", "24小时≤有效期<4日"),
    Reminder("Reminder", "24小时≤有效期<4日"),
    WELCOM_BOUNS("Welcome Bonus", "新科券消息 tag"),
    ;

    EMhTag(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public Boolean matchCode(String code){
        if(StrUtil.isEmpty(code)){
            return Boolean.FALSE;
        }
        return this.getCode().equals(code)? Boolean.TRUE : Boolean.FALSE;
    }
}

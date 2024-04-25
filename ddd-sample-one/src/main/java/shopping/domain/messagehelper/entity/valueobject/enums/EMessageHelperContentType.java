package shopping.domain.messagehelper.entity.valueobject.enums;


import cn.hutool.core.util.StrUtil;

public enum EMessageHelperContentType {
    SEND_NOTICE("sendNotice", "发放提醒"),
    LESS_FOUR_DAY("lessFourDay", "不足四日"),
    LESS_TWENTYFOUR_HOUR("lessTwentyfourhour", "不足24小时"),
    DIRECT_REWARD("directReward", "直邀奖励"),
    COMMISSION_REWARD("commissionReward", "分佣奖励"),
    ;

    EMessageHelperContentType(String code, String value) {
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

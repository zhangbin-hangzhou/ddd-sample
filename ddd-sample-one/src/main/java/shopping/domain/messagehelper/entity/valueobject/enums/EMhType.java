package shopping.domain.messagehelper.entity.valueobject.enums;


import cn.hutool.core.util.StrUtil;

public enum EMhType {

    COUPON("coupon", "券"),
    POINTS("points", "积分"),
    REFERRAL_REWARD("referralReward", "邀请奖励"),
    NEW_COUPON("newCustomersCoupon", "新客券"),
    ;

    EMhType(String code, String value) {
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

package shopping.domain.messagehelper.entity;

import org.springframework.stereotype.Component;
import shopping.domain.messagehelper.entity.dbo.MessageHelperDO;
import shopping.domain.messagehelper.entity.valueobject.enums.EMessageHelperContentType;
import shopping.domain.messagehelper.entity.valueobject.enums.EMhType;
import shopping.infrastructure.common.ObjectConverter;
import shopping.infrastructure.util.DateUtil;
import shopping.interfaces.dto.MessageHelperReqDTO;
import shopping.interfaces.dto.UserInfoDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MessageHelperDOConvert2 {


    public List<MessageHelperDO> toDO(MessageHelper helper) {
        List<MessageHelperDO> list = new ArrayList<>();
        helper.userInfoList.forEach(userInfo -> {
            MessageHelperDO entity = ObjectConverter.defaultConvert(helper, MessageHelperDO.class);
            entity.setDelete(0);
            entity.setUserId(userInfo.getUserId());
            entity.setInviteNo(userInfo.getInviteNo());
            entity.setCreateTime(new Date().getTime());
            entity.setReminderFlag(0);
            entity.setExpiringSoonFlag(0);
            entity.setStartTime(helper.getStartTime());
            entity.setEndTime(helper.getStartTime());
            entity.setReceiveTime(helper.getStartTime());
            entity.setReceiveToEndDiffSeconds(helper.calcuTimeInterval());
            entity.setTag(handlerTimeReturnTag(entity));
            list.add(entity);
        });
        return list;
    }

    public MessageHelperDO buildDO(MessageHelperReqDTO request, UserInfoDTO user) {
        MessageHelperDO entity = ObjectConverter.defaultConvert(request, MessageHelperDO.class);
        entity.setDelete(0);
        entity.setUserId(user.getUserId());
        entity.setInviteNo(user.getInviteNo());
        entity.setCreateTime(new Date().getTime());
        entity.setReminderFlag(0);
        entity.setExpiringSoonFlag(0);
        entity.setStartTime(request.getStartTime() == null?null:request.getStartTime().getTime());
        entity.setEndTime(request.getEndTime() == null?null:request.getEndTime().getTime());
        entity.setReceiveTime(request.getReceiveTime() == null?null:request.getReceiveTime().getTime());
        entity.setReceiveToEndDiffSeconds((entity.getEndTime() != null && entity.getReceiveTime() != null)?calcuTimeInterval(entity):null);
        entity.setTag(handlerTimeReturnTag(entity));
        return entity;
    }

    /**
     * 计算时间间隔
     * @param entity
     * @return
     */
    private Long calcuTimeInterval(MessageHelperDO entity) {
      return   BigDecimal.valueOf(entity.getEndTime() - entity.getReceiveTime()).divide(new BigDecimal(1000), 0, BigDecimal.ROUND_UP).longValue();
    }


    public static String handlerTimeReturnTag(MessageHelperDO helperDO) {
        if(EMhType.COUPON.matchCode(helperDO.getType()) ||
                EMhType.POINTS.matchCode(helperDO.getType())){

            //有效期大于等于4日为“发放到账”，有效期小于4日时变更为“限时有效”，不足24h为“即将过期”
            int daybetween = DateUtil.daysBetween(new Date(), new Date(helperDO.getEndTime()));
            if(daybetween >= 4){
                return "New";
            }

            Long hoursBetween = DateUtil.getHoursBetween(new Date(), new Date(helperDO.getEndTime()));
            if(daybetween < 4 && hoursBetween.intValue() >= 24){
                return "Reminder";
            }

            if(hoursBetween.intValue() < 24){
                return "Expiring Soon";
            }
        }

        //新客券
        if(EMhType.NEW_COUPON.matchCode(helperDO.getType())){
            //有效期大于等于4日为“发放到账”
            int daybetween = DateUtil.daysBetween(new Date(), new Date(helperDO.getEndTime()));
            if(daybetween >= 4){
                return "Welcome Bonus";
            }

            Long hoursBetween = DateUtil.getHoursBetween(new Date(), new Date(helperDO.getEndTime()));
            if(daybetween < 4 && hoursBetween.intValue() >= 24){
                return "Reminder";
            }

            if(hoursBetween.intValue() < 24){
                return "Expiring Soon";
            }
        }

        //裂变奖励
        if(EMhType.REFERRAL_REWARD.matchCode(helperDO.getType())){
            if(EMessageHelperContentType.COMMISSION_REWARD.matchCode(helperDO.getContentType())){
                return "Commission Reward";
            }
            else{
                return "Direct Reward";
            }
        }

        return "Expiring Soon";
    }
}

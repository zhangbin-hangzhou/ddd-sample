package shopping.domain.messagehelper.entity;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import shopping.domain.messagehelper.entity.valueobject.UserInfo;
import shopping.infrastructure.common.Asserts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 消息推送表
 * </p>
 *
 * @author kl
 * @since 2023-05-23
 */

@Data
public class MessageHelper implements Serializable {
    private String id;
    private Integer inviteNo;
    private String userId;
    private String tag;
    private String type;
    private String contentType;
    private String data;
    private Integer useStatus;
    private Long ticketId;
    private Long tempBalanceDetailId;
    private Integer reminderFlag;
    private Integer expiringSoonFlag;
    private Long receiveToEndDiffSeconds;
    private Long startTime;
    private Long endTime;
    private Long receiveTime;
    private Long createTime;
    private Integer delete;
    List<UserInfo> userInfoList;

    /**
     * 检测
     */
    public void checkUserinfoList(){
        Asserts.isTrue(CollUtil.isEmpty(userInfoList),"param userInfoList should not null");
        List<Integer> inviteNoList = getInviteNoList();
        Asserts.isTrue(CollUtil.isEmpty(inviteNoList),"param inviteNoList should not null");
    }

    //获取数据
    public List<Integer> getInviteNoList(){
        return userInfoList.stream().map(A->A.getInviteNo()).collect(Collectors.toList());
    }

    /**
     * 计算时间间隔
     * @return
     */
    public Long calcuTimeInterval(){
       return  (this.getEndTime() != null && this.getReceiveTime() != null)?
               BigDecimal.valueOf(this.getEndTime() - this.getReceiveTime()).divide(new BigDecimal(1000), 0, BigDecimal.ROUND_UP).longValue():null;
    }
}

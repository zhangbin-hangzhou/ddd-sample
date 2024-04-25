package shopping.domain.messagehelper.entity.dbo;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;

/**
 * <p>
 * 消息推送表
 * </p>
 *
 * @author kl
 * @since 2023-05-23
 */

@Data
public class MessageHelperDO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;
    /**
     * 用户的自增id
     */
    private Integer inviteNo;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 标签
     */
    private String tag;

    /**
     * 消息类型   coupon:券  points:积分
     */
    private String type;

    /**
     * 消息内容类型
     */
    private String contentType;
    /**
     * 元数据
     */
    private String data;
    /**
     * 使用状态
     */
    private Integer useStatus;
    /**
     * 券id
     */
    private Long ticketId;

    /**
     * 临时余额记录id
     */
    private Long tempBalanceDetailId;

    //第二阶段提醒
    private Integer reminderFlag;

    //第二阶段提醒
    private Integer expiringSoonFlag;

    /**
     * 领取时间到结束时间的 间隔秒
     */
    private Long receiveToEndDiffSeconds;

    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 领取时间
     */
    private Long receiveTime;
    /**
     * 创建时间
     */
    @Indexed
    private Long createTime;


    //是否删除标记
    private Integer delete;
}

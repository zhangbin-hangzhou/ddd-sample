package shopping.interfaces.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 消息助手
 * </p>
 *
 * @author beichen
 * @since 2024-05-04
 */

@Data
public class MessageHelperReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户的自增id
     */
    private Integer inviteNo;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 消息类型   coupon/points
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
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 券id
     */
    private Long ticketId;
    /**
     * 领取时间
     */
    private Date receiveTime;

    /**
     * 临时余额记录id
     */
    private Long tempBalanceDetailId;

    /**
     * 使用状态
     */
    private Integer useStatus;
    /**
     * 发送的用户id
     */
    private List<UserInfoDTO> sendUserList;
}

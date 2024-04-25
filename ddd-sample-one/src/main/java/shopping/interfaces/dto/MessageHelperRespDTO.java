package shopping.interfaces.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageHelperRespDTO implements Serializable {
    private String id;
    private Integer inviteNo;
    private String userId;
    private String type;//coupon 券  points 积分
    private String tag;
    private String data;
    private String content;
    private String contentType;
    private Date endTime;
    private Date startTime;
    private Date receiveTime;
    private Integer useStatus;
    private Long ticketId;
    private Long tempBalanceDetailId;
    private Date createTime;
    private Integer delete;
}

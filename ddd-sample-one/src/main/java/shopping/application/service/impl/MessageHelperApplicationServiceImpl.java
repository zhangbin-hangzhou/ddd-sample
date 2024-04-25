package shopping.application.service.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import shopping.application.service.MessageHelperApplicationService;
import shopping.domain.messagehelper.entity.MessageHelper;
import shopping.domain.messagehelper.entity.MessageHelperDOConvert2;
import shopping.domain.messagehelper.entity.dbo.MessageHelperDO;
import shopping.domain.messagehelper.repository.MessageHelperRepository;
import shopping.infrastructure.mongo.MongoTemplateService;
import shopping.infrastructure.redis.StringRedisService;
import shopping.interfaces.dto.MessageHelperReqDTO;
import shopping.interfaces.dto.UserInfoDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageHelperApplicationServiceImpl implements MessageHelperApplicationService {

    public final String MESSAGE_HELPER = "user:message:helper";
    public final String COLLECTTION_NAME = "MESSAGE_HELPER";

    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private StringRedisService stringRedisService;

    @Resource
    private MessageHelperRepository messageHelperRepository;

    @Resource
    private MessageHelperDOConvert messageHelperDOConvert;

    @Resource
    private MessageHelperDOConvert2 messageHelperDOConvert2;

    @Override
    public void save(MessageHelperReqDTO dto) {
        List<UserInfoDTO> sendUserList = dto.getSendUserList();
        List<MessageHelperDO> result = new ArrayList<>();
        for (UserInfoDTO user:sendUserList){
            try {
                if(user.getInviteNo() != null){
                    stringRedisTemplate.opsForValue().setBit(MESSAGE_HELPER,user.getInviteNo(),Boolean.TRUE);
                }
            }catch (Exception e){
                log.error("用户消息标识存放失败 userId:{} error:{}",user.getUserId(),e);
            }
            result.add(messageHelperDOConvert.buildDO(dto,user));
        }
        if(CollUtil.isNotEmpty(result)){
            mongoTemplate.insert(result, COLLECTTION_NAME);
        }
    }

    @Override
    public void save2(MessageHelper helper) {
        //校验数据
        helper.checkUserinfoList();
        //数据入库
        messageHelperRepository.insertBatch(messageHelperDOConvert2.toDO(helper));
        //更新 redis 位图
        stringRedisService.setBatch(MESSAGE_HELPER,helper.getInviteNoList(),Boolean.TRUE);
    }
}

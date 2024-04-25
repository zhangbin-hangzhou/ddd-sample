package shopping.domain.messagehelper.repository.impl;

import org.springframework.stereotype.Service;
import shopping.domain.messagehelper.entity.dbo.MessageHelperDO;
import shopping.domain.messagehelper.repository.MessageHelperRepository;
import shopping.infrastructure.mongo.MongoTemplateService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageHelperRepositoryImpl implements MessageHelperRepository {
    public final String COLLECTTION_NAME = "MESSAGE_HELPER";
    @Resource
    private MongoTemplateService mongoTemplateService;

    @Override
    public void insertBatch(List<MessageHelperDO> list) {
        mongoTemplateService.insertBatch(list,COLLECTTION_NAME);
    }
}

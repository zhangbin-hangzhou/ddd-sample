package shopping.infrastructure.mongo;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import shopping.infrastructure.common.Asserts;

import java.util.Objects;

@Component
public class MongoTemplateService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertBatch(Object data, String collectName){
        Asserts.isTrue(Objects.isNull(data),"param data should not null");
        Asserts.isTrue(StrUtil.isEmpty(collectName),"param collectName should not null");
        mongoTemplate.insert(data,collectName);
    }
}

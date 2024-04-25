package shopping.domain.messagehelper.repository;

import shopping.domain.messagehelper.entity.dbo.MessageHelperDO;

import java.util.List;

public interface MessageHelperRepository {
    void insertBatch(List<MessageHelperDO> list);
}

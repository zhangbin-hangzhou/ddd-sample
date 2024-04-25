package shopping.application.service;

import shopping.domain.messagehelper.entity.MessageHelper;
import shopping.interfaces.dto.MessageHelperReqDTO;

public interface MessageHelperApplicationService {
    void save(MessageHelperReqDTO dto);

    void save2(MessageHelper dto);
}

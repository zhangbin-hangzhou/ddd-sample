package shopping.interfaces.assembler;

import shopping.domain.messagehelper.entity.MessageHelper;
import shopping.interfaces.dto.MessageHelperReqDTO;
import shopping.interfaces.dto.MessageHelperRespDTO;

import java.util.ArrayList;
import java.util.List;

public class MessageHelperAssembler {

    public List<MessageHelperRespDTO> toDTO(List<MessageHelper> data) {
        return new ArrayList<>();
    }

    public MessageHelper toEntity(MessageHelperReqDTO dto) {

        return new MessageHelper();
    }
}

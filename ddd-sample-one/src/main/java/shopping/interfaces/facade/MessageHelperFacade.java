package shopping.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shopping.application.service.MessageHelperApplicationService;
import shopping.infrastructure.common.CommonResult;
import shopping.interfaces.assembler.MessageHelperAssembler;
import shopping.interfaces.dto.MessageHelperReqDTO;

import javax.annotation.Resource;

@RestController
@RequestMapping("/messageHelper")
@Slf4j
public class MessageHelperFacade {

    @Resource
    private MessageHelperApplicationService messageHelperApplicationService;

    @Resource
    private MessageHelperAssembler messageHelperAssembler;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> save(@RequestBody MessageHelperReqDTO dto) {
        messageHelperApplicationService.save(dto);
        messageHelperApplicationService.save2(messageHelperAssembler.toEntity(dto));
        return CommonResult.success();
    }

}

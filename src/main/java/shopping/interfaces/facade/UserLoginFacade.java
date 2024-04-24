import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.text.ParseException;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.ogc.standard.dao.base.ABaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.application.service.UserApplicationService;
import shopping.interfaces.dto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserLoginFacade  {
    @Autowired
    UserApplicationService userApplicationService;

    @PostMapping
    public Response create(UserDTO UserDTO) {
        try {
            userApplicationService.create(PersonAssembler.toDO(UserDTO));
            return Response.ok();
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
    }

    @PutMapping
    public Response update(UserDTO UserDTO) {
        try {
            userApplicationService.update(PersonAssembler.toDO(UserDTO));
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
        return Response.ok();
    }

    @DeleteMapping("/{userId}")
    public Response delete(@PathVariable String personId) {
        userApplicationService.deleteById(personId);
        return Response.ok();
    }

    @GetMapping("/{userId}")
    public Response get(@PathVariable String personId) {
        UserDo userDo = userApplicationService.findById(personId);
        return Response.ok(PersonAssembler.toDTO(userDo));
    }
}

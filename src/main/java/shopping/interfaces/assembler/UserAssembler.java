package shopping.interfaces.assembler;

import shopping.domain.user.entity.User;
import shopping.interfaces.dto.UserDTO;

public class UserAssembler {
    public static UserDTO toDTO(User User){
        UserDTO dto = new UserDTO();
        dto.setUserId(User.getUserId());
        dto.setUserType(User.getUserType().toString());
        dto.setUserName(User.getUserName());
        dto.setStatus(User.getStatus().toString());
        dto.setCreateTime(DateUtil.formatDateTime(User.getCreateTime()));
        dto.setLastModifyTime(DateUtil.formatDateTime(User.getLastModifyTime()));
        return dto;
    }

    public static User toDO(UserDTO dto) {
        User userDO = new User();
        userDO.setUserId(dto.getUserId());
        userDO.setUserType(UserType.valueOf(dto.getUserType()));
        userDO.setUserName(dto.getUserName());
        userDO.setStatus(UserStatus.valueOf(dto.getStatus()));
        userDO.setCreateTime(DateUtil.parseDateTime(dto.getCreateTime()));
        userDO.setLastModifyTime(DateUtil.parseDateTime(dto.getLastModifyTime()));
        return User;
    }
}

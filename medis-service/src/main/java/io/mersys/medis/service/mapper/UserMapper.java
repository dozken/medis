package io.mersys.medis.service.mapper;

import org.mapstruct.Mapper;

import io.mersys.medis.documents.user.User;
import io.mersys.medis.service.dto.UserDTO;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends DocumentMapper<UserDTO, User> {
}

package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.UserRequest;
import br.com.quatty.backend.api.dto.response.UserResponse;
import br.com.quatty.backend.business.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    User userRequestToEntity(UserRequest userRequest);

    UserResponse entityToUserResponse(User user);

}

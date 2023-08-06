package org.elbouchouki.hectify.core.users.mapper;

import org.elbouchouki.hectify.core.dto.PagingResponse;
import org.elbouchouki.hectify.core.users.dto.UserResponse;
import org.elbouchouki.hectify.core.users.entitie.User;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> user);

    User toEntity(UserResponse userResponse);

    List<User> toEntityList(List<UserResponse> userResponse);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UserResponse userResponse, @MappingTarget User user);


    @Mapping(target = "page", expression = "java(userPage.getNumber())")
    @Mapping(target = "size", expression = "java(userPage.getSize())")
    @Mapping(target = "totalPages", expression = "java(userPage.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(userPage.getTotalElements())")
    @Mapping(target = "records", expression = "java(toResponseList(userPage.getContent()))")
    PagingResponse<UserResponse> toPagingResponse(Page<User> userPage);
}

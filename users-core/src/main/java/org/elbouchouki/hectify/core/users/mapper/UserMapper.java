package org.elbouchouki.hectify.core.users.mapper;

import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.dto.user.*;
import org.elbouchouki.hectify.core.users.entitie.Role;
import org.elbouchouki.hectify.core.users.entitie.User;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {

    UserResponse toResponse(User entity);

    List<UserResponse> toResponseList(List<User> entity);

    @Mapping(target = "email", expression = "java(request.email().toLowerCase())")
    @Mapping(target = "username", expression = "java(request.username().toLowerCase())")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoleIds")
    User toEntity(UserCreateRequest request);

    List<User> toEntityList(List<UserCreateRequest> requests);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoleIds")
    void update(UserUpdateRequest request, @MappingTarget User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", expression = "java(request.newPassword())")
    void update(UserUpdatePasswordRequest request, @MappingTarget User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", expression = "java(request.email().toLowerCase())")
    void update(UserUpdateEmailRequest request, @MappingTarget User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "username", expression = "java(request.username().toLowerCase())")
    void update(UserUpdateUsernameRequest request, @MappingTarget User entity);

    @Mapping(target = "page", expression = "java(page.getNumber())")
    @Mapping(target = "size", expression = "java(page.getSize())")
    @Mapping(target = "totalPages", expression = "java(page.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(page.getTotalElements())")
    @Mapping(target = "records", expression = "java(toResponseList(page.getContent()))")
    PagingResponse<UserResponse> toPagingResponse(Page<User> page);

    @Named("mapRoleIds")
    default List<Role> mapRoleIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        return ids.stream()
                .map(
                        id -> Role.builder()
                                .roleId(id)
                                .build()
                )
                .toList();
    }
}

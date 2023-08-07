package org.elbouchouki.hectify.core.users.mapper;

import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.dto.role.RoleCreateRequest;
import org.elbouchouki.hectify.core.dto.role.RoleResponse;
import org.elbouchouki.hectify.core.dto.role.RoleUpdateRequest;
import org.elbouchouki.hectify.core.users.entitie.Permission;
import org.elbouchouki.hectify.core.users.entitie.Role;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface RoleMapper {

    RoleResponse toResponse(Role entity);

    List<RoleResponse> toResponseList(List<Role> entity);

    @Mapping(target = "roleName", expression = "java(request.roleName().toUpperCase())")
    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "mapPermissionIds")
    Role toEntity(RoleCreateRequest request);

    List<Role> toEntityList(List<RoleCreateRequest> requests);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "roleName", expression = "java(request.roleName().toUpperCase())")
    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "mapPermissionIds")
    void update(RoleUpdateRequest request, @MappingTarget Role entity);


    @Mapping(target = "page", expression = "java(page.getNumber())")
    @Mapping(target = "size", expression = "java(page.getSize())")
    @Mapping(target = "totalPages", expression = "java(page.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(page.getTotalElements())")
    @Mapping(target = "records", expression = "java(toResponseList(page.getContent()))")
    PagingResponse<RoleResponse> toPagingResponse(Page<Role> page);


    @Named("mapPermissionIds")
    default List<Permission> mapPermissionIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        return ids.stream()
                .map(
                        id -> Permission.builder()
                                .permissionId(id)
                                .build()
                )
                .toList();
    }
}

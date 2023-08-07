package org.elbouchouki.hectify.core.users.mapper;

import org.elbouchouki.hectify.core.dto.permission.PermissionCreateRequest;
import org.elbouchouki.hectify.core.dto.permission.PermissionResponse;
import org.elbouchouki.hectify.core.dto.permission.PermissionUpdateRequest;
import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.users.entitie.Permission;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface PermissionMapper {

    PermissionResponse toResponse(Permission entity);

    List<PermissionResponse> toResponseList(List<Permission> entity);

    @Mapping(target = "permissionName", expression = "java(request.permissionName().toUpperCase())")
    Permission toEntity(PermissionCreateRequest request);

    List<Permission> toEntityList(List<PermissionCreateRequest> requests);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "permissionName", expression = "java(request.permissionName().toUpperCase())")
    void update(PermissionUpdateRequest request, @MappingTarget Permission entity);


    @Mapping(target = "page", expression = "java(page.getNumber())")
    @Mapping(target = "size", expression = "java(page.getSize())")
    @Mapping(target = "totalPages", expression = "java(page.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(page.getTotalElements())")
    @Mapping(target = "records", expression = "java(toResponseList(page.getContent()))")
    PagingResponse<PermissionResponse> toPagingResponse(Page<Permission> page);
}

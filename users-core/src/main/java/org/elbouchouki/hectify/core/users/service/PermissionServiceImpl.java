package org.elbouchouki.hectify.core.users.service;

import lombok.RequiredArgsConstructor;
import org.elbouchouki.hectify.core.constant.CoreConstants;
import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.exception.AlreadyExistsException;
import org.elbouchouki.hectify.core.exception.NotFoundException;
import org.elbouchouki.hectify.core.dto.permission.PermissionCreateRequest;
import org.elbouchouki.hectify.core.dto.permission.PermissionResponse;
import org.elbouchouki.hectify.core.dto.permission.PermissionUpdateRequest;
import org.elbouchouki.hectify.core.users.entitie.Permission;
import org.elbouchouki.hectify.core.users.mapper.PermissionMapper;
import org.elbouchouki.hectify.core.users.repository.PermissionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final String ELEMENT_TYPE = "Permission";
    private final String ID_FIELD_NAME = "permissionId";
    private final PermissionRepository repository;
    private final PermissionMapper mapper;

    @Override
    public PermissionResponse createPermission(PermissionCreateRequest request) {
        if (this.existsByPermissionName(request.permissionName())) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_TYPE, "permissionName", request.permissionName()},
                    null
            );
        }

        return this.mapper.toResponse(
                this.repository.save(
                        this.mapper.toEntity(request)
                )
        );
    }

    @Override
    public PermissionResponse updatePermission(Long permissionId, PermissionUpdateRequest request) {
        Permission permission = this.repository.findById(permissionId).orElseThrow(
                () -> new NotFoundException(
                        CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                        new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, permissionId},
                        null
                )
        );

        if (this.existsByPermissionName(request.permissionName()) && !permission.getPermissionName().equals(request.permissionName())) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_TYPE, "permissionName", request.permissionName()},
                    null
            );
        }

        this.mapper.update(request, permission);

        return this.mapper.toResponse(
                this.repository.save(permission)
        );
    }

    @Override
    public PermissionResponse getPermission(Long permissionId) {
        return this.repository.findById(permissionId)
                .map(this.mapper::toResponse)
                .orElseThrow(
                        () -> new NotFoundException(
                                CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                                new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, permissionId},
                                null
                        )
                );
    }

    @Override
    public void deletePermission(Long permissionId) {
        if (!this.repository.existsById(permissionId)) {
            throw new NotFoundException(
                    CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, permissionId},
                    null
            );
        }
        this.repository.deleteById(permissionId);
    }

    @Override
    public PagingResponse<PermissionResponse> getPermissions(int page, int size) {
        return this.mapper.toPagingResponse(
                this.repository.findAll(
                        PageRequest.of(page, size)
                )
        );
    }

    @Override
    public boolean exists(Long permissionId) {
        return this.repository.existsById(permissionId);
    }

    @Override
    public boolean existsByPermissionName(String permissionName) {
        return this.repository.existsByPermissionName(permissionName);
    }

    @Override
    public void checkExistence(Set<Long> permissions) {
        Set<Long> foundPermissions = this.repository.findAllByPermissionIdIn(permissions)
                .stream().
                map(Permission::getPermissionId)
                .collect(Collectors.toSet());

        if (foundPermissions.size() != permissions.size()) {
            List<String> NotFoundPermissions = permissions.stream()
                    .filter(permission -> !foundPermissions.contains(permission))
                    .map(String::valueOf)
                    .toList();
            throw new NotFoundException(
                    CoreConstants.BusinessExceptionMessage.MANY_NOT_FOUND,
                    new Object[]{ELEMENT_TYPE},
                    NotFoundPermissions
            );
        }
    }
}

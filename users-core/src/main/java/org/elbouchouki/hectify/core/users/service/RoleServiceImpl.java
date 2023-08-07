package org.elbouchouki.hectify.core.users.service;

import lombok.RequiredArgsConstructor;
import org.elbouchouki.hectify.core.constant.CoreConstants;
import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.exception.AlreadyExistsException;
import org.elbouchouki.hectify.core.exception.NotFoundException;
import org.elbouchouki.hectify.core.dto.role.RoleCreateRequest;
import org.elbouchouki.hectify.core.dto.role.RoleResponse;
import org.elbouchouki.hectify.core.dto.role.RoleUpdateRequest;
import org.elbouchouki.hectify.core.users.entitie.Role;
import org.elbouchouki.hectify.core.users.mapper.RoleMapper;
import org.elbouchouki.hectify.core.users.repository.RoleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final String ELEMENT_TYPE = "Role";
    private final String ID_FIELD_NAME = "roleId";

    private final RoleRepository repository;
    private final RoleMapper mapper;
    private final PermissionService permissionService;

    @Override
    public RoleResponse createRole(RoleCreateRequest request) {
        if (this.existsByRoleName(request.roleName())) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_TYPE, "roleName", request.roleName()},
                    null
            );
        }

        this.checkPermissions(request.permissions());

        return this.mapper.toResponse(
                this.repository.save(
                        this.mapper.toEntity(request)
                )
        );
    }

    @Override
    public RoleResponse updateRole(Long roleId, RoleUpdateRequest request) {
        Role role = this.repository.findById(roleId).orElseThrow(
                () -> new NotFoundException(
                        CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                        new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, roleId},
                        null
                )
        );

        if (this.existsByRoleName(request.roleName()) && !role.getRoleName().equals(request.roleName())) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_TYPE, "roleName", request.roleName()},
                    null
            );
        }


        this.checkPermissions(request.permissions());

        this.mapper.update(request, role);

        return this.mapper.toResponse(
                this.repository.save(role)
        );
    }


    @Override
    public RoleResponse getRole(Long roleId) {
        return this.repository.findById(roleId)
                .map(this.mapper::toResponse)
                .orElseThrow(() -> new NotFoundException(
                        CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                        new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, roleId},
                        null
                ));
    }

    @Override
    public void deleteRole(Long roleId) {
        if (!this.exists(roleId)) {
            throw new NotFoundException(
                    CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, roleId},
                    null
            );
        }
        this.repository.deleteById(roleId);
    }

    @Override
    public PagingResponse<RoleResponse> getRoles(int page, int size) {
        return this.mapper.toPagingResponse(
                this.repository.findAll(
                        PageRequest.of(page, size)
                )
        );
    }

    @Override
    public boolean exists(Long roleId) {
        return this.repository.existsById(roleId);
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return this.repository.existsByRoleName(roleName);
    }

    @Override
    public void checkExistence(Set<Long> roles) {
        Set<Long> foundRoles = this.repository.findAllByRoleIdIn(roles)
                .stream().
                map(Role::getRoleId)
                .collect(Collectors.toSet());

        if (foundRoles.size() != roles.size()) {
            List<String> NotFoundRoles = roles.stream()
                    .filter(permission -> !foundRoles.contains(permission))
                    .map(String::valueOf)
                    .toList();
            throw new NotFoundException(
                    CoreConstants.BusinessExceptionMessage.MANY_NOT_FOUND,
                    new Object[]{ELEMENT_TYPE},
                    NotFoundRoles
            );
        }
    }

    private void checkPermissions(Set<Long> permissions) {
        this.permissionService.checkExistence(permissions);
    }
}

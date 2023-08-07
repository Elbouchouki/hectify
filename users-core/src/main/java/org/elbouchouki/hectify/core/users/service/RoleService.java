package org.elbouchouki.hectify.core.users.service;

import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.dto.role.RoleCreateRequest;
import org.elbouchouki.hectify.core.dto.role.RoleResponse;
import org.elbouchouki.hectify.core.dto.role.RoleUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    RoleResponse createRole(RoleCreateRequest request);

    RoleResponse updateRole(Long roleId, RoleUpdateRequest request);

    RoleResponse getRole(Long roleId);

    void deleteRole(Long roleId);

    PagingResponse<RoleResponse> getRoles(int page, int size);

    boolean exists(Long roleId);

    boolean existsByRoleName(String roleName);

    void checkExistence(Set<Long> roles);

}

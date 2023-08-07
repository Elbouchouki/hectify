package org.elbouchouki.hectify.core.users.service;

import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.dto.permission.PermissionCreateRequest;
import org.elbouchouki.hectify.core.dto.permission.PermissionResponse;
import org.elbouchouki.hectify.core.dto.permission.PermissionUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PermissionService {
    PermissionResponse createPermission(PermissionCreateRequest request);

    PermissionResponse updatePermission(Long permissionId, PermissionUpdateRequest request);

    PermissionResponse getPermission(Long permissionId);

    void deletePermission(Long permissionId);

    PagingResponse<PermissionResponse> getPermissions(int page, int size);

    boolean exists(Long permissionId);

    boolean existsByPermissionName(String permissionName);

    void checkExistence(Set<Long> permissionIds);

}

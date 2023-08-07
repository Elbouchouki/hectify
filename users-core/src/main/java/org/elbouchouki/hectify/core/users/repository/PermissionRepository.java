package org.elbouchouki.hectify.core.users.repository;

import org.elbouchouki.hectify.core.users.entitie.Permission;
import org.elbouchouki.hectify.core.users.entitie.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByPermissionName(String permissionName);

    Set<Permission> findAllByPermissionIdIn(Set<Long> permissionIds);

    Permission findByPermissionName(String permissionName);

}

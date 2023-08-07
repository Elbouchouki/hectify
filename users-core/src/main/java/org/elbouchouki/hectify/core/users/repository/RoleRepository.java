package org.elbouchouki.hectify.core.users.repository;

import org.elbouchouki.hectify.core.users.entitie.Permission;
import org.elbouchouki.hectify.core.users.entitie.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleName(String roleName);

    Set<Role> findAllByRoleIdIn(Set<Long> roleIds);

}

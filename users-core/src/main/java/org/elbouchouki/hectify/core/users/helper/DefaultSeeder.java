package org.elbouchouki.hectify.core.users.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elbouchouki.hectify.core.users.entitie.Permission;
import org.elbouchouki.hectify.core.users.entitie.Role;
import org.elbouchouki.hectify.core.users.repository.PermissionRepository;
import org.elbouchouki.hectify.core.users.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {

        log.info("Seeding default roles and permissions");

        List<Permission> defaultPermissions = getDefaultPermissions().stream().map(permission -> {
            Permission currentPerm = this.permissionRepository.findByPermissionName(
                    permission.getPermissionName()
            );
            if (Objects.isNull(currentPerm)) {
                return this.permissionRepository.save(permission);
            }
            return currentPerm;
        }).toList();

        getDefaultRoles(defaultPermissions).forEach(role -> {
            if (!this.roleRepository.existsByRoleName(role.getRoleName())) {
                this.roleRepository.save(role);
            }
        });

        log.info("Default roles and permissions seeded successfully");
    }

    List<Role> getDefaultRoles(List<Permission> defaultPermissions) {
        List<Permission> userPermissions = defaultPermissions.stream()
                .filter(permission -> permission
                        .getPermissionName()
                        .contains("READ")
                )
                .toList();

        return List.of(
                Role.builder()
                        .roleName("ADMIN")
                        .permissions(
                                defaultPermissions
                        )
                        .build(),
                Role.builder()
                        .roleName("USER")
                        .permissions(
                                userPermissions
                        )
                        .build()
        );
    }


    List<Permission> getDefaultPermissions() {
        return List.of(
                Permission.builder().permissionName("ROLE_CREATE").build(),
                Permission.builder().permissionName("ROLE_READ").build(),
                Permission.builder().permissionName("ROLE_UPDATE").build(),
                Permission.builder().permissionName("ROLE_DELETE").build(),
                Permission.builder().permissionName("USER_CREATE").build(),
                Permission.builder().permissionName("USER_READ").build(),
                Permission.builder().permissionName("USER_UPDATE").build(),
                Permission.builder().permissionName("USER_DELETE").build(),
                Permission.builder().permissionName("PERMISSION_CREATE").build(),
                Permission.builder().permissionName("PERMISSION_READ").build(),
                Permission.builder().permissionName("PERMISSION_UPDATE").build(),
                Permission.builder().permissionName("PERMISSION_DELETE").build(),
                Permission.builder().permissionName("ROLE_ASSIGN").build(),
                Permission.builder().permissionName("ROLE_UNASSIGN").build(),
                Permission.builder().permissionName("PERMISSION_ASSIGN").build(),
                Permission.builder().permissionName("PERMISSION_UNASSIGN").build()
        );
    }
}

package org.elbouchouki.hectify.core.users.entitie;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users_core_role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(unique = true, nullable = false)
    private String roleName;

    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;


    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId")
    )
    private List<Permission> permissions = new ArrayList<>();

}

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users_core_permission")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(unique = true, nullable = false)
    private String permissionName;

    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>();
}

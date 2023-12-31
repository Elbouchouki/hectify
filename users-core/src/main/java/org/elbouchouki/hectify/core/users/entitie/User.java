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
@Table(name = "users_core_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    @Builder.Default
    private long passwordVersion = 1L;
    @Builder.Default
    private Timestamp passwordUpdatedAt = new Timestamp(System.currentTimeMillis());

    @Builder.Default
    private Gender gender = Gender.U;

    private String picture;

    @Builder.Default
    private Boolean confirmed = false;
    @Builder.Default
    private Boolean active = true;
    @Builder.Default
    private Boolean locked = false;
    @Builder.Default
    private Boolean expired = false;

    @Builder.Default
    private Boolean twoFactor = false;
    @Builder.Default
    private Boolean twoFactorConfirmed = false;
    private String twoFactorSecret;


    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany
    private List<Credential> credentials = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private List<Role> roles = new ArrayList<>();
}

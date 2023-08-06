package org.elbouchouki.hectify.core.users.entitie;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long credentialId;

    private Long version;

    private String lastPassword;
    private Timestamp passwordUpdatedAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}

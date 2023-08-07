package org.elbouchouki.hectify.core.users.repository;

import org.elbouchouki.hectify.core.users.entitie.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {
//    List<Credential> findAllByUser_UserId(Long userId);
}

package org.elbouchouki.hectify.core.users.repository;

import org.elbouchouki.hectify.core.users.entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

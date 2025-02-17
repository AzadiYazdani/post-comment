package com.haraji.baseinfo.database.repository.authentication;

import com.haraji.baseinfo.database.entity.authentication.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}

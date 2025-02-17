package com.haraji.baseinfo.database.repository.authentication;

import com.haraji.baseinfo.database.entity.authentication.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findById(Long id);
}

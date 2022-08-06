package com.tecnotree.demo.database.repository;

import com.tecnotree.demo.database.entity.CommentEntity;
import com.tecnotree.demo.database.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Optional<CommentEntity> findById(Long id);

}

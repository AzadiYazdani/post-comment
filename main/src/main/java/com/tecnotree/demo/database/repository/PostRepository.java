package com.tecnotree.demo.database.repository;

import com.tecnotree.demo.database.entity.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {

    Optional<PostEntity> findById(Long id);

    List<PostEntity> findAllByTitleContains(String titleValue);
}

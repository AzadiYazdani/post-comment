package com.haraji.sale.database.repository;

import com.haraji.sale.database.entity.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {

    Optional<PostEntity> findByIdAndDeletedIsFalse(Long id);

    List<PostEntity> findAllByTitleContainsAndDeletedIsFalse(String titleValue);
}

package com.tecnotree.demo.database.repository;

import com.tecnotree.demo.database.entity.ToDoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends PagingAndSortingRepository<ToDoEntity, Long> {

    List<ToDoEntity> findAllByUserIdAndAndCompleted(Long userId, boolean completed);

}

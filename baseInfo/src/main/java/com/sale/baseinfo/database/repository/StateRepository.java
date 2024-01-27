package com.sale.baseinfo.database.repository;

import com.sale.baseinfo.database.entity.StateEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends PagingAndSortingRepository<StateEntity, Integer> {

    Optional<StateEntity> findById(int id);

    List<StateEntity> findAllByTitleContains(String titleValue);

    List<StateEntity> findAll();
}

package com.sale.baseinfo.database.repository;

import com.sale.baseinfo.database.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    Optional<CityEntity> findById(int id);

}

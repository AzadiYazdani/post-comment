package com.sale.baseinfo.service.city;


import com.sale.baseinfo.exception.CityNotFoundException;
import com.sale.baseinfo.mapper.CityMapper;
import com.sale.baseinfo.model.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CityServiceImpl implements CityService {


//    private final CityRepository cityRepository;

//    public CityServiceImpl(CityRepository cityRepository) {
//        this.cityRepository = cityRepository;
//    }
    private final CityMapper cityMapper;

    public CityServiceImpl(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

//    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
//        this.cityRepository = cityRepository;
//        this.cityMapper = cityMapper;
//    }


    @Override
    public City getById(@Min(1) int id) {
        try {
//            CityEntity cityEntity = cityRepository.findById(id)
//                    .orElseThrow(() -> new CityNotFoundException(id));
            return null;
//            return cityMapper.toModel(cityEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for CityService.getById({})", e.getMessage(), id);
            throw new CityNotFoundException(id);
        }
    }

    @Override
    public Page<City> getAllByPaging(@Valid @NotNull Pageable pageable) {
        try {
//            Page<CityEntity> cityEntityPage = cityRepository.findAll(pageable);
//
//            if (cityEntityPage.isEmpty()) {
//                throw new CityNotFoundException();
//            }
            List<City> cityList = new ArrayList<>();

//            cityEntityPage.forEach(cityEntity -> cityList.add(cityMapper.toModel(cityEntity)));
            return new PageImpl<>(cityList);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for CityService.getAllByPaging({}) ", e.getMessage(), pageable);
            throw new CityNotFoundException();
        }
    }
}

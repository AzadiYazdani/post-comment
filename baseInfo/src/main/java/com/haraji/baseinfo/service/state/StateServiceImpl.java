package com.haraji.baseinfo.service.state;


import com.haraji.baseinfo.exception.StateNotFoundException;
import com.haraji.baseinfo.mapper.CityMapper;
import com.haraji.baseinfo.mapper.StateMapper;
import com.haraji.baseinfo.model.City;
import com.haraji.baseinfo.model.State;
import com.haraji.common.exception.BadRequestException;
import com.haraji.baseinfo.database.entity.CityEntity;
import com.haraji.baseinfo.database.entity.StateEntity;
import com.haraji.baseinfo.database.repository.StateRepository;
import com.haraji.baseinfo.exception.CityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
@Validated
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final StateMapper stateMapper;
    private final CityMapper cityMapper;

    public StateServiceImpl(StateRepository stateRepository, StateMapper stateMapper, CityMapper cityMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
        this.cityMapper = cityMapper;
    }

    @Override
    public State getById(@Min(1) int id) {
        try {
            StateEntity stateEntity = stateRepository.findById(id)
                    .orElseThrow(() -> new StateNotFoundException(id));
            return stateMapper.toModel(stateEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for StateService.getById({})", e.getMessage(), id);
            throw new StateNotFoundException(id);
        }
    }

    @Override
    public List<State> getAll() {
        try {
            List<StateEntity> stateEntityList = stateRepository.findAll();
            if (stateEntityList != null && !stateEntityList.isEmpty())
                return stateMapper.toModelList(stateEntityList);
            return null;
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for StateService.getAll()", e.getMessage());
            throw new StateNotFoundException();
        }
    }

    @Override
    public Page<State> getAllByPaging(@Valid @NotNull Pageable pageable) {
        try {
            Page<StateEntity> stateEntityPage = stateRepository.findAll(pageable);

            if (stateEntityPage.isEmpty()) {
                throw new StateNotFoundException();
            }
            List<StateEntity> entities = stateEntityPage.getContent();
            List<State> stateList = stateMapper.toModelList(entities);
            return new PageImpl<>(stateList);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for StateService.getAllByPaging({}) ", e.getMessage(), pageable);
            throw new StateNotFoundException();
        }
    }

    @Override
    public List<City> getAllCitiesById(@Min(1) int stateId) {
        try {
            StateEntity stateEntity = stateRepository.findById(stateId)
                    .orElseThrow(() -> new StateNotFoundException(stateId));
            List<CityEntity> cityEntities = stateEntity.getCities();

            return cityMapper.toModelList(cityEntities);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for StateService.getAllCitiesById({})", e.getMessage(), stateId);
            throw new CityNotFoundException(stateId);
        }
    }

    @Override
    public List<State> searchTitle(@NotNull String titleValue) {
        if (StringUtils.isBlank(titleValue))
            throw new BadRequestException();

        List<StateEntity> stateEntityList = stateRepository.findAllByTitleContains(titleValue);
        return stateMapper.toModelList(stateEntityList);
    }
}

package com.haraji.baseinfo.api.location;


import com.haraji.baseinfo.api.location.state.StateResponseDto;
import com.haraji.baseinfo.model.location.City;
import com.haraji.baseinfo.model.location.State;
import com.haraji.common.dto.ResponseDto;
import com.haraji.baseinfo.api.location.city.CityResponseDto;
import com.haraji.baseinfo.mapper.location.CityMapper;
import com.haraji.baseinfo.mapper.location.StateMapper;
import com.haraji.baseinfo.service.location.state.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(value = "Location operations")
@RequestMapping("/location/states")
@Slf4j
@Validated
public class LocationController {

    private final StateService stateService;
    private final StateMapper stateMapper;
    private final CityMapper cityMapper;

    public LocationController(StateService stateService, StateMapper stateMapper, CityMapper cityMapper) {
        this.stateService = stateService;
        this.stateMapper = stateMapper;
        this.cityMapper = cityMapper;
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "یافتن همه استانها", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<StateResponseDto>> getAllStates() {
        List<State> state = stateService.getAll();
        List<StateResponseDto> lstDtoResponse = stateMapper.toDtoResponseList(state);
        log.debug("the StateDto for sending is {}", lstDtoResponse);
        return new ResponseEntity<ResponseDto<StateResponseDto>>(ResponseDto.success(lstDtoResponse), HttpStatus.OK);
    }

    @GetMapping("/paging")
    @ApiOperation(value = "دریافت همه استانها با صفحه بندی", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Page<StateResponseDto>>> getStatesWithPaging(@RequestParam @Valid @ApiParam(value = "شماره صفحه", example = "0", required = true) int pageNo, @RequestParam @Valid @ApiParam(value = "شمارگان اقلام در هر صفحه", example = "10", required = true) int pageSize, @ApiParam(value = "مرتب سازی بر اساس", example = "title") @RequestParam(required = false) String sortName, @ApiParam(value = "جهت مرتب سازی", example = "asc") @RequestParam(required = false) String asc) {
        log.debug("received page number and size for retrieving all states are {}, {}", pageNo, pageSize);
        String sortColumn = StringUtils.isNoneBlank(sortName) ? sortName : "title";
        String direction = (StringUtils.isNoneBlank(asc) && (asc.equalsIgnoreCase("DESC") || asc.equalsIgnoreCase("asc"))) ? asc : "DESC";
        Sort sort = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortColumn);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<State> statePage = stateService.getAllByPaging(pageable);
        log.debug("the statePage for sending is {}", statePage);
        return new ResponseEntity<ResponseDto<Page<StateResponseDto>>>(ResponseDto.success(statePage), HttpStatus.OK);
    }

    @GetMapping(value = "/{stateId}")
    @ApiOperation(value = "یافتن یک استان با شناسه", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ResponseDto<StateResponseDto>> getById(@PathVariable("stateId") @Valid @Min(1) @ApiParam(value = "شناسه استان مورد نظر", example = "1", required = true) int stateId) {
        log.debug("received stateId for retrieving a state is {}", stateId);
        State state = stateService.getById(stateId);
        StateResponseDto dtoResponse = stateMapper.toDtoResponse(state);
        log.debug("the StateDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<StateResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }

    @GetMapping("/{stateId}/cities")
    @ApiOperation(value = "یافتن همه شهرهای یک استان", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<CityResponseDto>> getAllCities(@PathVariable("stateId") @Valid @Min(1) @ApiParam(value = "شناسه استان مورد نظر", example = "1", required = true) int stateId) {
        log.debug("received stateId for retrieving all cities is {}", stateId);
        List<City> cities = stateService.getAllCitiesById(stateId);
        List<CityResponseDto> cityResponseDtoList = cityMapper.toDtoResponseList(cities);
        log.debug("the list of cities for sending is {}", cityResponseDtoList);
        return new ResponseEntity<ResponseDto<CityResponseDto>>(ResponseDto.success(cityResponseDtoList), HttpStatus.OK);
    }

    @GetMapping("/search")
    @ApiOperation(value = "یافتن همه استان هایی که بخشی از یک واژه را دارند", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<StateResponseDto>>> search(@RequestParam("name") @Valid @NotNull @ApiParam(value = "بخشی از نام استان مورد نظر", example = "آذر", required = true) String value) {
        log.debug("received value for searching name is {}", value);
        List<State> stateList = stateService.searchTitle(value);
        List<StateResponseDto> stateResponseDtoList = stateMapper.toDtoResponseList(stateList);
        log.debug("the list of state for sending is {}", stateResponseDtoList);
        return new ResponseEntity<ResponseDto<List<StateResponseDto>>>(ResponseDto.success(stateResponseDtoList), HttpStatus.OK);
    }
}

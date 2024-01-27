package com.sale.business.api;

import com.haraji.sale.dto.ResponseDto;
import com.sale.business.mapper.SaleMapper;
import com.sale.business.model.SaleView;
import com.sale.business.service.SaleViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/sale")
@Slf4j
@Validated
public class SaleViewController {

    private final SaleViewService saleViewService;
    private final SaleMapper saleMapper;

    public SaleViewController(SaleViewService saleViewService, SaleMapper saleMapper) {
        this.saleViewService = saleViewService;
        this.saleMapper = saleMapper;
    }

    @GetMapping("/all")
    @ApiOperation(value = "یافتن همه حراجی ها بر اساس شناسه شهر و شناسه نوع کسب و کار", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<SaleResponseDto>>> getAllSales(
            @RequestParam("city") @Valid @NotNull @ApiParam(value = "شناسه شهر مورد نظر", example = "2", required = true) String cityId
            , @RequestParam("businessType") @Valid @NotNull @ApiParam(value = "شناسه کسب و کار مورد نظر", example = "2", required = true) String businessTypeId
    ) {
        log.debug("received value for getAllSales is city {} and businessType {}", cityId, businessTypeId);
        List<SaleView> stateList = saleViewService.getAllSalesByCityAndBusinessType(Integer.parseInt(cityId), Integer.parseInt(businessTypeId));
        List<SaleResponseDto> saleResponseDtoList = saleMapper.toDtoResponseList(stateList);
        log.debug("the list of state for sending is {}", saleResponseDtoList);
        return new ResponseEntity<ResponseDto<List<SaleResponseDto>>>(ResponseDto.success(saleResponseDtoList), HttpStatus.OK);
    }

    @GetMapping(value = "/{saleId}")
    @ApiOperation(value = "یافتن یک حراجی با شناسه", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ResponseDto<SaleResponseDto>> getById(@PathVariable("saleId") @Valid @Min(1) @ApiParam(value = "شناسه حراج مورد نظر", example = "1", required = true) int saleId) {
        log.debug("received stateId for retrieving a state is {}", saleId);
        SaleView sale = saleViewService.getById(saleId);
        SaleResponseDto dtoResponse = saleMapper.toDtoResponse(sale);
        log.debug("the StateDto for sending is {}", dtoResponse);
        return new ResponseEntity<ResponseDto<SaleResponseDto>>(ResponseDto.success(dtoResponse), HttpStatus.OK);
    }
}

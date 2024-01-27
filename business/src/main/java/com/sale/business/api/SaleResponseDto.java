package com.sale.business.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.joda.time.DateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class SaleResponseDto {

    @ApiModelProperty(value = "شناسه حراجی", dataType = "long", example = "1")
    private long id;

    @ApiModelProperty(value = "شناسه کسب و کار", dataType = "int", example = "1")
    private int id_business;

    @ApiModelProperty(value = "نام فارسی کسب و کار", dataType = "String", example = "\"فروشگاه لوازم خانگی خانه سبز\"")
    private String business_title_per;

    @ApiModelProperty(value = "شهر محل کسب و کار", dataType = "String", example = "\"تهران\"")
    private String business_city;

    @ApiModelProperty(value = "نشانی محل کسب و کار و حراجی", dataType = "String", example = "\"چهارراه سیروس- کوچه قنادها- پلاک 82\"")
    private String address;

    @ApiModelProperty(value = "تلفن کسب و کار", dataType = "String", example = "\"02166341231\"")
    private String business_tel;

    @ApiModelProperty(value = "نوع کسب و کار", dataType = "String", example = "\"لوازم خانگی\"")
    private String business_type;

    @ApiModelProperty(value = "نام فرد پاسخگو به تلفن کسب و کار", dataType = "String", example = "\"جوادی\"")
    private String last_name;

    @ApiModelProperty(value = "تاریخ ثبت حراجی", dataType = "String", example = "\"تهران\"")
    private DateTime create_Date;

    @ApiModelProperty(value = "تاریخ آغاز حراجی", dataType = "String", example = "\"تهران\"")
    private DateTime start_Date;

    @ApiModelProperty(value = "تاریخ پایان حراجی", dataType = "String", example = "\"تهران\"")
    private DateTime end_Date;

    @ApiModelProperty(value = "طول حراجی", dataType = "String", example = "5")
    private int duration;

    @ApiModelProperty(value = "واحد زمانی طول حراجی (روز، هفته، ماه))", dataType = "String", example = "\"هفته\"")
    private int time_unit;

    @ApiModelProperty(value = "ساعت آغاز حراجی", dataType = "integer", example = "9")
    private int working_hour_from;

    @ApiModelProperty(value = "ساعت پایان حراجی", dataType = "integer", example = "21")
    private int working_hour_to;

    @ApiModelProperty(value = "کمترین درصد حراجی", dataType = "integer", example = "15")
    private int percentage_from;

    @ApiModelProperty(value = "بیشترین درصد حراجی", dataType = "integer", example = "50")
    private int percentage_to;

    @ApiModelProperty(value = "بیشترین قیمت", dataType = "int", example = "\"200000\"")
    private int highest_price;

    @ApiModelProperty(value = "کمترین قیمت", dataType = "int", example = "\"50000\"")
    private int lowest_price;

    @ApiModelProperty(value = "توضیحات حراجی", dataType = "String", example = "\"در این حراجی امکان پرداخت قسطی هم وجود دارد\"")
    private String description;

    @ApiModelProperty(value = "حراجی فعال ا ست", dataType = "boolean", example = "\"true\"")
    private boolean active;


    @Override
    public String toString() {
        return "SaleResponseDto{" +
                "id=" + id +
                ", id_business=" + id_business +
                ", business_title_per='" + business_title_per + '\'' +
                ", business_city='" + business_city + '\'' +
                ", address='" + address + '\'' +
                ", business_tel='" + business_tel + '\'' +
                ", business_type='" + business_type + '\'' +
                ", last_name='" + last_name + '\'' +
                ", create_Date=" + create_Date +
                ", start_Date=" + start_Date +
                ", end_Date=" + end_Date +
                ", duration=" + duration +
                ", time_unit=" + time_unit +
                ", working_hour_from=" + working_hour_from +
                ", working_hour_to=" + working_hour_to +
                ", percentage_from=" + percentage_from +
                ", percentage_to=" + percentage_to +
                ", highest_price=" + highest_price +
                ", lowest_price=" + lowest_price +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}

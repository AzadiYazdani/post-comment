package com.haraji.baseinfo.database.entity.business;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "sale_db", name = "business_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class BusinessTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

}

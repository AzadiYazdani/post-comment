package com.sale.baseinfo.database.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(schema = "sale_db", name = "city")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true, builderMethodName = "newInstance")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "fk_state")
    private StateEntity state;

    private String title;
}

package com.tuanle.loyaltyprogram.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "convert")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "Sales")
    private Long sales;

    @Column(name = "Points")
    private Long points;


}

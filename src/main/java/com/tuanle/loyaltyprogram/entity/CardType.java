package com.tuanle.loyaltyprogram.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "loyalty_card_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardType implements Serializable {

    private static final long serialVersionUID = 2279603098520808944L;
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "spentthreshold")
    private Long spentThreshold;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "discountpercent")
    private Integer discountPercent;

    @Column(name = "createdon")
    private Date createdOn;

    @Column(name = "modifiedon")
    private Date modifiedOn;

}

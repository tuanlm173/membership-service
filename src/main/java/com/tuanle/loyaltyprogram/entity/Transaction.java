package com.tuanle.loyaltyprogram.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "transaction")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable{

    private static final long serialVersionUID = -1416635324682687501L;
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "pointadjust")
    private Long pointAdjust;

    @Column(name = "spentadjust")
    private Long spentAdjust;

    @Column(name = "createdon")
    private Date createdOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loyaltycardid", nullable = false)
    private LoyaltyCard loyaltyCard;
}

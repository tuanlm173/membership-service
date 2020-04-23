package com.tuanle.loyaltyprogram.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "loyalty_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCard implements Serializable {

    private static final long serialVersionUID = -916231395497820637L;
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "Code")
    private String code;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "point")
    private Long point;

    @Column(name = "totalspent")
    private Long totalSpent;

    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "enddate")
    private Date endDate;

    @Column(name = "createdon")
    private Date createdOn;

    @Column(name = "modifiedon")
    private Date modifiedOn;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loyaltycarttypeid", nullable = false)
    private CardType cardType;

}

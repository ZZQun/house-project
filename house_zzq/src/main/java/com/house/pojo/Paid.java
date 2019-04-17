package com.house.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "paid")
public class Paid {

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private Double price;

    @Column(name = "date")
    private Date date;

    @Column(name = "paydate")
    private Date payDate;

    @Column(name = "name")
    private String name;

    @Column(name = "userlist_id")
    private Integer userlist_id;

    @Column(name = "status")
    private String status;

    @Column(name = "houseid")
    private Integer houseId;


}

package com.ecoharvest.deliveryorder.domain;

import javax.persistence.*;

@Entity
@Table(name = "delivery_order")
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String orderList;

    @Column(nullable=false)
    private String userName;

    @Column(nullable=false)
    private String address;

    public DeliveryOrder() {
        super();
    }

    public DeliveryOrder(String orderList, String userName, String address) {
        super();
        this.orderList = orderList;
        this.userName = userName;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderList() {
        return orderList;
    }

    public void setOrderList(String orderList) {
        this.orderList = orderList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package com.ecoharvest.deliveryorder.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_order")
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL)
    private List<Item> items;

    @Column(name="user_name")
    private String userName;

    @Column(name="address")
    private String address;

    @Column(name="timeslot")
    private String timeslot;

    @Column(name="delivery_status")
    private String deliveryStatus;

    public DeliveryOrder() {
        super();
    }

    public DeliveryOrder(List<Item> items, String userName, String address) {
        super();
        this.items = items;
        this.userName = userName;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

}

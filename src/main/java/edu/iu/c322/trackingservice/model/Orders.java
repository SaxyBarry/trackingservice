package edu.iu.c322.trackingservice.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private int customerId;
    private double total;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shippingAddress")
    @Valid
    private Address shippingAddress;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment")
    private Payment payment;
    private String dateOrdered = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders order = (Orders) o;
        return id == order.id && customerId == order.customerId && Double.compare(order.total, total) == 0 && shippingAddress.equals(order.shippingAddress) && items.equals(order.items) && payment.equals(order.payment) && dateOrdered.equals(order.dateOrdered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, total, shippingAddress, items, payment, dateOrdered);
    }
}

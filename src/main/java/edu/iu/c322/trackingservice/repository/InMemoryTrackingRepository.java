package edu.iu.c322.trackingservice.repository;

import edu.iu.c322.trackingservice.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class InMemoryTrackingRepository {

    private Boolean testCreated = false;
    List<Orders> orders = new ArrayList<Orders>(){};

    public void createTestOrder(){
        if(!testCreated) {
            Orders order = new Orders();
            order.setId(1);
            Address a = new Address();
            a.setPostalCode(47408);
            a.setCity("Bloomington");
            a.setState("Indiana");
            order.setShippingAddress(a);
            Payment p = new Payment();
            p.setBillingAddress(a);
            p.setMethod("Debit");
            p.setNumber("1234567890");
            order.setPayment(p);
            Item i1 = new Item();
            i1.setName("rose");
            i1.setPrice(3);
            i1.setQuantity(1);
            Item i2 = new Item();
            i2.setName("sunflower");
            i2.setPrice(5);
            i2.setQuantity(3);
            order.setItems(new ArrayList<Item>(Arrays.asList(i1, i2)));
            order.setDateOrdered("3/28/2023");
            order.setCustomerId(1);
            order.setTotal(20.00);
            orders.add(order);
            testCreated = true;
        }
    }

    public Status getItemStatus(int id, int itemid) {
        Orders x = getOrderByID(id);
        if(x != null){
            List<Item> items = x.getItems();
            if(items.size() > itemid-1){
                Item newItem = items.get(itemid-1);
                Status status = new Status();
                status.setStatus(newItem.getStatus());
                status.setDate(newItem.getDateModified());
                return status;
            }else {
                throw new IllegalStateException("item id not found");
            }
        }else{
            throw new IllegalStateException("order id not found");
        }
    }
    public void update(UpdateRequest updateRequest, int id){
        Orders x = getOrderByID(id);
        if(x != null){
            List<Item> items = x.getItems();
            if(items.size() > updateRequest.getItemId()-1){
                Item newItem = items.get(updateRequest.getItemId()-1);
                newItem.setStatus(updateRequest.getStatus());
                newItem.setDateModified("today");
                items.set(updateRequest.getItemId()-1, newItem);
                x.setItems(items);
            }else {
                throw new IllegalStateException("item id not found");
            }
        }else{
            throw new IllegalStateException("order id not found");
        }
    }

    private Orders getOrderByID(int id) {
        return orders.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }


}

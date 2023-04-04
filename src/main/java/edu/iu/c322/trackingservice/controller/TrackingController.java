package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.Item;
import edu.iu.c322.trackingservice.model.Orders;
import edu.iu.c322.trackingservice.model.Status;
import edu.iu.c322.trackingservice.model.UpdateRequest;
import edu.iu.c322.trackingservice.repository.OrderRepository;
import edu.iu.c322.trackingservice.repository.TrackingRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private TrackingRepository repository;
    private OrderRepository orderRepository;
    public TrackingController(TrackingRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }
    @GetMapping("/{id}/{itemid}")
    public Status getOrdersByCustomerId(@PathVariable int id, @PathVariable int itemid){
        Orders x = getOrderByID(id);
        if(x != null){
            List<Item> items = x.getItems();
            for(Item i :items){
                if(i.getId() == itemid) {
                    Status status = new Status();
                    status.setStatus(i.getStatus());
                    status.setDate(i.getDateModified());
                    status.setOrderID(id);
                    status.setItemID(itemid);
                    repository.save(status);
                    return status;
                }
            }
            throw new IllegalStateException("item id not found");
        }else{
            throw new IllegalStateException("order id not found");
        }
    }
    private Orders getOrderByID(int id) {
        return orderRepository.findAll().stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UpdateRequest updateRequest, @PathVariable int id){
        Orders x = getOrderByID(id);
        Boolean found = false;
        if(x != null){
            Iterator<Item> iter = x.getItems().iterator();
            while(iter.hasNext()) {
                Item i = iter.next();
                if(i.getId() == updateRequest.getItemId()) {
                    i.setStatus(updateRequest.getStatus());
                    i.setDateModified("04/08/2023");
                    orderRepository.save(x);
                    found = true;
                }
            }
            if(!found) {
                throw new IllegalStateException("item id not found");
            }
        }else{
            throw new IllegalStateException("order id not found");
        }
    }

}

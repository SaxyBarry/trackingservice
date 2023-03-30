package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.Status;
import edu.iu.c322.trackingservice.model.UpdateRequest;
import edu.iu.c322.trackingservice.repository.TrackingRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private TrackingRepository repository;
    public TrackingController(TrackingRepository repository) {
        this.repository = repository;
        this.repository.createTestOrder();
    }
    @GetMapping("/{id}/{itemid}")
    public Status getOrdersByCustomerId(@PathVariable int id, @PathVariable int itemid){
        return repository.getItemStatus(id, itemid);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UpdateRequest updateRequest, @PathVariable int id){
        repository.update(updateRequest, id);
    }

}

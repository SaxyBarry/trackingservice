package edu.iu.c322.trackingservice.repository;

import edu.iu.c322.trackingservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<Status, Integer> {
}

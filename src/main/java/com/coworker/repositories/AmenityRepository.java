package com.coworker.repositories;

import com.coworker.entities.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity , Long> {
}

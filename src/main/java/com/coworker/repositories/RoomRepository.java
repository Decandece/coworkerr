package com.coworker.repositories;

import com.coworker.entities.Room;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findBySpace_Name(String space);

    List<Room> findByCapacityGreaterThanEqual(Integer capacity);

    List<Room> findDistinctByAmenities_NameIn(Collection<String> amenitiesNames);

}

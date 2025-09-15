package com.coworker.repositories;

import com.coworker.entities.Room;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // PROCURAR EVITAR LLEGAR A SQL NATIVO

    // QueryMethods
    List<Room> findBySpace_Name(String space);

    List<Room> findByCapacityGreaterThanEqual(Integer capacity);

    List<Room> findDistinctByAmenities_NameIn(Collection<String> amenitiesNames);

    // CONSULTA ORIENTADA A OBJETOS (JPQL)
    @Query("""
            SELECT r FROM Room r
            WHERE r.space.id = :spaceId
            AND NOT EXISTS(
                SELECT ri FROM ReservationItem ri
                WHERE ri.room = r
                AND ri.startAt < :end
                AND ri.endAt > :start
            )
            """)
    List<Room> findAvailableInterval(@Param("spaceId") Long spaceId, @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end);

    // CONSULTA NATIVA SQL (NATIVO)
    @Query(value = """
            SELECT r.* FROM rooms r
            JOIN room_amenities ra ON ra.room_id = r.id
            JOIN amenities a ON a.id = ra.amenity_id
            WHERE a.name IN (:amenities)
            GROUP BY r.id
            HAVING COUNT(DISTINCT a.name) = :requiredCount
            """, nativeQuery = true)
    List<Room> findRoomsWithAllAmenities(@Param("amenities") Collection<String> amenities,
            @Param("requiredCount") Long requiredCount);

    @EntityGraph(attributePaths = { "amenities", "space" })
    List<Room> findAll();

}

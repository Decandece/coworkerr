package com.coworker.repositories;

import com.coworker.entities.ReservationItem;
import com.coworker.view.ReservationItemView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {

    List<ReservationItem> findAllByRoom_Id(Long roomId); // Solo me traigo los ID de las rooms

    @Query("""
                SELECT CASE WHEN COUNT(ri) > 0 THEN TRUE ELSE FALSE END
                FROM ReservationItem ri
                WHERE ri.room.id = :roomId
                AND ri.startAt < :end
                AND ri.endAt > :start
            """)
    boolean existsOverLap(@Param("roomId") Long roomId,
            @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end);

    @Query("""
                SELECT new com.coworker.view.ReservationItemView(
                ri.reservation.id, ri.room.id, ri.startAt, ri.endAt
                )
                FROM ReservationItem ri
                WHERE ri.reservation.id = :reservationId
                ORDER BY ri.startAt
            """)

    List<ReservationItemView> findViewByReservation(@Param("reservationId") Long reservationId);



}

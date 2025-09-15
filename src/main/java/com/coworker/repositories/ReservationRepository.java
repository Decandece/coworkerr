package com.coworker.repositories;

import com.coworker.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Buscar reservas de un miembro especifico
    @Query("""
                select r from Reservation r
                WHERE r.member.name = :memberName
            """)
    List<Reservation> findByReservationByMemberName(@Param("memberName") String memberName);

    // Buscar reservas en una fecha especifica
    @Query("""
                SELECT r FROM Reservation r
                WHERE DATE(r.createdAt) = DATE(:date)
            """)
    List<Reservation> findByReservationByDate(@Param("date") OffsetDateTime date);

    // Contar las reservas que tenga un miembro

    @Query("""

                SELECT COUNT (r) FROM Reservation r
                WHERE r.member.name = :memberName
            """)
    Long countReservationsByMemberName(@Param("memberName") String memberName);
}

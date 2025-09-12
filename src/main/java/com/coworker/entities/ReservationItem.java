package com.coworker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "reservation_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private OffsetDateTime startAt;

    @Column(nullable = false)
    private OffsetDateTime endAt;
}

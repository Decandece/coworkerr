package com.coworker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "reservation")
    @Builder.Default
    private List<ReservationItem> items = new ArrayList<>();

    public void addItem(ReservationItem item) {
        items.add(item);
        item.setReservation(this);
    }
}

package com.coworker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer capacity;

    @Column(name = "hourly_price")
    private Double hourlyPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    @ManyToMany
    @JoinTable(name = "room_amenities", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    @Builder.Default
    private Set<Amenity> amenities = new HashSet<>();

    public void addAmenity(Amenity amenity) {
        amenities.add(amenity);
        amenity.getRooms().add(this);
    }
}

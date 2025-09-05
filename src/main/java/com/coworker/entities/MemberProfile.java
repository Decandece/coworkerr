package com.coworker.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder

public class MemberProfile {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String phone;
    private String company;


}

package com.jemersoft.pokechallenge.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "abilities")
@Data
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ability_id")
    private Integer ailityId;

    @Column (name = "ability_name")
    private String abilityName;
}

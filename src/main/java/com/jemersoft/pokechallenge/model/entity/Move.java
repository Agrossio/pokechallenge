package com.jemersoft.pokechallenge.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "moves")
@Data
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "move_id")
    private Integer moveId;

    @Column (name = "move_name")
    String moveName;
}

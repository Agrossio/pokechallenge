package com.jemersoft.pokechallenge.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Entity
@Table(name = "pokemons")
@Data
public class Pokemon {

    @Id
    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToMany
    private List<Type> types;
    @Column(name = "weight", precision = 2)
    private double weight;
    @ManyToMany
    private List<Ability> abilities;
    @Column(name = "description")
    private String description;
    @ManyToMany
    private List<Move> moves;
}

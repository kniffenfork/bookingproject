package com.petprojects.bookingproject.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    private String description;

    public Room() {

    }

    public Room(Long id, int price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }
}

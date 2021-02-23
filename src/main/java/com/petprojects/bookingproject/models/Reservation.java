package com.petprojects.bookingproject.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="check_in")
    private Date checkIN;

    @Column(name="check_out")
    private Date checkOUT;

    @Column(name="roomID")
    private Long roomID;

    public Reservation(){
    }

    public Reservation(Long id, Date checkIN, Date checkOUT, Long roomID) {
        this.id = id;
        this.checkIN = checkIN;
        this.checkOUT = checkOUT;
        this.roomID = roomID;
    }
}

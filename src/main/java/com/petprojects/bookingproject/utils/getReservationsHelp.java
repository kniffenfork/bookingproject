package com.petprojects.bookingproject.utils;

import lombok.Data;

import java.util.Date;

@Data
public class getReservationsHelp {
    private Long id;
    private Date checkIN;
    private Date checkOUT;

    public getReservationsHelp(Long id, Date checkIN, Date checkOUT) {
        this.id = id;
        this.checkIN = checkIN;
        this.checkOUT = checkOUT;
    }
}

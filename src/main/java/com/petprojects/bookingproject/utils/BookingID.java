package com.petprojects.bookingproject.utils;

import lombok.Data;

@Data
public class BookingID {
    private Long booking_id;

    public BookingID(Long booking_id) {
        this.booking_id = booking_id;
    }
}

package com.petprojects.bookingproject.utils;

import lombok.Data;

@Data
public class RoomID {
    private Long roomID;

    public RoomID(Long roomID) {
        this.roomID = roomID;
    }
}

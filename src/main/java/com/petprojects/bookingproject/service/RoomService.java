package com.petprojects.bookingproject.service;

import com.petprojects.bookingproject.models.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface RoomService {
    ResponseEntity<?> create(@RequestBody Room room);
    ResponseEntity<?> findAllRoomsInc();
    ResponseEntity<?> findAllRoomsDecr();
    ResponseEntity<?> findAllPriceIncrease();
    ResponseEntity<?> findAllPriceDecrease();
    ResponseEntity<?> updateRoom(@PathVariable("id") Long room_id, @RequestBody Room room);
    ResponseEntity<?> getReservations(@PathVariable("id") Long id);
    ResponseEntity<?> findRoomById(@PathVariable("id") Long room_id);
    ResponseEntity<?> deleteRoom(@PathVariable("id") Long id);
    Room findById(Long id);
    List<Room> findAll();
    void saveRoom(Room room);
    void deleteRoomById(Long id);
}

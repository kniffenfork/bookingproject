package com.petprojects.bookingproject.controller;

import com.petprojects.bookingproject.models.Room;
import com.petprojects.bookingproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value="/rooms/create")
    public ResponseEntity<?> createRoom(@RequestBody Room room){
        return roomService.create(room);
    }

    @GetMapping(value="/rooms/find")
    public ResponseEntity<?> findAllRoomsInc(){
        return roomService.findAllRoomsInc();
    }

    @GetMapping(value="/rooms/find/date_decrease")
    public ResponseEntity<?> findAllRoomsDecr(){
        return roomService.findAllRoomsDecr();
    }

    @GetMapping(value="/rooms/find/price_increase")
    public ResponseEntity<?> findAllPriceIncrease() {
        return roomService.findAllPriceIncrease();
    }

    @GetMapping(value="/rooms/find/price_decrease")
    public ResponseEntity<?> findAllPriceDecrease(){
        return roomService.findAllPriceDecrease();
    }

    @PutMapping(value="/rooms/update/id={id}")
    public ResponseEntity<?> updateRoom(@PathVariable("id") Long id, @RequestBody Room room){
        return roomService.updateRoom(id, room);
    }

    @GetMapping(value="/rooms/get_reservations/id={id}")
    public ResponseEntity<?> getReservations(@PathVariable("id") Long id){
        return roomService.getReservations(id);
    }

    @GetMapping(value="/rooms/find/id={id}")
    public ResponseEntity<?> findRoomById(@PathVariable("id") Long id){
        return roomService.findRoomById(id);
    }


    @DeleteMapping(value="/rooms/delete/id={id}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id){
        return roomService.deleteRoom(id);
    }
}

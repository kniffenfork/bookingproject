package com.petprojects.bookingproject.service.implementations;

import com.petprojects.bookingproject.exceptions.ExceptionHandler;
import com.petprojects.bookingproject.models.Reservation;
import com.petprojects.bookingproject.models.Room;
import com.petprojects.bookingproject.repository.RoomRepository;
import com.petprojects.bookingproject.service.ResService;
import com.petprojects.bookingproject.service.RoomService;
import com.petprojects.bookingproject.utils.RoomID;
import com.petprojects.bookingproject.utils.getReservationsHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger logger = LoggerFactory.getLogger(ResServiceImpl.class);

    private final ResService resService;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(ResService resService, RoomRepository roomRepository) {
        this.resService = resService;
        this.roomRepository = roomRepository;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody Room room){
        saveRoom(room);
        RoomID roomID = new RoomID(room.getId());
        logger.info("Successfully created the room. Details:" + room);
        return new ResponseEntity<>(roomID, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAllRoomsInc(){
        List<Room> roomList = findAll();
        if (roomList.isEmpty()){
            logger.info("Error in finding all rooms.");
            return new ResponseEntity<>(new ExceptionHandler("Something went wrong"), HttpStatus.NOT_FOUND);
        }
        logger.info("Successfully found all rooms.");
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAllRoomsDecr(){
        List<Room> roomList = findAll();
        if (roomList.isEmpty()){
            logger.info("Error in finding all rooms.");
            return new ResponseEntity<>(new ExceptionHandler("Something went wrong"), HttpStatus.NOT_FOUND);
        }
        Collections.reverse(roomList);
        logger.info("Successfully found all rooms.");
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAllPriceIncrease(){
        List<Room> roomList = findAll();
        roomList.sort(Comparator.comparing(Room::getPrice));
        if (roomList.isEmpty()){
            logger.info("Error in finding all rooms. Price INCREASE.");
            return new ResponseEntity<>(new ExceptionHandler("Something went wrong."), HttpStatus.NOT_FOUND);
        }
        logger.info("Successfully found all rooms. Price INCREASE");
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findAllPriceDecrease(){
        List<Room> roomList = findAll();
        roomList.sort(Comparator.comparing(Room::getPrice));
        Collections.reverse(roomList);
        if (roomList.isEmpty()){
            logger.info("Error in finding all rooms. Price DECREASE.");
            return new ResponseEntity<>(new ExceptionHandler("Something went wrong."), HttpStatus.NOT_FOUND);
        }
        logger.info("Successfully found all rooms. Price DECREASE");
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateRoom(@PathVariable("id") Long room_id, @RequestBody Room room){
        Room source_room = findById(room_id);

        if (source_room == null){
            logger.info("Can't find that room. ID:" + room_id);
            return new ResponseEntity<>(new ExceptionHandler("Can't find that room."), HttpStatus.OK);
        }

        source_room.setDescription(room.getDescription());
        source_room.setPrice(room.getPrice());
        saveRoom(source_room);
        logger.info("Successfully updated room. Details:" + source_room);
        return new ResponseEntity<>(new ExceptionHandler("Room successfully updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getReservations(@PathVariable("id") Long id){
        List<Reservation> allReservations = resService.findAll();

        if (allReservations.isEmpty()){
            logger.info("Can't find all reservations");
            return new ResponseEntity<>(new ExceptionHandler("Can't find all reservations."), HttpStatus.NOT_FOUND);
        }

        List<getReservationsHelp> result = new ArrayList<>();
        for (Reservation res: allReservations){
            if (res.getRoomID().equals(id)){
                getReservationsHelp helper = new getReservationsHelp(res.getId(), res.getCheckIN(), res.getCheckOUT());
                result.add(helper);
            }
        }
        result.sort(Comparator.comparing(getReservationsHelp::getCheckIN));
        logger.info("Successfully found all reservations for room. Details: " + result);
        return result.isEmpty()
                ? new ResponseEntity<>(new ExceptionHandler("There is no reservations for this room."), HttpStatus.OK)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findRoomById(@PathVariable("id") Long room_id){
        Room room = findById(room_id);

        if (room == null){
            logger.info("Can't find the room. ID:" + room_id);
            return new ResponseEntity<>(new ExceptionHandler("Can't find the room."), HttpStatus.BAD_REQUEST);
        }

        logger.info("Successfully found the room. Details: " + room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id){
        deleteRoomById(id);
        List<Reservation> allReservations = resService.findAll();
        for (Reservation res: allReservations){
            if (res.getRoomID().equals(id)){
                logger.info("Reservation deleted. ID:" + res.getId());
                resService.deleteById(res.getId());
            }
        }
        logger.info("Successfully deleted the room. roomID:" + id);
        return new ResponseEntity<>(new ExceptionHandler("Successfully deleted."), HttpStatus.OK);
    }

    @Override
    public Room findById(Long id){
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    @Override
    public void saveRoom(Room room){
        roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(Long id){
        roomRepository.deleteById(id);
    }
}

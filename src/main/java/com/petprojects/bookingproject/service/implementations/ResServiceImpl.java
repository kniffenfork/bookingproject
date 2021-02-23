package com.petprojects.bookingproject.service.implementations;
import com.petprojects.bookingproject.exceptions.ExceptionHandler;
import com.petprojects.bookingproject.models.Reservation;
import com.petprojects.bookingproject.repository.ResRepository;
import com.petprojects.bookingproject.repository.RoomRepository;
import com.petprojects.bookingproject.service.ResService;
import com.petprojects.bookingproject.service.RoomService;
import com.petprojects.bookingproject.utils.BookingID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ResServiceImpl implements ResService {
    private final ResRepository resRepository;
    private final RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(ResServiceImpl.class);

    public static final int CANT_FIND_RESERVATION = 800;
    public static final int CANT_FIND_ROOM = 801;
    public static final int EMPTY = 802;
    public static final int SUCCESS = 200;

    @Autowired
    public ResServiceImpl(ResRepository resRepository, RoomRepository roomRepository) {
        this.resRepository = resRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ResponseEntity<?> findAll_ResponseRequest(){
        final List<Reservation> reservations = findAll();
        if (reservations.isEmpty()){
            logger.info("Can't load all reservations.");
            return new ResponseEntity<>(new ExceptionHandler("Something is wrong in your request."), HttpStatus.NOT_FOUND);
        }
        logger.info("Successfully found all reservations.");
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById_ResponseRequest(@PathVariable("id") Long res_id){
        Reservation reservation = findById(res_id);
        if (reservation == null){
            logger.info("Can't find the reservation with ID:" + res_id);
            return new ResponseEntity<>(new ExceptionHandler("Can't find the reservation."), HttpStatus.NOT_FOUND);
        }
        logger.info("Successfully found the reservation. Details: " + reservation);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @Override
    public int checkResForUpdate(Long res_id, Reservation reservation){
        if (findById(res_id) == null){
            logger.info("Can't update the reservation because can't find it in system. ID:" + res_id);
            return CANT_FIND_RESERVATION;
        }
        else if (roomRepository.findById(reservation.getRoomID()).orElse(null) == null){
            logger.info("Can't update the reservation. There isn't room with such ID in system.");
            return CANT_FIND_ROOM;
        }
        else if (reservation.getCheckIN() == null || reservation.getCheckOUT() == null){
            logger.info("ERROR. checkIN or checkOUT are empty.");
            return EMPTY;
        }
        return SUCCESS;
    }

    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long res_id, @RequestBody Reservation reservation){

        switch(checkResForUpdate(res_id, reservation)){
            case CANT_FIND_ROOM: {return new ResponseEntity<>(new ExceptionHandler("Can't find the room."), HttpStatus.NOT_FOUND);}
            case CANT_FIND_RESERVATION: {return new ResponseEntity<>(new ExceptionHandler("Can't find the reservation."), HttpStatus.NOT_FOUND);}
            case EMPTY: {return new ResponseEntity<>(new ExceptionHandler("checkIN or checkOUT are empty."), HttpStatus.BAD_REQUEST);}
        }

        try {
            Reservation source_res = findById(res_id);
            source_res.setCheckIN(reservation.getCheckIN());
            source_res.setCheckOUT(reservation.getCheckOUT());
            source_res.setRoomID(reservation.getRoomID());
            saveReservation(source_res);
        } catch (Exception e){
            logger.info("Something went wrong when updated reservation with id =" + res_id + ". Details:" + reservation);
            return new ResponseEntity<>(new ExceptionHandler("Something went wrong."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ExceptionHandler("Successfully changed data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long res_id){
        try {
            deleteById(res_id);
        } catch (Exception e){
            logger.info("Can't delete the reservation. ID:" + res_id);
            return new ResponseEntity<>(new ExceptionHandler("Can't delete the reservation."), HttpStatus.BAD_REQUEST);
        }
        logger.info("Successfully deleted the reservation. ID:" + res_id);
        return new ResponseEntity<>(new ExceptionHandler("Reservation successfully deleted."), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(@RequestBody Reservation reservation){
        if (roomRepository.findById(reservation.getRoomID()).orElse(null) == null){
            logger.info("Can't create reservation. Room doesn't exists. Details: " + reservation);
            return new ResponseEntity<>(new ExceptionHandler("There is no such room."), HttpStatus.NOT_FOUND);
        }
        saveReservation(reservation);
        BookingID bookingID = new BookingID(reservation.getId());
        logger.info("Successfully created reservation. Details: " + reservation);
        return new ResponseEntity<>(bookingID, HttpStatus.CREATED);
    }

    @Override
    public Reservation findById(Long id){
        return resRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> findAll(){
        return resRepository.findAll();
    }

    @Override
    public void saveReservation(Reservation reservation){
        resRepository.save(reservation);
    }

    @Override
    public void deleteById(Long id){
        resRepository.deleteById(id);
    }
}

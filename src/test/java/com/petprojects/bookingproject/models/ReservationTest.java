package com.petprojects.bookingproject.models;

import com.petprojects.bookingproject.BookingprojectApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ReservationTest {

    public static final Long TEST_RES_ID = 1L;
    public static final Long TEST_ROOM_ID = 2L;

    public static final Long TEST_SET_RES_ID = 10L;
    public static final Long TEST_SET_ROOM_ID = 20L;

    private Reservation reservation;


    static Date createDate(String Date) throws ParseException {
        return new SimpleDateFormat( "dd.MM.yyyy").parse(Date);
    }


    @BeforeEach
    void setUp() throws ParseException {
        reservation = new Reservation(TEST_RES_ID, createDate("20.12.2020"), createDate("25.12.2020"), TEST_ROOM_ID);
    }

    @Test
    void getId() {
        Long id = reservation.getId();
        assert (id.equals(TEST_RES_ID));
    }

    @Test
    void getCheckIN() throws ParseException {
        Date checkIN = reservation.getCheckIN();
        assert (checkIN.equals(createDate("20.12.2020")));
    }

    @Test
    void getCheckOUT() throws ParseException {
        Date checkOUT = reservation.getCheckOUT();
        assert (checkOUT.equals(createDate("25.12.2020")));
    }

    @Test
    void getRoomID() {
        Long roomID = reservation.getRoomID();
        assert (roomID.equals(TEST_ROOM_ID));
    }

    @Test
    void setId() {
        reservation.setId(TEST_SET_RES_ID);
        assert (reservation.getId().equals(TEST_SET_RES_ID));
    }

    @Test
    void setCheckIN() throws ParseException {
        Date set_checkIN = createDate("30.12.2020");
        reservation.setCheckIN(set_checkIN);
        assert (reservation.getCheckIN().equals(set_checkIN));
    }

    @Test
    void setCheckOUT() throws ParseException {
        Date set_checkOUT = createDate("30.12.2020");
        reservation.setCheckIN(set_checkOUT);
        assert (reservation.getCheckIN().equals(set_checkOUT));
    }

    @Test
    void setRoomID() {
        reservation.setRoomID(TEST_SET_ROOM_ID);
        assert (reservation.getRoomID().equals(TEST_SET_ROOM_ID));
    }
}
package com.petprojects.bookingproject.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoomTest {

    public static final Long TEST_ID = 1L;
    public static final int TEST_PRICE = 100;
    public static final String TEST_DESCRIPTION = "TEST";

    public static final Long TEST_SET_ID = 2L;
    public static final int TEST_SET_PRICE = 200;
    public static final String TEST_SET_DESCRIPTION = "SET TEST";

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(TEST_ID, TEST_PRICE, TEST_DESCRIPTION);
    }

    @Test
    void getId() {
        Long id = room.getId();
        assert (id.equals(TEST_ID));
    }

    @Test
    void getPrice() {
        int price = room.getPrice();
        assert (price == TEST_PRICE);
    }

    @Test
    void getDescription() {
        String description_actual = room.getDescription();
        assert (TEST_DESCRIPTION.equals(description_actual));
    }

    @Test
    void setId() {
        room.setId(TEST_SET_ID);
        assert (TEST_SET_ID.equals(room.getId()));
    }

    @Test
    void setPrice() {
        room.setPrice(TEST_SET_PRICE);
        assert (TEST_SET_PRICE == room.getPrice());
    }

    @Test
    void setDescription() {
        room.setDescription(TEST_SET_DESCRIPTION);
        assert (TEST_SET_DESCRIPTION.equals(room.getDescription()));
    }
}
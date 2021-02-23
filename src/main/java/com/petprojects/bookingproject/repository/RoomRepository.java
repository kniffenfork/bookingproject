package com.petprojects.bookingproject.repository;

import com.petprojects.bookingproject.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}

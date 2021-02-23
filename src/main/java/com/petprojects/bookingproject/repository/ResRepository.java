package com.petprojects.bookingproject.repository;

import com.petprojects.bookingproject.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResRepository extends JpaRepository<Reservation, Long> {
}

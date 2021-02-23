package com.petprojects.bookingproject.service;

import com.petprojects.bookingproject.models.Reservation;
import com.petprojects.bookingproject.repository.ResRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ResService {
    ResponseEntity<?> findAll_ResponseRequest();
    ResponseEntity<?> findById_ResponseRequest(@PathVariable("id") Long id);
    int checkResForUpdate(Long id, Reservation reservation);
    ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Reservation reservation);
    ResponseEntity<?> delete(@PathVariable("id") Long id);
    ResponseEntity<?> create(@RequestBody Reservation reservation);
    Reservation findById(Long id);
    List<Reservation> findAll();
    void saveReservation(Reservation reservation);
    void deleteById(Long id);
}

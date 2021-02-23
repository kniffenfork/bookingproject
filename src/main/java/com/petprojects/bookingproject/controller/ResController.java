package com.petprojects.bookingproject.controller;

import com.petprojects.bookingproject.models.Reservation;
import com.petprojects.bookingproject.service.ResService;
import com.petprojects.bookingproject.service.implementations.ResServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ResController {
    private final ResService resService;

    @Autowired
    public ResController(ResService resService) {
        this.resService = resService;
    }

    @PostMapping(value="/reservations/create")
    public ResponseEntity<?> createRes(@RequestBody Reservation reservation){
        return resService.create(reservation);
    }

    @GetMapping(value="/reservations/find")
    public ResponseEntity<?> findAllRes(){
        return resService.findAll_ResponseRequest();
    }

    @GetMapping(value="/reservations/find/id={id}")
    public ResponseEntity<?> findResById(@PathVariable("id") Long id){
        return resService.findById_ResponseRequest(id);
    }

    @PutMapping(value="/reservations/update/id={id}")
    public ResponseEntity<?> updateRes(@PathVariable("id") Long id, @RequestBody Reservation reservation){
        return resService.update(id, reservation);
    }

    @DeleteMapping(value="/reservations/delete/id={id}")
    public ResponseEntity<?> deleteRes(@PathVariable("id") Long id) {
        return resService.delete(id);
    }
}

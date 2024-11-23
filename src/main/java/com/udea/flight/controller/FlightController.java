package com.udea.flight.controller;

import com.udea.flight.exception.InvalidRating;
import com.udea.flight.exception.ModelNotFoundException;
import com.udea.flight.model.Flight;
import com.udea.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
@CrossOrigin("*")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/save")
    public long save(@RequestBody Flight flight) throws InvalidRating {
        if (flight.getRating() > 5) {
            throw new InvalidRating("Rating must be less than 5");
        }
        flightService.save(flight);
        return flight.getIdFlight();
    }

    @GetMapping("/listAll")
    public Iterable<Flight> listAllFlights() {
        return flightService.list();
    }

    @GetMapping("/list/{id}")
    public Flight listFlightById(@PathVariable("id") int id) {
        Optional<Flight> flight = flightService.listId(id);
        if (flight.isPresent()) {
            return flight.get();
        }
        throw new ModelNotFoundException("Id de flight inválido");
    }

    @GetMapping("/topFlights")
    public ResponseEntity<List<Flight>> viewBestFlight() {
        List<Flight> list = flightService.viewBestFlight();
        return new ResponseEntity<List<Flight>>(list, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public Flight updateFlight(@RequestBody Flight flight) {
        return flightService.update(flight);
    }

    @DeleteMapping("/{id}")
    public String deleteFlight(@PathVariable("id") long id) {
        return flightService.delete(id);
    }

}

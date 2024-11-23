package com.udea.flight.service;

import com.udea.flight.dao.IFlightDAO;
import com.udea.flight.exception.FlightNotFoundException;
import com.udea.flight.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private IFlightDAO dao;

    public Flight save(Flight t) {
        return dao.save(t);
    }

    public String delete(long id) {
        dao.deleteById(id);
        return "Flight removed";
    }

    public Iterable<Flight> list() {
        return dao.findAll();
    }

    public Optional<Flight> listId(long id) {
        return dao.findById(id);
    }

    public Flight update(Flight t) {
        Flight existingFlight = dao.findById(t.getIdFlight()).orElse(null);
        existingFlight.setNombreAvion(t.getNombreAvion());
        existingFlight.setNumeroVuelo(t.getNumeroVuelo());
        existingFlight.setDestino(t.getDestino());
        existingFlight.setOrigen(t.getOrigen());
        existingFlight.setCapacidad(t.getCapacidad());
        existingFlight.setRating(t.getRating());
        existingFlight.setPlanVuelo(t.getPlanVuelo());
        existingFlight.setCumplido(t.getCumplido());
        return dao.save(existingFlight);
    }

    public List<Flight> viewBestFlight() throws FlightNotFoundException {
        List<Flight> flights = dao.viewBestFlight();
        if (flights.size()>0) {
            return flights;
        } else {
            throw new FlightNotFoundException("No flight found with ration >= 4");
        }
    }

}

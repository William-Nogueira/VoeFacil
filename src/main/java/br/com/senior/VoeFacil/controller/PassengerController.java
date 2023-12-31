package br.com.senior.VoeFacil.controller;

import br.com.senior.VoeFacil.domain.flightticket.FlightTicketService;
import br.com.senior.VoeFacil.domain.passenger.DTO.GetPassengerDTO;
import br.com.senior.VoeFacil.domain.passenger.DTO.PostPassengerDTO;
import br.com.senior.VoeFacil.domain.passenger.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping
    public ResponseEntity<GetPassengerDTO> createPassenger(@RequestBody @Valid PostPassengerDTO dto, UriComponentsBuilder uriBuilder) {
        var passenger = passengerService.createPassenger(dto);
        var uri = uriBuilder.path("/passenger/{id}").buildAndExpand(passenger.id()).toUri();
        return ResponseEntity.created(uri).body(passenger);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPassengerDTO> getPassengerById(@PathVariable UUID id) {
        var passenger = passengerService.findPassengerById(id);
        return ResponseEntity.ok(passenger);
    }

}

package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.ClientDto;
import io.github.rvdxk.carrentalspringproject.entity.Client;
import io.github.rvdxk.carrentalspringproject.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class ClientController {

    public ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clientDtoList = clientService.getAllClients();
        return new ResponseEntity<>(clientDtoList, HttpStatus.OK);
    }

    @PostMapping("/clients/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createClient(@RequestBody @Valid ClientDto clientDto){
        clientService.createClient(clientDto);
        return "Client successfully created!";
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("clientId") Long clientId){
        ClientDto clientDto = clientService.getClientById(clientId);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PutMapping("clients/{clientId}")
    public String updateClient(@PathVariable("clientId") Long clientId,
                               @RequestBody @Valid ClientDto clientDto){
        clientDto.setId(clientId);
        clientService.updateClient(clientDto, clientId);
        return "Client successfully updated!";
    }

    @DeleteMapping("clients/{clientId}")
    public void deleteClient(@PathVariable("clientId")Long clientId){
        clientService.deleteClient(clientId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;

    }
}

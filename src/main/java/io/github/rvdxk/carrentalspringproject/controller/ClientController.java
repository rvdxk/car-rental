package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.ClientDto;
import io.github.rvdxk.carrentalspringproject.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clientDtoList = clientService.getAllClients();
        return new ResponseEntity<>(clientDtoList, HttpStatus.OK);
    }

    @PostMapping("/clients/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody ClientDto clientDto){
        clientService.createClient(clientDto);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("clientId") Long clientId){
        ClientDto clientDto = clientService.getClientById(clientId);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PutMapping("clients/{clientId}")
    public ResponseEntity<HttpStatus> updateClient(@PathVariable("clientId") Long clientId,
                               @RequestBody ClientDto clientDto){
        clientDto.setId(clientId);
        clientService.updateClient(clientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("clients/{clientId}")
    public void deleteClient(@PathVariable("clientId")Long clientId){
        clientService.deleteClient(clientId);
    }



}

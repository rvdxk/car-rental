package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();
    void createClient(ClientDto clientDto);
    ClientDto getClientById(Long clientId);
    void updateClient(ClientDto clientDto, Long clientId);
    void deleteClient(Long clientId);
}

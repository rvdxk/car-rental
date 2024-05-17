package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.ClientDto;
import io.github.rvdxk.carrentalspringproject.entity.Client;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.ClientMapper;
import io.github.rvdxk.carrentalspringproject.repository.ClientRepository;
import io.github.rvdxk.carrentalspringproject.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }



    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clientsList = clientRepository.findAll();
        List<ClientDto> clientsDtoList = clientsList.stream()
                .map((client) -> ClientMapper.mapToClientDto(client))
                .collect(Collectors.toUnmodifiableList());
        return clientsDtoList;
    }

    @Override
    public void createClient(ClientDto clientDto) {
        Client client = ClientMapper.mapToClient(clientDto);
        clientRepository.save(client);
    }

    @Override
    public ClientDto getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new ResourceNotFoundException("Client with id " + clientId + " not found"));
        ClientDto clientDto = ClientMapper.mapToClientDto(client);
        return clientDto;
    }

    @Override
    public void updateClient(ClientDto clientDto, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new ResourceNotFoundException("Client with id " + clientId + " not found"));

        clientRepository.save(ClientMapper.mapToClient(clientDto));

    }

    @Override
    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new ResourceNotFoundException("Client with id " + clientId + " not found"));

        clientRepository.deleteById(clientId);
    }
}

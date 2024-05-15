package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.ClientDto;
import io.github.rvdxk.carrentalspringproject.entity.Client;
import io.github.rvdxk.carrentalspringproject.mapper.ClientMapper;
import io.github.rvdxk.carrentalspringproject.repository.ClientRepository;
import io.github.rvdxk.carrentalspringproject.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clientsList = clientRepository.findAll();
        List<ClientDto> clientsDtoList = clientsList.stream()
                .map((client) -> ClientMapper.mapToStudentDto(client))
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
        Client client = clientRepository.findById(clientId).get();
        ClientDto clientDto = ClientMapper.mapToStudentDto(client);
        return clientDto;
    }

    @Override
    public void updateClient(ClientDto clientDto) {
        Client client = clientRepository.save(ClientMapper.mapToClient(clientDto));

    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}

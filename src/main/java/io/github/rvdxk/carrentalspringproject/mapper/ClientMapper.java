package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.ClientDto;
import io.github.rvdxk.carrentalspringproject.entity.Client;

public class ClientMapper {

    public static ClientDto mapToClientDto(Client client){
        ClientDto clientDto = new ClientDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getDateOfBirth(),
                client.getAddress()
        );
        return clientDto;
    }

    public static Client mapToClient(ClientDto clientDto){
        Client client = new Client(
                clientDto.getId(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail(),
                clientDto.getDateOfBirth(),
                clientDto.getAddress()
        );
        return client;
    }

}

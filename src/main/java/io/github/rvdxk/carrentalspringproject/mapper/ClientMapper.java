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
                client.getPhoneNumber(),
                client.getDateOfBirth(),
                client.getStreet(),
                client.getHouseNumber(),
                client.getApartmentNumber(),
                client.getCity(),
                client.getPostalCode(),
                client.getCountry()
        );
        return clientDto;
    }

    public static Client mapToClient(ClientDto clientDto){
        Client client = new Client(
                clientDto.getId(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail(),
                clientDto.getPhoneNumber(),
                clientDto.getDateOfBirth(),
                clientDto.getStreet(),
                clientDto.getHouseNumber(),
                clientDto.getApartmentNumber(),
                clientDto.getCity(),
                clientDto.getPostalCode(),
                clientDto.getCountry()
        );
        return client;
    }

}

package by.pvt.newmultiproject.core.service;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.ClientResponse;
import by.pvt.newmultiproject.core.domain.Client;
import by.pvt.newmultiproject.core.repository.ClientRepository;
import by.pvt.newmultiproject.core.mapper.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ClientService {
    private final ClientRepository clientRepository;
    private final MappingUtils mappingUtils;

    public ClientService(ClientRepository clientRepository, MappingUtils mappingUtils) {
        this.clientRepository = clientRepository;
        this.mappingUtils = mappingUtils;
    }

    public ClientResponse add(ClientRequest clientRequest) {
        Client client = mappingUtils.mapToClientEntity(clientRequest);
        List<Client> clients = clientRepository.getAllClients();
        client.setId((clients.get(clients.size() - 1).getId() + 1));
        return mappingUtils.mapToClientDto(clientRepository.add(client));
    }

    public List<ClientResponse> getAllClients() {
        return clientRepository.getAllClients().stream().map(mappingUtils::mapToClientDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        clientRepository.delete(id);
    }

    public ClientResponse findById(Long id) {
        return mappingUtils.mapToClientDto(clientRepository.getClientById(id));
    }

    public ClientResponse autorise(String login, String password) {
        return mappingUtils.mapToClientDto(clientRepository.checkPassword(login, password));
    }

    public List<ClientResponse> update(Long id, ClientRequest clientRequest) {
        return clientRepository.updateClients(id, clientRequest).stream().map(mappingUtils::mapToClientDto).collect(Collectors.toList());
    }
}

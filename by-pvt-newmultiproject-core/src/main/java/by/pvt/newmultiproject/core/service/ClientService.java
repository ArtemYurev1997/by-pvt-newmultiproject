package by.pvt.newmultiproject.core.service;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.ClientResponse;
import by.pvt.newmultiproject.core.domain.Client;
import by.pvt.newmultiproject.core.repository.ClientRepository;
import by.pvt.newmultiproject.core.mapper.MappingUtils;
import by.pvt.newmultiproject.core.repository.impl.ClientRepositoryDBImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientRepositoryDBImpl clientRepositoryDB;
    private final MappingUtils mappingUtils;

    public ClientService(ClientRepository clientRepository, ClientRepositoryDBImpl clientRepositoryDB, MappingUtils mappingUtils) {
        this.clientRepository = clientRepository;
        this.clientRepositoryDB = clientRepositoryDB;
        this.mappingUtils = mappingUtils;
    }

    public ClientResponse add(ClientRequest clientRequest) {
        Client client = mappingUtils.mapToClientEntity(clientRequest);
        List<Client> clients = clientRepository.getAllClients();
        client.setId((clients.get(clients.size() - 1).getId() + 1));
        return mappingUtils.mapToClientDto(clientRepository.add(client));
    }

    public void addUser(ClientRequest clientRequest) {
        clientRepositoryDB.addUser(mappingUtils.mapToClientEntity(clientRequest));
    }


    public ClientResponse getClient() {
        return mappingUtils.mapToClientDto(clientRepositoryDB.getClient());
    }

    public List<ClientResponse> getAllClients() {
        return clientRepositoryDB.getAllClients().stream().map(mappingUtils::mapToClientDto).collect(Collectors.toList());
    }

    public List<ClientResponse> getAllClients1() {
        return clientRepository.getAllClients().stream().map(mappingUtils::mapToClientDto).collect(Collectors.toList());
    }

    public void delete(Long id) {
        clientRepositoryDB.delete(id);
    }

    public ClientResponse findById(Long id) {
        return mappingUtils.mapToClientDto(clientRepositoryDB.getClientById(id));
    }

    public ClientResponse autorise(String login, String password) {
        return mappingUtils.mapToClientDto(clientRepositoryDB.checkPassword(login, password));
    }

    public List<ClientResponse> update(Long id, ClientRequest clientRequest) {
        return clientRepository.updateClients(id, clientRequest).stream().map(mappingUtils::mapToClientDto).collect(Collectors.toList());
    }

    public void updateDB(Long id, ClientRequest clientRequest) {
         clientRepositoryDB.update(id, clientRequest);
    }
}

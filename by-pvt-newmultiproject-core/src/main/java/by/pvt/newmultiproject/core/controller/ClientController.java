package by.pvt.newmultiproject.core.controller;

import by.pvt.newmultiproject.api.contract.ClientApi;
import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.ClientResponse;
import by.pvt.newmultiproject.core.domain.Client;
import by.pvt.newmultiproject.core.service.ClientService;

import java.util.List;

public class ClientController implements ClientApi {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientResponse register(ClientRequest clientRequest) {
        return clientService.add(clientRequest);
    }


    public ClientResponse getClient() {
        return clientService.getClient();
    }

    @Override
    public ClientResponse authorise(String login, String password) {
        return clientService.autorise(login, password);
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    public List<ClientResponse> getAllClients1() {
        return clientService.getAllClients1();
    }

    public void addUser(ClientRequest clientRequest) {
         clientService.addUser(clientRequest);
    }

    @Override
    public ClientResponse findClientById(Long id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientResponse> update(Long id, ClientRequest clientRequest) {
        return clientService.update(id, clientRequest);
    }

    public void updateDB(Long id, ClientRequest clientRequest) {
        clientService.updateDB(id, clientRequest);
    }
}

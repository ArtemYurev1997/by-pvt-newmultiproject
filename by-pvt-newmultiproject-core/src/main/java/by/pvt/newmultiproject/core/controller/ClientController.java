package by.pvt.newmultiproject.core.controller;

import by.pvt.newmultiproject.api.contract.ClientApi;
import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.ClientResponse;
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

    @Override
    public ClientResponse findClientById(Long id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientResponse> update(Long id, ClientRequest clientRequest) {
        return clientService.update(id, clientRequest);
    }

}

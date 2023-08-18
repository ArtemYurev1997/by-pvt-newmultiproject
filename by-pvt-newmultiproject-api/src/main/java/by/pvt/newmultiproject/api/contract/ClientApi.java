package by.pvt.newmultiproject.api.contract;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.api.dto.ClientResponse;

import java.util.List;

public interface ClientApi {
    ClientResponse register(ClientRequest userRequest);

    ClientResponse authorise(String login, String password);

    void delete(Long id);

    List<ClientResponse> getAllClients();

    ClientResponse findClientById(Long id);

    List<ClientResponse> update(Long id, ClientRequest clientRequest);
}

package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.core.domain.Client;

import java.util.List;

public interface ClientRepositoryDB {
    Client add(Client client);

    void addUser(Client client);

    List<Client> getAllClients();

    List<Client> updateClients(Long id, ClientRequest clientRequest);

    void delete(Long id);

    void update(Long id, ClientRequest clientRequest);

    Client getClientById(Long id);

    Client checkPassword(String login, String password);
}

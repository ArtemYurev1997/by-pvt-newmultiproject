package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.api.dto.ClientRequest;
import by.pvt.newmultiproject.core.FileWorker;
import by.pvt.newmultiproject.core.domain.Client;
import by.pvt.newmultiproject.core.mapper.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepository extends FileWorker implements ClientRepositoryDB{

    private final MappingUtils mappingUtils;

    public ClientRepository(MappingUtils mappingUtils) {
        this.mappingUtils = mappingUtils;
    }

    public static String FILE = "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbClient";

    public static List<Client> clients = new ArrayList<>();

    @Override
    public Client add(Client client) {
        clients = getAllClients();
        clients.add(client);
        serializeObject(clients, FILE);
        return client;
    }


    @Override
    public List<Client> getAllClients() {
        Object object = deserializeObject(FILE);
        List<Client> user = new ArrayList<>();
        if (object instanceof List<?>) {
            user = (List<Client>) object;
        }
        return user;
    }

    @Override
    public List<Client> updateClients(Long id, ClientRequest clientRequest)  {
        Object object = deserializeObject(FILE);
        if (object instanceof List<?>) {
            clients = (List<Client>) object;
        }
        Client client = getClientById(id);
        client.setName(clientRequest.getName());
        client.setSurname(clientRequest.getSurname());
        client.setLogin(clientRequest.getLogin());
        client.setPassword(clientRequest.getPassword());
        client.setRole(clientRequest.getRole());
        serializeObject(clients, FILE);
        return clients;
    }

    @Override
    public void delete(Long id) {
        clients = getAllClients();
        if (clients.isEmpty()) {
            return;
        }
        Client client = getClientById(id);
        clients.remove(client);
        for (Client client1 : clients) {
            if(client1.getId() > id) {
                client1.setId(client1.getId() - 1);
            }
        }
        serializeObject(clients, FILE);
        System.out.println(clients.stream().map(mappingUtils::mapToClientDto).collect(Collectors.toList()));
    }


    @Override
    public Client getClientById(Long id) {
        clients = getAllClients();
        return clients.stream().filter(client -> client.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Client checkPassword(String login, String password) {
        List<Client> clients = getAllClients();
        Client client = clients.stream().filter(client1 -> client1.getLogin().equals(login)).findFirst().orElse(null);
        if (!client.getPassword().equals(password)) {
            throw new RuntimeException("Error");
        }
        return client;
    }


    @Override
    public void update(Long id, ClientRequest clientRequest) {
    }

    @Override
    public void addUser(Client client) {

    }

}

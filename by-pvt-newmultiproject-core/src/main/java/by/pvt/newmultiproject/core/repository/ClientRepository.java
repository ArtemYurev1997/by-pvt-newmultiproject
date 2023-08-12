package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.core.FileWorker;
import by.pvt.newmultiproject.core.domain.Client;
import by.pvt.newmultiproject.core.mapper.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepository extends FileWorker {

    private final MappingUtils mappingUtils;

    public ClientRepository(MappingUtils mappingUtils) {
        this.mappingUtils = mappingUtils;
    }

    public static String FILE = "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbClient";

    public static List<Client> clients = new ArrayList<>();

    public Client add(Client client) {
        clients = getAllClients();
        clients.add(client);
        serializeObject(clients, FILE);
        return client;
    }

    public List<Client> getAllClients() {
        Object object = deserializeObject(FILE);
        List<Client> user = new ArrayList<>();
        if (object instanceof List<?>) {
            user = (List<Client>) object;
        }
        return user;
    }

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

    public Client getClientById(Long id) {
        clients = getAllClients();
        return clients.stream().filter(client -> client.getId().equals(id)).findFirst().orElse(null);
    }

    public Client checkPassword(String login, String password) {
        List<Client> clients = getAllClients();
        Client client = clients.stream().filter(client1 -> client1.getLogin().equals(login)).findFirst().orElse(null);
        if (!client.getPassword().equals(password)) {
            throw new RuntimeException("Error");
        }
        return client;
    }

}

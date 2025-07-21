package kelvipadilla.me.crm.services;

import kelvipadilla.me.crm.dtos.ClientDto;
import kelvipadilla.me.crm.models.Client;
import kelvipadilla.me.crm.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> findAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Optional<Client> findClientById(Integer id) {
        return clientRepository.findById(id);
    }

    public Client createClient(ClientDto clientDto) {
        Client newClient = new Client();
        newClient.setName(clientDto.getName());
        newClient.setEmail(clientDto.getEmail());
        newClient.setPhone(clientDto.getPhone());
        newClient.setAddress(clientDto.getAddress());
        return clientRepository.save(newClient);
    }

    public Optional<Client> updateClient(Integer id, ClientDto updatedClientDto) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(updatedClientDto.getName());
                    client.setEmail(updatedClientDto.getEmail());
                    client.setPhone(updatedClientDto.getPhone());
                    client.setAddress(updatedClientDto.getAddress());
                    return clientRepository.save(client);
                });
    }

    public boolean deleteClient(Integer id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return true;
                }).orElse(false);
    }
}

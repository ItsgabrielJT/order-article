package io.orders.orders_articles_back.service;

import io.orders.orders_articles_back.domain.Client;
import io.orders.orders_articles_back.domain.Order;
import io.orders.orders_articles_back.model.ClientDTO;
import io.orders.orders_articles_back.repos.ClientRepository;
import io.orders.orders_articles_back.repos.OrderRepository;
import io.orders.orders_articles_back.util.NotFoundException;
import io.orders.orders_articles_back.util.ReferencedWarning;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public ClientService(final ClientRepository clientRepository,
            final OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public List<ClientDTO> findAll() {
        final List<Client> clients = clientRepository.findAll(Sort.by("uuid"));
        return clients.stream()
                .map(client -> mapToDTO(client, new ClientDTO()))
                .toList();
    }

    public ClientDTO get(final UUID uuid) {
        return clientRepository.findById(uuid)
                .map(client -> mapToDTO(client, new ClientDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final ClientDTO clientDTO) {
        final Client client = new Client();
        mapToEntity(clientDTO, client);
        return clientRepository.save(client).getUuid();
    }

    public void update(final UUID uuid, final ClientDTO clientDTO) {
        final Client client = clientRepository.findById(uuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(clientDTO, client);
        clientRepository.save(client);
    }

    public void delete(final UUID uuid) {
        clientRepository.deleteById(uuid);
    }

    private ClientDTO mapToDTO(final Client client, final ClientDTO clientDTO) {
        clientDTO.setUuid(client.getUuid());
        clientDTO.setName(client.getName());
        clientDTO.setLastname(client.getLastname());
        return clientDTO;
    }

    private Client mapToEntity(final ClientDTO clientDTO, final Client client) {
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        return client;
    }

    public ReferencedWarning getReferencedWarning(final UUID uuid) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Client client = clientRepository.findById(uuid)
                .orElseThrow(NotFoundException::new);
        final Order clientUuidOrder = orderRepository.findFirstByClientUuid(client);
        if (clientUuidOrder != null) {
            referencedWarning.setKey("client.order.clientUuid.referenced");
            referencedWarning.addParam(clientUuidOrder.getUuid());
            return referencedWarning;
        }
        return null;
    }

}

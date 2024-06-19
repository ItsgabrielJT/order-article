package io.orders.orders_articles_back.rest;

import io.orders.orders_articles_back.model.ClientDTO;
import io.orders.orders_articles_back.service.ClientService;
import io.orders.orders_articles_back.util.ReferencedException;
import io.orders.orders_articles_back.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable(name = "uuid") final UUID uuid) {
        return ResponseEntity.ok(clientService.get(uuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createClient(@RequestBody @Valid final ClientDTO clientDTO) {
        final UUID createdUuid = clientService.create(clientDTO);
        return new ResponseEntity<>(createdUuid, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UUID> updateClient(@PathVariable(name = "uuid") final UUID uuid,
            @RequestBody @Valid final ClientDTO clientDTO) {
        clientService.update(uuid, clientDTO);
        return ResponseEntity.ok(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteClient(@PathVariable(name = "uuid") final UUID uuid) {
        final ReferencedWarning referencedWarning = clientService.getReferencedWarning(uuid);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        clientService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

}

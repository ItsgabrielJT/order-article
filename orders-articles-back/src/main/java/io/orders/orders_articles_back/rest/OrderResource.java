package io.orders.orders_articles_back.rest;

import io.orders.orders_articles_back.model.OrderDTO;
import io.orders.orders_articles_back.service.OrderService;
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
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable(name = "uuid") final UUID uuid) {
        return ResponseEntity.ok(orderService.get(uuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createOrder(@RequestBody @Valid final OrderDTO orderDTO) {
        final UUID createdUuid = orderService.create(orderDTO);
        return new ResponseEntity<>(createdUuid, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UUID> updateOrder(@PathVariable(name = "uuid") final UUID uuid,
            @RequestBody @Valid final OrderDTO orderDTO) {
        orderService.update(uuid, orderDTO);
        return ResponseEntity.ok(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrder(@PathVariable(name = "uuid") final UUID uuid) {
        orderService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

}

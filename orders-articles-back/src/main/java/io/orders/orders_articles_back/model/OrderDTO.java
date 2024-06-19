package io.orders.orders_articles_back.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDTO {

    private UUID uuid;

    @Size(max = 255)
    @OrderCodUnique
    private String cod;

    @NotNull
    private OffsetDateTime fecha;

    private UUID clientUuid;

    private List<UUID> articleUuid;

}

package io.orders.orders_articles_back.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ArticleDTO {

    private UUID uuid;

    @Size(max = 255)
    @ArticleCodUnique
    private String cod;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Double price;

}

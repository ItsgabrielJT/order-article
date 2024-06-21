package io.orders.orders_articles_back.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderArticleDTO {

    private UUID articleUuid;

    private int quantity;

}

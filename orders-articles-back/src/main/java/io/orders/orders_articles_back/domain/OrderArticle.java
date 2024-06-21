package io.orders.orders_articles_back.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
public class OrderArticle {

    @Id
    @Column(
            nullable = false,
            updatable = false,
            columnDefinition = "uniqueidentifier"
    )
    @GeneratedValue
    @UuidGenerator
    private UUID uuid;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderUuid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "articleUuid")
    private Article article;

}

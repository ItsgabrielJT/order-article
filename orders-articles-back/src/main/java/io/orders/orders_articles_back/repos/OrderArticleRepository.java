package io.orders.orders_articles_back.repos;

import io.orders.orders_articles_back.domain.OrderArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderArticleRepository extends JpaRepository<OrderArticle, UUID> {
}

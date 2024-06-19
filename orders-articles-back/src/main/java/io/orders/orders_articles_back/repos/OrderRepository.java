package io.orders.orders_articles_back.repos;

import io.orders.orders_articles_back.domain.Article;
import io.orders.orders_articles_back.domain.Client;
import io.orders.orders_articles_back.domain.Order;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, UUID> {

    Order findFirstByClientUuid(Client client);

    Order findFirstByArticleUuid(Article article);

    List<Order> findAllByArticleUuid(Article article);

    boolean existsByCodIgnoreCase(String cod);

}

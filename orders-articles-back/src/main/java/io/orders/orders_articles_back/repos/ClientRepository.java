package io.orders.orders_articles_back.repos;

import io.orders.orders_articles_back.domain.Client;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, UUID> {
}

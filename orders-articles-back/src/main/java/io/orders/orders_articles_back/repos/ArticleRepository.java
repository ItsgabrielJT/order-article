package io.orders.orders_articles_back.repos;

import io.orders.orders_articles_back.domain.Article;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, UUID> {

    boolean existsByCodIgnoreCase(String cod);

    Article findAllByUuid(UUID articleUuid);
}

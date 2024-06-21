package io.orders.orders_articles_back.service;

import io.orders.orders_articles_back.domain.Article;
import io.orders.orders_articles_back.model.ArticleDTO;
import io.orders.orders_articles_back.repos.ArticleRepository;
import io.orders.orders_articles_back.repos.OrderRepository;
import io.orders.orders_articles_back.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final OrderRepository orderRepository;

    public ArticleService(final ArticleRepository articleRepository,
            final OrderRepository orderRepository) {
        this.articleRepository = articleRepository;
        this.orderRepository = orderRepository;
    }

    public List<ArticleDTO> findAll() {
        final List<Article> articles = articleRepository.findAll(Sort.by("uuid"));
        return articles.stream()
                .map(article -> mapToDTO(article, new ArticleDTO()))
                .toList();
    }

    public ArticleDTO get(final UUID uuid) {
        return articleRepository.findById(uuid)
                .map(article -> mapToDTO(article, new ArticleDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final ArticleDTO articleDTO) {
        final Article article = new Article();
        mapToEntity(articleDTO, article);
        return articleRepository.save(article).getUuid();
    }

    public void update(final UUID uuid, final ArticleDTO articleDTO) {
        final Article article = articleRepository.findById(uuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(articleDTO, article);
        articleRepository.save(article);
    }

    private ArticleDTO mapToDTO(final Article article, final ArticleDTO articleDTO) {
        articleDTO.setUuid(article.getUuid());
        articleDTO.setCod(article.getCod());
        articleDTO.setName(article.getName());
        articleDTO.setPrice(article.getPrice());
        return articleDTO;
    }

    private Article mapToEntity(final ArticleDTO articleDTO, final Article article) {
        article.setCod(articleDTO.getCod());
        article.setName(articleDTO.getName());
        article.setPrice(articleDTO.getPrice());
        return article;
    }

    public boolean codExists(final String cod) {
        return articleRepository.existsByCodIgnoreCase(cod);
    }

}

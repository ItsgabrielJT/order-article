package io.orders.orders_articles_back.service;

import io.orders.orders_articles_back.domain.Article;
import io.orders.orders_articles_back.domain.Client;
import io.orders.orders_articles_back.domain.Order;
import io.orders.orders_articles_back.model.OrderDTO;
import io.orders.orders_articles_back.repos.ArticleRepository;
import io.orders.orders_articles_back.repos.ClientRepository;
import io.orders.orders_articles_back.repos.OrderRepository;
import io.orders.orders_articles_back.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;

    public OrderService(final OrderRepository orderRepository,
            final ClientRepository clientRepository, final ArticleRepository articleRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findAll(Sort.by("uuid"));
        return orders.stream()
                .map(order -> mapToDTO(order, new OrderDTO()))
                .toList();
    }

    public OrderDTO get(final UUID uuid) {
        return orderRepository.findById(uuid)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getUuid();
    }

    public void update(final UUID uuid, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(uuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final UUID uuid) {
        orderRepository.deleteById(uuid);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setUuid(order.getUuid());
        orderDTO.setCod(order.getCod());
        orderDTO.setFecha(order.getFecha());
        orderDTO.setClientUuid(order.getClientUuid() == null ? null : order.getClientUuid().getUuid());
        orderDTO.setArticleUuid(order.getArticleUuid().stream()
                .map(article -> article.getUuid())
                .toList());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        order.setCod(orderDTO.getCod());
        order.setFecha(orderDTO.getFecha());
        final Client clientUuid = orderDTO.getClientUuid() == null ? null : clientRepository.findById(orderDTO.getClientUuid())
                .orElseThrow(() -> new NotFoundException("clientUuid not found"));
        order.setClientUuid(clientUuid);
        final List<Article> articleUuid = articleRepository.findAllById(
                orderDTO.getArticleUuid() == null ? Collections.emptyList() : orderDTO.getArticleUuid());
        if (articleUuid.size() != (orderDTO.getArticleUuid() == null ? 0 : orderDTO.getArticleUuid().size())) {
            throw new NotFoundException("one of articleUuid not found");
        }
        order.setArticleUuid(new HashSet<>(articleUuid));
        return order;
    }

    public boolean codExists(final String cod) {
        return orderRepository.existsByCodIgnoreCase(cod);
    }

}

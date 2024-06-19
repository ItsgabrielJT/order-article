package io.orders.orders_articles_back.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "\"Order\"")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Order {

    @Id
    @Column(
            nullable = false,
            updatable = false,
            columnDefinition = "uniqueidentifier"
    )
    @GeneratedValue
    @UuidGenerator
    private UUID uuid;

    @Column
    private String cod;

    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_uuid_id")
    private Client clientUuid;

    @ManyToMany
    @JoinTable(
            name = "OrderArticle",
            joinColumns = @JoinColumn(name = "orderUuid"),
            inverseJoinColumns = @JoinColumn(name = "articleUuid")
    )
    private Set<Article> articleUuid;

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "datetime2")
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "datetime2")
    private OffsetDateTime lastUpdated;

}

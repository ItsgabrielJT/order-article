package io.orders.orders_articles_back.rest;

import io.orders.orders_articles_back.model.ArticleDTO;
import io.orders.orders_articles_back.service.ArticleService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleResource {

    private final ArticleService articleService;

    public ArticleResource(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable(name = "uuid") final UUID uuid) {
        return ResponseEntity.ok(articleService.get(uuid));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createArticle(@RequestBody @Valid final ArticleDTO articleDTO) {
        final UUID createdUuid = articleService.create(articleDTO);
        return new ResponseEntity<>(createdUuid, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UUID> updateArticle(@PathVariable(name = "uuid") final UUID uuid,
            @RequestBody @Valid final ArticleDTO articleDTO) {
        articleService.update(uuid, articleDTO);
        return ResponseEntity.ok(uuid);
    }

//    @DeleteMapping("/{uuid}")
//    @ApiResponse(responseCode = "204")
//    public ResponseEntity<Void> deleteArticle(@PathVariable(name = "uuid") final UUID uuid) {
//        articleService.delete(uuid);
//        return ResponseEntity.noContent().build();
//    }

}

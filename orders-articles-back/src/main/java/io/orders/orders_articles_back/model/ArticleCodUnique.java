package io.orders.orders_articles_back.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import io.orders.orders_articles_back.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the cod value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = ArticleCodUnique.ArticleCodUniqueValidator.class
)
public @interface ArticleCodUnique {

    String message() default "{Exists.article.cod}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ArticleCodUniqueValidator implements ConstraintValidator<ArticleCodUnique, String> {

        private final ArticleService articleService;
        private final HttpServletRequest request;

        public ArticleCodUniqueValidator(final ArticleService articleService,
                final HttpServletRequest request) {
            this.articleService = articleService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("uuid");
            if (currentId != null && value.equalsIgnoreCase(articleService.get(UUID.fromString(currentId)).getCod())) {
                // value hasn't changed
                return true;
            }
            return !articleService.codExists(value);
        }

    }

}

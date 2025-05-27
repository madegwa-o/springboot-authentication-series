package com.karu.counsellingsection.articles;

import com.karu.counsellingsection.articles.dtos.ArticleDto;
import com.karu.counsellingsection.user.User;
import com.karu.counsellingsection.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final PagedResourcesAssembler<Article> pagedResourcesAssembler;
    private final UserService userService;

    @GetMapping("/get-all")
    public PagedModel<?> getFeeds(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Page<Article> feedPage = articleService.getFeeds(PageRequest.of(page, size));
        return pagedResourcesAssembler.toModel(feedPage);
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleDto articleDto) {
        log.info("the dto is: {}",articleDto.toString());

        Optional<User> user = userService.findByUserId(articleDto.getAuthorId());

        if (user.isEmpty()) {
            log.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .category(articleDto.getCategory())
                .author(user.get()).build();

       Article savedArticle =  articleService.save(article);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateArticle(@RequestBody ArticleDto articleDto) {
        log.info("the dto is: {}",articleDto.toString());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long articleId) {

        articleService.deleteById(articleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

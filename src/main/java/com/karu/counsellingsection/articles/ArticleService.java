package com.karu.counsellingsection.articles;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Page<Article> getFeeds(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public void deleteById(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}

package com.karu.counsellingsection.articles.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDto {
    private String title;
    private String content;
    private Long authorId;
    private String category;
}

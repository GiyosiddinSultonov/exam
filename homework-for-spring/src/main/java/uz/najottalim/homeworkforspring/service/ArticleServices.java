package uz.najottalim.homeworkforspring.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.homeworkforspring.dto.ArticleDtoMostCommend;
import uz.najottalim.homeworkforspring.dto.ArticlesDto;

import java.util.List;

public interface ArticleServices {

    ResponseEntity<ArticlesDto> addArticle(ArticlesDto articlesDto);
    ResponseEntity<?> deleteArticleById(Integer id);
    ResponseEntity<?> getArticleById(Integer id);
    ResponseEntity<?> getAllArticles();
    ResponseEntity<ArticlesDto> editArticle(ArticlesDto articlesDto);
    ResponseEntity<List<ArticleDtoMostCommend>> getMostCommend();

    ResponseEntity<List<ArticlesDto>> getAllArticleBySortPageSize();
}

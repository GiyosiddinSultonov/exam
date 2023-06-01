package uz.najottalim.homeworkforspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.homeworkforspring.dto.ArticleDtoMostCommend;
import uz.najottalim.homeworkforspring.dto.ArticlesDto;
import uz.najottalim.homeworkforspring.models.Article;
import uz.najottalim.homeworkforspring.repository.ArticleRepository;
import uz.najottalim.homeworkforspring.service.ArticleServices;
import uz.najottalim.homeworkforspring.service.mapper.ArticleMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServicesImpl implements ArticleServices {
    @Autowired
    private final ArticleRepository articleRepository;

    @Autowired
    private final ArticleMapper articleMapper;


    @Override
    public ResponseEntity<ArticlesDto> addArticle(ArticlesDto articlesDto) {
        Article article = articleMapper.toEntity(articlesDto);
        try {
            return ResponseEntity
                    .ok()
                    .body(
                            articleMapper.toDto(
                                    articleRepository.save(article)));
        } catch (Exception e) {
            throw new RuntimeException("Error while saving article to database: " + e.getMessage());
        }

    }



    @Override
    public ResponseEntity<Void> deleteArticleById(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        articleRepository.deleteById(id);

        return ResponseEntity.status(200).build();
    }

    @Override
    public ResponseEntity<?> getArticleById(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Article> articleOptional = articleRepository.findById(id);

        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(articleMapper.toDto(articleOptional.get()));
    }

    @Override
    public ResponseEntity<?> getAllArticles() {
        List<Article> articleOptional = articleRepository.findAll();
        return ResponseEntity.status(200).body(articleOptional.stream().map(articleMapper::toDto).toList());
    }

    @Override
    public ResponseEntity<ArticlesDto> editArticle(ArticlesDto articlesDto) {
        if (articlesDto.getId() == null) {
            return ResponseEntity.ofNullable(articlesDto);
        }

        Optional<Article> articleOptional = articleRepository.findById(articlesDto.getId());

        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Article article = articleOptional.get();

        if (articlesDto.getTitle() != null) {
            article.setTitle(articlesDto.getTitle());
        }
        if (articlesDto.getBody() != null) {
            article.setBody(articlesDto.getBody());
        }
        if (articlesDto.getUpdatedAt() != null) {
            article.setUpdatedAt(LocalDateTime.now());
        }

        articleRepository.save(article);
        return ResponseEntity.accepted().body(articleMapper.toDto(article));
    }

    @Override
    public ResponseEntity<List<ArticleDtoMostCommend>> getMostCommend() {
List<ArticleDtoMostCommend> mostCommend = articleRepository.getMostCommend().stream().toList();
        return ResponseEntity.ok(mostCommend);
    }

    @Override
    public ResponseEntity<List<ArticlesDto>> getAllArticleBySortPageSize() {
        PageRequest p = null;
        Sort sorted = null;

        if (pageNum.isPresent() && pageSize.isPresent()) {
            p = PageRequest.of(pageNum.get(), pageSize.get());
        }
        if (sortColumnName.isPresent()) {
            sorted = Sort.by(sortColumnName.get());
            if (p != null) {
                p = p.withSort(sorted);
            }
        }
        List<Article> hTow = null;
        if (p != null){
            hTow = articleRepository.findAll(p).stream().toList();
        } else if (sorted != null) {
            hTow = articleRepository.findAll(sorted);
        } else {
            hTow = articleRepository.findAll();
        }
        return ResponseEntity.ok(hTow.stream().map(article -> articleMapper.toDto(article)).toList());
    }
}




package uz.najottalim.homeworkforspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.homeworkforspring.dto.ArticleDtoMostCommend;
import uz.najottalim.homeworkforspring.dto.ArticlesDto;
import uz.najottalim.homeworkforspring.dto.CommentsDto;
import uz.najottalim.homeworkforspring.service.ArticleServices;
import uz.najottalim.homeworkforspring.service.CommentsServices;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("articles")
public class ArticleResources {

    @Autowired
    private ArticleServices articleServices;

    @Autowired
    private CommentsServices commentsServices;

    @PostMapping
    public ResponseEntity<ArticlesDto> addArticle(@RequestBody ArticlesDto articlesDto) {
        return articleServices.addArticle(articlesDto);
    }

    @PatchMapping
    public ResponseEntity<ArticlesDto> editArticle(@RequestBody ArticlesDto articlesDto){
        return articleServices.editArticle(articlesDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticleById(@PathVariable Integer id) {
        return articleServices.deleteArticleById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Integer id) {
        return articleServices.getArticleById(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllArticles() {
        return articleServices.getAllArticles();
    }

    @PostMapping("/{article_id}/comment")
    public ResponseEntity<CommentsDto> addComment(@PathVariable Integer article_id, @RequestBody CommentsDto commentsDto){
        return commentsServices.addComment(commentsDto);
    }

    @GetMapping("/{article_id}/{comment_id}")
    public ResponseEntity<?> getCommentByArticleId(@PathVariable Integer id){
        return commentsServices.getCommentsByArticleId(id);
    }

//    articles/most-commented/?check_date={date}
//    public ResponseEntity<?>
    @GetMapping("/most-commented")
    public ResponseEntity<List<ArticleDtoMostCommend>> getMostCommented() {
        return articleServices.getMostCommend();
    }
   @GetMapping("/sortBy={column}&pageSize={1}&pageNum={}")
    public ResponseEntity<List<ArticlesDto>> getAllArticleBySortPageSize(@RequestParam Optional<String> sortColumnName,
                                                                         @RequestParam Optional<Integer> pageNum,
                                                                         @RequestParam Optional<Integer> pageSize){
        return articleServices.getAllArticleBySortPageSize();
   }


}

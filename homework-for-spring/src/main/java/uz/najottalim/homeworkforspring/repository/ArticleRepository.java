package uz.najottalim.homeworkforspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.najottalim.homeworkforspring.dto.ArticleDtoMostCommend;
import uz.najottalim.homeworkforspring.models.Article;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
@Query(
        value = "SELECT count(*), a.title, a.content, a.user_id, a.created_at FROM comments c\n" +
                "join articles a on a.article_id = c.article_id\n" +
                "WHERE a.created_at > TO_DATE('2023-05-31','yyyy-mm-dd')\n" +
                "GROUP BY a.title, a.content, a.user_id, a.created_at\n" +
                "ORDER BY count(*) DESC\n" +
                "LIMIT 10;",
        nativeQuery = true
)
    Collection<ArticleDtoMostCommend> getMostCommend();

    Object searchByTitleAndBodyAndUserIdAndMinPublishDateAndminPublishDateAndMaxPublishDate(Optional<List<String>> title, Optional<List<String>> bady, Optional<List<Integer>> userId, Optional<List<LocalDateTime>> publishDate, Optional<List<LocalDateTime>> updatedAt);
}

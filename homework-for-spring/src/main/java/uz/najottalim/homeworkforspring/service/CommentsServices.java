package uz.najottalim.homeworkforspring.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.homeworkforspring.dto.CommentsDto;

import java.util.List;

public interface CommentsServices {

    ResponseEntity<CommentsDto> addComment(CommentsDto commentsDto);
    ResponseEntity<List<CommentsDto>> getCommentsByArticleId(Integer article_id);
}

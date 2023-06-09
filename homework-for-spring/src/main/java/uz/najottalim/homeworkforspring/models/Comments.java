package uz.najottalim.homeworkforspring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COMMENTS")
public class Comments {
    @Id
    @GeneratedValue(generator = "comment_id_seq")
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    private Integer id;
    private String body;
    @ManyToOne(targetEntity = User.class)
    private User author;
    @ManyToOne(targetEntity = Article.class)
    private Article article;
    private LocalDateTime publishDate;
    private LocalDateTime updatedAt;
}

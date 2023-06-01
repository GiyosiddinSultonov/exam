package uz.najottalim.homeworkforspring.dto;

import java.time.LocalDate;

public interface ArticleDtoMostCommend {
Long getId();
String getTitle();
String getBody();
Long getUserId();
LocalDate PublishDate();
LocalDate UpdateAt();

}

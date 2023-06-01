package uz.najottalim.homeworkforspring.service;
import org.springframework.http.ResponseEntity;
import uz.najottalim.homeworkforspring.dto.UserDto;

public interface UserService {

    ResponseEntity<String> addUser(UserDto userDto);

    ResponseEntity<UserDto> getUser(Integer id);

    ResponseEntity<UserDto> updateUser(UserDto userDto);

}


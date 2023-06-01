package uz.najottalim.homeworkforspring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.homeworkforspring.dto.UserDto;
import uz.najottalim.homeworkforspring.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    @PostMapping()
    private ResponseEntity<String> addUser(@Valid @RequestBody UserDto userDto){

        return userService.addUser(userDto);
    }

    @GetMapping({"/{id}"}) ResponseEntity<UserDto> getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }
    @PatchMapping ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
}

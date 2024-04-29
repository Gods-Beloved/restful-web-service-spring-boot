package dev.james.restfulwebservice.controllers;

import dev.james.restfulwebservice.dto.UserDto;
import dev.james.restfulwebservice.models.User;
import dev.james.restfulwebservice.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sql.rowset.Predicate;
import java.net.URI;
import java.util.List;

@RequestMapping("/")
@RestController
public class UserController {

    UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> findAllUsers(){

        List<UserDto> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }


    @GetMapping("users/{id}")
    public ResponseEntity<UserDto> findUserById(
            @PathVariable
            int id
    ){

        UserDto user = userService.findOne(id);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("users")
    public ResponseEntity<UserDto> createUser(
            @Valid
            @RequestBody
            User userBody
    ){

       UserDto userDto = userService.save(userBody);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(userDto.getId())
//                .toUri();

        return new ResponseEntity<>(userDto,HttpStatus.CREATED);

    }

    @DeleteMapping("users/{id}")
    public void deleteUserById(
            @PathVariable
            int id
    ){

        userService.deleteById(id);


    }






}

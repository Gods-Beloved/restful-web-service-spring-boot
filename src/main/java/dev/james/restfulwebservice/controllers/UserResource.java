package dev.james.restfulwebservice.controllers;

import dev.james.restfulwebservice.dto.UserDto;
import dev.james.restfulwebservice.hateousentity.UserModeAssembler;
import dev.james.restfulwebservice.models.User;
import dev.james.restfulwebservice.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


//HATEOAS
//hypermedia as the engine of application state
//Used to generate HAL (Json) Hypertext Language
@RequestMapping("/")
@RestController
public class UserResource {

    UserServiceImpl userService;
    UserModeAssembler assembler;

    @Autowired
    public UserResource(UserServiceImpl userService,UserModeAssembler assembler) {

        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> findAllUsers(){

        List<UserDto> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }


    @GetMapping("users/{id}")
    public EntityModel<ResponseEntity<UserDto>> findUserById(
            @PathVariable
            int id
    ){

        UserDto user = userService.findOne(id);

        ResponseEntity<UserDto> response = new ResponseEntity<>(user, HttpStatus.OK);

//        Link link = linkTo(methodOn(this.getClass()).findUserById(id)).withSelfRel();
//        Link link2 = linkTo(methodOn(this.getClass()).findAllUsers()).withRel("users");

//        return EntityModel.of(response,link,link2);

      return  assembler.toModel(response);



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

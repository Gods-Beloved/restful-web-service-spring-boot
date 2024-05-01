package dev.james.restfulwebservice.hateousentity;

import dev.james.restfulwebservice.controllers.UserResource;
import dev.james.restfulwebservice.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


//Using custom hateos assembler class
@Component
public class UserModeAssembler implements RepresentationModelAssembler<ResponseEntity<UserDto>, EntityModel<ResponseEntity<UserDto>>> {

    @Override
    public EntityModel<ResponseEntity<UserDto>> toModel(ResponseEntity<UserDto> entity) {
        Link link = linkTo(methodOn(UserResource.class).findUserById(Objects.requireNonNull(entity.getBody()).getId())).withSelfRel();
        Link link2 = linkTo(methodOn(UserResource.class).findAllUsers()).withRel("users");

        return EntityModel.of(entity,link,link2);
    }

    @Override
    public CollectionModel<EntityModel<ResponseEntity<UserDto>>> toCollectionModel(Iterable<? extends ResponseEntity<UserDto>> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}

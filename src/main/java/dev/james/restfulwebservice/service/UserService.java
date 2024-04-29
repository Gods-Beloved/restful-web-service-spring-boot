package dev.james.restfulwebservice.service;

import dev.james.restfulwebservice.dto.UserDto;
import dev.james.restfulwebservice.models.User;

import java.util.List;

public interface UserService {

   List<UserDto> findAll();
   UserDto save(User user);
   UserDto findOne(int id);

   void deleteById(int id);


}

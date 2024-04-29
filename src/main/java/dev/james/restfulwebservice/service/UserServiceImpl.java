package dev.james.restfulwebservice.service;

import dev.james.restfulwebservice.dto.UserDto;
import dev.james.restfulwebservice.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private static final List<UserDto> users = new ArrayList<>();

    private static int count = 0;


    static{
        users.add(new UserDto(++count,"James", LocalDate.now().minusYears(26)));
        users.add(new UserDto(++count,"Alex", LocalDate.now().minusYears(20)));
        users.add(new UserDto(++count,"Ernest", LocalDate.now().minusYears(30)));

    }



    @Override
    public List<UserDto> findAll() {
        return users;
    }

    @Override
    public UserDto save(User user) {
        UserDto userDto = new UserDto();
        userDto.setBirthDate(user.getBirthDate());
        userDto.setName(user.getName());
        userDto.setId(++count);
        users.add(userDto);
        return userDto;
    }

    @Override
    public UserDto findOne(int id) {
        return users.stream().filter(predicate->predicate.getId() == id).findFirst().get();
    }
}

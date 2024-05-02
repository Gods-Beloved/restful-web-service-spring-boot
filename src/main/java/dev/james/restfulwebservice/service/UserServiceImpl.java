package dev.james.restfulwebservice.service;

import dev.james.restfulwebservice.dto.PostDto;
import dev.james.restfulwebservice.dto.UserDto;
import dev.james.restfulwebservice.exception.UserNotFoundException;
import dev.james.restfulwebservice.models.Post;
import dev.james.restfulwebservice.models.User;
import dev.james.restfulwebservice.repository.PostResourceRepository;
import dev.james.restfulwebservice.repository.UserResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private static final List<UserDto> users = new ArrayList<>();

    private final UserResourceRepository repository;
    private final PostResourceRepository repository_post;

    @Autowired
    public UserServiceImpl(UserResourceRepository repository, PostResourceRepository repository_post) {
        this.repository = repository;
        this.repository_post = repository_post;
    }

    private static int count = 0;


    static{
        users.add(new UserDto(++count,"James", LocalDate.now().minusYears(26)));
        users.add(new UserDto(++count,"Alex", LocalDate.now().minusYears(20)));
        users.add(new UserDto(++count,"Ernest", LocalDate.now().minusYears(30)));

    }



    @Override
    public List<UserDto> findAll() {

        return repository.findAll().stream().map(
                this::mapToDto
        ).collect(Collectors.toList());
    }

    private UserDto mapToDto(User user) {
        UserDto userdto = new UserDto();
        userdto.setBirthDate(user.getBirthDate());
        userdto.setName(user.getName());
        userdto.setId(user.getId());

        return userdto;



    }

    private PostDto mapToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());


        return postDto;



    }

    @Override
    public UserDto save(User user) {

       User user1 = repository.save(user);

        UserDto userDto = new UserDto();
        userDto.setBirthDate(user1.getBirthDate());
        userDto.setName(user1.getName());
        userDto.setId(user1.getId());
      //  users.add(userDto);
        return userDto;
    }

    @Override
    public UserDto findOne(int id) {
     User user = repository.findById(id).orElseThrow(()-> new UserNotFoundException("Specified user not found"));

     UserDto userDto = new UserDto();
     userDto.setId(user.getId());
     userDto.setName(user.getName());
     userDto.setBirthDate(user.getBirthDate());

      return userDto;
//        return users.stream().filter(predicate->predicate.getId() == id).findFirst().orElseThrow(()->new UserNotFoundException("User " +id+" Not Found"));
    }

    @Override
    public List<PostDto> findAllUserPost(int id) {

        User user = repository.findById(id).orElseThrow(()-> new UserNotFoundException("Specified user not found"));

        return user.getPosts().stream().map(
                this::mapToPostDto
        ).collect(Collectors.toList());



    }

    @Override
    public PostDto savePost(int id, Post post) {

        User user = repository.findById(id).orElseThrow(()-> new UserNotFoundException("Specified user not found"));

        post.setUser(user);

        Post post1 = repository_post.save(post);

        PostDto postDto = new PostDto();
        postDto.setDescription(post1.getDescription());
        postDto.setId(post1.getId());

        return postDto;



    }

    @Override
    public void deleteById(int id) {

        repository.deleteById(id);
//        users.removeIf(predicate -> predicate.getId() == id);

    }
}

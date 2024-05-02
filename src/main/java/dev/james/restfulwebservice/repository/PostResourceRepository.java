package dev.james.restfulwebservice.repository;

import dev.james.restfulwebservice.models.Post;
import dev.james.restfulwebservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostResourceRepository extends JpaRepository<Post, Integer> {
}

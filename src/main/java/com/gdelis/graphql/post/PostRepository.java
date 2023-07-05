package com.gdelis.graphql.post;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

   private final List<Post> postList;

   public PostRepository(final List<Post> postList) {
      this.postList = postList;
   }

   public List<Post> findAll() {
      return postList;
   }

   public Optional<Post> findById(final Integer id) {
      return postList.stream()
                     .filter(s -> Objects.equals(id, s.id()))
                     .findAny();
   }
}

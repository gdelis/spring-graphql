package com.gdelis.graphql.user;

import com.gdelis.graphql.post.Post;
import com.gdelis.graphql.post.PostRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserGraphQLController {

   private final UserRepository userRepository;
   private final PostRepository postRepository;

   public UserGraphQLController(final UserRepository userRepository,
       final PostRepository postRepository) {
      this.userRepository = userRepository;
      this.postRepository = postRepository;
   }

   @SchemaMapping(typeName = "Query", field = "getAllUsers")
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @QueryMapping
   public User getUserById(@Argument(name = "id") final Integer id) {
      return userRepository.findById(id).orElse(null);
   }

   // This approach generates a N+1 invocations:
   //@SchemaMapping(typeName = "User", field = "followers")
   //public List<User> getFollowers(final User user) {
   //   return userRepository.findAll().stream()
   //                        .filter(s -> user.followers().contains(s.id()))
   //                        .toList();
   //}

   @BatchMapping(typeName = "User", field = "followers")
   public Map<User, List<User>> getFollowers() {
      return userRepository.findAll()
                           .stream()
                           .collect(Collectors.toMap(
                               s -> s,
                               user -> userRepository.findAll()
                                                     .stream()
                                                     .filter(s -> user.followers().contains(s.id()))
                                                     .toList()));
   }

   @BatchMapping(typeName = "User", field = "posts")
   public Map<User, List<Post>> getPosts() {
      return userRepository.findAll()
                           .stream()
                           .collect(Collectors.toMap(
                               s -> s,
                               user -> postRepository.findAll()
                                                     .stream()
                                                     .filter(s -> user.posts().contains(s.id()))
                                                     .toList()
                           ));
   }
}

package com.gdelis.graphql;

import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserGraphQLController {

   private final UserRepository userRepository;

   public UserGraphQLController(final UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @SchemaMapping(typeName = "Query", field = "getAllUsers")
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @QueryMapping
   public User getUserById(@Argument(name = "id") final Integer id) {
      return userRepository.findById(id).orElse(null);
   }
}
